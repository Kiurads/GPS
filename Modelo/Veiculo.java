package GPS.Modelo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

abstract public class Veiculo implements Constantes {

    //Dados introduzidos
    protected String matricula;
    protected int KmReais;
    protected int KmMensais;
    protected Seguro seguro;

    //Dados vindos da BD
    protected LocalDate dataRegistoMatricula;
    protected String marca;
    protected String modelo;
    protected int intervaloKmsOleo;

    //Lista de eventos que vão ser criados
    protected List<Evento> eventos;

    public Veiculo(String matricula, int KmReais, int KmMensais, String seguradora, LocalDate dataRegistoSeguro, double custoAnualSeguro) {
        this.matricula = matricula;
        this.KmReais = KmReais;
        this.KmMensais = KmMensais;
        this.seguro = new Seguro(seguradora, dataRegistoSeguro, custoAnualSeguro);
        this.eventos = new ArrayList<>();

        //dados da BD
        getDadosMatricula(matricula, BD_MATRICULAS_TXT);

        //criar eventos
        CalculaProximaPagementoImpostoCirculaçao();
        CalcularProximaDataDePagamentoSeguro();
        CalculaProximaInspecao();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////GETS E SETS
    public int getKmReais() {
        return KmReais;
    }

    public int getKmMensais() {
        return KmMensais;
    }

    public String getMatricula() {
        return matricula;
    }

    public Seguro getSeguro() {
        return seguro;
    }

    public void setKmReais(int KmReais) {
        this.KmReais = KmReais;
    }

    public void setSeguro(Seguro seguro) {
        this.seguro = seguro;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////CALCULAR
    private void CalculaProximaPagementoImpostoCirculaçao() {
        eventos.add(new Evento(getDataComMaisUmAno(dataRegistoMatricula), PAGAMENTO_IMPOSTO, matricula, TipoEvento.Obrigacoes));
    }

    private void CalcularProximaDataDePagamentoSeguro() {
        eventos.add(new Evento(getDataComMaisUmAno(seguro.dataRegisto), PAGAMENTO_SEGURO, matricula, TipoEvento.Obrigacoes));
    }

    private void CalculaProximaInspecao() {
        LocalDate proxData = getDataProximaInspecao();
        //Aqui é necessário verificar se a data da proxima ispeção vem a null. Uma vez que se o veiculo for uma moto com menos de 250cc não é necessário criar um evento.
        if(proxData != null)
            eventos.add(new Evento(proxData, INSPECAO, matricula, TipoEvento.Obrigacoes));
    }

    private void CalcularProximaMudancaOleo() {
    }


    private void CalcularProximaMudancaDeCorreia() {
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////REALIZAR
    public void RealizaMudancaOleo(int custo) {
        Evento aux = PesquisaEvento(MUDANCA_OLEO);

        if (aux != null && !aux.isCheck()) {
            aux.setCheck(true);
            aux.setCusto(custo);
            CalcularProximaMudancaOleo();

        }
    }

    public void RealizaPagamentoSeguro() {

        Evento aux = PesquisaEvento(PAGAMENTO_SEGURO);

        if (aux != null && !aux.isCheck()) {
            aux.setCheck(true);
            aux.setCusto(seguro.custoAnual);
            CalcularProximaDataDePagamentoSeguro();
        }
    }

    public void RealizaMudancaDeCorreia(int custo) {
        Evento aux = PesquisaEvento(MUDANCA_CORREIA);

        if (aux != null && !aux.isCheck()) {
            aux.setCheck(true);
            aux.setCusto(custo);
            CalcularProximaMudancaDeCorreia();
        }
    }

    public void RealizaPagamentoImpostoCirculacao(int custo) {
        Evento aux = PesquisaEvento(PAGAMENTO_IMPOSTO);

        if (aux != null && !aux.isCheck()) {
            aux.setCheck(true);
            aux.setCusto(custo);
            CalculaProximaPagementoImpostoCirculaçao();
        }
    }

    public void RealizaInspecao(int custo) {
        Evento aux = PesquisaEvento(INSPECAO);

        if (aux != null && !aux.isCheck()) {
            aux.setCheck(true);
            aux.setCusto(custo);
            CalculaProximaInspecao();
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////FUNCOES AUXILIARES

    protected LocalDate getDataComMaisUmAno(LocalDate data) {
        //O ano da data que entra vai ser alterado para o ano corrente + 1
        return LocalDate.of(LocalDate.now().getYear() + UM_ANO , data.getMonthValue(), data.getDayOfMonth());

    }

    abstract protected LocalDate getDataProximaInspecao();

    private void getDadosMatricula(String matricula, String FileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(FileName))) {

            String linha;

            while ((linha = br.readLine()) != null) {
                Scanner sc = new Scanner(linha);
                String matriculaLida = sc.next();
                if (matriculaLida.equals(matricula)) {
                    dataRegistoMatricula = LocalDate.of(Integer.parseInt(sc.next()),Integer.parseInt(sc.next()), Integer.parseInt(sc.next()));
                    marca = sc.next();
                    modelo = sc.next();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Evento PesquisaEvento(String Nome) {
        for (Evento evento : eventos) {
            if (evento.getDescricao().compareTo(Nome) == 0) {
                return evento;
            }
        }
        return null;
    }

    private List<Evento> ListarEventos() {
        return eventos;
    }

    private void CriarEvento(Evento evento) {
        if (evento != null) {
            eventos.add(evento);
        }
    }

    @Override
    public String toString() {
        String s = "";
        s += "Matricula: " + matricula + " " + dataRegistoMatricula;
        s += "\nMarca: " + marca;
        s += "\nModelo: " + modelo;
        s += "\nKmReais: " + KmReais;
        s += "\nKmMensais: " + KmMensais;
        s += seguro.toString();

        for (Evento e : eventos) {
            s += e.toString();
        }
        return s;
    }
}

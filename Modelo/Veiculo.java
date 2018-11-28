package GPS.Modelo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

abstract public class Veiculo implements Constantes, Serializable {

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
    protected TipoVeiculo tipoVeiculo;
    protected int cilindrada;

    //Lista de eventos que vão ser criados
    protected List<Evento> eventos;

    public Veiculo(String matricula, int KmReais, int KmMensais, String seguradora, LocalDate dataRegistoSeguro, double custoAnualSeguro) {
        this.matricula = matricula;
        this.KmReais = KmReais;
        this.KmMensais = KmMensais;
        this.seguro = new Seguro(seguradora, dataRegistoSeguro, custoAnualSeguro);
        this.eventos = new ArrayList<>();

        //dados da BD
        getDadosMatricula(matricula);

        //criar eventos
        CalculaProximaPagementoImpostoCirculaçao();
        CalcularProximaDataDePagamentoSeguro();
        CalculaProximaInspecao();
        CalcularProximaMudancaOleo();
        CalcularProximaMudancaDeCorreia();
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
    private boolean CalculaProximaPagementoImpostoCirculaçao() {
        return eventos.add(new Evento(getDataComMaisUmAno(dataRegistoMatricula), PAGAMENTO_IMPOSTO, matricula, TipoEvento.OBRIGACOES));
    }

    private boolean CalcularProximaDataDePagamentoSeguro() {
        return eventos.add(new Evento(getDataComMaisUmAno(seguro.dataRegisto), PAGAMENTO_SEGURO, matricula, TipoEvento.OBRIGACOES));
    }

    private boolean CalculaProximaInspecao() {
        LocalDate proxData = getDataProximaInspecao();
        //Aqui é necessário verificar se a data da proxima ispeção vem a null. Uma vez que se o veiculo for uma moto com menos de 250cc não é necessário criar um evento.
        if (proxData != null) {
            return eventos.add(new Evento(proxData, INSPECAO, matricula, TipoEvento.OBRIGACOES));
        }
        return false;
    }

    private boolean CalcularProximaMudancaOleo() {
        int kmsNecessarios = KmReais + intervaloKmsOleo, nMeses = 0, aux = KmReais;
        while (aux <= kmsNecessarios) {
            aux += (nMeses++) * KmMensais;
        }
        return eventos.add(new Evento(LocalDate.now().plusMonths(nMeses), MUDANCA_OLEO, matricula, TipoEvento.MANUTENCOES));
    }

    private boolean CalcularProximaMudancaDeCorreia() {
        int kmsNecessarios = KmReais + KMS_NECESSARIOS_MUDANCA_CORREIA, nMeses = 0, aux = KmReais;
        while (aux <= kmsNecessarios) {
            aux += (nMeses++) * KmMensais;
        }
        return eventos.add(new Evento(LocalDate.now().plusMonths(nMeses), MUDANCA_CORREIA, matricula, TipoEvento.MANUTENCOES));
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////REALIZAR
    public boolean RealizaMudancaOleo(int custo) {
        Evento aux = PesquisaEvento(MUDANCA_OLEO);

        if (aux != null && !aux.isCheck()) {
            aux.setCheck(true);
            aux.setCusto(custo);
            return CalcularProximaMudancaOleo();
        }
        return false;
    }

    public boolean RealizaPagamentoSeguro() {

        Evento aux = PesquisaEvento(PAGAMENTO_SEGURO);

        if (aux != null && !aux.isCheck()) {
            aux.setCheck(true);
            aux.setCusto(seguro.custoAnual);
            return CalcularProximaDataDePagamentoSeguro();
        }
        return false;
    }

    public boolean RealizaMudancaDeCorreia(int custo) {
        Evento aux = PesquisaEvento(MUDANCA_CORREIA);

        if (aux != null && !aux.isCheck()) {
            aux.setCheck(true);
            aux.setCusto(custo);
            return CalcularProximaMudancaDeCorreia();
        }
        return false;
    }

    public boolean RealizaPagamentoImpostoCirculacao(int custo) {
        Evento aux = PesquisaEvento(PAGAMENTO_IMPOSTO);

        if (aux != null && !aux.isCheck()) {
            aux.setCheck(true);
            aux.setCusto(custo);
            return CalculaProximaPagementoImpostoCirculaçao();
        }
        return false;
    }

    public boolean RealizaInspecao(int custo) {
        Evento aux = PesquisaEvento(INSPECAO);

        if (aux != null && !aux.isCheck()) {
            aux.setCheck(true);
            aux.setCusto(custo);
            return CalculaProximaInspecao();
        }
        return false;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////FUNCOES AUXILIARES
    protected LocalDate getDataComMaisUmAno(LocalDate data) {
        //O ano da data que entra vai ser alterado para o ano corrente + 1

        return LocalDate.of(LocalDate.now().getYear() + UM_ANO, data.getMonthValue(), data.getDayOfMonth());

    }

    abstract protected LocalDate getDataProximaInspecao();

    private boolean getDadosMatricula(String matricula) {
        try (BufferedReader br = new BufferedReader(new FileReader(BD_MATRICULAS_TXT))) {

            String linha;

            while ((linha = br.readLine()) != null) {
                Scanner sc = new Scanner(linha);
                String matriculaLida = sc.next();
                if (matriculaLida.equals(matricula)) {
                    dataRegistoMatricula = LocalDate.of(Integer.parseInt(sc.next()), Integer.parseInt(sc.next()), Integer.parseInt(sc.next()));
                    marca = sc.next();
                    modelo = sc.next();
                    String tipo = sc.next();

                    switch (tipo) {
                        case LIGEIRO:
                            tipoVeiculo = TipoVeiculo.LIGEIRO;
                            break;
                        case PESADO:
                            tipoVeiculo = TipoVeiculo.PESADO;
                            break;
                        case MOTOCICLO:
                            tipoVeiculo = TipoVeiculo.MOTOCICLO;
                            break;
                    }
                    intervaloKmsOleo = Integer.parseInt(sc.next());
                    cilindrada = Integer.parseInt(sc.next());

                    return true;
                
            }
        }

    }
    catch (IOException e

    
        ) {
            e.printStackTrace();
    }

return false;
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

    private boolean CriarEvento(Evento evento) {
        if (evento != null) {
            return eventos.add(evento);
        }
        return false;
    }

    @Override
        public String toString() {
        String s = "";
        s += "Matricula: " + matricula + " " + dataRegistoMatricula;
        s += "\nMarca: " + marca;
        s += "\nModelo: " + modelo;
        s += "\nKmReais: " + KmReais;
        s += "\nKmMensais: " + KmMensais;
        
          switch (this.tipoVeiculo) {
                        case LIGEIRO:
                            s += "\nTipo " + LIGEIRO;
                            break;
                        case PESADO:
                            s += "\nTipo " + PESADO;
                            break;
                        case MOTOCICLO:
                            s += "\nTipo " + MOTOCICLO;
                            break;
                    }
        s += "\nCelindrada: " + cilindrada;
        s += seguro.toString();

        for (Evento e : eventos) {
            s += e.toString();
        }
        return s;
    }
}

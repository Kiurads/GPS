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
    protected String nome;
    protected String matricula;
    protected int kmReais;
    protected int kmMensais;
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

    public Veiculo(String nome, String matricula, int KmReais, int KmMensais, String seguradora, LocalDate dataRegistoSeguro, String tipoSeguro) {
        this.nome = nome;
        this.matricula = matricula;
        this.KmReais = KmReais;
        this.KmMensais = KmMensais;
        this.seguro = new Seguro(seguradora, tipoSeguro, dataRegistoSeguro);
        this.eventos = new ArrayList<>();

        //dados da BD
        getDadosMatricula(matricula);

        //criar eventos
        calcularProximaDataPagamentoImpostoCirculacao();
        calcularProximaDataDePagamentoSeguro();
        calcularProximaDataInspecao();
        calcularProximaDataMudancaOleo();
        calcularProximaDataMudancaDeCorreia();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////GETS E SETS
    public int getKmReais() {
        return kmReais;
    }

    public int getKmMensais() {
        return kmMensais;
    }

    public String getMatricula() {
        return matricula;
    }

    public Seguro getSeguro() {
        return seguro;
    }

    public void setKmReais(int KmReais) {
        this.kmReais = KmReais;
    }

    public void setSeguro(Seguro seguro) {
        this.seguro = seguro;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////CALCULAR
    private boolean calcularProximaDataPagamentoImpostoCirculacao() {
        return eventos.add(new Evento(getDataComMaisUmAno(dataRegistoMatricula), PAGAMENTO_IMPOSTO, matricula, TipoEvento.OBRIGACOES));
    }

    private boolean calcularProximaDataDePagamentoSeguro() {
        return eventos.add(new Evento(getDataComMaisUmAno(seguro.dataRegisto), PAGAMENTO_SEGURO, matricula, TipoEvento.OBRIGACOES));
    }

    private boolean calcularProximaDataInspecao() {
        LocalDate proxData = getDataProximaInspecao();
        //Aqui é necessário verificar se a data da proxima ispeção vem a null. Uma vez que se o veiculo for uma moto com menos de 250cc não é necessário criar um evento.
        if (proxData != null) {
            return eventos.add(new Evento(proxData, INSPECAO, matricula, TipoEvento.OBRIGACOES));
        }
        return false;
    }

    private boolean calcularProximaDataMudancaOleo() {
        int kmsNecessarios = kmReais + intervaloKmsOleo, nMeses = 0, aux = kmReais;
        while (aux <= kmsNecessarios) {
            aux += (nMeses++) * kmMensais;
        }
        return eventos.add(new Evento(LocalDate.now().plusMonths(nMeses), MUDANCA_OLEO, matricula, TipoEvento.MANUTENCOES));
    }

    private boolean calcularProximaDataMudancaDeCorreia() {
        int kmsNecessarios = kmReais + KMS_NECESSARIOS_MUDANCA_CORREIA, nMeses = 0, aux = kmReais;
        while (aux <= kmsNecessarios) {
            aux += (nMeses++) * kmMensais;
        }
        return eventos.add(new Evento(LocalDate.now().plusMonths(nMeses), MUDANCA_CORREIA, matricula, TipoEvento.MANUTENCOES));
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////REALIZAR
    public boolean realizaMudancaOleo(int custo) {
        Evento evento = pesquisaEvento(MUDANCA_OLEO);

        if (evento != null && !evento.isCheck()) {
            evento.setCheck(true);
            evento.setCusto(custo);
            return calcularProximaDataMudancaOleo();
        }
        return false;
    }

    public boolean realizaPagamentoSeguro() {

        Evento evento = pesquisaEvento(PAGAMENTO_SEGURO);

        if (evento != null && !evento.isCheck()) {
            evento.setCheck(true);
            evento.setCusto(seguro.custoAnual);
            return calcularProximaDataDePagamentoSeguro();
        }
        return false;
    }

    public boolean realizaMudancaDeCorreia(int custo) {
        Evento evento = pesquisaEvento(MUDANCA_CORREIA);

        if (evento != null && !evento.isCheck()) {
            evento.setCheck(true);
            evento.setCusto(custo);
            return calcularProximaDataMudancaDeCorreia();
        }
        return false;
    }

    public boolean realizaPagamentoImpostoCirculacao(int custo) {
        Evento evento = pesquisaEvento(PAGAMENTO_IMPOSTO);

        if (evento != null && !evento.isCheck()) {
            evento.setCheck(true);
            evento.setCusto(custo);
            return calcularProximaDataPagamentoImpostoCirculacao();
        }
        return false;
    }

    public boolean realizaInspecao(int custo) {
        Evento evento = pesquisaEvento(INSPECAO);

        if (evento != null && !evento.isCheck()) {
            evento.setCheck(true);
            evento.setCusto(custo);
            return calcularProximaDataInspecao();
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

    private Evento pesquisaEvento(String nome) {
        for (Evento evento : eventos) {
            if (evento.getDescricao().compareTo(nome) == 0) {
                return evento;
            }
        }
        return null;
    }

    private List<Evento> listarEventos() {
        return eventos;
    }

    private boolean criarEvento(Evento evento) {
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
        s += "\nKmReais: " + kmReais;
        s += "\nKmMensais: " + kmMensais;
        
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

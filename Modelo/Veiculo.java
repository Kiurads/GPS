package GPS.Modelo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

abstract public class Veiculo implements Constantes, Serializable {

    //Dados introduzidos
    protected String nome;
    protected String matricula;
    protected int kmReais;
    protected int kmMensais;
    protected Seguro seguro;

    //Dados vindos da BD
    protected LocalDate dataRegistoMatricula;
    protected String modelo;
    protected int intervaloKmsOleo;
    protected int cilindrada;

    //Lista de eventos que vão ser criados
    protected List<Evento> eventos;

    public Veiculo(String nome, String matricula, int KmReais, int KmMensais, String seguradora, LocalDate dataRegistoSeguro, String tipoSeguro) {
        this.nome = nome;
        this.matricula = matricula;
        this.kmReais = KmReais;
        this.kmMensais = KmMensais;
        this.seguro = new Seguro(seguradora, tipoSeguro, dataRegistoSeguro);
        this.eventos = new ArrayList<>();

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

        if (evento != null) {
            return calcularProximaDataDePagamentoSeguro();
        }

        return false;
    }

    public boolean realizaMudancaDeCorreia(int custo) {
        Evento evento = pesquisaEvento(MUDANCA_CORREIA);

        if (evento != null) {
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

    public List<Evento> getEventos() {
        return eventos;
    }

    public String getNome() {
        return nome;
    }

    public LocalDate getDataRegistoMatricula() {
        return dataRegistoMatricula;
    }

    public String getModelo() {
        return modelo;
    }

    @Override
        public String toString() {
        String s = "";
        s += "Matricula: " + matricula + " " + dataRegistoMatricula + "\n";
        s += "Modelo: " + modelo + "\n";
        s += "KmReais: " + kmReais + "\n";
        s += "KmMensais: " + kmMensais + "\n";
        s += seguro.toString();

        return s;
    }

    public void altera(String nome, String seguradora, String tipoSeguro, LocalDate registoSeguro, int kmreais, int kmmensais) {
        this.nome = nome;
        this.seguro.seguradora = seguradora;
        this.seguro.tipo = tipoSeguro;
        this.seguro.dataRegisto = registoSeguro;
        this.kmReais = kmreais;
        this.kmMensais = kmmensais;
    }
}

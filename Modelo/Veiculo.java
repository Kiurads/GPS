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
    private int kmReais;
    private int kmMensais;
    private Seguro seguro;

    //Dados vindos da BD
    protected LocalDate dataRegistoMatricula;
    protected String modelo;
    protected int intervaloKmsOleo;

    //Lista de eventos que vão ser criados
    protected List<Evento> eventos;

    public Veiculo(String nome, String matricula, int KmReais, int KmMensais, String seguradora, LocalDate dataRegistoSeguro, String tipoSeguro) {
        this.nome = nome;
        this.matricula = matricula;
        this.kmReais = KmReais;
        this.kmMensais = KmMensais;
        this.seguro = new Seguro(seguradora, tipoSeguro, dataRegistoSeguro);
        this.eventos = new ArrayList<>();

        getDados();

        //criar eventos
        calcularProximaDataPagamentoImpostoCirculacao();
        calcularProximaDataDePagamentoSeguro();
        calcularProximaDataInspecao();
        calcularProximaDataMudancaOleo();
        calcularProximaDataMudancaDeCorreia();
    }

    private void getDados() {
        try (BufferedReader br = new BufferedReader(new FileReader(BD_MATRICULAS_TXT))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                Scanner sc = new Scanner(linha);
                String matriculaLida = sc.next();
                if (matriculaLida.equals(matricula)) {
                    dataRegistoMatricula = LocalDate.of(sc.nextInt(), sc.nextInt(), sc.nextInt());
                    modelo = sc.next() + " " + sc.next();

                    sc.next();

                    intervaloKmsOleo = sc.nextInt();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
    public boolean calcularProximaDataPagamentoImpostoCirculacao() {
        return eventos.add(new Evento(getDataComMaisUmAno(dataRegistoMatricula), PAGAMENTO_IMPOSTO, matricula, TipoEvento.OBRIGACOES));
    }

    public boolean calcularProximaDataDePagamentoSeguro() {
        return eventos.add(new Evento(getDataComMaisUmAno(seguro.dataRegisto), PAGAMENTO_SEGURO, matricula, TipoEvento.OBRIGACOES));
    }

    public boolean calcularProximaDataInspecao() {
        LocalDate proxData = getDataProximaInspecao();
        //Aqui é necessário verificar se a data da proxima ispeção vem a null. Uma vez que se o veiculo for uma moto com menos de 250cc não é necessário criar um evento.
        if (proxData != null) {
            return eventos.add(new Evento(proxData, INSPECAO, matricula, TipoEvento.OBRIGACOES));
        }
        return false;
    }

    public boolean calcularProximaDataMudancaOleo() {
        int kmsNecessarios = kmReais + intervaloKmsOleo, nMeses = 0, aux = kmReais;
        while (aux <= kmsNecessarios) {
            aux += (nMeses++) * kmMensais;
        }
        return eventos.add(new Evento(LocalDate.now().plusMonths(nMeses), MUDANCA_OLEO, matricula, TipoEvento.MANUTENCOES));
    }

    public boolean calcularProximaDataMudancaDeCorreia() {
        int kmsNecessarios = kmReais + KMS_NECESSARIOS_MUDANCA_CORREIA, nMeses = 0, aux = kmReais;
        while (aux <= kmsNecessarios) {
            aux += (nMeses++) * kmMensais;
        }
        return eventos.add(new Evento(LocalDate.now().plusMonths(nMeses), MUDANCA_CORREIA, matricula, TipoEvento.MANUTENCOES));
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////FUNCOES AUXILIARES
    protected LocalDate getDataComMaisUmAno(LocalDate data) {
        //O ano da data que entra vai ser alterado para o ano corrente + 1
        return LocalDate.of(LocalDate.now().getYear() + UM_ANO, data.getMonthValue(), data.getDayOfMonth());
    }

    abstract protected LocalDate getDataProximaInspecao();

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
        this.seguro = new Seguro(seguradora, tipoSeguro, registoSeguro);
        this.kmReais = kmreais;
        this.kmMensais = kmmensais;
    }

    public void criaEvento(Evento evento) {
        eventos.add(evento);
    }

    public void realizaEvento(Evento evento, double custo) {
        evento.setCusto(custo);
        evento.setCheck(true);

        if (evento.getTipoEvento() == TipoEvento.OBRIGACOES) {
            switch (evento.getDescricao()) {
                case INSPECAO:
                    calcularProximaDataInspecao();
                    break;
                case MUDANCA_CORREIA:
                    calcularProximaDataMudancaDeCorreia();
                    break;
                case MUDANCA_OLEO:
                    calcularProximaDataMudancaOleo();
                    break;
                case PAGAMENTO_IMPOSTO:
                    calcularProximaDataPagamentoImpostoCirculacao();
                    break;
                case PAGAMENTO_SEGURO:
                    calcularProximaDataDePagamentoSeguro();
                    break;
            }
        }
    }
}

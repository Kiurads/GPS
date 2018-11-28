package GPS.Modelo;

public interface Constantes {

    enum TipoEvento {
        REPARACAO, MECANICA, MANUTENCOES, OBRIGACOES;
    }
    enum TipoVeiculo {
        MOTOCICLO, LIGEIRO, PESADO;
    }
    
    
    static String INSPECAO = "Inspecao";
    static String MUDANCA_OLEO = "Mudanca Oleo";
    static String PAGAMENTO_SEGURO = "Pagamento de Seguro";
    static String MUDANCA_CORREIA = "Mudanca de correia";
    static String PAGAMENTO_IMPOSTO = "Pagamento de imposto de circulacao";

    public final int CC_NECESSARIOS_PARA_INSPECAO = 250;
    public final int KMS_NECESSARIOS_MUDANCA_CORREIA = 100000;




    public final String BD_MATRICULAS_TXT = "C:\\Users\\cristiano\\Documents\\NetBeansProjects\\GPSPROjecT\\src\\GPS\\BD.txt";

    public final String BD_FROTA_BIN = "C:\\Users\\cristiano\\Documents\\NetBeansProjects\\GPSPROjecT\\src\\GPS\\BD_Frota.dat";

    
    public final int UM_ANO = 1;
    public final int DOIS_ANOS = 2;
    public final int QUATRO_ANOS = 4;
    public final int OITO_ANOS = 8;


}

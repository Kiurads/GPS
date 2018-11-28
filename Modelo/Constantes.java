package GPS.Modelo;

public interface Constantes {

    enum TipoEvento {
        REPARACAO, MECANICA, MANUTENCOES, OBRIGACOES;
    }
    enum TipoVeiculo {
        MOTOCICLO, LIGEIRO, PESADO;
    }
    
    
    static String INSPECAO = "Inspeção";
    static String MUDANCA_OLEO = "Mudança de óleo";
    static String PAGAMENTO_SEGURO = "Pagamento de Seguro";
    static String MUDANCA_CORREIA = "Mudança de correia";
    static String PAGAMENTO_IMPOSTO = "Pagamento de imposto de circulação";

    public final int CC_NECESSARIOS_PARA_INSPECAO = 250;
    public final int KMS_NECESSARIOS_MUDANCA_CORREIA = 100000;




    public final String BD_MATRICULAS_TXT = "C:\\Users\\a21260315\\Desktop\\GPS_TP\\src\\GPS\\Modelo\\ficheiros\\BD.txt";

    public final String BD_FROTA_BIN = "C:\\Users\\a21260315\\Desktop\\GPS_TP\\src\\GPS\\Modelo\\ficheiros\\BD_Frota.dat";

    
    public final int UM_ANO = 1;
    public final int DOIS_ANOS = 2;
    public final int QUATRO_ANOS = 4;
    public final int OITO_ANOS = 8;


}

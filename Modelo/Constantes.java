
package GPS.Modelo;

public interface Constantes {
    enum TipoEvento{
        Reparacao,Mecanica,Menutencoes,Obrigacoes;
    }
    
    static String INSPECAO="Inspecao";
    static String MUDANCA_OLEO="Mudanca Oleo";
    static String PAGAMENTO_SEGURO="Pagamento de Seguro";
    static String MUDANCA_CORREIA="Mudanca de correia";
    static String PAGAMENTO_IMPOSTO="Pagamento de imposto de circulacao";
    
    public final int CC_NECESSARIOS_PARA_INSPECAO = 250;
    public final String BD_MATRICULAS_TXT = "BD.txt";

    
}

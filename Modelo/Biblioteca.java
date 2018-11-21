/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GPS.Modelo;

/**
 *
 * @author Pedro
 */
public interface Biblioteca {
    enum TipoEvento{
        Reparacao,Mecanica,Menutencoes,Obrigacoes;
    }
    
    static String INSPECAO="Inspecao";
    static String MUDANCA_OLEO="Mudanca Oleo";
    static String PAGAMENTO_SEGURO="Pagamento de Seguro";
    static String MUDANCA_CORREIA="Mudanca de correia";
    static String PAGAMENTO_IMPOSTO="Pagamento de imposto de circulacao";

    
}

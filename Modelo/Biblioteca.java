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
        Reparacao,Mecanica,Menutencoes;
    }
    
    static String MUDANCA_OLEO="Mudanca Oleoa";
    static String INSPECAO="Inspeçao";
    static String PAGAMENTO_SEGURO="Pagamento de Seguro";
    static String PAGEMENTO_IMPOSTO_CIRCULA="Pagamento de imposto de circulaçao";
    static String MUDANCA_CORREIA="Mudanca de correia";
    
    
}

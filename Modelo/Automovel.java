/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GPS.Modelo;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Márcio Guia
 */
public class Automovel extends Veiculo {

    public Automovel(String matricula, Calendar dataRegistoMatricula, int KmReais, int KmMensais, String seguradora, Date dataRegistoSeguro) {
        super(matricula, dataRegistoMatricula, KmReais, KmMensais, seguradora, dataRegistoSeguro);
    }

    @Override
    public Calendar CalcularProximaMudancaOleo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Calendar CalculaProximaInspecao() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

 
    

   
    
}
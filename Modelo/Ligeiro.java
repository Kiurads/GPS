/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.Date;

/**
 *
 * @author Márcio Guia
 */
public class Ligeiro extends Veiculo {

    public Ligeiro(String matricula, Date dataRegistoMatricula, int KmReais, int KmMensais, String seguradora, Date dataRegistoSeguro) {
        super(matricula, dataRegistoMatricula, KmReais, KmMensais, seguradora, dataRegistoSeguro);

    }

    @Override
    public Date CalculaProximaInspecao() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Date CalcularProximaMudancaDeCorreia() {
        return super.CalcularProximaMudancaDeCorreia(); //To change body of generated methods, choose Tools | Templates.
    }

}

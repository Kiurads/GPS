/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Márcio Guia
 */
import java.util.Date;

public class Seguro {

    String Seguradora;
    Date dataRegisto;

    public Seguro(String Seguradora, Date dataRegito) {
        this.Seguradora = Seguradora;
        this.dataRegisto = dataRegito;
    }

    public Date getDataRegisto() {
        return dataRegisto;
    }

    public String getSeguradora() {
        return Seguradora;
    }
}

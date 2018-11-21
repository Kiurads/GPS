/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GPS.Modelo;

import java.util.Date;

/**
 *
 * @author Márcio Guia
 */



public class Evento implements Biblioteca{
    private Date DataEvento;
    private String Descricao;
    private String idVeiculo;
    private TipoEvento tipoevento;
    
   
    
    public Evento(Date DataEvento, String Descricao,String idVeiculo,TipoEvento tipoevento) {
        this.DataEvento = DataEvento;
        this.Descricao = Descricao;
        this.idVeiculo=idVeiculo;
        this.tipoevento=tipoevento;
    }

    public String getDescricao() {
        return Descricao;
    }

    public String getIdVeiculo() {
        return idVeiculo;
    }

    public Date getDataEvento() {
        return DataEvento;
    }

    public TipoEvento getTipoevento() {
        return tipoevento;
    }
    
    
    
    
    
    
}

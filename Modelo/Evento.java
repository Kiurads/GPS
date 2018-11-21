/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GPS.Modelo;

import GPS.Modelo.Biblioteca.TipoEvento;

import java.util.Date;

/**
 *
 * @author Márcio Guia
 */



public class Evento implements Biblioteca{
    private Date DataEvento;
    private String Nome;
    private String Descricao;
    private String idVeiculo;
    private TipoEvento tipoevento;
    private boolean cheack=false;
    
   
    
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

    public boolean isCheack() {
        return cheack;
    }

    public void setCheack(boolean cheack) {
        this.cheack = cheack;
    }

    public String getNome() {
        return Nome;
    }
   
    
    
    
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GPS.Modelo;
import GPS.Modelo.Biblioteca.TipoEvento;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Márcio Guia
 */



public class Evento implements Biblioteca {
    private Calendar DataEvento;
    private String Nome;
    private String idVeiculo;
    private TipoEvento tipoevento;
    private boolean cheak;
    
   
    
    public Evento(Calendar DataEvento, String Descricao,String idVeiculo,TipoEvento tipoevento) {
        this.DataEvento = DataEvento;
        this.Nome = Descricao;
        this.idVeiculo=idVeiculo;
        this.tipoevento=tipoevento;
    }

    public String getNome() {
        return Nome;
    }

    public String getIdVeiculo() {
        return idVeiculo;
    }

    public Calendar getDataEvento() {
        return DataEvento;
    }

    public TipoEvento getTipoevento() {
        return tipoevento;
    }

    public boolean isCheak() {
        return cheak;
    }

    public void setCheak(boolean cheak) {
        this.cheak = cheak;
    }
    
    
    
    
    
    
}

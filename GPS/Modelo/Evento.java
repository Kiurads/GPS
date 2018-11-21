package GPS.Modelo;

import java.util.Calendar;
import java.util.Date;

public class Evento implements Biblioteca {

    private Calendar DataEvento;
    private String Descricao;
    private String idVeiculo;
    private TipoEvento tipoevento;
    private boolean Check;

    public Evento(Calendar DataEvento, String Descricao, String idVeiculo, TipoEvento tipoevento) {
        this.DataEvento = DataEvento;
        this.Descricao = Descricao;
        this.idVeiculo = idVeiculo;
        this.tipoevento = tipoevento;
        this.Check = false;
    }

    public String getDescricao() {
        return Descricao;
    }

    public String getIdVeiculo() {
        return idVeiculo;
    }

    public TipoEvento getTipoevento() {
        return tipoevento;
    }

}

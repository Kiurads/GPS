package GPS.Modelo;

import java.io.Serializable;
import java.time.LocalDate;

public class Evento implements Constantes, Serializable {
    private LocalDate data;
    private String descricao;
    private String matricula;
    private TipoEvento tipoEvento;
    private boolean notificado;
    private int diasAntes;
    private boolean check;
    private double custo;
    
    public Evento(LocalDate DataEvento, String Descricao,String matricula,TipoEvento tipoevento) {
        this.data = DataEvento;
        this.descricao = Descricao;
        this.matricula = matricula;
        this.tipoEvento = tipoevento;
        this.custo = 0;
        diasAntes = 0;
        notificado = true;
    }

    public Evento(LocalDate DataEvento, String Descricao,String matricula,TipoEvento tipoevento, String diasAntes) {
        this.data = DataEvento;
        this.descricao = Descricao;
        this.matricula = matricula;
        this.tipoEvento = tipoevento;
        this.custo = 0;

        for (int i = 0; i < tempos.length; i++) {
            if (tempos[i].equals(diasAntes)) this.diasAntes = temposValores[i];
        }
    }

    public String getDescricao() {
        return descricao;
    }
    
    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public double getCusto() {
        return custo;
    }

    public void setCusto(double custo) {
        this.custo = custo;
    }

    public LocalDate getData() {
        return data;
    }

    public TipoEvento getTipoEvento() {
        return tipoEvento;
    }

    public boolean isNotificado() {
        return notificado;
    }

    public void setNotificado(boolean notificado) {
        this.notificado = notificado;
    }

    public int getDiasAntes() {
        return diasAntes;
    }

    @Override
    public String toString() {
        String s;

        s = "" + data.getDayOfMonth() + '/' + data.getMonthValue() + '/' + data.getYear() + " - " + descricao;
        return s;
    }
}

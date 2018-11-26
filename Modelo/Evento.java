package GPS.Modelo;

import GPS.Modelo.Constantes.TipoEvento;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Evento implements Constantes {
    private LocalDate data;
    private String descricao;
    private String matricula;
    private TipoEvento tipoEvento;
    private boolean check;
    private double custo;
    
    public Evento(LocalDate DataEvento, String Descricao,String idVeiculo,TipoEvento tipoevento) {
        this.data = DataEvento;
        this.descricao = Descricao;
        this.matricula=idVeiculo;
        this.tipoEvento=tipoevento;
    }

    public String getDescricao() {
        return descricao;
    }
    
    public boolean isCheak() {
        return check;
    }

    public void setCheck(boolean cheak) {
        this.check = cheak;
    }

    public double getCusto() {
        return custo;
    }

    public void setCusto(double custo) {
        this.custo = custo;
    }

    @Override
    public String toString() {
        String s = "";
        
        s+="\nEvento: "+ descricao  + " " + data;
        
        return s;
    }
    
    
    
    
    
    
    
    
}

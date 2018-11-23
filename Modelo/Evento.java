package GPS.Modelo;

import GPS.Modelo.Constantes.TipoEvento;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Evento implements Constantes {
    private GregorianCalendar data;
    private String descricao;
    private String matricula;
    private TipoEvento tipoEvento;
    private boolean check;
    private int custo;
    
    public Evento(GregorianCalendar DataEvento, String Descricao,String idVeiculo,TipoEvento tipoevento) {
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

    public void setCheak(boolean cheak) {
        this.check = cheak;
    }

    public int getCusto() {
        return custo;
    }

    public void setCusto(int custo) {
        this.custo = custo;
    }

    @Override
    public String toString() {
        String s = "";
        
        s+="\nEvento: "+ descricao  + " " + data.get(Calendar.DAY_OF_MONTH) +"-"+ 
                data.get(Calendar.MONTH) +"-"+data.get(Calendar.YEAR);
        
        return s;
    }
    
    
    
    
    
    
    
    
}

package GPS.Modelo;

import GPS.Modelo.Constantes.TipoEvento;
import java.time.LocalDate;

public class Evento implements Constantes {
    private LocalDate data;
    private String descricao;
    private String matricula;
    private TipoEvento tipoEvento;
    private boolean check;
    private double custo;
    
    public Evento(LocalDate DataEvento, String Descricao,String matricula,TipoEvento tipoevento) {
        this.data = DataEvento;
        this.descricao = Descricao;
        this.matricula=matricula;
        this.tipoEvento=tipoevento;
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

    @Override
    public String toString() {
        String s = "";
        
        s+="\nEvento: "+ descricao  + " " + data;
        
        return s;
    }
    
    
    
    
    
    
    
    
}

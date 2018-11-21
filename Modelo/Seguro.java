package GPS.Modelo;

import java.util.Date;

public class Seguro {
    private String Seguradora;
    private Date dataRegisto;

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

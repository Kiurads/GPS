package GPS.Modelo;

import java.io.Serializable;
import java.time.LocalDate;

public class Seguro implements Constantes, Serializable{

    String seguradora;
    LocalDate dataRegisto;
    double custoAnual;

    public Seguro(String Seguradora, LocalDate dataRegito, double custoAnual) {
        this.seguradora = Seguradora;
        this.dataRegisto = dataRegito;
        this.custoAnual = custoAnual;
    }

    public LocalDate getDataRegisto() {
        return dataRegisto;
    }

    public String getSeguradora() {
        return seguradora;
    }

    public double getCustoAnual() {
        return custoAnual;
    }

    @Override
    public String toString() {
        String s = "\nSeguradora: " + seguradora + " Data: "+ dataRegisto+ " " + "Montante Anual:" + custoAnual;
        return s;
    }

}

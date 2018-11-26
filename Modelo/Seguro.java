package GPS.Modelo;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Seguro {

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
        String s = "\nSeguradora: " + seguradora + "Data: "+ dataRegisto+ " " + "Montante Anual:" + custoAnual;
        return s;
    }

}

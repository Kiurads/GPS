
package GPS.Modelo;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Seguro {

    String seguradora;
    GregorianCalendar dataRegisto;

    public Seguro(String Seguradora, GregorianCalendar dataRegito) {
        this.seguradora = Seguradora;
        this.dataRegisto = dataRegito;
    }

    public GregorianCalendar getDataRegisto() {
        return dataRegisto;
    }

    public String getSeguradora() {
        return seguradora;
    }

    @Override
    public String toString() {
        String s = "\nSeguradora: " + seguradora + " " + dataRegisto.get(Calendar.DAY_OF_MONTH) +"-"+ 
                dataRegisto.get(Calendar.MONTH) +"-"+dataRegisto.get(Calendar.YEAR);
        
        return s;
    }
    
    
}

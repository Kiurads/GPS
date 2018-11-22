
package GPS.Modelo;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Pesado extends Veiculo {

    public Pesado(String matricula, int KmReais, int KmMensais, String seguradora, GregorianCalendar dataRegistoSeguro) {
        super(matricula, KmReais, KmMensais, seguradora, dataRegistoSeguro);
    }

    @Override
    protected void CalculaProximaInspecao() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}

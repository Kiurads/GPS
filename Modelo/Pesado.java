
package GPS.Modelo;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Pesado extends Veiculo {

    public Pesado(String matricula, int KmReais, int KmMensais, String seguradora, GregorianCalendar dataRegistoSeguro,int custoAnual) {
        super(matricula, KmReais, KmMensais, seguradora, dataRegistoSeguro,custoAnual);
    }

    @Override
    protected GregorianCalendar getDataProximaInspecao() {
       return getDataComMaisUmAno(dataRegistoMatricula);
    }

}

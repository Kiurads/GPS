
package GPS.Modelo;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Pesado extends Veiculo {

    public Pesado(String matricula, int KmReais, int KmMensais, String seguradora, GregorianCalendar dataRegistoSeguro,int custoAnual) {
        super(matricula, KmReais, KmMensais, seguradora, dataRegistoSeguro,custoAnual);
    }

  

    @Override
    protected GregorianCalendar getDataPorximaInspeçao(GregorianCalendar data) {
         Calendar calendarioAuxialiar = Calendar.getInstance();
        calendarioAuxialiar.set(Calendar.MONTH, data.get(Calendar.MONTH));

        return new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR) + 1,
                data.get(Calendar.MONTH), data.get(Calendar.DATE));

    }

}

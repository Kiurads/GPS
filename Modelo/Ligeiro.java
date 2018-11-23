package GPS.Modelo;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Ligeiro extends Veiculo {

    public Ligeiro(String matricula, int KmReais, int KmMensais, String seguradora, GregorianCalendar dataRegistoSeguro,int custoAnual) {
        super(matricula, KmReais, KmMensais, seguradora, dataRegistoSeguro,custoAnual);
    }

  

    @Override
     protected GregorianCalendar getDataPorximaInspeçao(GregorianCalendar data) {
        Calendar calendarioAuxiliar = Calendar.getInstance();
        calendarioAuxiliar.set(Calendar.MONTH, data.get(Calendar.MONTH));
        calendarioAuxiliar.set(Calendar.YEAR, data.get(Calendar.YEAR));

        if (calendarioAuxiliar.get(Calendar.YEAR) - data.get((Calendar.YEAR)) < QUATRO_ANOS) {
            return new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR) + 4,
                    data.get(Calendar.MONTH), data.get(Calendar.DATE));
        } else if (calendarioAuxiliar.get(Calendar.YEAR) - data.get((Calendar.YEAR)) > QUATRO_ANOS && calendarioAuxiliar.get(Calendar.YEAR) - data.get((Calendar.YEAR)) < OITO_ANOS) {
            return new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR) + 2,
                    data.get(Calendar.MONTH), data.get(Calendar.DATE));
        } else {
            return new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR) + 1,
                    data.get(Calendar.MONTH), data.get(Calendar.DATE));
        }

    }
}

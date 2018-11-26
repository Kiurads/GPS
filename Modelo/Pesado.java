
package GPS.Modelo;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Pesado extends Veiculo {

    public Pesado(String matricula, int KmReais, int KmMensais, String seguradora, LocalDate dataRegistoSeguro, double custoAnualSeguro) {
        super(matricula, KmReais, KmMensais, seguradora, dataRegistoSeguro, custoAnualSeguro);
    }


    @Override
    protected LocalDate getDataProximaInspecao() {
       return getDataComMaisUmAno(dataRegistoMatricula);
    }

}

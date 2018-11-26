package GPS.Modelo;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Motociclo extends Veiculo {

    int cilindrada;

    public Motociclo(String matricula, int KmReais, int KmMensais, String seguradora, LocalDate dataRegistoSeguro, double custoAnualSeguro, int cilindrada) {
        super(matricula, KmReais, KmMensais, seguradora, dataRegistoSeguro, custoAnualSeguro);
        this.cilindrada = cilindrada;
    }

    @Override
    protected LocalDate getDataProximaInspecao() {
       return cilindrada >= CC_NECESSARIOS_PARA_INSPECAO ? getDataComMaisUmAno(dataRegistoMatricula) : null;
    }

    @Override
    public String toString() {  //falta a cilindrada
        String s = super.toString();
        return s;
    }
}

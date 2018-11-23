package GPS.Modelo;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Motociclo extends Veiculo {

    int cilindrada;

    public Motociclo(String matricula, int KmReais, int KmMensais, String seguradora, GregorianCalendar dataRegistoSeguro, int cilindrada, int custoAnual) {
        super(matricula, KmReais, KmMensais, seguradora, dataRegistoSeguro, custoAnual);
        this.cilindrada = cilindrada;
    }

    @Override
    protected GregorianCalendar getDataProximaInspecao() {
        return cilindrada >= CC_NECESSARIOS_PARA_INSPECAO ? getDataComMaisUmAno(dataRegistoMatricula) : null;
    }

    @Override
    public String toString() {  //falta a cilindrada
        String s = super.toString();
        return s;
    }
}

package GPS.Modelo;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Ligeiro extends Veiculo {

    public Ligeiro(String matricula, int KmReais, int KmMensais, String seguradora, GregorianCalendar dataRegistoSeguro,int custoAnual) {
        super(matricula, KmReais, KmMensais, seguradora, dataRegistoSeguro,custoAnual);
    }

    @Override
     protected GregorianCalendar getDataProximaInspecao() {
        Calendar dataCorrente = Calendar.getInstance();

        if (dataCorrente.get(Calendar.YEAR) - dataRegistoMatricula.get((Calendar.YEAR)) <= QUATRO_ANOS) {
            return new GregorianCalendar(dataRegistoMatricula.get(Calendar.YEAR) + QUATRO_ANOS, 
                    dataRegistoMatricula.get(Calendar.MONTH), dataRegistoMatricula.get(Calendar.DATE));
            
        } else if (dataCorrente.get(Calendar.YEAR) - dataRegistoMatricula.get((Calendar.YEAR)) > QUATRO_ANOS && dataCorrente.get(Calendar.YEAR) - dataRegistoMatricula.get((Calendar.YEAR)) <= OITO_ANOS) {
           return new GregorianCalendar(dataCorrente.get(Calendar.YEAR) + DOIS_ANOS,
                    dataRegistoMatricula.get(Calendar.MONTH), dataRegistoMatricula.get(Calendar.DATE));
        } else {
            return new GregorianCalendar(dataCorrente.get(Calendar.YEAR) + UM_ANO,
                    dataRegistoMatricula.get(Calendar.MONTH), dataRegistoMatricula.get(Calendar.DATE));
        }

    }
}

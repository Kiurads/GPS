package GPS.Modelo;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Motociclo extends Veiculo {

    int cilindrada;

    public Motociclo(String matricula, int KmReais, int KmMensais, String seguradora, GregorianCalendar dataRegistoSeguro, int cilindrada,int custoAnual) {
        super(matricula, KmReais, KmMensais, seguradora, dataRegistoSeguro,custoAnual);
        this.cilindrada = cilindrada;
    }

    
    @Override
    protected GregorianCalendar getDataPorximaInspeçao(GregorianCalendar data) {
        if (cilindrada >= CC_NECESSARIOS_PARA_INSPECAO) {

            GregorianCalendar dataEvento = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR) + 1,
                    data.get(Calendar.MONTH), data.get(Calendar.DATE));

           return dataEvento;
        }
        return null;
    }
    
    
}

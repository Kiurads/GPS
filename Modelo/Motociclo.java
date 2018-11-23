package GPS.Modelo;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Motociclo extends Veiculo {

    int cilindrada;

    public Motociclo(String matricula, int KmReais, int KmMensais, String seguradora, GregorianCalendar dataRegistoSeguro, int cilindrada) {
        super(matricula, KmReais, KmMensais, seguradora, dataRegistoSeguro);
        this.cilindrada = cilindrada;
    }

    @Override
    protected void CalculaProximaInspecao() {
        if (cilindrada >= CC_NECESSARIOS_PARA_INSPECAO) {

            GregorianCalendar dataEvento = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR) + 1,
                    dataRegistoMatricula.get(Calendar.MONTH), dataRegistoMatricula.get(Calendar.DATE));

            eventos.add(new Evento(dataEvento, "Seguro pagar até", matricula, TipoEvento.Obrigacoes));
        }
    }
}

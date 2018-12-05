package GPS.Modelo;

import java.time.LocalDate;

public class Motociclo extends Veiculo {



    public Motociclo(String matricula, int KmReais, int KmMensais, String seguradora, LocalDate dataRegistoSeguro, double custoAnualSeguro) {
        super(matricula, KmReais, KmMensais, seguradora, dataRegistoSeguro, custoAnualSeguro);

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

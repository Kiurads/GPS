package GPS.Modelo;

import java.time.LocalDate;

public class Motociclo extends Veiculo {

    int cilindrada;

    public Motociclo(String nome, String matricula, int KmReais, int KmMensais, String seguradora, LocalDate dataRegistoSeguro, String tipoSeguro, int cilindrada) {
        super(nome, matricula, KmReais, KmMensais, seguradora, dataRegistoSeguro, tipoSeguro);
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

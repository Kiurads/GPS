
package GPS.Modelo;

import java.time.LocalDate;

public class Pesado extends Veiculo {

    public Pesado(String nome, String matricula, int KmReais, int KmMensais, String seguradora, LocalDate dataRegistoSeguro, double custoAnualSeguro) {
        super(nome, matricula, KmReais, KmMensais, seguradora, dataRegistoSeguro, custoAnualSeguro);
    }


    @Override
    protected LocalDate getDataProximaInspecao() {
       return getDataComMaisUmAno(dataRegistoMatricula);
    }

}

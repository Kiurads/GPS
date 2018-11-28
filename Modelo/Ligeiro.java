package GPS.Modelo;

import java.time.LocalDate;

public class Ligeiro extends Veiculo {

    public Ligeiro(String nome, String matricula, int KmReais, int KmMensais, String seguradora, LocalDate dataRegistoSeguro, double custoAnualSeguro) {
        super(nome, matricula, KmReais, KmMensais, seguradora, dataRegistoSeguro, custoAnualSeguro);
    }

    @Override
    protected LocalDate getDataProximaInspecao() {
        LocalDate dataCorrente = LocalDate.now();

        if (dataCorrente.getYear() - dataRegistoMatricula.getYear() <= QUATRO_ANOS) {
            return LocalDate.of(dataRegistoMatricula.getYear() + QUATRO_ANOS, 
                    dataRegistoMatricula.getMonthValue(), dataRegistoMatricula.getDayOfMonth());
            
        } else if (dataCorrente.getYear() - dataRegistoMatricula.getYear() > QUATRO_ANOS && dataCorrente.getYear() - dataRegistoMatricula.getYear() <= OITO_ANOS) {
           return LocalDate.of(dataRegistoMatricula.getYear() + DOIS_ANOS, 
                    dataRegistoMatricula.getMonthValue(), dataRegistoMatricula.getDayOfMonth());
        } else {
            return LocalDate.of(dataRegistoMatricula.getYear() + UM_ANO,
                    dataRegistoMatricula.getMonthValue(), dataRegistoMatricula.getDayOfMonth());
        }
    }
}

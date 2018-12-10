package GPS.gpsproject;

import GPS.Modelo.Evento;
import GPS.Modelo.Veiculo;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Notifica extends Thread {
    private List<Veiculo> veiculos;
    private Controlador controlador;
    private boolean stop;

    public Notifica(List<Veiculo> veiculos, Controlador controlador) {
        this.controlador = controlador;
        this.veiculos = new CopyOnWriteArrayList<>(veiculos);
        stop = false;
    }

    @Override
    public void run() {
        while (!stop) {
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            for (Veiculo v : veiculos) {
                for (Evento e : v.getEventos()) {
                    long dias = Duration.between(LocalDate.now().atStartOfDay(), e.getData().atStartOfDay()).toDays();
                    if (dias <= e.getDiasAntes() && !e.isNotificado()) {
                        e.setNotificado(true);
                        controlador.sendNotification("Vehicle Companion", e.getDescricao() +
                                "\n" +
                                "Faltam " + dias + " dias");
                    }
                }
            }
        }
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }
}

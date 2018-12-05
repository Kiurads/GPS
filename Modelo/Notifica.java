
package GPS.Modelo;

import GPS.Modelo.Notification.Notification;
import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

public class Notifica extends Thread implements Serializable{
    List<Veiculo> veiculos;

    public Notifica(List<Veiculo> veiculos) {
        this.veiculos = veiculos;
    }
    
    @Override
    public void run() {
        while (true) {
            for (Veiculo v : veiculos) {
                for (Evento e : v.eventos) {
                    if (Duration.between(e.getData().atStartOfDay(), LocalDate.now().atStartOfDay()).toDays() < e.getDiasAntes()) {
                        Notification.sendNotification("Vehicle Companion - Evento", e.toString());
                    }
                }
            }
        }
    }
    
}

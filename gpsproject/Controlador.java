package GPS.gpsproject;

import GPS.Modelo.Notification.Notification;
import javafx.event.ActionEvent;

import java.awt.*;

public class Controlador {
    public void sendNotification(ActionEvent actionEvent) {
        try {
            Notification.sendNotification("Notification test", "Vehicle Companion");
        } catch (AWTException ignore) {}
    }
}

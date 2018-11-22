package GPS.gpsproject;

import GPS.Modelo.Notification.Notification;
import javafx.event.ActionEvent;
import javafx.scene.control.ListView;

import java.awt.*;

public class Controlador {
    ListView list;
    Button addButton;

    public void sendNotification(ActionEvent actionEvent) {
        Notification.sendNotification("Vehicle Companion", "Notification Test");
    }
}
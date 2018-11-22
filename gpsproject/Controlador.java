package GPS.gpsproject;

import GPS.Modelo.Notification.Notification;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

public class Controlador {
    @FXML
    ListView list;
    @FXML
    Button addButton;

    @FXML
    public void initialize() {

    }

    public void sendNotification(ActionEvent actionEvent) {
        Notification.sendNotification("Vehicle Companion", "Notification Test");
    }
}
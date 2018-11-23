package GPS.gpsproject;

import GPS.Modelo.Notification.Notification;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class Controlador {
    public TitledPane details;
    public PieChart pie;
    public TitledPane events;
    public ListView eventslist;
    public Button eliminateButton;
    public TextArea detailstext;
    public ListView list;
    public Button addButton;
    public VBox left;
    public VBox right;
    public Button categoria;
    public Button mensais;
    public Button gerais;
    public Button mecanica;
    public Button reparacoes;
    public Button manutencoes;

    @FXML
    public void initialize() {
        noneSelected();
    }

    public void sendNotification(ActionEvent actionEvent) {
        Notification.sendNotification("Vehicle Companion", "Notification Test");
    }

    public void switchViews(ActionEvent actionEvent) {
        if (left.isVisible()) noneSelected();
        else vehicleSelected();
    }

    private void noneSelected() {
        left.setVisible(false);

        events.setVisible(false);
        gerais.setVisible(false);
        mecanica.setVisible(false);
        reparacoes.setVisible(false);
        manutencoes.setVisible(false);
        eliminateButton.setVisible(false);
    }

    private void vehicleSelected() {
        left.setVisible(true);

        events.setVisible(true);
        gerais.setVisible(true);
        mecanica.setVisible(true);
        reparacoes.setVisible(true);
        manutencoes.setVisible(true);
        eliminateButton.setVisible(true);
    }
}
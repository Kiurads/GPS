package GPS.gpsproject;

import GPS.Modelo.Notification.Notification;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class Controlador {
    private final Image plus = new Image("GPS/gpsproject/images/plus.png", 16, 16, false, true);
    private final Image cross = new Image("GPS/gpsproject/images/cross.png", 16, 16, false, true);
    private final Image refresh = new Image("GPS/gpsproject/images/refresh.png", 16, 16, false, true);
    private final Image check = new Image("GPS/gpsproject/images/check.png", 16, 16, false, true);

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
    public Button updateAll;
    public VBox detalhes;
    public Button guardaalteracoes;

    @FXML
    public void initialize() {
        eliminateButton.setGraphic(new ImageView(cross));
        addButton.setGraphic(new ImageView(plus));
        updateAll.setGraphic(new ImageView(refresh));
        guardaalteracoes.setGraphic(new ImageView(check));

        noneSelected();
    }


    public void sendNotification(String title, String message) {
        Notification.sendNotification(title, message);
    }

    public void switchViews(ActionEvent actionEvent) {
        if (left.isVisible()) noneSelected();
        else vehicleSelected();
    }

    private void noneSelected() {
        left.setVisible(false);

        detalhes.setVisible(false);

        events.setVisible(false);
        gerais.setVisible(false);
        mecanica.setVisible(false);
        reparacoes.setVisible(false);
        manutencoes.setVisible(false);
        eliminateButton.setVisible(false);
    }

    private void vehicleSelected() {
        left.setVisible(true);

        detalhes.setVisible(true);

        events.setVisible(true);
        gerais.setVisible(true);
        mecanica.setVisible(true);
        reparacoes.setVisible(true);
        manutencoes.setVisible(true);
        eliminateButton.setVisible(true);
    }
}
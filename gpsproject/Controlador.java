package GPS.gpsproject;

import GPS.Modelo.Notification.Notification;
import GPS.gpsproject.calendar.FullCalendarView;
import GPS.gpsproject.images.BibliotecaImagens;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.time.YearMonth;

import static GPS.gpsproject.calendar.DateUtils.noFutureDates;

public class Controlador implements BibliotecaImagens {
    public ListView list;
    public Button updateAll;
    public Button addButton;
    //Visão Geral
    public VBox left;
    public VBox right;
    public TitledPane detailsvehicle;
    public TextArea detailsvehicletext;
    public PieChart pie;
    public TitledPane events;
    public ListView eventslist;
    public Button eliminateButton;
    public Button categoria;
    public Button mensais;
    public Button gerais;
    public Button mecanica;
    public Button reparacoes;
    public Button manutencoes;
    //Editar detalhes
    public VBox detalhes;
    public Button guardaalteracoes;
    public TextField nome;
    public TextField modelo;
    public TextField matricula;
    public ChoiceBox tipo;
    public DatePicker registomatricula;
    public TextField seguradora;
    public TextField tiposeguro;
    public DatePicker registoseguro;
    public TextField kmmensais;
    public TextField kmreais;
    //Calendario Geral
    public HBox calendarbox;
    public TextArea detailsdia;

    private FullCalendarView calendarView;

    @FXML
    public void initialize() {
        eliminateButton.setGraphic(new ImageView(cross));
        addButton.setGraphic(new ImageView(plus));
        updateAll.setGraphic(new ImageView(refresh));
        guardaalteracoes.setGraphic(new ImageView(check));

        registoseguro.setDayCellFactory(noFutureDates);
        registomatricula.setDayCellFactory(noFutureDates);

        calendarView = new FullCalendarView(YearMonth.now());
        calendarbox.getChildren().add(calendarView.getView());
        HBox.setHgrow(calendarView.getView(), Priority.ALWAYS);

        noneSelected();
    }

    public void switchViews(ActionEvent actionEvent) {
        Notification.sendNotification("Vehicle Companion", "Notificação de teste");
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

    @FXML
    private void onGuardaAlteracoes(ActionEvent actionEvent) {
        if (!everyFieldIsFilled()) {
            Alert.display("Alerta", "Não é permitido guardar campos vazios!");
            return;
        }

        if (!everyFieldIsValid()) {
            Alert.display("Alerta", "Verifique se os campos possuem dados válidos!");
            return;
        }
    }

    private boolean everyFieldIsValid() {
        try {
            Integer.parseInt(kmmensais.getText());
            Integer.parseInt(kmreais.getText());
        } catch(NumberFormatException | NullPointerException e) {
            return false;
        }
        return true;
    }

    private boolean everyFieldIsFilled() {
        return !nome.getText().trim().equals("") &&
                !modelo.getText().trim().equals("") &&
                tipo.getValue() != null &&
                registomatricula.getValue() != null &&
                !seguradora.getText().trim().equals("") &&
                !tiposeguro.getText().trim().equals("") &&
                registoseguro.getValue() != null &&
                !kmreais.getText().trim().equals("") &&
                !kmmensais.getText().trim().equals("");
    }

    public void onAddButton(ActionEvent actionEvent) {
        //TODO Adicionar veículo
    }
}
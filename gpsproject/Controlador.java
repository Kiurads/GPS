package GPS.gpsproject;

import GPS.Modelo.Constantes;
import GPS.Modelo.Evento;
import GPS.Modelo.Frota;
import GPS.Modelo.Veiculo;
import GPS.gpsproject.calendar.FullCalendarView;
import GPS.gpsproject.images.BibliotecaImagens;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

import java.io.IOException;
import java.time.YearMonth;

import static GPS.gpsproject.calendar.DateUtils.noFutureDates;

public class Controlador implements BibliotecaImagens, Constantes {
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
    public TextField tipo;
    public DatePicker registomatricula;
    public TextField seguradora;
    public TextField tiposeguro;
    public DatePicker registoseguro;
    public TextField kmmensais;
    public TextField kmreais;
    //Calendario Geral
    public HBox calendarbox;
    public TextArea detailsdia;

    private Frota frota;
    private Veiculo veiculoSelecionado;

    @FXML
    public void initialize() {
        setGraphics();

        registoseguro.setDayCellFactory(noFutureDates);
        registomatricula.setDayCellFactory(noFutureDates);

        frota = new Frota();

        FullCalendarView calendarView = new FullCalendarView(YearMonth.now(), detailsdia, frota.getEventosTotal());
        calendarbox.getChildren().add(calendarView.getView());
        HBox.setHgrow(calendarView.getView(), Priority.ALWAYS);

        noneSelected();
        initializeList();
        initializeListeners();
    }

    private void initializeFields() {
        detailsvehicletext.setText(veiculoSelecionado.toString());

        nome.setText(veiculoSelecionado.getNome());
        matricula.setText(veiculoSelecionado.getMatricula());
        modelo.setText(veiculoSelecionado.getModelo());
        registomatricula.setValue(veiculoSelecionado.getDataRegistoMatricula());
        tipo.setText(String.valueOf(TipoVeiculo.LIGEIRO));
        seguradora.setText(veiculoSelecionado.getSeguro().getSeguradora());
        tiposeguro.setText(veiculoSelecionado.getSeguro().getTipo());
        kmreais.setText(String.valueOf(veiculoSelecionado.getKmReais()));
        kmmensais.setText(String.valueOf(veiculoSelecionado.getKmMensais()));
        registoseguro.setValue(veiculoSelecionado.getSeguro().getDataRegisto());
    }

    private void initializeList() {
        list.getItems().setAll(frota.getNomesVeiculos());
        if(veiculoSelecionado != null) list.getSelectionModel().select(veiculoSelecionado.getNome());

        eventslist.setCellFactory(CheckBoxListCell.forListView((Callback<Evento, ObservableValue<Boolean>>) param -> {
            BooleanProperty observable = new SimpleBooleanProperty();
            observable.addListener(((observable1, oldValue, newValue) -> {
                if (!param.isCheck()) {
                    param.setCusto(getCusto());
                    param.setCheck(true);

                    updatePie();
                }
            }));
            return observable;
        }));
    }

    private void updatePie() {
        ObservableList<PieChart.Data> pieData = FXCollections.observableArrayList();

        for (Evento e : veiculoSelecionado.getEventos()) {
            if (e.getCusto() > 0)
                pieData.add(new PieChart.Data(e.getDescricao(), e.getCusto()));
        }

        pie.setData(pieData);
        try {
            frota.guardarFrotaBD(Constantes.BD_FROTA_BIN);
        } catch (IOException ignored) {}
    }

    private double getCusto() {
        return Custo.display();
    }

    private void initializeListeners() {
        list.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> setVeiculo((String) newValue));
    }

    private void setVeiculo(String veiculo) {
        if(frota.pesquisaVeiculo(veiculo) == null) {
            noneSelected();
            return;
        }
        veiculoSelecionado = frota.pesquisaVeiculo(veiculo);
        initializeFields();
        eventslist.getItems().setAll(veiculoSelecionado.getEventos());
        updatePie();
        vehicleSelected();
    }

    private void setGraphics() {
        eliminateButton.setGraphic(new ImageView(cross));
        addButton.setGraphic(new ImageView(plus));
        updateAll.setGraphic(new ImageView(refresh));
        guardaalteracoes.setGraphic(new ImageView(check));
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
        veiculoSelecionado.altera(nome.getText(),
                seguradora.getText(),
                tiposeguro.getText(),
                registoseguro.getValue(),
                Integer.parseInt(kmreais.getText()),
                Integer.parseInt(kmmensais.getText()));

        try {
            frota.guardarFrotaBD(Constantes.BD_FROTA_BIN);
        } catch (IOException ignore) {}
        initializeList();
    }

    private boolean everyFieldIsValid() {
        try {
            int kms = Integer.parseInt(kmmensais.getText());
            int kmsr = Integer.parseInt(kmreais.getText());

            if(kms <= 0 || kmsr <= 0)
                return false;
        } catch(NumberFormatException | NullPointerException e) {
            return false;
        }
        return true;
    }

    private boolean everyFieldIsFilled() {
        return !nome.getText().trim().equals("") &&
                !modelo.getText().trim().equals("") &&
                !tipo.getText().trim().equals("") &&
                registomatricula.getValue() != null &&
                !seguradora.getText().trim().equals("") &&
                !tiposeguro.getText().trim().equals("") &&
                registoseguro.getValue() != null &&
                !kmreais.getText().trim().equals("") &&
                !kmmensais.getText().trim().equals("");
    }

    public void onAddButton(ActionEvent actionEvent) {
        //TODO adiciona veículo
    }
}
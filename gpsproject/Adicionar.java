package GPS.gpsproject;

import GPS.Modelo.Ligeiro;
import GPS.Modelo.Motociclo;
import GPS.Modelo.Pesado;
import GPS.Modelo.Veiculo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import static GPS.gpsproject.calendar.DateUtils.noFutureDates;

public class Adicionar implements BibliotecaInterface {
    private Veiculo veiculo;

    public VBox detalhes;
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
    public Button cancelar;
    public Button adicionar;

    @FXML
    public void initialize() {
        registomatricula.setDayCellFactory(noFutureDates);
        registoseguro.setDayCellFactory(noFutureDates);

        tipo = new ChoiceBox(tipos);
    }

    public void onAdiciona(ActionEvent actionEvent) {
        if (!everyFieldIsFilled()) {
            Alert.display("Alerta", "Não é permitido guardar campos vazios!");
            return;
        }

        //TODO Verificar base de dados

        if (!everyFieldIsValid()) {
            Alert.display("Alerta", "Verifique se os campos possuem dados válidos!");
            return;
        }

        switch (tipo.getValue().toString()) {
            case "Motociclo":
                veiculo = new Motociclo(nome.getText(),
                        matricula.getText(),
                        Integer.parseInt(kmreais.getText()),
                        Integer.parseInt(kmmensais.getText()),
                        seguradora.getText(),
                        registoseguro.getValue(),
                        tiposeguro.getText(),
                        getCilindrada());
                break;
            case "Pesado":
                veiculo = new Pesado(nome.getText(),
                        matricula.getText(),
                        Integer.parseInt(kmreais.getText()),
                        Integer.parseInt(kmmensais.getText()),
                        seguradora.getText(),
                        registoseguro.getValue(),
                        tiposeguro.getText());
                break;
            case "Ligeiro":
                veiculo = new Ligeiro(nome.getText(),
                        matricula.getText(),
                        Integer.parseInt(kmreais.getText()),
                        Integer.parseInt(kmmensais.getText()),
                        seguradora.getText(),
                        registoseguro.getValue(),
                        tiposeguro.getText());
                break;
        }
    }

    private int getCilindrada() {
        return 0;
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

    public Veiculo getVeiculo() {
        return veiculo;
    }
}

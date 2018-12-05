package GPS.gpsproject;

import GPS.Modelo.Constantes;
import GPS.Modelo.Veiculo;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import static GPS.gpsproject.calendar.DateUtils.noFutureDates;

public class Adicionar implements Constantes {
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

        tipo = new ChoiceBox(FXCollections.observableArrayList(TipoVeiculo.values()));
    }

    public void onAdiciona(ActionEvent actionEvent) {
        if (!everyFieldIsFilled()) {
            Alert.display("Alerta", "Não é permitido guardar campos vazios!");
            return;
        }

        if (!everyFieldIsValid()) {
            Alert.display("Alerta", "Verifique se os campos possuem dados válidos!\n\n" +
                    "Kilómetros têm de ser maiores que 0");
            return;
        }
    }

    private boolean everyFieldIsValid() {
        try {
            int kms = Integer.parseInt(kmmensais.getText());
            int kmsr = Integer.parseInt(kmreais.getText());

            if(kms <= 0 || kmsr <= 0) throw new NumberFormatException();

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
}

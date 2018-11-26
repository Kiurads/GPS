package GPS.gpsproject;

import GPS.Modelo.Veiculo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

import java.time.LocalDate;

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
        Callback<DatePicker, DateCell> dayCellFactory = new Callback<DatePicker, DateCell>() {
            public DateCell call(final DatePicker datePicker) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        // Must call super
                        super.updateItem(item, empty);

                        // Disable all future date cells
                        if (item.isAfter(LocalDate.now())) {
                            this.setDisable(true);
                        }
                    }
                };
            }
        };

        registomatricula.setDayCellFactory(dayCellFactory);
        registoseguro.setDayCellFactory(dayCellFactory);

        tipo = new ChoiceBox(tipos);
    }

    public void onAdiciona(ActionEvent actionEvent) {
        //TODO Verificar base de dados

        if (!everyFieldIsFilled()) {
            Alert.display("Alerta", "Não é permitido guardar campos vazios!");
            return;
        }

        if (!everyFieldIsValid()) {
            Alert.display("Alerta", "Verifique se os campos possuem dados válidos!");
            return;
        }

        switch (tipo.getValue().toString()) {
            case "Automóvel":
                /*veiculo = new Automovel(matricula.getText(),
                        DateUtils.asDate(registomatricula.getValue()),
                        Integer.parseInt(kmreais.getText()),
                        Integer.parseInt(kmmensais.getText()),
                        seguradora.getText(),
                        DateUtils.asDate(registoseguro.getValue()));*/
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
}

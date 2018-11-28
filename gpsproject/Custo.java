package GPS.gpsproject;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Custo {
    private static double value;

    public static double display() {
        Stage window = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(Alert.class.getResource("FXML/Custo.fxml"));
        } catch (IOException e) {
            System.exit(1);
        }
        root.setStyle("-fx-effect: innershadow(gaussian, #039ed3, 2, 1.0, 0, 0);");

        Scene scene = new Scene(root);

        Button button = (Button) scene.lookup("#button");
        TextField text = (TextField) scene.lookup("#text");

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!text.getText().trim().equals("")) {
                    value = Double.parseDouble(text.getText());
                    window.close();
                }
            }
        });

        window.setScene(scene);
        window.initModality(Modality.APPLICATION_MODAL);
        window.initStyle(StageStyle.UNDECORATED);
        window.showAndWait();

        return Double.parseDouble(text.getText());
    }
}

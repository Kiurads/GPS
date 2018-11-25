package GPS.gpsproject;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;

public class Alert {
    public static void display(String title, String message) {
        Stage window = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(Alert.class.getResource("FXML/Warning.fxml"));
        } catch (IOException e) {
            return;
        }
        root.setStyle("-fx-effect: innershadow(gaussian, #039ed3, 2, 1.0, 0, 0);");
        Scene scene = new Scene(root);
        URL resource = Alert.class.getResource("/GPS/gpsproject/sounds/unsure.mp3");
        MediaPlayer mediaPlayer = new MediaPlayer(new Media(resource.toString()));

        ImageView imageView = (ImageView) scene.lookup("#imageView");
        Label warningTitle = (Label) scene.lookup("#messageLabel");
        Label warningMessage = (Label) scene.lookup("#detailsLabel");
        Button okButton = (Button) scene.lookup("#okButton");

        imageView.setImage(new Image("GPS/gpsproject/images/warning.png"));
        warningTitle.setText(title);
        warningMessage.setText(message);

        okButton.setOnAction(e -> window.close());


        window.initModality(Modality.APPLICATION_MODAL);
        window.initStyle(StageStyle.UNDECORATED);
        window.setTitle("Alerta");
        window.setScene(scene);
        mediaPlayer.play();
        window.showAndWait();
    }
}

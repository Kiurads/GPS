package GPS.gpsproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;

public class GpsProject extends Application {
    private ListView list;

    @Override
    public void start(final Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("VC.fxml"));
        Scene scene = new Scene(root, 1000, 700);

        getItemsFromScene(scene);

        stage.setTitle("Vehicle Companion");
        stage.setScene(scene);
        stage.setOnCloseRequest(event -> System.exit(0));
        stage.show();
    }

    private void getItemsFromScene(Scene scene) {
        list = (ListView) scene.lookup("#list");

    }

    public static void main(String[] args) {
        launch(args);
    }
}

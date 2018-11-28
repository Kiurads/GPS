package GPS.gpsproject;

import GPS.Modelo.Frota;
import GPS.Modelo.Ligeiro;
import GPS.Modelo.Veiculo;
import com.sun.glass.ui.Screen;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

public class GpsProject extends Application {

    @Override
    public void start(final Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("VC.fxml"));
        int sceneWidth = 2 * Screen.getMainScreen().getWidth() / 3;
        int sceneHeight = 2 * Screen.getMainScreen().getHeight() / 3;
        Scene scene = new Scene(root, sceneWidth, sceneHeight);

        stage.setTitle("Vehicle Companion");
        stage.setScene(scene);
        stage.setOnCloseRequest(event -> System.exit(0));
        stage.show();
    }

    public static void main(String[] args) throws ClassNotFoundException, IOException {
//        launch(args);

        Frota f = new Frota();
        System.out.println(f.toString());
    }
}

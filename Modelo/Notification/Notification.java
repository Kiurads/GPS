package GPS.Modelo.Notification;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

import java.net.URL;

public class Notification {
    public static void sendNotification(String title, String message) {
        Image image = new Image("/GPS/gpsproject/images/sign-check-icon.png", 100, 100, false, true);
        URL resource = Notification.class.getResource("/GPS/gpsproject/sounds/finished-task.mp3");
        MediaPlayer mediaPlayer = new MediaPlayer(new Media(resource.toString()));


        Notifications notification = Notifications.create()
                .title(title)
                .text(message)
                .graphic(new ImageView(image))
                .hideAfter(Duration.seconds(5))
                .position(Pos.BOTTOM_RIGHT);


        notification.show();
        mediaPlayer.play();
    }
}

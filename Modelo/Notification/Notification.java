package GPS.Modelo.Notification;

import GPS.gpsproject.Sounds.BibliotecaSons;
import GPS.gpsproject.images.BibliotecaImagens;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

public class Notification implements BibliotecaImagens, BibliotecaSons {
    public static void sendNotification(String title, String message) {
        MediaPlayer mediaPlayer = new MediaPlayer(notificationSound);


        Notifications notification = Notifications.create()
                .title(title)
                .text(message)
                .graphic(new ImageView(notificationIcon))
                .hideAfter(Duration.seconds(5))
                .position(Pos.BOTTOM_RIGHT);


        notification.show();
        mediaPlayer.play();
    }
}

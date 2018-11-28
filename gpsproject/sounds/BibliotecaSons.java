package GPS.gpsproject.sounds;

import javafx.scene.media.Media;

public interface BibliotecaSons {
    Media notificationSound = new Media(BibliotecaSons.class.getResource("/GPS/gpsproject/sounds/finished-task.mp3").toString());
    Media errorSound = new Media(BibliotecaSons.class.getResource("/GPS/gpsproject/sounds/unsure.mp3").toString());
}

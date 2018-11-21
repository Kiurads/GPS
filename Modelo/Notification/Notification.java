package GPS.Modelo.Notification;

import java.awt.*;

public class Notification {
    public static void sendNotification(String message, String title, String tooltip) throws AWTException {
        SystemTray tray = SystemTray.getSystemTray();

        Image image = Toolkit.getDefaultToolkit().createImage("icon.png");

        TrayIcon trayIcon = new TrayIcon(image, tooltip);
        trayIcon.setImageAutoSize(true);
        tray.add(trayIcon);

        trayIcon.displayMessage(title, message, TrayIcon.MessageType.INFO);
    }
}

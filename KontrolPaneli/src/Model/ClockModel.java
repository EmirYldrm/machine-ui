package Model;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class ClockModel {

    private StringProperty timeProperty = new SimpleStringProperty();
    private DateTimeFormatter	 timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    public StringProperty timeProperty() {
        return timeProperty;
    }

    public void start() {
        Thread clockThread = new Thread(() -> {
            while (true) {
                LocalTime currentTime = LocalTime.now();
                
             // Format the LocalTime without fractional seconds
                String formattedTime = currentTime.format(timeFormatter);

                // Use Platform.runLater to update the UI on the JavaFX Application Thread
                Platform.runLater(() -> {
                    timeProperty.set(formattedTime);
                });
                
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        clockThread.setDaemon(true);
        clockThread.start();
    }

}

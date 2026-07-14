package hurraa;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class HurraaSovellus extends Application {

    @Override
    public void start(Stage stage) {
        BorderPane layout = new BorderPane();

        Button hurrayButton = new Button("Hurray!");

        hurrayButton.setOnAction((event) -> {
            try {
                // 1. Locate the wav audio file relative to the root directory
                File soundFile = new File("Applause-Yannick_Lemieux.wav");
                
                // 2. Open an audio input stream from the file resource target
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);
                
                // 3. Acquire a systemic clip runtime resource slot
                Clip clip = AudioSystem.getClip();
                
                // 4. Open and play the sound instantly via standard Java Sound APIs
                clip.open(audioStream);
                clip.start();
            } catch (Exception e) {
                System.out.println("Playback error: " + e.getMessage());
            }
        });

        layout.setCenter(hurrayButton);

        Scene scene = new Scene(layout, 300, 250);
        stage.setScene(scene);
        stage.setTitle("Hurraa!");
        stage.show();
    }

    public static void main(String[] args) {
        launch(HurraaSovellus.class);
    }
}

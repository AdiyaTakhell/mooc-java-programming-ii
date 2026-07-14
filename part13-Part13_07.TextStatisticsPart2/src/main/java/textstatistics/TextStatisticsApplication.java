package textstatistics;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class TextStatisticsApplication extends Application {

    @Override
    public void start(Stage window) {
        BorderPane layout = new BorderPane();

        // 1. Create the input text area component
        TextArea textArea = new TextArea();
        layout.setCenter(textArea);

        // 2. Create the labels for tracking the stats
        Label lettersLabel = new Label("Letters: 0");
        Label wordsLabel = new Label("Words: 0");
        Label longestWordLabel = new Label("The longest word is:");

        // 3. Place the stats horizontally in the bottom layout slot
        HBox statisticsLayout = new HBox();
        statisticsLayout.setSpacing(10);
        statisticsLayout.getChildren().addAll(lettersLabel, wordsLabel, longestWordLabel);
        layout.setBottom(statisticsLayout);

        // 4. Attach a listener to recalculate automatically on every keystroke
        textArea.textProperty().addListener((change, oldValue, newValue) -> {
            int characters = newValue.length();
            
            // Split text by any sequence of spaces, tabs, or newlines
            String[] parts = newValue.split("\\s+");
            int words = parts.length;
            
            // Adjust count if the input box is completely blank
            if (newValue.trim().isEmpty()) {
                words = 0;
            }

            // Loop through words to isolate the longest one
            String longest = "";
            for (String word : parts) {
                if (word.length() > longest.length()) {
                    longest = word;
                }
            }

            // Instantly apply the updated strings to the layout labels
            lettersLabel.setText("Letters: " + characters);
            wordsLabel.setText("Words: " + words);
            longestWordLabel.setText("The longest word is: " + longest);
        });

        // 5. Initialize the application window
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.setTitle("Text Statistics");
        window.show();
    }

    public static void main(String[] args) {
        // Starts the JavaFX application cycle
        launch(TextStatisticsApplication.class);
    }
}

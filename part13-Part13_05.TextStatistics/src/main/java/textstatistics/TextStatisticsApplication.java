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
        // 1. Create the main BorderPane layout
        BorderPane layout = new BorderPane();

        // 2. Create the TextArea and place it in the center
        TextArea textArea = new TextArea();
        layout.setCenter(textArea);

        // 3. Create the three text components (Labels)
        Label lettersLabel = new Label("Letters: 0");
        Label wordsLabel = new Label("Words: 0");
        Label longestWordLabel = new Label("The longest word is:");

        // 4. Create the HBox layout for the bottom components
        HBox bottomLayout = new HBox();
        bottomLayout.setSpacing(10); // Optional: creates a clean spacing between labels
        
        // Add components to HBox in the exact requested order
        bottomLayout.getChildren().add(lettersLabel);
        bottomLayout.getChildren().add(wordsLabel);
        bottomLayout.getChildren().add(longestWordLabel);

        // 5. Place the HBox at the bottom edge of the BorderPane
        layout.setBottom(bottomLayout);

        // 6. Set up the scene and window
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.setTitle("Text Statistics");
        window.show();
    }

    public static void main(String[] args) {
        launch(TextStatisticsApplication.class);
    }
}

package notifier;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class NotifierApplication extends Application {

    @Override
    public void start(Stage window) {
        // 1. Create the UI components
        TextField textField = new TextField();
        Button button = new Button("Update");
        Label label = new Label();

        // 2. Add functionality to the button
        button.setOnAction((event) -> {
            label.setText(textField.getText());
        });

        // 3. Arrange components vertically using VBox
        VBox layout = new VBox();
        layout.setSpacing(10); // Optional spacing between elements
        layout.getChildren().addAll(textField, button, label);

        // 4. Set the scene and display the window
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.setTitle("Notifier");
        window.show();
    }

    public static void main(String[] args) {
        // Launches the JavaFX application loop
        launch(NotifierApplication.class);
    }
}

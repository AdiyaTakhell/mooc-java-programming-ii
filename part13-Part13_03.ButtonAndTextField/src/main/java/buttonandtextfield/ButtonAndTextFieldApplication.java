package buttonandtextfield;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ButtonAndTextFieldApplication extends Application {

    @Override
    public void start(Stage window) {
        // 1. Create the UI components
        Button button = new Button("Click me!");
        TextField textField = new TextField();

        // 2. Arrange elements side-by-side using HBox (Button on the Left)
        HBox layout = new HBox();
        layout.setSpacing(10); // Optional: adds a neat 10-pixel gap between components
        layout.getChildren().add(button);    // Added first (left side)
        layout.getChildren().add(textField); // Added second (right side)

        // 3. Create the scene with the layout layout
        Scene scene = new Scene(layout);

        // 4. Configure and show the main window
        window.setScene(scene);
        window.setTitle("Button and TextField Exercise");
        window.show();
    }

    public static void main(String[] args) {
        launch(ButtonAndTextFieldApplication.class);
    }
}

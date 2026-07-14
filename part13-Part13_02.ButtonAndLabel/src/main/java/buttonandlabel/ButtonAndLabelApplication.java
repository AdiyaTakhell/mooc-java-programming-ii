package buttonandlabel;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ButtonAndLabelApplication extends Application {

    @Override
    public void start(Stage window) {
        // Create components
        Label labelComponent = new Label("This is a label");
        Button buttonComponent = new Button("This is a button");

        // Layout places Label on top of Button
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(labelComponent, buttonComponent);

        // Create scene and set up the window
        Scene view = new Scene(layout, 300, 200);
        window.setScene(view);
        window.setTitle("Button and Label Application");
        window.show();
    }

    public static void main(String[] args) {
        // Launches the JavaFX application
        launch(ButtonAndLabelApplication.class);
    }
}

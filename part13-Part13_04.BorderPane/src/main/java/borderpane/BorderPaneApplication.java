package borderpane;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class BorderPaneApplication extends Application {

    @Override
    public void start(Stage window) {
        // 1. Create a BorderPane layout container
        BorderPane layout = new BorderPane();

        // 2. Create the required text components (Labels)
        Label northText = new Label("NORTH");
        Label eastText = new Label("EAST");
        Label southText = new Label("SOUTH");

        // 3. Place the components at their respective edges
        layout.setTop(northText);
        layout.setRight(eastText);
        layout.setBottom(southText);

        // 4. Create the scene, pass the layout, and show the window
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.setTitle("BorderPane Exercise");
        window.show();
    }

    public static void main(String[] args) {
        launch(BorderPaneApplication.class);
    }
}

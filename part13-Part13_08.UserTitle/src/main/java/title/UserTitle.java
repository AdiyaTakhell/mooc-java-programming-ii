package title;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class UserTitle extends Application {

    @Override
    public void start(Stage window) {
        // Read the parameters passed during launch
        Parameters params = getParameters();
        String title = params.getNamed().getOrDefault("title", "Default Title");

        // Set up an empty layout and scene
        VBox layout = new VBox();
        Scene scene = new Scene(layout, 400, 300);

        // Apply the custom title to the stage
        window.setTitle(title);
        window.setScene(scene);
        window.show();
    }
}

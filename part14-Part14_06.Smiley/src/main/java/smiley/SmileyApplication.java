package smiley;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class SmileyApplication extends Application {

    @Override
    public void start(Stage stage) {
        BorderPane layout = new BorderPane();

        // 1. Create a canvas of 400x400 pixels
        Canvas canvas = new Canvas(400, 400);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        // 2. Clear background and color it completely white
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, 400, 400);

        // 3. Set paint color to black for drawing the smiley features
        gc.setFill(Color.BLACK);

        // Draw Left Eye (a 50x50 block)
        gc.fillRect(100, 80, 50, 50);

        // Draw Right Eye (a 50x50 block)
        gc.fillRect(250, 80, 50, 50);

        // Draw Mouth corners (left and right elevated points)
        gc.fillRect(50, 200, 50, 50);
        gc.fillRect(300, 200, 50, 50);

        // Draw Main Smile base line spanning across the lower center
        gc.fillRect(100, 250, 200, 50);

        // 4. Position the canvas layout inside the center of the pane
        layout.setCenter(canvas);

        // 5. Build and launch the stage scene frame
        Scene scene = new Scene(layout);
        stage.setScene(scene);
        stage.setTitle("Smiley!");
        stage.show();
    }

    public static void main(String[] args) {
        launch(SmileyApplication.class);
    }
}

package collage;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class CollageApplication extends Application {

    @Override
    public void start(Stage stage) {

        Image sourceImage = new Image("file:monalisa.png");
        PixelReader imageReader = sourceImage.getPixelReader();

        int width = (int) sourceImage.getWidth();
        int height = (int) sourceImage.getHeight();

        WritableImage targetImage = new WritableImage(width, height);
        PixelWriter imageWriter = targetImage.getPixelWriter();

        // Calculate the boundaries for the downscaled grid cells
        int halfWidth = width / 2;
        int halfHeight = height / 2;

        // Iterate through the target coordinates of the top-left sub-image cell
        for (int y = 0; y < halfHeight; y++) {
            for (int x = 0; x < halfWidth; x++) {

                // Part 1: Pull pixels from source by doubling coordinates to skip half of them
                Color sourceColor = imageReader.getColor(x * 2, y * 2);

                // Part 3: Calculate the negative value for every color channel
                double red = 1.0 - sourceColor.getRed();
                double green = 1.0 - sourceColor.getGreen();
                double blue = 1.0 - sourceColor.getBlue();
                double opacity = sourceColor.getOpacity();

                Color negativeColor = new Color(red, green, blue, opacity);

                // Part 2: Place this modified negative pixel across all 4 grid positions
                // Top-Left corner cell
                imageWriter.setColor(x, y, negativeColor);
                
                // Top-Right corner cell
                imageWriter.setColor(x + halfWidth, y, negativeColor);
                
                // Bottom-Left corner cell
                imageWriter.setColor(x, y + halfHeight, negativeColor);
                
                // Bottom-Right corner cell
                imageWriter.setColor(x + halfWidth, y + halfHeight, negativeColor);
            }
        }

        ImageView image = new ImageView(targetImage);
        Pane pane = new Pane();
        pane.getChildren().add(image);

        stage.setScene(new Scene(pane));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

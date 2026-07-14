package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class JokeApplication extends Application {

    @Override
    public void start(Stage window) {
        // 1. Create the main layout
        BorderPane mainLayout = new BorderPane();

        // 2. Create the top navigation menu components
        HBox menuLayout = new HBox();
        menuLayout.setPadding(new Insets(10, 10, 10, 10));
        menuLayout.setSpacing(10);
        menuLayout.setAlignment(Pos.CENTER);

        Button jokeButton = new Button("Joke");
        Button answerButton = new Button("Answer");
        Button explanationButton = new Button("Explanation");

        menuLayout.getChildren().addAll(jokeButton, answerButton, explanationButton);
        mainLayout.setTop(menuLayout);

        // 3. Create the central content container
        StackPane contentLayout = new StackPane();
        contentLayout.setPadding(new Insets(20, 20, 20, 20));
        contentLayout.setAlignment(Pos.CENTER);

        // Default initial text view
        Label textComponent = new Label("What do you call a bear with no teeth?");
        contentLayout.getChildren().add(textComponent);
        mainLayout.setCenter(contentLayout);

        // 4. Add functionality to button selections
        jokeButton.setOnAction((event) -> {
            textComponent.setText("What do you call a bear with no teeth?");
        });

        answerButton.setOnAction((event) -> {
            textComponent.setText("A gummy bear.");
        });

        explanationButton.setOnAction((event) -> {
            textComponent.setText("Because candies called gummy bears are soft, squishy, and don't have teeth either!");
        });

        // 5. Initialize the application window state
        Scene scene = new Scene(mainLayout, 450, 200);
        window.setScene(scene);
        window.setTitle("Joke Application");
        window.show();
    }

    public static void main(String[] args) {
        launch(JokeApplication.class);
    }
}

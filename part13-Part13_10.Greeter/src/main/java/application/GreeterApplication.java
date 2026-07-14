package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GreeterApplication extends Application {

    @Override
    public void start(Stage window) {
        // --- VIEW 1: Input Name ---
        Label instructions = new Label("Enter your name and start:");
        TextField nameField = new TextField();
        Button startButton = new Button("Start");

        VBox inputLayout = new VBox();
        inputLayout.setSpacing(10);
        inputLayout.setPadding(new Insets(20, 20, 20, 20));
        inputLayout.setAlignment(Pos.CENTER);
        inputLayout.getChildren().addAll(instructions, nameField, startButton);

        Scene inputScene = new Scene(inputLayout, 300, 200);

        // --- VIEW 2: Welcome Greeting ---
        Label welcomeLabel = new Label();

        VBox welcomeLayout = new VBox();
        welcomeLayout.setPadding(new Insets(20, 20, 20, 20));
        welcomeLayout.setAlignment(Pos.CENTER);
        welcomeLayout.getChildren().add(welcomeLabel);

        Scene welcomeScene = new Scene(welcomeLayout, 300, 200);

        // --- BUTTON EVENT HANDLER ---
        startButton.setOnAction((event) -> {
            String name = nameField.getText();
            welcomeLabel.setText("Welcome " + name + "!");
            window.setScene(welcomeScene);
        });

        // --- STARTUP CONFIGURATION ---
        window.setScene(inputScene);
        window.setTitle("Greeter");
        window.show();
    }

    public static void main(String[] args) {
        launch(GreeterApplication.class);
    }
}

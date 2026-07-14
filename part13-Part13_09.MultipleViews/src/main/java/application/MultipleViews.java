package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MultipleViews extends Application {

    @Override
    public void start(Stage window) {
        // --- VIEW 1 ---
        BorderPane layout1 = new BorderPane();
        Label text1 = new Label("First view!");
        Button button1 = new Button("To the second view!");
        
        layout1.setTop(text1);
        layout1.setCenter(button1);
        
        Scene firstView = new Scene(layout1, 400, 300);

        // --- VIEW 2 ---
        VBox layout2 = new VBox();
        Button button2 = new Button("To the third view!");
        Label text2 = new Label("Second view!");
        
        layout2.getChildren().addAll(button2, text2);
        
        Scene secondView = new Scene(layout2, 400, 300);

        // --- VIEW 3 ---
        GridPane layout3 = new GridPane();
        Label text3 = new Label("Third view!");
        Button button3 = new Button("To the first view!");
        
        // GridPane uses (column, row) coordinates
        layout3.add(text3, 0, 0);
        layout3.add(button3, 1, 1);
        
        Scene thirdView = new Scene(layout3, 400, 300);

        // --- BUTTON ACTION LISTENERS ---
        button1.setOnAction((event) -> {
            window.setScene(secondView);
        });

        button2.setOnAction((event) -> {
            window.setScene(thirdView);
        });

        button3.setOnAction((event) -> {
            window.setScene(firstView);
        });

        // --- APPLICATION INITIALIZATION ---
        window.setScene(firstView); // Display the first view on startup
        window.setTitle("Multiple Views");
        window.show();
    }

    public static void main(String[] args) {
        launch(MultipleViews.class);
    }
}

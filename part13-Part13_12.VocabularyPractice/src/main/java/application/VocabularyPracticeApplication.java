package application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class VocabularyPracticeApplication extends Application {

    // Data structures to store our word pairs
    private Map<String, String> dictionary;
    private List<String> words;
    private String currentWord;

    @Override
    public void init() {
        // Initialize storage variables before the UI launches
        this.dictionary = new HashMap<>();
        this.words = new ArrayList<>();
        this.currentWord = "";
    }

    @Override
    public void start(Stage window) {
        BorderPane mainLayout = new BorderPane();

        // 1. Create the navigation menu components
        HBox menuLayout = new HBox();
        menuLayout.setPadding(new Insets(10));
        menuLayout.setSpacing(10);
        menuLayout.setAlignment(Pos.CENTER);

        Button enterWordsMenuButton = new Button("Enter new words");
        Button practiceMenuButton = new Button("Practice");
        menuLayout.getChildren().addAll(enterWordsMenuButton, practiceMenuButton);
        mainLayout.setTop(menuLayout);

        // 2. Build the two alternative inner sub-layouts
        Parent inputView = createInputView();
        Parent practiceView = createPracticeView();

        // Set the input view as the default center on application load
        mainLayout.setCenter(inputView);

        // 3. Attach menu button listeners to switch out the active center sub-views
        enterWordsMenuButton.setOnAction((event) -> mainLayout.setCenter(inputView));
        practiceMenuButton.setOnAction((event) -> {
            // Re-render or refresh the practice view to fetch a fresh random word
            mainLayout.setCenter(createPracticeView());
        });

        // 4. Finalize application layout window environment
        Scene scene = new Scene(mainLayout, 450, 300);
        window.setScene(scene);
        window.setTitle("Vocabulary Practice");
        window.show();
    }

    // Creates the layout view where users input new words
    private Parent createInputView() {
        VBox layout = new VBox();
        layout.setPadding(new Insets(20));
        layout.setSpacing(10);
        layout.setAlignment(Pos.CENTER);

        Label wordInstruction = new Label("Word:");
        TextField wordField = new TextField();

        Label translationInstruction = new Label("Translation:");
        TextField translationField = new TextField();

        Button addButton = new Button("Add the word pair");

        layout.getChildren().addAll(wordInstruction, wordField, translationInstruction, translationField, addButton);

        // Button action to append input strings into dictionary memory structures
        addButton.setOnAction((event) -> {
            String word = wordField.getText().trim();
            String translation = translationField.getText().trim();

            if (!word.isEmpty() && !translation.isEmpty()) {
                if (!dictionary.containsKey(word)) {
                    words.add(word);
                }
                dictionary.put(word, translation);

                // Reset fields for user convenience
                wordField.clear();
                translationField.clear();
            }
        });

        return layout;
    }

    // Creates the layout view where users drill practice translations
    private Parent createPracticeView() {
        VBox layout = new VBox();
        layout.setPadding(new Insets(20));
        layout.setSpacing(10);
        layout.setAlignment(Pos.CENTER);

        // Edge case: if no words have been entered yet
        if (words.isEmpty()) {
            layout.getChildren().add(new Label("Please enter some word pairs first!"));
            return layout;
        }

        // Randomly pick a word from our tracked list
        Random random = new Random();
        currentWord = words.get(random.nextInt(words.size()));

        Label promptLabel = new Label("Translate the word '" + currentWord + "':");
        TextField answerField = new TextField();
        Button checkButton = new Button("Check");
        Label feedbackLabel = new Label("");

        layout.getChildren().addAll(promptLabel, answerField, checkButton, feedbackLabel);

        checkButton.setOnAction((event) -> {
            String userAnswer = answerField.getText().trim();
            String correctAnswer = dictionary.get(currentWord);

            if (userAnswer.equalsIgnoreCase(correctAnswer)) {
                feedbackLabel.setText("Correct!");
            } else {
                feedbackLabel.setText("Incorrect! The correct translation is: " + correctAnswer);
            }
        });

        return layout;
    }

    public static void main(String[] args) {
        launch(VocabularyPracticeApplication.class);
    }
}

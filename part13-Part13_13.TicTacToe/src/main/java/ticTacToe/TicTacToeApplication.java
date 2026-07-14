package ticTacToe;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font; // Corrected package path
import javafx.stage.Stage;

public class TicTacToeApplication extends Application {

    private String currentTurn = "X";
    private Button[][] board = new Button[3][3];
    private Label statusLabel = new Label("Turn: X");
    private boolean gameEnded = false;

    @Override
    public void start(Stage window) {
        BorderPane mainLayout = new BorderPane();
        mainLayout.setPadding(new Insets(10));

        statusLabel.setFont(Font.font("Monospaced", 24));
        BorderPane.setAlignment(statusLabel, Pos.CENTER);
        mainLayout.setTop(statusLabel);

        GridPane gridLayout = new GridPane();
        gridLayout.setHgap(10);
        gridLayout.setVgap(10);
        gridLayout.setPadding(new Insets(10));
        gridLayout.setAlignment(Pos.CENTER);

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                Button btn = new Button(" ");
                btn.setFont(Font.font("Monospaced", 40));
                btn.setPrefSize(90, 90);

                board[row][col] = btn;

                int currentRow = row;
                int currentCol = col;

                btn.setOnAction((event) -> {
                    handleTurn(currentRow, currentCol);
                });

                gridLayout.add(btn, col, row);
            }
        }

        mainLayout.setCenter(gridLayout);

        Scene scene = new Scene(mainLayout, 340, 400);
        window.setScene(scene);
        window.setTitle("Tic-Tac-Toe");
        window.show();
    }

    private void handleTurn(int row, int col) {
        if (gameEnded || !board[row][col].getText().equals(" ")) {
            return;
        }

        board[row][col].setText(currentTurn);

        if (checkWin()) {
            statusLabel.setText("The end!");
            gameEnded = true;
            return;
        }

        if (isBoardFull()) {
            statusLabel.setText("The end!");
            gameEnded = true;
            return;
        }

        if (currentTurn.equals("X")) {
            currentTurn = "O";
        } else {
            currentTurn = "X";
        }
        statusLabel.setText("Turn: " + currentTurn);
    }

    private boolean checkWin() {
        for (int i = 0; i < 3; i++) {
            if (!board[i][0].getText().equals(" ") &&
                board[i][0].getText().equals(board[i][1].getText()) &&
                board[i][0].getText().equals(board[i][2].getText())) {
                return true;
            }
            if (!board[0][i].getText().equals(" ") &&
                board[0][i].getText().equals(board[1][i].getText()) &&
                board[0][i].getText().equals(board[2][i].getText())) {
                return true;
            }
        }

        if (!board[0][0].getText().equals(" ") &&
            board[0][0].getText().equals(board[1][1].getText()) &&
            board[0][0].getText().equals(board[2][2].getText())) {
            return true;
        }

        if (!board[0][2].getText().equals(" ") &&
            board[0][2].getText().equals(board[1][1].getText()) &&
            board[0][2].getText().equals(board[2][0].getText())) {
            return true;
        }

        return false;
    }

    private boolean isBoardFull() {
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                if (board[r][c].getText().equals(" ")) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        launch(TicTacToeApplication.class);
    }
}


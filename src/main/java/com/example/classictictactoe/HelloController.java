package com.example.classictictactoe;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.GridPane;

public class HelloController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private GridPane gridPane;

    private char nowSym = 'x';

    private char gameField[][] = new char[3][3];

    private boolean isGame = true;

    @FXML
    void btnClick(ActionEvent event) {

        Button btn = (Button) event.getSource();

        if (!isGame || !btn.getText().isEmpty()) return;

        int rowIndex = GridPane.getRowIndex(btn) == null ? 0 : GridPane.getRowIndex(btn);
        int columnIndex = GridPane.getColumnIndex(btn) == null ? 0 : GridPane.getColumnIndex(btn);

        gameField[rowIndex][columnIndex] = nowSym;

        btn.setText(String.valueOf(nowSym));

        if (checkWinner()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "We have a winner <<" + nowSym + ">>", ButtonType.OK);
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    resetGame();
                }
            });
        } else if (isBoardFull()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "It's a draw!", ButtonType.OK);
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    resetGame();
                }
            });
        } else {
            nowSym = nowSym == 'x' ? 'o' : 'x';
        }
    }

    private boolean checkWinner() {
        for (int i = 0; i < 3; i++) {
            // Check rows
            if (gameField[i][0] == gameField[i][1] && gameField[i][0] == gameField[i][2] && gameField[i][0] != '\0') {
                return true;
            }
            // Check columns
            if (gameField[0][i] == gameField[1][i] && gameField[0][i] == gameField[2][i] && gameField[0][i] != '\0') {
                return true;
            }
        }
        // Check diagonals
        if (gameField[0][0] == gameField[1][1] && gameField[0][0] == gameField[2][2] && gameField[0][0] != '\0') {
            return true;
        }
        if (gameField[0][2] == gameField[1][1] && gameField[0][2] == gameField[2][0] && gameField[0][2] != '\0') {
            return true;
        }
        return false;
    }

    private boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (gameField[i][j] == '\0') {
                    return false;
                }
            }
        }
        return true;
    }

    private void resetGame() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                gameField[i][j] = '\0';
            }
        }
        nowSym = 'x';
        isGame = true;

        // Clear all button texts
        gridPane.getChildren().forEach(node -> {
            if (node instanceof Button) {
                ((Button) node).setText("");
            }
        });
    }

    @FXML
    void initialize() {
        resetGame();
    }
}

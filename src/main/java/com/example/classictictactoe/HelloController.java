package com.example.classictictactoe;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class HelloController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    private char nowSym = 'x';

    private char gameField[][] = new char[3][3];

    @FXML
    void btnClick(ActionEvent event) {
        Button btn = (Button)event.getSource();

        int rowIndex = GridPane.getRowIndex(btn) == null ? 0 : GridPane.getRowIndex(btn);
        int columnIndex = GridPane.getColumnIndex(btn) == null ? 0 : GridPane.getColumnIndex(btn);

        gameField[rowIndex][columnIndex] = nowSym;

        //System.out.println(rowIndex);
        //System.out.println(columnIndex);


        btn.setText(String.valueOf(nowSym));
        nowSym = nowSym == 'x' ? 'o' : 'x';
    }

    @FXML
    void initialize() {

    }

}

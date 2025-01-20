module com.example.classictictactoe {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.classictictactoe to javafx.fxml;
    exports com.example.classictictactoe;
}
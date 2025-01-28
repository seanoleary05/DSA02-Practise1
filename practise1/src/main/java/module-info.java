module com.example.practise1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.practise1 to javafx.fxml;
    exports com.example.practise1;
}
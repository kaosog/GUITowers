module com.example.guitowers {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.guitowers to javafx.fxml;
    exports com.example.guitowers;
}
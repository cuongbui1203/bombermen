module com.example.bomber {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.gui to javafx.fxml;
    exports com.gui;
    exports com.graphics;
    opens com.graphics to javafx.fxml;
}
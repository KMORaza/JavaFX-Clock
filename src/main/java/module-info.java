module com.example.clock_app {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;

    opens com.javafx.clock_app to javafx.fxml;
    exports com.javafx.clock_app;
}
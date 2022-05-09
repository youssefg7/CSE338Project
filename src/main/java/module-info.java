module com.example.cse338project {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;
    requires jackson.databind;
    requires jackson.core;


    opens com.example.cse338project to javafx.fxml;
    exports com.example.cse338project;
}
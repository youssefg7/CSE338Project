module com.example.cse338project {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.core;
    requires json;


    opens com.example.cse338project to javafx.fxml;
    opens Scrapping to com.fasterxml.jackson.databind;
    exports com.example.cse338project;
}
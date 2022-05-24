module com.example.cse338project {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.core;
    requires json;
    requires org.jsoup;

    opens Scrapping to com.fasterxml.jackson.databind;
    exports com.example.cse338project;
    exports com.example.cse338project.gui;
    exports com.example.cse338project.classes;
    opens com.example.cse338project.gui to javafx.fxml;
    opens com.example.cse338project.classes to javafx.base;

}
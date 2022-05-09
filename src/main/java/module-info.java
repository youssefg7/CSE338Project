module com.example.cse338project {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.example.cse338project to javafx.fxml;
    exports com.example.cse338project;
}
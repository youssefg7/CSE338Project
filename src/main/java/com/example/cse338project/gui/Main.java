package com.example.cse338project.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("RankTable.fxml")));
        Image logo = new Image("FIFA.png");
        primaryStage.getIcons().add(logo);
        Scene scene = new Scene(root);
        primaryStage.setTitle("FIFA RANKING");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

package com.tedomi2705.bomberman;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Home extends Application {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 660;

    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(Home.class.getResource("/fxml/home.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), WIDTH, HEIGHT);

        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("Bomberman");
        stage.setScene(scene);
        stage.show();

        Sound.playBackground(Sound.background);

    }

    public static void main(String[] args) {
        launch();
    }
}

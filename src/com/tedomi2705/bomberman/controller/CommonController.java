package com.tedomi2705.bomberman.controller;

import com.tedomi2705.bomberman.Home;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


abstract public class CommonController implements Initializable {

    @FXML
    protected Button backButton;
    @FXML
    protected Button quitButton;

    public static void changeScene(Stage stage, String path) throws IOException {
        FXMLLoader fxmlLoader_signup = new FXMLLoader(Home.class.getResource(path));
        Scene scene = new Scene(fxmlLoader_signup.load(), Home.WIDTH, Home.HEIGHT);
        stage.setScene(scene);
        stage.show();

    }

    public void quitButtonOnAction() {
        Stage stage = (Stage) quitButton.getScene().getWindow();
        stage.close();
    }

    public void backButtonOnAction(String path) throws IOException {
        Stage stage = (Stage) backButton.getScene().getWindow();
        changeScene(stage, path);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}

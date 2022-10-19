package com.tedomi2705.bomberman.controller;

import com.tedomi2705.bomberman.BombermanGame;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeController extends CommonController {
    @FXML
    private Button playButton;
    @FXML
    private Button howToPlayButton;
    @FXML
    private Button settingButton;


    public void playButtonOnAction() throws IOException {
        Stage stage = (Stage) playButton.getScene().getWindow();
        BombermanGame game = new BombermanGame();
        game.start(stage);
    }

    public void settingButtonOnAction() throws IOException {
        Stage stage = (Stage) settingButton.getScene().getWindow();
        changeScene(stage, "/fxml/setting.fxml");
    }

    public void howToPlayButtonOnAction() throws IOException {
        Stage stage = (Stage) howToPlayButton.getScene().getWindow();
        changeScene(stage, "/fxml/howtoplay.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {}
}

package com.tedomi2705.bomberman.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import com.tedomi2705.bomberman.Sound;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;

public class SettingController extends CommonController {

    @FXML
    private ImageView SFXImage;

    @FXML
    private Slider SFXSlider;

    @FXML
    private Button backButton;

    @FXML
    private ImageView bgMusicImage;

    @FXML
    private Slider bgMusicSlider;

    @FXML
    private ImageView muteBgMusicImage;

    @FXML
    private ImageView muteSFXImage;

    @FXML
    void backButtonOnAction(ActionEvent event) throws IOException {
        super.backButtonOnAction("/fxml/home.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bgMusicSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                Sound.setBackgroundVolume(bgMusicSlider.getValue() * 0.01);
                Sound.backgroundPlayer.setVolume(Sound.getBackgroundVolume());
            }
        });
        SFXSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                Sound.setSoundVolume(SFXSlider.getValue() * 0.01);
            }
        });
    }
    

    

}

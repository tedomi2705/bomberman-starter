package com.tedomi2705.bomberman.controller;

import java.io.IOException;

public class HowToPlayController extends CommonController {
    public void backButtonOnAction() throws IOException {
        super.backButtonOnAction("/fxml/home.fxml");
    }

}

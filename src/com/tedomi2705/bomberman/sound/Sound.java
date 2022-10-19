package com.tedomi2705.bomberman.sound;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Sound {
    private static MediaPlayer backgroundPlayer;
    private static double backgroundVolume = 1.0;
    private static double soundVolume = 1.0;

    public static Media background = new Media(Sound.class.getResource("/sound/background.mp3").toString());
    public static Media explode = new Media(Sound.class.getResource("/sound/bomb.mp3").toString());
    public static Media item = new Media(Sound.class.getResource("/sound/item.mp3").toString());
    public static Media killenemy = new Media(Sound.class.getResource("/sound/killenemy.mp3").toString());
    public static Media menuselect = new Media(Sound.class.getResource("/sound/menuselect.mp3").toString());
    public static Media move = new Media(Sound.class.getResource("/sound/move.mp3").toString());
    public static Media gameover = new Media(Sound.class.getResource("/sound/gameover.mp3").toString());

    public static void playBackground(Media media) {
        backgroundPlayer = new MediaPlayer(media);
        backgroundPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        backgroundPlayer.play();
    }

    public static void playSound(Media media) {
        MediaPlayer soundPlayer = new MediaPlayer(media);
        soundPlayer.setVolume(soundVolume);
        soundPlayer.play();
    }
}

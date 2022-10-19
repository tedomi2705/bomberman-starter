package com.tedomi2705.bomberman;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Sound {
    public static MediaPlayer backgroundPlayer;
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

    public static MediaPlayer getBackgroundPlayer() {
        return backgroundPlayer;
    }

    public static void setBackgroundPlayer(MediaPlayer backgroundPlayer) {
        Sound.backgroundPlayer = backgroundPlayer;
    }

    public static double getBackgroundVolume() {
        return backgroundVolume;
    }

    public static void setBackgroundVolume(double backgroundVolume) {
        Sound.backgroundVolume = backgroundVolume;
    }

    public static double getSoundVolume() {
        return soundVolume;
    }

    public static void setSoundVolume(double soundVolume) {
        Sound.soundVolume = soundVolume;
    }

    public static Media getBackground() {
        return background;
    }

    public static void setBackground(Media background) {
        Sound.background = background;
    }

    public static Media getExplode() {
        return explode;
    }

    public static void setExplode(Media explode) {
        Sound.explode = explode;
    }

    public static Media getItem() {
        return item;
    }

    public static void setItem(Media item) {
        Sound.item = item;
    }

    public static Media getKillenemy() {
        return killenemy;
    }

    public static void setKillenemy(Media killenemy) {
        Sound.killenemy = killenemy;
    }

    public static Media getMenuselect() {
        return menuselect;
    }

    public static void setMenuselect(Media menuselect) {
        Sound.menuselect = menuselect;
    }

    public static Media getMove() {
        return move;
    }

    public static void setMove(Media move) {
        Sound.move = move;
    }

    public static Media getGameover() {
        return gameover;
    }

    public static void setGameover(Media gameover) {
        Sound.gameover = gameover;
    }
    
}

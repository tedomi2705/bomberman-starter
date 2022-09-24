package com.tedomi2705.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import com.tedomi2705.bomberman.entities.*;
import com.tedomi2705.bomberman.graphics.Sprite;
import static com.tedomi2705.bomberman.entities.Movable.DIRECTION.*;
import static com.tedomi2705.bomberman.EntitiesList.bomber;
import static com.tedomi2705.bomberman.EntitiesList.entities;

public class BombermanGame extends Application {

    public static final int WIDTH = 20;
    public static final int HEIGHT = 15;

    private GraphicsContext gc;
    private Canvas canvas;


    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) {
        // Tao Canvas
        Map.readMap();
        canvas = new Canvas(Sprite.SCALED_SIZE * Map.HEIGHT, Sprite.SCALED_SIZE * Map.WIDTH);
        gc = canvas.getGraphicsContext2D();

        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvas);

        // Tao scene
        Scene scene = new Scene(root);


        // Them scene vao stage
        stage.setScene(scene);
        stage.show();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                render();
                update();
            }
        };
        timer.start();


        bomber = new Bomber(1, 1, Sprite.player_right.getFxImage());
        // Handle input
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case LEFT -> {
                        bomber.setMoving(true);
                        bomber.setDirection(LEFT);
                    }
                    case RIGHT -> {
                        bomber.setMoving(true);
                        bomber.setDirection(RIGHT);
                    }
                    case UP -> {
                        bomber.setMoving(true);
                        bomber.setDirection(UP);
                    }
                    case DOWN -> {
                        bomber.setMoving(true);
                        bomber.setDirection(DOWN);
                    }
                    case SPACE -> {
                        // bomber.placeBomb();
                    }
                    case ESCAPE -> {
                        System.exit(0);
                    }
                    default -> {
                        // Do nothing;
                    }
                }
            }
        });
        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                bomber.setMoving(false);
            }
        });
        entities.add(bomber);
    }

    public void update() {
        EntitiesList.update();
    }

    public void render() {
        EntitiesList.render(gc, canvas);
    }
}

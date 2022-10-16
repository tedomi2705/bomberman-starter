package com.tedomi2705.bomberman;

import static com.tedomi2705.bomberman.EntitiesList.bomber;
import static com.tedomi2705.bomberman.EntitiesList.entities;
import com.tedomi2705.bomberman.entities.character.Bomber;
import com.tedomi2705.bomberman.graphics.Sprite;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class BombermanGame extends Application {

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


        // Handle input
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event){
                if (event.getCode().toString().equals("RIGHT")) {
                    bomber.setRightPressed(true);
                }
                if (event.getCode().toString().equals("LEFT")) {
                    bomber.setLeftPressed(true);
                }
                if (event.getCode().toString().equals("UP")) {
                    bomber.setUpPressed(true);
                }
                if (event.getCode().toString().equals("DOWN")) {
                    bomber.setDownPressed(true);
                }
                if (event.getCode().toString().equals("SPACE")) {
                    bomber.setSpacePressed(true);
                }

            }
        });
        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode().toString().equals("RIGHT")) {
                    bomber.setRightPressed(false);
                }
                if (event.getCode().toString().equals("LEFT")) {
                    bomber.setLeftPressed(false);
                }
                if (event.getCode().toString().equals("UP")) {
                    bomber.setUpPressed(false);
                }
                if (event.getCode().toString().equals("DOWN")) {
                    bomber.setDownPressed(false);
                }
                if (event.getCode().toString().equals("SPACE")) {
                    bomber.setSpacePressed(false);
                }
                
            }
        });
        bomber = new Bomber(1, 1, Sprite.player_right.getFxImage());
        entities.add(bomber);
    }

    public void update() {
        EntitiesList.update();
    }

    public void render() {
        EntitiesList.render(gc, canvas);
    }
}

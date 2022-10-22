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
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class BombermanGame extends Application {

    private GraphicsContext gc;
    private Canvas canvas;

    private static int score = 0;
    private Text scoreText = new Text("SCORE: " + score);
    private int bFSCoolDown = 60;

    public static void increaseScore(int amount) {
        score += amount;
    }

    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) {
        // Tao Canvas
        Map.readMap();
        canvas = new Canvas(Sprite.SCALED_SIZE * Map.HEIGHT, Sprite.SCALED_SIZE * (Map.WIDTH + 1));
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
            public void handle(KeyEvent event) {
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
        // score
        scoreText.setFont(Font.font("Berlin Sans FB Demi Bold", FontWeight.BOLD, 25));
        scoreText.setFill(Color.BLACK);
        scoreText.setX(/* Map.WIDTH * Sprite.SCALED_SIZE - */ 100);
        scoreText.setY(Map.WIDTH * (Sprite.SCALED_SIZE + 1) + Sprite.SCALED_SIZE / 2 - 5);
        System.err.println("text position: " + scoreText.getX() + " " + scoreText.getY());
        root.getChildren().add(scoreText);
        bomber = new Bomber(1, 1, Sprite.player_right.getFxImage());
        // entities.add(bomber);
    }

    public void update() {
        EntitiesList.update();
        if (bFSCoolDown > 0) {
            bFSCoolDown--;
        } else {
            Map.BFS(bomber.getGridX(), bomber.getGridY());
            bFSCoolDown = 60;
        }
        scoreText.setText("SCORE: " + score);
    }

    public void render() {
        EntitiesList.render(gc, canvas);
    }
}

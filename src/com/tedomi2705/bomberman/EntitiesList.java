package com.tedomi2705.bomberman;

import java.util.ArrayList;
import java.util.List;
import com.tedomi2705.bomberman.entities.*;
import com.tedomi2705.bomberman.entities.abstracts.Entity;
import com.tedomi2705.bomberman.entities.character.Bomber;
import com.tedomi2705.bomberman.entities.still.Brick;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class EntitiesList {
    public static Bomber bomber;
    public static List<Entity> entities = new ArrayList<>();
    public static List<Entity> stillObjects = new ArrayList<>();
    public static List<Brick> bricks = new ArrayList<>();

    public static void update() {
        bomber.update();
        entities.forEach(Entity::update);
        bricks.forEach(Brick::update);
    }

    public static void render(GraphicsContext gc, Canvas canvas) {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));
        bricks.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));
        bomber.render(gc);
    }
}

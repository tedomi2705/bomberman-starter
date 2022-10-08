package com.tedomi2705.bomberman;

import java.util.ArrayList;
import java.util.List;
import com.tedomi2705.bomberman.entities.Item;
import com.tedomi2705.bomberman.entities.abstracts.Entity;
import com.tedomi2705.bomberman.entities.character.Bomb;
import com.tedomi2705.bomberman.entities.character.Bomber;
import com.tedomi2705.bomberman.entities.character.Explosion;
import com.tedomi2705.bomberman.entities.still.Brick;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class EntitiesList {
    public static Bomber bomber;
    public static List<Entity> entities = new ArrayList<>();
    public static List<Entity> stillObjects = new ArrayList<>();
    public static List<Brick> bricks = new ArrayList<>();
    public static List<Bomb> bombs = new ArrayList<>();
    public static List<Explosion> explosions = new ArrayList<>();
    public static List<Item> items = new ArrayList<>();

    public static void update() {
        bomber.update();
        entities.forEach(Entity::update);
        bricks.forEach(Brick::update);
        explosions.forEach(Explosion::update);
        bombs.forEach(Bomb::update);
        bombs.removeIf(Bomb::isFullyExploded);
        explosions.removeIf(Explosion::isExploded);
        bricks.removeIf(Brick::isFullyDestroyed);
        items.forEach(Item::update);
        items.removeIf(Item::isEaten);
    }

    public static void render(GraphicsContext gc, Canvas canvas) {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));
        items.forEach(g -> g.render(gc));
        bricks.forEach(g -> g.render(gc));
        explosions.forEach(g -> g.render(gc));
        bombs.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));
        bomber.render(gc);
    }
}

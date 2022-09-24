package com.tedomi2705.bomberman;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;
import com.tedomi2705.bomberman.entities.*;
import com.tedomi2705.bomberman.graphics.Sprite;
import static com.tedomi2705.bomberman.EntitiesList.*;

public class Map {
    public static int WIDTH = 100;
    public static int HEIGHT = 100;

    public static final Entity[][] map = new Entity[HEIGHT][WIDTH];


    public static Entity getObjectAt(int i, int j) {
        return map[i][j];
    }

    public static void setObjectAt(int i, int j, Entity entity) {
        map[i][j] = entity;
    }

    public static void readMap() {

        Scanner scanner = null;
        try {
            InputStream is = Map.class.getResourceAsStream("/levels/Level1.txt");
            scanner = new Scanner(is);
        } catch (Exception e) {
            e.printStackTrace();
        }

        assert scanner != null;
        int levelId = scanner.nextInt();
        int numRow = scanner.nextInt();
        int numCol = scanner.nextInt();

        scanner.nextLine();
        for (int i = 0; i < numRow; ++i) {
            String s = scanner.nextLine();
            for (int j = 0; j < numCol; ++j) {
                Entity object;
                switch (s.charAt(j)) {
                    case '#' -> {
                        // TODO: Wall
                        object = new Wall(j, i, Sprite.wall.getFxImage());
                        stillObjects.add(object);
                    }
                    case '*', 'x', 'b', 's', 'f' -> {
                        // TODO: Brick
                        object = new Brick(j, i, Sprite.brick.getFxImage());
                        bricks.add((Brick) object);
                        object = new Grass(j, i, Sprite.grass.getFxImage());
                        stillObjects.add(object);
                    }
                    case 'p' -> {
                        bomber = new Bomber(j, i, Sprite.player_right.getFxImage());
                        object = new Grass(j, i, Sprite.grass.getFxImage());
                        stillObjects.add(object);
                    }
                    case '1' -> {
                        // TODO: Enemy 1
                        object = new Grass(j, i, Sprite.grass.getFxImage());
                        stillObjects.add(object);
                        object = new Enemy1(j, i, Sprite.mob_dead1.getFxImage());
                        entities.add(object);
                    }
                    case '2' -> {
                        // TODO: Enemy 2
                        object = new Grass(j, i, Sprite.grass.getFxImage());
                        stillObjects.add(object);
                        object = new Enemy1(j, i, Sprite.mob_dead1.getFxImage());
                        entities.add(object);
                    }
                    
                    default -> {
                        object = new Grass(j, i, Sprite.grass.getFxImage());
                        stillObjects.add(object);
                    }
                }
            }
        }
        HEIGHT = numCol;
        WIDTH = numRow;

    }

    public static boolean moveAble(int x, int y) {
        // TODO
        return true;
    }

}

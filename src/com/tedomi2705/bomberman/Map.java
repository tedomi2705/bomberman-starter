package com.tedomi2705.bomberman;

import static com.tedomi2705.bomberman.EntitiesList.bomber;
import static com.tedomi2705.bomberman.EntitiesList.bricks;
import static com.tedomi2705.bomberman.EntitiesList.entities;
import static com.tedomi2705.bomberman.EntitiesList.stillObjects;
import static com.tedomi2705.bomberman.EntitiesList.items;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.tedomi2705.bomberman.entities.Item;
import com.tedomi2705.bomberman.entities.abstracts.Entity;
import com.tedomi2705.bomberman.entities.character.Bomber;
import com.tedomi2705.bomberman.entities.enemies.Enemy1;
import com.tedomi2705.bomberman.entities.still.Brick;
import com.tedomi2705.bomberman.entities.still.Grass;
import com.tedomi2705.bomberman.entities.still.Wall;
import com.tedomi2705.bomberman.graphics.Sprite;

public class Map {
    public static int WIDTH;
    public static int HEIGHT;

    private static Entity[][] map;

    public static Entity getObjectAt(int i, int j) {
        return map[i][j];
    }

    public static void setObjectAt(int i, int j, Entity entity) {
        map[i][j] = entity;
    }

    // #region CollidingList
    private static List<Integer>[][] collidingList;

    public static final int OFFSET = 2;

    public static boolean isColliding(int x, int y, int x1, int y1) {
        return Math.max(0,
                Math.min(x + OFFSET, x1 + OFFSET) + Sprite.SCALED_SIZE - OFFSET
                        - Math.max(x + OFFSET, x1 + OFFSET))
                * Math.max(0, Math.min(y + OFFSET, y1 + OFFSET) + Sprite.SCALED_SIZE - OFFSET
                        - Math.max(y + OFFSET, y1 + OFFSET)) > 0;
    }

    private static void initializeCollidingList() {
        int numRow = HEIGHT * Sprite.SCALED_SIZE;
        int numCol = WIDTH * Sprite.SCALED_SIZE;
        System.err.println("Map.initializeCollidingList()");
        System.err.println("Map size: " + numRow + " " + numCol);
        collidingList = new ArrayList[numRow][numCol];
        for (int i = 0; i < numRow; i++) {
            for (int j = 0; j < numCol; j++) {
                collidingList[i][j] = new ArrayList<>();
                for (int m = 0; m < HEIGHT; m++) {
                    for (int n = 0; n < WIDTH; n++) {
                        if (isColliding(i, j, m * Sprite.SCALED_SIZE, n * Sprite.SCALED_SIZE)) {
                            collidingList[i][j].add(m * WIDTH + n);
                        }
                    }
                }
            }
        }
    }


    /**
     * @param x Coordinate x
     * @param y Coordinate y
     * @return {@code true} if the coordinate is moveable
     */
    public static boolean moveAble(int x, int y) {

        for (int k : collidingList[x][y]) {
            if (!(map[k / WIDTH][k % WIDTH] instanceof Grass)) {
                return false;
            }
        }


        return true;
    }
    // #endregion

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
        map = new Entity[numCol][numRow];
        scanner.nextLine();
        for (int j = 0; j < numRow; j++) {
            String s = scanner.nextLine();
            for (int i = 0; i < numCol; i++) {
                Entity object;
                switch (s.charAt(i)) {
                    case '#' -> {
                        object = new Wall(i, j, Sprite.wall.getFxImage());
                        stillObjects.add(object);
                        System.err.print("#");
                    }
                    case '*' -> {
                        object = new Grass(i, j, Sprite.grass.getFxImage());
                        stillObjects.add(object);
                        object = new Brick(i, j, Sprite.brick.getFxImage());
                        bricks.add((Brick) object);
                        System.err.print("*");
                    }
                    case 'x', 'b', 's', 'f' -> {
                        object = new Grass(i, j, Sprite.grass.getFxImage());
                        stillObjects.add(object);
                        Item.ITEM_TYPE type = Item.ITEM_TYPE.FLAME_ITEM;
                        switch (s.charAt(i)) {
                            case 'x' -> type = Item.ITEM_TYPE.PORTAL;
                            case 'b' -> type = Item.ITEM_TYPE.BOMB_ITEM;
                            case 's' -> type = Item.ITEM_TYPE.SPEED_ITEM;
                            case 'f' -> type = Item.ITEM_TYPE.FLAME_ITEM;
                        }
                        items.add(new Item(i, j, type));

                        object = new Brick(i, j, Sprite.brick.getFxImage());
                        bricks.add((Brick) object);

                    }
                    case 'p' -> {
                        bomber = new Bomber(i, j, Sprite.player_right.getFxImage());
                        object = new Grass(i, j, Sprite.grass.getFxImage());
                        stillObjects.add(object);
                        System.err.print("p");
                    }
                    case '1' -> {
                        // TODO: Enemy 1
                        object = new Grass(i, j, Sprite.grass.getFxImage());
                        stillObjects.add(object);
                        object = new Enemy1(i, j, Sprite.mob_dead1.getFxImage());
                        entities.add(object);
                        System.err.print("1");
                    }
                    case '2' -> {
                        // TODO: Enemy 2
                        object = new Grass(i, j, Sprite.grass.getFxImage());
                        stillObjects.add(object);
                        object = new Enemy1(i, j, Sprite.mob_dead1.getFxImage());
                        entities.add(object);
                        System.err.print("2");
                    }

                    default -> {
                        object = new Grass(i, j, Sprite.grass.getFxImage());
                        stillObjects.add(object);
                        System.err.print(" ");
                    }
                }
                map[i][j] = object;
            }
            System.err.println();
        }
        HEIGHT = numCol;
        WIDTH = numRow;
        initializeCollidingList();

    }

    public static boolean isTouching(int x, int y, int x1, int y1) {
        return Math.max(0, Math.min(x, x1) + Sprite.SCALED_SIZE - Math.max(x, x1))
                * Math.max(0, Math.min(y, y1) + Sprite.SCALED_SIZE - Math.max(y, y1)) > OFFSET
                        * Sprite.SCALED_SIZE;
    }

}

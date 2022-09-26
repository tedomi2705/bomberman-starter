package com.tedomi2705.bomberman;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.tedomi2705.bomberman.entities.*;
import com.tedomi2705.bomberman.graphics.Sprite;
import static com.tedomi2705.bomberman.EntitiesList.*;

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
            if (!(map[k % WIDTH][k / WIDTH] instanceof Grass)) {
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
        map = new Entity[numRow][numCol];
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
                        object = new Grass(j, i, Sprite.grass.getFxImage());
                        stillObjects.add(object);
                        object = new Brick(j, i, Sprite.brick.getFxImage());
                        bricks.add((Brick) object);
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
                map[i][j] = object;
            }
        }
        for (int k = 0; k < numRow; k++) {
            for (int l = 0; l < numCol; l++) {
                if (map[k][l] instanceof Brick) {
                    System.err.print("*");
                } else if (map[k][l] instanceof Wall) {
                    System.err.print("#");
                } else {
                    System.err.print(" ");
                }
            }
            System.err.println();
        }
        HEIGHT = numCol;
        WIDTH = numRow;
        initializeCollidingList();

    }


}

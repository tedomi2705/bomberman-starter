package com.tedomi2705.bomberman.entities.enemies;

import java.util.ArrayList;
import com.tedomi2705.bomberman.entities.abstracts.Enemy;
import com.tedomi2705.bomberman.graphics.Sprite;
import com.tedomi2705.bomberman.BombermanGame;
import com.tedomi2705.bomberman.Map;
import javafx.scene.image.Image;

public class Pontan extends Enemy {


    private static final int SCORE = 8000;

    public Pontan(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        this.left = Sprite.pontan_left1;
        this.left1 = Sprite.pontan_left2;
        this.left2 = Sprite.pontan_left3;
        this.right = Sprite.pontan_right1;
        this.right1 = Sprite.pontan_right2;
        this.right2 = Sprite.pontan_right3;
        this.deadSprite = Sprite.pontan_dead;

        movingAnimations[0] = this.left;
        movingAnimations[1] = this.left1;
        movingAnimations[2] = this.left2;

        this.setSpeed(3);
        this.setImg(this.left.getFxImage());

        //TODO Auto-generated constructor stub
    }

    @Override
    protected DIRECTION getBestDirection() {
        ArrayList<DIRECTION> directions = getMovableDirections();
        DIRECTION bfsDirection = Map.getBestDirection(this.getGridX(), this.getGridY());
        if (bfsDirection != null && directions.contains(bfsDirection)) {
            return bfsDirection;
        } else {
            return getRandomDirection();
        }
    }

    @Override
    protected boolean moveable(int x, int y) {
        return Map.kondoriaMoveAble(x, y);
    }

    @Override
    public void setDead(boolean dead) {
        // TODO Auto-generated method stub
        if (!this.dead)
            BombermanGame.increaseScore(SCORE);
        super.setDead(dead);
    }

}

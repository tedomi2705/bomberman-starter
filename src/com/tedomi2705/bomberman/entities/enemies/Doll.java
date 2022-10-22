package com.tedomi2705.bomberman.entities.enemies;

import java.util.ArrayList;
import com.tedomi2705.bomberman.entities.abstracts.Enemy;
import com.tedomi2705.bomberman.graphics.Sprite;
import com.tedomi2705.bomberman.BombermanGame;
import com.tedomi2705.bomberman.Map;
import javafx.scene.image.Image;

public class Doll extends Enemy {


    private static final int SCORE = 2000;

    public Doll(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        this.left = Sprite.doll_left1;
        this.left1 = Sprite.doll_left2;
        this.left2 = Sprite.doll_left3;
        this.right = Sprite.doll_right1;
        this.right1 = Sprite.doll_right2;
        this.right2 = Sprite.doll_right3;
        this.deadSprite = Sprite.doll_dead;

        movingAnimations[0] = this.left;
        movingAnimations[1] = this.left1;
        movingAnimations[2] = this.left2;

        this.setSpeed(2);
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
    public void setDead(boolean dead) {
        // TODO Auto-generated method stub
        if (!this.dead)
            BombermanGame.increaseScore(SCORE);
        super.setDead(dead);
    }

    
}

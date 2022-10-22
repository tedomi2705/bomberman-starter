package com.tedomi2705.bomberman.entities.enemies;

import java.util.ArrayList;
import com.tedomi2705.bomberman.entities.abstracts.Enemy;
import com.tedomi2705.bomberman.graphics.Sprite;
import com.tedomi2705.bomberman.BombermanGame;
import com.tedomi2705.bomberman.EntitiesList;
import com.tedomi2705.bomberman.Map;
import javafx.scene.image.Image;

public class Oneal extends Enemy {

    /**
     *
     */
    private static final int SCORE = 200;

    public Oneal(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        this.left = Sprite.oneal_left1;
        this.left1 = Sprite.oneal_left2;
        this.left2 = Sprite.oneal_left3;
        this.right = Sprite.oneal_right1;
        this.right1 = Sprite.oneal_right2;
        this.right2 = Sprite.oneal_right3;
        this.deadSprite = Sprite.oneal_dead;

        movingAnimations[0] = this.left;
        movingAnimations[1] = this.left1;
        movingAnimations[2] = this.left2;

        this.setSpeed(1);
        this.setImg(this.left.getFxImage());
        //TODO Auto-generated constructor stub
    }

    @Override
    protected DIRECTION getBestDirection() {
        if (this.getGridX() == EntitiesList.bomber.getGridX()) {
            if (this.getGridY() > EntitiesList.bomber.getGridY()) {
                return DIRECTION.UP;
            } else {
                return DIRECTION.DOWN;
            }
        } else if (this.getGridY() == EntitiesList.bomber.getGridY()) {
            if (this.getGridX() > EntitiesList.bomber.getGridX()) {
                return DIRECTION.LEFT;
            } else {
                return DIRECTION.RIGHT;
            }
        } else {
            return getRandomDirection();
        }
    }

    @Override
    public void setDead(boolean dead) {
        // TODO Auto-generated method stub
        if (!this.dead) BombermanGame.increaseScore(SCORE);
        super.setDead(dead);
    }

    
    
}

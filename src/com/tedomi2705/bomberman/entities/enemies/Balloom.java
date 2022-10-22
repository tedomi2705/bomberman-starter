package com.tedomi2705.bomberman.entities.enemies;

import com.tedomi2705.bomberman.BombermanGame;
import com.tedomi2705.bomberman.entities.abstracts.Enemy;
import com.tedomi2705.bomberman.entities.abstracts.Movable;
import com.tedomi2705.bomberman.graphics.Sprite;
import javafx.scene.image.Image;

public class Balloom extends Enemy {

    /**
     *
     */
    private static final int SCORE = 100;

    public Balloom(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        this.left = Sprite.balloom_left1;
        this.left1 = Sprite.balloom_left2;
        this.left2 = Sprite.balloom_left3;
        this.right = Sprite.balloom_right1;
        this.right1 = Sprite.balloom_right2;
        this.right2 = Sprite.balloom_right3;
        this.deadSprite = Sprite.balloom_dead;

        movingAnimations[0] = this.left;
        movingAnimations[1] = this.left1;
        movingAnimations[2] = this.left2;

        this.setSpeed(1);
        this.setImg(this.left.getFxImage());
        // TODO Auto-generated constructor stub
    }

    @Override
    protected DIRECTION getBestDirection() {
        return getRandomDirection();
        // TODO Auto-generated method stub
    }

    @Override
    public void setDead(boolean dead) {
        // TODO Auto-generated method stub
        if (!this.dead)
            BombermanGame.increaseScore(SCORE);
        super.setDead(dead);
    }

}

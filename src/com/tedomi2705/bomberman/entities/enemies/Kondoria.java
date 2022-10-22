package com.tedomi2705.bomberman.entities.enemies;

import java.util.ArrayList;
import com.tedomi2705.bomberman.entities.abstracts.Enemy;
import com.tedomi2705.bomberman.graphics.Sprite;
import com.tedomi2705.bomberman.BombermanGame;
import com.tedomi2705.bomberman.Map;
import javafx.scene.image.Image;

public class Kondoria extends Enemy {


    private static final int SCORE = 500;

    public Kondoria(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        this.left = Sprite.kondoria_left1;
        this.left1 = Sprite.kondoria_left2;
        this.left2 = Sprite.kondoria_left3;
        this.right = Sprite.kondoria_right1;
        this.right1 = Sprite.kondoria_right2;
        this.right2 = Sprite.kondoria_right3;
        this.deadSprite = Sprite.kondoria_dead;

        movingAnimations[0] = this.left;
        movingAnimations[1] = this.left1;
        movingAnimations[2] = this.left2;

        this.setSpeed(1);
        this.setImg(this.left.getFxImage());

        //TODO Auto-generated constructor stub
    }

    @Override
    protected DIRECTION getBestDirection() {
        return getRandomDirection();
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

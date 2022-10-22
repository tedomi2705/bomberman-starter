package com.tedomi2705.bomberman.entities.enemies;

import com.tedomi2705.bomberman.BombermanGame;
import com.tedomi2705.bomberman.EntitiesList;
import com.tedomi2705.bomberman.entities.abstracts.Enemy;
import com.tedomi2705.bomberman.graphics.Sprite;
import javafx.scene.control.skin.TextInputControlSkin.Direction;
import javafx.scene.image.Image;

public class Ovape extends Enemy {

    public static final int SCORE = 2000;
    public Ovape(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        this.left = Sprite.ovape_left1;
        this.left1 = Sprite.ovape_left2;
        this.left2 = Sprite.ovape_left3;
        this.right = Sprite.ovape_right1;
        this.right1 = Sprite.ovape_right2;
        this.right2 = Sprite.ovape_right3;
        this.deadSprite = Sprite.ovape_dead;

        movingAnimations[0] = this.left;
        movingAnimations[1] = this.left1;
        movingAnimations[2] = this.left2;

        this.setSpeed(1);
        this.setImg(this.left.getFxImage());

        // TODO Auto-generated constructor stub
    }

    @Override
    protected DIRECTION getBestDirection() {
        // TODO Auto-generated method stub
        return getRandomDirection();
    }

    @Override
    protected boolean moveable(int x, int y) {
        return true;
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub
        if (isTouchingExplosion()) {
            setDead(true);
        }
        if (!isDead()) {
            updateSpeed();
            if (this.getGridX() > EntitiesList.bomber.getGridX()
                    && this.getGridY() > EntitiesList.bomber.getGridY()) {
                setDirection(DIRECTION.UPLEFT);
            } else if (this.getGridX() > EntitiesList.bomber.getGridX()
                    && this.getGridY() < EntitiesList.bomber.getGridY()) {
                setDirection(DIRECTION.DOWNLEFT);
            } else if (this.getGridX() < EntitiesList.bomber.getGridX()
                    && this.getGridY() > EntitiesList.bomber.getGridY()) {
                setDirection(DIRECTION.UPRIGHT);
            } else if (this.getGridX() < EntitiesList.bomber.getGridX()
                    && this.getGridY() < EntitiesList.bomber.getGridY()) {
                setDirection(DIRECTION.DOWNRIGHT);
            } else if (this.getGridX() > EntitiesList.bomber.getGridX()) {
                setDirection(DIRECTION.LEFT);
            } else if (this.getGridX() < EntitiesList.bomber.getGridX()) {
                setDirection(DIRECTION.RIGHT);
            } else if (this.getGridY() > EntitiesList.bomber.getGridY()) {
                setDirection(DIRECTION.UP);
            } else if (this.getGridY() < EntitiesList.bomber.getGridY()) {
                setDirection(DIRECTION.DOWN);
            } 

            updateImage();
        }
        animate();
    }

    public void setDead(boolean dead) {
        // TODO Auto-generated method stub
        if (!this.dead) BombermanGame.increaseScore(SCORE);
        super.setDead(dead);
    }


}

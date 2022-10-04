package com.tedomi2705.bomberman.entities.abstracts;

import com.tedomi2705.bomberman.Map;
import javafx.scene.image.Image;

public abstract class Movable extends Entity {
    public static enum DIRECTION {
        UP, DOWN, LEFT, RIGHT, UPLEFT, UPRIGHT, DOWNLEFT, DOWNRIGHT, STOP
    }

    public final static int MAX_STEP = 0xBADC0DE;;

    protected int speed;
    protected boolean isMoving;
    protected DIRECTION direction;

    protected int animationStep;

    public Movable(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        direction = DIRECTION.STOP;
        speed = 2;
    }

    public void moveLeft() {
        if (!isMoving() || !moveable(x - speed, y))
            return;
        this.setX(x - speed);
    }

    public void moveRight() {
        if (!isMoving() || !moveable(x + speed, y))
            return;
        this.setX(x + speed);
    }

    public void moveUp() {
        if (!isMoving() || !moveable(x, y - speed))
            return;
        this.setY(y - speed);
    }


    public void moveDown() {
        if (!isMoving() || !moveable(x, y + speed))
            return;
        this.setY(y + speed);
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public boolean isMoving() {
        return isMoving;
    }

    public void setMoving(boolean isMoving) {
        this.isMoving = isMoving;
    }

    public DIRECTION getDirection() {
        return direction;
    }

    public void setDirection(DIRECTION direction) {
        this.direction = direction;
    }

    public int getAnimationStep() {
        return animationStep;
    }

    public void setAnimationStep(int animationStep) {
        this.animationStep = animationStep;
    }

    public void animate() {
        if (this.animationStep == MAX_STEP) {
            this.animationStep = 0;
        } else {
            this.animationStep = this.animationStep + 1;
        }
    }

    protected boolean moveable(int x, int y) {
        return Map.moveAble(x, y);
    }
}
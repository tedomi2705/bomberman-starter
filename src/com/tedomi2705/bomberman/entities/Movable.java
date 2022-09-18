package com.tedomi2705.bomberman.entities;

import javafx.scene.image.Image;

public abstract class Movable extends Entity {
    public Movable(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        direction = DIRECTION.STOP;
        speed = 2;
    }

    public static enum DIRECTION {
        UP, DOWN, LEFT, RIGHT, STOP
    };

    protected int speed;
    protected boolean isMoving;
    protected DIRECTION direction;

    protected int animationStep;

    public final static int MAX_STEP = 0xBADC0DE;

    public void moveLeft() {
        if (!isMoving())
            return;
        this.setX(x - speed);
    }

    public void moveRight() {
        if (!isMoving())
            return;
        this.setX(x + speed);
    }

    public void moveUp() {
        if (!isMoving())
            return;
        this.setY(y - speed);
    }


    public void moveDown() {
        if (!isMoving())
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
}

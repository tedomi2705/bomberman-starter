package com.tedomi2705.bomberman.entities.abstracts;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Random;
import com.tedomi2705.bomberman.Sound;
import com.tedomi2705.bomberman.graphics.Sprite;

public abstract class Enemy extends Movable {
    private static final int DIRECTION_CHANGING_TIME = 100;
    private int directionChangeCountdown;
    private final Random random = new Random();

    protected Sprite left;
    protected Sprite left1;
    protected Sprite left2;

    protected Sprite right;
    protected Sprite right1;
    protected Sprite right2;

    protected Sprite[] movingAnimations = new Sprite[3];

    protected Sprite deadSprite;

    public Enemy(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        this.setMoving(true);
    }

    @Override
    public void update() {
        if (isTouchingExplosion()) {
            setDead(true);
        }
        if (!isDead()) {
            updateSpeed();

            setDirection(getBestDirection());
            updateImage();
        }
        animate();
    }

    @Override
    public void updateImage() {
        if (direction == DIRECTION.LEFT || direction == DIRECTION.UPLEFT || direction == DIRECTION.DOWNLEFT) {
            movingAnimations[0] = left;
            movingAnimations[1] = left1;
            movingAnimations[2] = left2;
        } else if (direction == DIRECTION.RIGHT || direction == DIRECTION.UPRIGHT || direction == DIRECTION.DOWNRIGHT) {
            movingAnimations[0] = right;
            movingAnimations[1] = right1;
            movingAnimations[2] = right2;
        }
        this.setImg(Sprite.movingSprite(movingAnimations[0], movingAnimations[1],
                movingAnimations[2], getAnimationStep(), Sprite.ANIMATING_CYCLE * 3).getFxImage());
        switch (direction) {
            case UP -> this.moveUp();
            case DOWN -> this.moveDown();
            case LEFT -> this.moveLeft();
            case RIGHT -> this.moveRight();
            case UPLEFT -> {
                this.moveUp();
                this.moveLeft();
            }
            case UPRIGHT -> {
                this.moveUp();
                this.moveRight();
            }
            case DOWNLEFT -> {
                this.moveDown();
                this.moveLeft();
            }
            case DOWNRIGHT -> {
                this.moveDown();
                this.moveRight();
            }
        }
    }

    protected ArrayList<DIRECTION> getMovableDirections() {
        ArrayList<DIRECTION> directions = new ArrayList<>();
        directions.add(DIRECTION.STOP);
        if (moveable(x, y - speed))
            directions.add(DIRECTION.UP);
        if (moveable(x, y + speed))
            directions.add(DIRECTION.DOWN);
        if (moveable(x - speed, y))
            directions.add(DIRECTION.LEFT);
        if (moveable(x + speed, y))
            directions.add(DIRECTION.RIGHT);
        return directions;
    }

    protected void updateSpeed() {

    }

    @Override
    public void setDead(boolean dead) {
        if (!this.dead)
            Sound.playSound(Sound.killenemy);
        this.dead = true;
        deadTime = 40;
        this.setImg(this.deadSprite.getFxImage());
    }

    protected abstract DIRECTION getBestDirection();

    protected DIRECTION getRandomDirection() {
        ArrayList<DIRECTION> directions = getMovableDirections();
        if (direction != DIRECTION.STOP && directions.contains(direction)
                && directionChangeCountdown > 0) {
            directionChangeCountdown--;
            return direction;
        } else {
            directionChangeCountdown = DIRECTION_CHANGING_TIME;
            return directions.get(Math.abs(random.nextInt()) % directions.size());
        }
    }
}

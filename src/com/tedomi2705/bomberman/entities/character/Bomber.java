package com.tedomi2705.bomberman.entities.character;

import static com.tedomi2705.bomberman.entities.abstracts.Movable.DIRECTION.*;
import org.apache.logging.log4j.Logger;
import com.tedomi2705.bomberman.EntitiesList;
import com.tedomi2705.bomberman.Map;
import com.tedomi2705.bomberman.entities.abstracts.Movable;
import com.tedomi2705.bomberman.entities.still.Grass;
import com.tedomi2705.bomberman.graphics.Sprite;
import javafx.scene.image.Image;

public class Bomber extends Movable {
    private boolean upPressed;
    private boolean downPressed;
    private boolean leftPressed;
    private boolean rightPressed;
    private boolean spacePressed;

    private int bombLimit = 1;
    private int bombLength = 2;

    private static Logger logger = org.apache.logging.log4j.LogManager.getLogger(Bomber.class);

    public Bomber(int x, int y, Image img) {
        super(x, y, img);
        this.setMoving(false);
        this.setDirection(DIRECTION.STOP);
    }

    @Override
    public void update() {
        if (isTouchingExplosion()) {
            setDead(true);
            logger.info("Player died");
        }
        // if (dead) {
        // updateImage();
        // return;
        // }
        final boolean moveUp = upPressed && (!upPressed || !downPressed);
        final boolean moveDown = downPressed && (!upPressed || !downPressed);
        final boolean moveLeft = leftPressed && (!leftPressed || !rightPressed);
        final boolean moveRight = rightPressed && (!leftPressed || !rightPressed);
        final boolean moveUpLeft = (upPressed && leftPressed) && (!upPressed || !downPressed)
                && (!leftPressed || !rightPressed);
        final boolean moveUpRight = (upPressed && rightPressed) && (!upPressed || !downPressed)
                && (!leftPressed || !rightPressed);
        final boolean moveDownLeft = (downPressed && leftPressed) && (!upPressed || !downPressed)
                && (!leftPressed || !rightPressed);
        final boolean moveDownRight = (downPressed && rightPressed) && (!upPressed || !downPressed)
                && (!leftPressed || !rightPressed);
        if (moveUp) {
            setMoving(true);
            this.setDirection(UP);
        }
        if (moveDown) {
            setMoving(true);
            this.setDirection(DOWN);
        }
        if (moveLeft) {
            setMoving(true);
            this.setDirection(LEFT);
        }
        if (moveRight) {
            setMoving(true);
            this.setDirection(RIGHT);
        }
        if (moveUpLeft) {
            setMoving(true);
            this.setDirection(UPLEFT);
        }
        if (moveUpRight) {
            setMoving(true);
            this.setDirection(UPRIGHT);
        }
        if (moveDownLeft) {
            setMoving(true);
            this.setDirection(DOWNLEFT);
        }
        if (moveDownRight) {
            setMoving(true);
            this.setDirection(DOWNRIGHT);
        }
        if (!moveUp && !moveDown && !moveLeft && !moveRight && !moveUpLeft && !moveUpRight
                && !moveDownLeft && !moveDownRight) {
            setMoving(false);
        }
        if (spacePressed) {
            placeBomb();
        }
        updateImage();
        animate();
    }

    @Override
    public void updateImage() {
        if(isFullyDead()){
            setImg(null);
                    
            return;
        }
        if (dead) {
            setImg(Sprite.movingSprite(Sprite.player_dead1, Sprite.player_dead2,
                    Sprite.player_dead3, this.animationStep, Sprite.ANIMATING_CYCLE * 3)
                    .getFxImage());
            return;
        }
        Sprite sprite;
        switch (getDirection()) {
            case LEFT -> {
                if (isMoving()) {
                    moveLeft();
                    sprite = Sprite.movingSprite(Sprite.player_left_1, Sprite.player_left_2,
                            getAnimationStep(), Sprite.ANIMATING_CYCLE);
                } else {
                    sprite = Sprite.player_left;
                }
            }
            case UPLEFT -> {
                if (isMoving()) {
                    moveLeft();
                    moveUp();
                    sprite = Sprite.movingSprite(Sprite.player_left_1, Sprite.player_left_2,
                            getAnimationStep(), Sprite.ANIMATING_CYCLE);
                } else {
                    sprite = Sprite.player_left;
                }
            }
            case DOWNLEFT -> {
                if (isMoving()) {
                    moveLeft();
                    moveDown();
                    sprite = Sprite.movingSprite(Sprite.player_left_1, Sprite.player_left_2,
                            getAnimationStep(), Sprite.ANIMATING_CYCLE);
                } else {
                    sprite = Sprite.player_left;
                }
            }
            case RIGHT -> {
                if (isMoving()) {
                    moveRight();
                    sprite = Sprite.movingSprite(Sprite.player_right_1, Sprite.player_right_2,
                            getAnimationStep(), Sprite.ANIMATING_CYCLE);
                } else {
                    sprite = Sprite.player_right;
                }
            }
            case UPRIGHT -> {
                if (isMoving()) {
                    moveRight();
                    moveUp();
                    sprite = Sprite.movingSprite(Sprite.player_right_1, Sprite.player_right_2,
                            getAnimationStep(), Sprite.ANIMATING_CYCLE);
                } else {
                    sprite = Sprite.player_right;
                }
            }
            case DOWNRIGHT -> {
                if (isMoving()) {
                    moveRight();
                    moveDown();
                    sprite = Sprite.movingSprite(Sprite.player_right_1, Sprite.player_right_2,
                            getAnimationStep(), Sprite.ANIMATING_CYCLE);
                } else {
                    sprite = Sprite.player_right;
                }
            }
            case UP -> {
                if (isMoving()) {
                    moveUp();
                    sprite = Sprite.movingSprite(Sprite.player_up_1, Sprite.player_up_2,
                            getAnimationStep(), Sprite.ANIMATING_CYCLE);
                } else {
                    sprite = Sprite.player_up;
                }
            }
            case DOWN -> {
                if (isMoving()) {
                    moveDown();
                    sprite = Sprite.movingSprite(Sprite.player_down_1, Sprite.player_down_2,
                            getAnimationStep(), Sprite.ANIMATING_CYCLE);
                } else {
                    sprite = Sprite.player_down;
                }
            }
            default -> sprite = Sprite.player_right;
        }
        setImg(sprite.getFxImage());
    }

    public boolean isUpPressed() {
        return upPressed;
    }

    public void setUpPressed(boolean upPressed) {
        this.upPressed = upPressed;
    }

    public boolean isDownPressed() {
        return downPressed;
    }

    public void setDownPressed(boolean downPressed) {
        this.downPressed = downPressed;
    }

    public boolean isLeftPressed() {
        return leftPressed;
    }

    public void setLeftPressed(boolean leftPressed) {
        this.leftPressed = leftPressed;
    }

    public boolean isRightPressed() {
        return rightPressed;
    }

    public void setRightPressed(boolean rightPressed) {
        this.rightPressed = rightPressed;
    }

    public boolean isSpacePressed() {
        return spacePressed;
    }

    public void setSpacePressed(boolean spacePressed) {
        this.spacePressed = spacePressed;
    }

    public void placeBomb() {
        int bombCount = EntitiesList.bombs.size();
        if (bombCount < bombLimit && isBombPlacable()) {
            Bomb bomb = new Bomb(getGridX(), getGridY(), Sprite.bomb.getFxImage());
            logger.info("Bomb placed at (" + bomb.getGridX() + ";" + bomb.getGridY() + ")");
            EntitiesList.bombs.add(bomb);
            bombCount++;
        }
    }

    private boolean isBombPlacable() {
        for (Bomb bomb : EntitiesList.bombs) {
            if (bomb.getGridX() == getGridX() && bomb.getGridY() == getGridY()) {
                return false;
            }
        }
        return Map.getObjectAt(getGridX(), getGridY()) instanceof Grass;
    }

    public void increaseBombLimit() {
        bombLimit++;
    }

    public void increaseBombLength() {
        bombLength++;
    }

    public void increaseSpeed() {
        speed++;
    }

    public int getBombLimit() {
        return bombLimit;
    }

    public void setBombLimit(int bombLimit) {
        this.bombLimit = bombLimit;
    }

    public int getBombLength() {
        return bombLength;
    }

    public void setBombLength(int bombLength) {
        this.bombLength = bombLength;
    }

    public int getSpeed() {
        return speed;
    }

    @Override
    public void setDead(boolean dead) {
        // TODO Auto-generated method stub
        super.setDead(dead);
        if (dead) {
            deadTime = 24;
        }

    }


}

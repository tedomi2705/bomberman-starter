package com.tedomi2705.bomberman.entities.character;

import static com.tedomi2705.bomberman.entities.abstracts.Movable.DIRECTION.*;
import com.tedomi2705.bomberman.EntitiesList;
import com.tedomi2705.bomberman.entities.abstracts.Movable;
import com.tedomi2705.bomberman.graphics.Sprite;
import javafx.scene.image.Image;

public class Bomber extends Movable {
    private boolean upPressed;
    private boolean downPressed;
    private boolean leftPressed;
    private boolean rightPressed;
    private boolean spacePressed;
    private int bombLimit = 1;

    public Bomber(int x, int y, Image img) {
        super(x, y, img);
        this.setMoving(false);
        this.setDirection(DIRECTION.STOP);
    }

    @Override
    public void update() {
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
        if (bombCount < bombLimit) {
            Bomb bomb = new Bomb(getGridX(), getGridY(), Sprite.bomb.getFxImage());
            EntitiesList.bombs.add(bomb);
            bombCount++;
        }
    }

    public void increaseBombLimit() {
        bombLimit++;
    }


}

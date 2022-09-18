package com.tedomi2705.bomberman.entities;

import com.tedomi2705.bomberman.graphics.Sprite;
import javafx.scene.image.Image;

public class Bomber extends Movable {

    public Bomber(int x, int y, Image img) {
        super(x, y, img);
        this.setMoving(false);
        this.setDirection(DIRECTION.STOP);
    }

    @Override
    public void update() {
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
            case RIGHT -> {
                if (isMoving()) {
                    moveRight();
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

}

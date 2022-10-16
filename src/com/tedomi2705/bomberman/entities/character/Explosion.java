package com.tedomi2705.bomberman.entities.character;

import com.tedomi2705.bomberman.Map;
import com.tedomi2705.bomberman.entities.abstracts.Entity;
import com.tedomi2705.bomberman.graphics.Sprite;
import javafx.scene.image.Image;

/**
 * Explosion
 */
public class Explosion extends Entity {

    public Explosion(int xUnit, int yUnit, Image img, EXPLOSION_DIRECTION direction) {
        super(xUnit, yUnit, img);
        this.direction = direction;
    }

    public enum EXPLOSION_DIRECTION {
        VERTICAL, HORIZONTAL, TOP, DOWN, LEFT, RIGHT
    }

    EXPLOSION_DIRECTION direction;
    int animationStep = 0;

    @Override
    public void update() {
        updateImage();

    }

    @Override
    public void updateImage() {
        if (isExploded()) {
            return;
        }
        setImg(getExplosionSprite().getFxImage());
        animationStep++;


    }

    public boolean isExploded() {
        return animationStep > Sprite.ANIMATING_CYCLE;
    }

    private Sprite getExplosionSprite() {
        switch (direction) {
            case VERTICAL:
                return Sprite.movingSprite(Sprite.explosion_vertical, Sprite.explosion_vertical1,
                        Sprite.explosion_vertical2, animationStep, Sprite.ANIMATING_CYCLE);
            case HORIZONTAL:
                return Sprite.movingSprite(Sprite.explosion_horizontal,
                        Sprite.explosion_horizontal1, Sprite.explosion_horizontal2, animationStep,
                        Sprite.ANIMATING_CYCLE);
            case TOP:
                return Sprite.movingSprite(Sprite.explosion_vertical_top_last,
                        Sprite.explosion_vertical_top_last1, Sprite.explosion_vertical_top_last2,
                        animationStep, Sprite.ANIMATING_CYCLE);
            case DOWN:
                return Sprite.movingSprite(Sprite.explosion_vertical_down_last,
                        Sprite.explosion_vertical_down_last1, Sprite.explosion_vertical_down_last2,
                        animationStep, Sprite.ANIMATING_CYCLE);
            case LEFT:
                return Sprite.movingSprite(Sprite.explosion_horizontal_left_last,
                        Sprite.explosion_horizontal_left_last1,
                        Sprite.explosion_horizontal_left_last2, animationStep,
                        Sprite.ANIMATING_CYCLE);
            case RIGHT:
                return Sprite.movingSprite(Sprite.explosion_horizontal_right_last,
                        Sprite.explosion_horizontal_right_last1,
                        Sprite.explosion_horizontal_right_last2, animationStep,
                        Sprite.ANIMATING_CYCLE);

            default:
                return null;
        }
    }

    public boolean isTouching(Entity entity) {
        return Map.isTouching(this.getX(), this.getY(), entity.getX(), entity.getY());
    }
}

package com.tedomi2705.bomberman.entities.still;

import com.tedomi2705.bomberman.Map;
import com.tedomi2705.bomberman.entities.abstracts.Entity;
import com.tedomi2705.bomberman.graphics.Sprite;
import javafx.scene.image.Image;

public class Brick extends Entity {
    public Brick(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        destroyed = false;
    }

    private int animationStep;
    private boolean destroyed;

    @Override
    public void update() {
        updateImage();

    }


    @Override
    public void updateImage() {
        if (isFullyDestroyed())
            return;
        if (isDestroyed()) {
            this.setImg(Sprite
                    .movingSprite(Sprite.brick_exploded, Sprite.brick_exploded1,
                            Sprite.brick_exploded2, animationStep, Sprite.ANIMATING_CYCLE)
                    .getFxImage());
            animationStep--;
            if (animationStep == 0) {
                Map.setObjectAt(this.getGridX(), this.getGridY(),
                        new Grass(this.getGridX(), this.getGridY(), Sprite.grass.getFxImage()));
            }
        }

    }


    public int getAnimationStep() {
        return animationStep;
    }


    public void setAnimationStep(int animationStep) {
        this.animationStep = animationStep;
    }


    public boolean isDestroyed() {
        return destroyed;
    }


    public void setDestroyed(boolean destroyed) {
        this.destroyed = destroyed;
        if (destroyed)
            animationStep = Sprite.ANIMATING_CYCLE;
    }

    public boolean isFullyDestroyed() {
        return destroyed && animationStep <= 0;
    }
}


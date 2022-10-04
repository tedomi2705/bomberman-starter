package com.tedomi2705.bomberman.entities.character;

import com.tedomi2705.bomberman.EntitiesList;
import com.tedomi2705.bomberman.Map;
import com.tedomi2705.bomberman.entities.abstracts.Entity;
import com.tedomi2705.bomberman.entities.still.Brick;
import com.tedomi2705.bomberman.entities.still.Wall;
import com.tedomi2705.bomberman.graphics.Sprite;
import javafx.scene.image.Image;

public class Bomb extends Entity {
    public static final int TICKING_CYCLE = 90;
    public static final int EXPLODING_CYCLE = 20;

    int length = 2;
    int animationStep;
    boolean exploded;

    public Bomb(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        animationStep = 0;
    }

    @Override
    public void update() {
        updateImage();
    }

    @Override
    public void updateImage() {
        if (!isExploded()) {
            setImg(Sprite.movingSprite(Sprite.bomb, Sprite.bomb_1, Sprite.bomb_2, animationStep,
                    Sprite.ANIMATING_CYCLE).getFxImage());
            animationStep++;
            if (animationStep == TICKING_CYCLE) {
                this.setExploded(true);
            }
        } else {
            if (animationStep == EXPLODING_CYCLE) {
                return;
            }
            setImg(Sprite
                    .movingSprite(Sprite.bomb_exploded, Sprite.bomb_exploded1,
                            Sprite.bomb_exploded2, animationStep, Sprite.ANIMATING_CYCLE)
                    .getFxImage());
            animationStep++;
        }
    }

    public int getAnimationStep() {
        return animationStep;
    }

    public void setAnimationStep(int animationStep) {
        this.animationStep = animationStep;
    }

    public boolean isExploded() {
        return exploded;
    }

    public void setExploded(boolean exploded) {
        this.exploded = true;
        animationStep = 0;

        int sx = this.getGridX(), sy = this.getGridY();

        for (int i = 1; i < length; ++i) {
            int nx = sx;
            int ny = sy - i;
            if (explosionBlock(nx, ny))
                break;
            EntitiesList.explosions.add(
                    new Explosion(nx, ny, null, i == length - 1 ? Explosion.EXPLOSION_DIRECTION.TOP
                            : Explosion.EXPLOSION_DIRECTION.VERTICAL));
        }

        for (int i = 1; i < length; ++i) {
            int nx = sx;
            int ny = sy + i;
            if (explosionBlock(nx, ny))
                break;
            EntitiesList.explosions.add(
                    new Explosion(nx, ny, null, i == length - 1 ? Explosion.EXPLOSION_DIRECTION.DOWN
                            : Explosion.EXPLOSION_DIRECTION.VERTICAL));
        }

        for (int i = 1; i < length; ++i) {
            int nx = sx - i;
            int ny = sy;
            if (explosionBlock(nx, ny))
                break;
            EntitiesList.explosions.add(
                    new Explosion(nx, ny, null, i == length - 1 ? Explosion.EXPLOSION_DIRECTION.LEFT
                            : Explosion.EXPLOSION_DIRECTION.HORIZONTAL));
        }

        for (int i = 1; i < length; ++i) {
            int nx = sx + i;
            int ny = sy;
            if (explosionBlock(nx, ny))
                break;
            EntitiesList.explosions.add(new Explosion(nx, ny, null,
                    i == length - 1 ? Explosion.EXPLOSION_DIRECTION.RIGHT
                            : Explosion.EXPLOSION_DIRECTION.HORIZONTAL));
        }
    }

    private boolean explosionBlock(int x, int y) {
        Entity entity = Map.getObjectAt(x, y);
        if (entity instanceof Wall) {
            return true;
        }
        if (entity instanceof Brick) {
            ((Brick) entity).setDestroyed(true);
            return true;
        }
        return false;
    }

    public boolean isFullyExploded() {
        return isExploded() && animationStep >= EXPLODING_CYCLE;
    }
}

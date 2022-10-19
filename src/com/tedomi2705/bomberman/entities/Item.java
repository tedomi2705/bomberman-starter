package com.tedomi2705.bomberman.entities;

import static com.tedomi2705.bomberman.EntitiesList.bomber;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.tedomi2705.bomberman.entities.abstracts.Entity;
import com.tedomi2705.bomberman.graphics.Sprite;
import com.tedomi2705.bomberman.sound.Sound;

public class Item extends Entity {
    public static enum ITEM_TYPE {
        BOMB_ITEM, FLAME_ITEM, SPEED_ITEM, PORTAL
    }

    private boolean eaten;
    private final ITEM_TYPE type;

    private static Logger logger = LogManager.getLogger(Item.class);

    public Item(int xUnit, int yUnit, ITEM_TYPE type) {
        super(xUnit, yUnit, null);
        switch (type) {
            case BOMB_ITEM:
                setImg(Sprite.powerup_bombs.getFxImage());
                break;
            case FLAME_ITEM:
                setImg(Sprite.powerup_flames.getFxImage());
                break;
            case SPEED_ITEM:
                setImg(Sprite.powerup_speed.getFxImage());
                break;
            case PORTAL:
                setImg(Sprite.portal.getFxImage());
                break;
        }
        this.type = type;
    }

    @Override
    public void update() {
        if (this.isEaten())
            return;

        if (bomber.getGridX() == this.getGridX() && bomber.getGridY() == this.getGridY()) {
            if (type != ITEM_TYPE.PORTAL)
                Sound.playSound(Sound.item);
            switch (type) {
                case BOMB_ITEM -> {
                    bomber.increaseBombLimit();
                    logger.info("Bomber picked up a bomb item");
                    logger.info("Bomber's bomb limit is now " + bomber.getBombLimit());
                }
                case FLAME_ITEM -> {
                    bomber.increaseBombLength();
                    logger.info("Bomber picked up a flame item");
                    logger.info("Bomber's bomb length is now " + bomber.getBombLength());
                }
                case SPEED_ITEM -> {
                    bomber.increaseSpeed();
                    logger.info("Bomber picked up a speed item");
                    logger.info("Bomber's speed is now " + bomber.getSpeed());
                }
                case PORTAL -> {
                    // TODO Portal
                    return;
                }
            }
            this.setEaten(true);
            this.setImg(null);
        }
    }

    @Override
    public void updateImage() {
        // Hmm I don't think we need this

    }

    public boolean isEaten() {
        return eaten;
    }

    public void setEaten(boolean eaten) {
        this.eaten = eaten;
    }

    public ITEM_TYPE getType() {
        return type;
    }

}

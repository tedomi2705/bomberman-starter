package com.tedomi2705.bomberman.entities;

import static com.tedomi2705.bomberman.EntitiesList.bomber;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.tedomi2705.bomberman.BombermanGame;
import com.tedomi2705.bomberman.EntitiesList;
import com.tedomi2705.bomberman.Map;
import com.tedomi2705.bomberman.Sound;
import com.tedomi2705.bomberman.entities.abstracts.Entity;
import com.tedomi2705.bomberman.entities.character.Bomber;
import com.tedomi2705.bomberman.graphics.Sprite;

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
                    if (EntitiesList.entities.isEmpty()) {
                        logger.info("Bomber picked up a portal item");
                        logger.info("Bomber is now in the next level");
                        EntitiesList.bomber.setMoving(false);
                        EntitiesList.bomber.update();
                        if(BombermanGame.level == BombermanGame.LEVEL_AMOUNT) {
                            BombermanGame.level = 1;
                        } else {
                            BombermanGame.level++;
                        }
                        EntitiesList.bomber = new Bomber(1, 1, Sprite.player_right.getFxImage());
                        EntitiesList.entities.add(EntitiesList.bomber);
                        Sound.playSound(Sound.lvlup);
                        Map.readMap(BombermanGame.level);
                    }
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

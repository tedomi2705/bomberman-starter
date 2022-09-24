package com.tedomi2705.bomberman.entities;

import javafx.scene.image.Image;

public class Brick extends Entity {
    public Brick(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        isDestroyed = false;
    }

    private int animationStep;
    private boolean isDestroyed;
    
    @Override
    public void update() {
        
        // TODO Auto-generated method stub

    }


    @Override
    public void updateImage() {
        // TODO Auto-generated method stub
        
    }
}


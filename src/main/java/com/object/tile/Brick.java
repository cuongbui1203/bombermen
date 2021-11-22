package com.object.tile;

import com.graphics.ScreenGame;
import com.graphics.Sprite;

public class Brick extends Tile {
    private boolean destroyed;

    public Brick(float x, float y, Sprite sprite) {
        super(x, y, sprite);
        destroyed = false;
    }

    @Override
    public void update() {

    }

    @Override
    public void render(ScreenGame screenGame, int now) {

    }

    public final boolean isDestroyed() {
        return destroyed;
    }

    public void destroy() {
        this.destroyed =true;
    }

}

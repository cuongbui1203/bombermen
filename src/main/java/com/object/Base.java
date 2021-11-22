package com.object;

import com.graphics.Render;
import com.graphics.ScreenGame;
import com.graphics.Sprite;

public abstract class Base implements Render {
    protected float x;
    protected float y;
    protected float xOffset;
    protected float yOffset;
    protected float mapW;
    protected float mapH;
    protected float mapViewW;
    protected float mapViewH;
    protected Sprite sprite;

    public Base(float x, float y, Sprite sprite) {
        this.sprite = sprite;
        this.y = y;
        this.x = x;
        xOffset = 0;
        yOffset = 0;

    }

    @Override
    public void render(ScreenGame screenGame) {
        screenGame.drawSprite(sprite, x, y);
    }

    public Sprite getSprite() {
        return sprite;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setMapH(float mapH) {
        this.mapH = mapH;
    }

    public void setMapW(float mapW) {
        this.mapW = mapW;
    }

    public void setMapViewH(float mapViewH) {
        this.mapViewH = mapViewH;
    }

    public void setMapViewW(float mapViewW) {
        this.mapViewW = mapViewW;
    }

    @Override
    public void render(ScreenGame screenGame, int now) {
    }

}


package com.object.moveavailable.enemy;

import com.graphics.Sprite;
import com.hs.ConstGame;
import com.object.Base;
import com.object.moveavailable.MoveAvailable;
import com.object.moveavailable.enemy.ai.Ai;

public abstract class Enemy extends MoveAvailable {
    protected Ai ai;

    public Enemy(float x, float y, Sprite sprite) {
        super(x, y, sprite);
    }
    @Override
    public void move() {
        switch (dir) {
            case ConstGame.Direction.UP -> y -= speed;
            case ConstGame.Direction.DOWN -> y += speed;
            case ConstGame.Direction.LEFT -> x -= speed;
            case ConstGame.Direction.RIGHT -> x += speed;
        }
    }


}

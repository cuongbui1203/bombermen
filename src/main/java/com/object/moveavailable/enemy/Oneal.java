package com.object.moveavailable.enemy;

import com.graphics.Sprite;
import com.graphics.ScreenGame;
import com.hs.ConstGame;
import com.object.moveavailable.enemy.ai.AiLow;

public class Oneal extends Enemy{

    public Oneal(float x, float y, Sprite sprite) {
        super(x, y, sprite);
        ai = new AiLow();
    }

    @Override
    public void update() {

    }

    @Override
    public void render(ScreenGame screenGame, int now) {
        if (moving) {
            if (dir == ConstGame.Direction.RIGHT || dir == ConstGame.Direction.UP) {
                screenGame.drawSprite(Sprite.movingSprite(Sprite.oneal_right1,
                        Sprite.oneal_right2, Sprite.oneal_right3, now), getX(), getY());
            } else {
                screenGame.drawSprite(Sprite.movingSprite(Sprite.oneal_left1,
                        Sprite.oneal_left2, Sprite.oneal_left3, now), getX(), getY());
            }
        } else {

            if (dir == ConstGame.Direction.RIGHT || dir == ConstGame.Direction.UP) {
                screenGame.drawSprite(Sprite.oneal_right1, getX(), getY());
            } else {
                screenGame.drawSprite(Sprite.oneal_left1, getX(), getY());
            }

        }
    }

    @Override
    public void calculateMove() {
        dir = ai.calculateMove();
    }

    @Override
    public void move() {

    }

    @Override
    public boolean checkMap(boolean[][] obj) {
        return false;
    }
}

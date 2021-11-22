package com.object.moveavailable.enemy;

import com.graphics.Sprite;
import com.graphics.ScreenGame;
import com.hs.ConstGame;
import com.object.Base;
import com.object.moveavailable.enemy.ai.AiLow;

public class Balloon extends Enemy {

    public Balloon(float x, float y, Sprite sprite) {
        super(x, y, sprite);
        ai = new AiLow();
    }

    @Override
    public void update() {
        move();

    }

    @Override
    public void calculateMove() {
        dir = ai.calculateMove();
        moving = true;
    }

    @Override
    public void render(ScreenGame screenGame, int now) {
        if (moving) {
            if (dir == ConstGame.Direction.RIGHT || dir == ConstGame.Direction.UP) {
                screenGame.drawSprite(Sprite.movingSprite(Sprite.balloom_right1,
                        Sprite.balloom_right2, Sprite.balloom_right3, now), getX(), getY());
            } else {
                screenGame.drawSprite(Sprite.movingSprite(Sprite.balloom_left1,
                        Sprite.balloom_left2, Sprite.balloom_left3, now), getX(), getY());
            }
        } else {

            if (dir == ConstGame.Direction.RIGHT || dir == ConstGame.Direction.UP) {
                screenGame.drawSprite(Sprite.balloom_right1, getX(), getY());
            } else {
                screenGame.drawSprite(Sprite.balloom_left1, getX(), getY());
            }

        }
    }

}

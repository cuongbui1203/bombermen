package com.object.bom.flame;

import com.graphics.ScreenGame;
import com.graphics.Sprite;
import com.object.Base;

public class FlameSegment extends Base {

    private static final Sprite[] FLAMES = {
            // 0
            Sprite.bomb_exploded,
            Sprite.explosion_horizontal,
            Sprite.explosion_horizontal_left_last,
            Sprite.explosion_horizontal_right_last,
            Sprite.explosion_vertical,
            Sprite.explosion_vertical_down_last,
            Sprite.explosion_vertical_top_last,
            // 7
            Sprite.bomb_exploded1,
            Sprite.explosion_horizontal1,
            Sprite.explosion_horizontal_left_last1,
            Sprite.explosion_horizontal_right_last1,
            Sprite.explosion_vertical1,
            Sprite.explosion_vertical_down_last1,
            Sprite.explosion_vertical_top_last1,
            // 14
            Sprite.bomb_exploded2,
            Sprite.explosion_horizontal2,
            Sprite.explosion_horizontal_left_last2,
            Sprite.explosion_horizontal_right_last2,
            Sprite.explosion_vertical2,
            Sprite.explosion_vertical_down_last2,
            Sprite.explosion_vertical_top_last2
    };

    private int upLength = 0;
    private int downLength = 0;
    private int leftLength = 0;
    private int rightLength = 0;

    public FlameSegment(float x, float y) {
        super(x, y, null);
    }


    public void update(int up, int right, int down, int left) {
        upLength = up;
        downLength = down;
        leftLength = left;
        rightLength = right;
    }

    @Override
    public void update() {

    }

    @Override
    public void render(ScreenGame screenGame, int now) {
        // trung tâm
        screenGame.drawSprite(Sprite.movingSprite(FlameSegment.FLAMES[0],
                        FlameSegment.FLAMES[7],
                        FlameSegment.FLAMES[14], now)
                , getX(), getY());
        // bên trái
        if (leftLength > 0) {
            for (int i = 1; i <= leftLength - 1; i++) {
                screenGame.drawSprite(Sprite.movingSprite(FlameSegment.FLAMES[1],
                                FlameSegment.FLAMES[8],
                                FlameSegment.FLAMES[15], now)
                        , getX() - i, getY());
            }
            screenGame.drawSprite(Sprite.movingSprite(FlameSegment.FLAMES[2],
                            FlameSegment.FLAMES[9],
                            FlameSegment.FLAMES[16], now)
                    , getX() - leftLength, getY());

        }

        // bên phải
        if (rightLength > 0) {
            for (int i = 1; i <= rightLength - 1; i++) {
                screenGame.drawSprite(Sprite.movingSprite(FlameSegment.FLAMES[1],
                                FlameSegment.FLAMES[8],
                                FlameSegment.FLAMES[15], now)
                        , getX() + i, getY());
            }
            screenGame.drawSprite(Sprite.movingSprite(FlameSegment.FLAMES[3],
                            FlameSegment.FLAMES[10],
                            FlameSegment.FLAMES[17], now)
                    , getX() + rightLength, getY());
        }
        // bên dưới
        if (downLength > 0) {
            for (int i = 1; i <= downLength - 1; i++) {
                screenGame.drawSprite(Sprite.movingSprite(FlameSegment.FLAMES[4],
                                FlameSegment.FLAMES[11],
                                FlameSegment.FLAMES[18], now)
                        , getX(), getY() + i);
            }
            screenGame.drawSprite(Sprite.movingSprite(FlameSegment.FLAMES[5],
                            FlameSegment.FLAMES[12],
                            FlameSegment.FLAMES[19], now)
                    , getX(), getY() + downLength);
        }
        // bên trên
        if (upLength > 0) {
            for (int i = 1; i <= upLength - 1; i++) {
                screenGame.drawSprite(Sprite.movingSprite(FlameSegment.FLAMES[4],
                                FlameSegment.FLAMES[11],
                                FlameSegment.FLAMES[18], now)
                        , getX(), getY() - i);
            }
            screenGame.drawSprite(Sprite.movingSprite(FlameSegment.FLAMES[6],
                            FlameSegment.FLAMES[13],
                            FlameSegment.FLAMES[20], now)
                    , getX(), getY() - upLength);
        }
    }
}

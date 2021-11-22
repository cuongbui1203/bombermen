package com.object.moveavailable.character;

import com.graphics.Sprite;
import com.graphics.ScreenGame;
import com.hs.ConstGame;
import com.input.Keyboard;
import com.object.bom.flame.FlameSegment;
import com.object.tile.Wall;

/**
 * class quản lý nhân vật của người chơi.
 */
public class Bomber extends Character {

    private Keyboard input;

    public Bomber(float x, float y, Sprite sprite) {
        super(x, y, sprite);
        xOffset = 0;
        yOffset = 0;
    }



    public double getXOffset() {
        return xOffset;
    }

    public double getYOffset() {
        return yOffset;
    }

    public void setInput(Keyboard input) {
        this.input = input;
    }

    /**
     * di chuyển theo chiều ngang.
     *
     * @param d chiều chuyển dộng
     */
    private void moveW(int d) {
        float xc = mapViewW / 2;
        if (d == ConstGame.Direction.RIGHT) {
            if (x < xc) {
                x += speed;
                if (x >= xc)
                    x = xc;
            } else if (xOffset < mapW - mapViewW) {
                xOffset += speed;
                if (xOffset >= mapW - mapViewW)
                    xOffset = mapW - mapViewW;
            } else if (x < mapViewW - 1.7) {
                x += speed;
                if (x >= mapViewW - 1.7)
                    x = (float) (mapViewW - 1.7);
            }
        }

        if (d == ConstGame.Direction.LEFT) {

            if (x > xc) {
                x -= speed;
                if (x <= xc)
                    x = xc;
            } else if (xOffset > 0) {
                xOffset -= speed;
                if (xOffset <= 0)
                    xOffset = 0;
            } else if (x > 0) {
                x -= speed;
                if (x <= 0)
                    x = 0;
            }
        }
    }

    /**
     * di chuyển theo chiều dọc.
     *
     * @param d chiều chuyển dộng
     */
    private void moveH(int d) {
        float yc = mapViewH / 2;

        if (d == ConstGame.Direction.DOWN) {

            if (y < yc) {
                y += speed;
                if (y >= yc)
                    y = yc;
            } else if (yOffset < mapH - mapViewH) {
                yOffset += speed;
                if (yOffset >= mapH - mapViewH)
                    yOffset = mapH - mapViewH;
            } else if (y < mapViewH - 2) {
                y += speed;
                if (y >= mapViewH - 2)
                    y = mapViewH - 2;
            }

        }

        if (d == ConstGame.Direction.UP) {
            if (y > yc) {
                y -= speed;
                if (y <= yc)
                    y = yc;
            } else if (yOffset > 0) {
                yOffset -= speed;
                if (yOffset <= 0)
                    yOffset = 0;
            } else if (y > 0) {
                y -= speed;
                if (y <= 0)
                    y = 0;
            }
        }
    }


    @Override
    public void move() {
        if (moving) {
            switch (dir) {
                case ConstGame.Direction.LEFT, ConstGame.Direction.RIGHT -> {
                    if (Math.abs(getY() - (int) getY()) < 0.1 )
                        moveW(dir);
                    else if (getY() > ((int) getY()) + 0.5)
                        moveH(ConstGame.Direction.DOWN, (int) getY());
                    else
                        moveH(ConstGame.Direction.UP, (int) getY());

                }
                case ConstGame.Direction.DOWN, ConstGame.Direction.UP -> {
                    if (Math.abs(getX() - (int) getX()) < 0.1)
                        moveH(dir);
                    else if (getX() > ((int) getX() + 0.5))
                        moveW(ConstGame.Direction.RIGHT, (int) getX());
                    else
                        moveW(ConstGame.Direction.LEFT, (int) getX());
                }
            }
        }
    }

    @Override
    public void update() {

        if (input.up)
            dir = ConstGame.Direction.UP;
        if (input.down)
            dir = ConstGame.Direction.DOWN;
        if (input.left)
            dir = ConstGame.Direction.LEFT;
        if (input.right)
            dir = ConstGame.Direction.RIGHT;

        moving = (input.up || input.down || input.left || input.right);

        move();


    }

    /**
     * hàm render các hình ảnh cảu nhân vật.
     *
     * @param screenGame màn hình cần render.
     * @param now        số thứ tự khung hình hiển thị.
     */
    public void render(ScreenGame screenGame, int now) {
        if (moving) {
            switch (dir) {
                case ConstGame.Direction.UP:
                    screenGame.drawSprite(Sprite.movingSprite(Sprite.player_up_1, Sprite.player_up_2, now), getX(), getY());
                    break;
                case ConstGame.Direction.DOWN:
                    screenGame.drawSprite(Sprite.movingSprite(Sprite.player_down_1, Sprite.player_down_2, now), getX(), getY());
                    break;
                case ConstGame.Direction.LEFT:
                    screenGame.drawSprite(Sprite.movingSprite(Sprite.player_left_1, Sprite.player_left_2, now), getX(), getY());
                    break;
                case ConstGame.Direction.RIGHT:
                    screenGame.drawSprite(Sprite.movingSprite(Sprite.player_right_1, Sprite.player_right_2, now), getX(), getY());
                    break;
            }
        } else {

            switch (dir) {
                case ConstGame.Direction.UP:
                    screenGame.drawSprite(Sprite.player_up, getX(), getY());
                    break;
                case ConstGame.Direction.DOWN:
                    screenGame.drawSprite(Sprite.player_down, getX(), getY());
                    break;
                case ConstGame.Direction.LEFT:
                    screenGame.drawSprite(Sprite.player_left, getX(), getY());
                    break;
                case ConstGame.Direction.RIGHT:
                    screenGame.drawSprite(Sprite.player_right, getX(), getY());
                    break;
            }
        }
    }


    public void setMapW(double mapW) {
        this.mapW = (float) mapW;
    }

    public void setMapH(double mapH) {
        this.mapH = (float) mapH;
    }

    @Override
    public float getX() {
        return super.getX() + xOffset;
    }

    @Override
    public float getY() {
        return super.getY() + yOffset;
    }

    @Override
    public void calculateMove() {
    }

    private void moveW(int d, int endX) {
        double xc = mapViewW / 2;

        if (d == ConstGame.Direction.RIGHT) {
//            endX++;
            if (getX() < endX) {
                if (getX() <= endX) {
                    if (endX < xc) {
                        x += speed;
                        if (x >= endX) {
                            x = endX;
                        }
                    } else if (endX < mapW - mapViewW) {
                        xOffset += speed;
                        if (getX() >= endX) {
                            xOffset = endX - x;
                        }
                    } else if (endX < mapW) {
                        x += speed;
                        if (getX() >= endX) {
                            x = endX - xOffset;
                        }
                    }
                }
            }
        }


        if (d == ConstGame.Direction.LEFT) {
//            endX--;
            if (getX() > endX) {
                if (endX > xc + mapViewW) {
                    x -= speed;
                    if (x <= endX)
                        x = endX - xOffset;
                } else if (endX > xc) {
                    xOffset -= speed;
                    if (getX() <= endX)
                        xOffset = (float) (endX - xc);
                } else if (endX > 1) {
                    x -= speed;
                    if (getX() <= endX)
                        x = endX;
                }
            }
        }
    }

    private void moveH(int d, int endY) {
        double yc = mapViewH / 2;
        if (d == ConstGame.Direction.DOWN) {
            endY++;
            if (getY() <= endY) {
                if (endY < yc) {
                    y += speed;
                    if (getY() >= endY) {
                        y = endY;
                    }
                } else if (endY < mapW - mapViewW) {
                    yOffset += speed;
                    if (getY() >= endY) {
                        yOffset = endY - y;
                    }
                } else if (endY < mapW) {
                    y += speed;
                    if (getY() >= endY) {
                        y = endY - yOffset;
                    }
                }
            }

        }

        if (d == ConstGame.Direction.UP) {
            endY--;
            if (endY < 0) {
                endY = 0;
            }
            if (getY() >= endY) {
                if (endY > yc + mapViewH) {
                    y -= speed;
                    if (getY() <= endY)
                        y = endY - mapViewH;
                } else if (endY > yc) {
                    yOffset -= speed;
                    if (getY() <= endY)
                        yOffset = endY - y;
                } else if (endY > 1) {
                    y -= speed;
                    if (getY() < endY)
                        y = endY;
                }
            }
        }
    }

    public void setSpeed(long now) {
         speed = now;

    }
}

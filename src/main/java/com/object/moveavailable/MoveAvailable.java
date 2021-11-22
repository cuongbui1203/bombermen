package com.object.moveavailable;

import com.graphics.Sprite;
import com.hs.ConstGame;
import com.object.Base;

public abstract class MoveAvailable extends Base {

    protected boolean alive;
    protected boolean moving;
    protected int dir = ConstGame.Direction.RIGHT;
    protected int lastDir = dir;
    protected double speed;
    protected float speed1;
    protected boolean canMove;
    protected float oldTime;

    public MoveAvailable(float x, float y, Sprite sprite) {
        super(x, y, sprite);
        moving = false;
        alive = true;
        canMove = true;
    }


    public abstract void calculateMove();

    public abstract void move();

    public int getDir() {
        return dir;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public void kill() {
        alive = false;
    }

    public boolean checkMap(boolean[][] temp) {
        float x = getX();
        float y = getY();
        switch (getDir()) {
            case ConstGame.Direction.RIGHT -> x += speed + 1;
            case ConstGame.Direction.LEFT -> x -= speed;
            case ConstGame.Direction.UP -> y -= speed;
            case ConstGame.Direction.DOWN -> y += speed + 1;
        }
        return temp[(int) y][(int) x];
    }

    public float calculateSpeed(float now) {

        float v = (ConstGame.InfoObject.SPEED_CHARACTER * (now - oldTime))%100;
        oldTime = now;
//        v%=1;
//        System.out.println(now);
        speed = v;
        return v;

    }

}

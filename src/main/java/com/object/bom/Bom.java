package com.object.bom;

import com.graphics.ScreenGame;
import com.graphics.Sprite;
import com.hs.ConstGame;
import com.object.Base;
import com.object.bom.flame.FlameSegment;
import javafx.scene.effect.Light;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Bom extends Base {

    private final FlameSegment flameSegment;
    private final int lengthFlame = 3;
    private final List<Light.Point> points;
    private Timer timer;
    private Timer timer1;
    private int countdown;
    private int explosionFrame = 3;
    private int bombFrame = 0;
    private int up = 0;
    private int right = 0;
    private int left = 0;
    private int down = 0;


    public Bom(float x, float y, boolean[][] temp) {
        super(x, y, null);

        points = new ArrayList<>();

        points.add(new Light.Point());//up
        points.add(new Light.Point());//right
        points.add(new Light.Point());//down
        points.add(new Light.Point());//left

        flameSegment = new FlameSegment(x, y);

        countdown = ConstGame.InfoObject.BOM_EXPLOSION_TIME;
        checkMap(temp);
        start();
    }

    private void start() {
        timer = new Timer();
        timer1 = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                update();
            }
        }, 0, 1000);
        timer1.schedule(new TimerTask() {
            @Override
            public void run() {
                bombFrame++;
            }
        }, 0, 400);
    }

    @Override
    public void update() {
        countdown--;
        if (countdown == 0) {
            explosion();
        }
    }

    private void explosionEven() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                update2();
            }
        }, 0, 400);
    }

    private void update2() {
        explosionFrame--;
        if (explosionFrame == 0) {
            timer.cancel();
        }
    }

    public boolean endCountdown() {
        boolean res = (countdown == 0);
        if (res) {
            countdown = -1;
        }
        return res;
    }

    public void explosion() {
        if (timer != null)
            timer.cancel();
        if (timer1 != null)
            timer1.cancel();
        timer = null;
        timer1 = null;
        countdown = -1;
        explosionEven();
    }

    public boolean end() {
        return explosionFrame == 0;
    }

    public void ex(List<Bom> boms) {
        for (Bom a : boms) {
            if(a!=this) {
                if (checkPoint(a)) {
                    a.explosion();
                    System.out.println("ex ...");
                }
            }
        }
    }

    private boolean checkPoint(Base a) {

        if(a.getX() == getX()){
            return true;
        }
        return false;
    }

    @Override
    public void render(ScreenGame screenGame, int now) {

        if (countdown>0)
            screenGame.drawSprite(Sprite.movingSprite(Sprite.bomb, Sprite.bomb_1, Sprite.bomb_2, bombFrame)
                    , getX(), getY());
        else {
            flameSegment.render(screenGame, explosionFrame + 2);
        }
    }

    public void remove(List<Bom> boms) {
        boms.remove(this);
    }

    public void checkMap(boolean[][] temp) {
        int x = (int) getX();
        int y = (int) getY();

        boolean upcheck = false;
        boolean leftcheck = false;
        boolean rightcheck = false;
        boolean downcheck = false;

        for (int i = 1; i <= lengthFlame && !rightcheck; i++) {
            if (!temp[y][x + i]) {
                right++;
            } else {
                rightcheck = true;
            }
        }

        for (int i = 1; i <= lengthFlame && !upcheck; i++) {
            if (!temp[y - i][x]) {
                up++;
            } else {
                upcheck = true;
            }
        }

        for (int i = 1; i <= lengthFlame && !downcheck; i++) {
            if (!temp[y + i][x]) {
                down++;
            } else {
                downcheck = true;
            }
        }

        for (int i = 1; i <= lengthFlame && !leftcheck; i++) {
            if (!temp[y][x - i]) {
                left++;
            } else {
                leftcheck = true;
            }
        }
        flameSegment.update(up, right, down, left);

        if (up < lengthFlame) {
            setTd(0, getX(), getY() - up - 1);
        } else {
            setTd(0, getX(), getY() - up);
        }

        if (right < lengthFlame) {
            setTd(1, getX() + right + 1, getY());
        } else {
            setTd(1, getX() + right, getY());
        }

        if (down < lengthFlame) {
            setTd(2, getX(), getY() + down);
        } else {
            setTd(2, getX(), getY() + down);
        }

        if (left < lengthFlame) {
            setTd(3, getX() - left - 1, getY());
        } else {
            setTd(3, getX() - left, getY());
        }

    }

    private void setTd(int pos, float x, float y) {
        points.get(pos).setX(x);
        points.get(pos).setY(y);
    }


    public List<Light.Point> getPoints() {
        return points;
    }

}

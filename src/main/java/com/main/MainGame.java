package com.main;

import com.graphics.ScreenGame;
import com.graphics.Sprite;
import com.hs.ConstGame;
import com.input.Keyboard;
import com.map.MapView;
import com.object.Processes;
import com.object.bom.Bom;
import com.object.moveavailable.character.Bomber;
import com.object.moveavailable.enemy.Balloon;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.effect.Light;

import java.util.ArrayList;
import java.util.List;


public class MainGame {

    private final ScreenGame screenGame;

    private final Bomber player;
    private final MapView mapView;
    private final Keyboard in;
    private final List<Bom> boms;
    private final Balloon balloon;
    private boolean attacked = false;
    private boolean c = false;

    public MainGame(Canvas canvas, Keyboard input) {
        this.in = input;
        canvas.setWidth(ConstGame.InfoGame.WIDTH);
        canvas.setHeight(ConstGame.InfoGame.HEIGHT);

        canvas.setLayoutY(40);
        canvas.setLayoutX(0);


        mapView = new MapView(canvas.getWidth(), canvas.getHeight());

        mapView.load(1);

        screenGame = new ScreenGame(canvas);
        screenGame.setMap(mapView);

        player = new Bomber(1, 1, Sprite.player_right);

        balloon = new Balloon(9, 1, Sprite.balloom_left1);


        player.setInput(this.in);

        boms = new ArrayList<>();

        init();
    }

    public void init() {
        player.setMapH(mapView.getMap().y);
        player.setMapW(mapView.getMap().x);
        player.setMapViewH(mapView.getHeight());
        player.setMapViewW(mapView.getWidth());
        run();
    }

    public void run() {

        AnimationTimer timer = new AnimationTimer() {
            private long old = 0;
            private long old2 = 0;
            private int f = 0;
            private int time = 0;
            private int time2 = 0;

            @Override
            public void handle(long now) {
                f++;
                render(time);
                update(time2);

                // FPS
                if (now - old >= 1000000000) {
                    System.out.println("FPS: " + f);
                    System.out.println("Bomb: " + (boms.size()));
                    f = 0;
                    old = now;
                    if (time2 == 10) {
                        time2 = 0;
                    }
                    time2++;
                    c = false;
                }
                // ki
                if (now - old2 >= 400000000) {
                    time++;
                    if (time == 30) {
                        time = 0;
                    }
                    old2 = now;
                    attacked = false;
                }

            }
        };

        timer.start();
    }

    private void render() {
        screenGame.clearScreen();
        mapView.render(screenGame);
    }

    private void render(int now) {
        render();
        if (!boms.isEmpty()) {
            for (Bom a : boms) {
                a.render(screenGame, now);
            }
        }
        balloon.render(screenGame, now);
        player.render(screenGame, now);
    }

    private void update(int time) {
        if (Processes.check2(player, mapView.getMap().temp)) {
            player.setSpeed(0);
        } else player.setSpeed(ConstGame.InfoObject.SPEED_CHARACTER);
        player.update();

        mapView.update(player);

        if (time % 3 == 0 && !c) {
            c = true;
            balloon.calculateMove();
        }

        balloon.update();

        if (in.attack && !attacked) {
            boms.add(new Bom((int) player.getX(), (int) player.getY(), mapView.getMap().temp));
            attacked = true;
        }

        for (Bom a : boms) {
            if (a.endCountdown()) {
                a.ex(boms);
//                tem = a;
                System.out.println("end countdown");
                break;
            }
        }
        for (Bom a : boms) {
            if (a.end()) {
                a.remove(boms);
                System.out.println("End Life");
                break;
            }
        }
        //System.out.println("r c: "+(int)player.getX()+","+(int)player.getY());
//        System.out.println("td: "+player.getX()+","+player.getY());
        //System.out.println("td2: "+(int)player.getX()+","+(int)player.getY());
//        if(Processes.check(player,maplevel.obj))
//            System.out.println("Wall");
    }


    private boolean checkBom(Bom bom, List<Light.Point> points) {
        if ((int) bom.getX() == (int) points.get(1).getX()) {
            return (int) bom.getY() >= (int) points.get(0).getY() && (int) bom.getY() <= (int) points.get(1).getY();
        }
        if ((int) bom.getY() == (int) points.get(0).getY()) {
            return (int) bom.getX() >= (int) points.get(3).getX() && (int) bom.getX() <= (int) points.get(2).getX();
        }
        return false;
    }

}
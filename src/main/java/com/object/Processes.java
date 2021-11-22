package com.object;

import com.hs.ConstGame;
import com.object.moveavailable.character.Bomber;
import com.object.tile.Wall;

public class Processes {
    public static boolean check(Bomber bomber, Base[][] obj) {
        // tọa độ main int
        int x = (int) bomber.getX();
        int y = (int) bomber.getY();

        // tọa độ ô đến
        int a = 0;
        int b = 0;

        switch (bomber.getDir()) {
            case ConstGame.Direction.RIGHT -> a = 1;
//            case ConstGame.Direction.LEFT -> a = -1;
//            case ConstGame.Direction.UP -> b = -1;
            case ConstGame.Direction.DOWN -> b = 1;
        }
        if (y + b < 0) b = 0;
        if (x + a < 0) a = 0;
//        System.out.println(obj[y + b][x + a].getClass());
//        System.out.println((bomber.getX()) + ","+(bomber.getY()));
        return obj[y + b][x + a] instanceof Wall;
    }

    // theo minh
    public static boolean check2(Bomber bomber, boolean[][] temp) {
        // tọa độ main int
        float x =  bomber.getX();
        float y =  bomber.getY();
        float speed = ConstGame.InfoObject.SPEED_CHARACTER;
        switch (bomber.getDir()) {
            case ConstGame.Direction.RIGHT -> x+=speed;
            case ConstGame.Direction.LEFT -> x-=speed;
            case ConstGame.Direction.UP -> y-=speed;
            case ConstGame.Direction.DOWN -> y+=speed;
        }
        if(x == 0.98) x = 1;
        if(y == 0.98) y = 1;
        if(y == 11 + 0.05) y = 11;
        if(x == 29 + 0.05) x = 29;

//        x = round(x);
//        y = round(y);
//        System.out.println(obj[y + b][x + a].getClass());
//        System.out.println(x  + "," + y);
        return temp[(int) y][(int) x];
    }

    public static int round(float num) {
        if(num - (int) num <= 0.02) return (int) num +1;
        else return (int) num;
        //return num - (int) num >= 0.95 ? (int) num + 1 : (int) num;
    }
}

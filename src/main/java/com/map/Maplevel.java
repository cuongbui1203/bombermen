package com.map;

import com.graphics.*;
import com.object.Base;
import com.object.moveavailable.enemy.Balloon;
import com.object.moveavailable.enemy.Enemy;
import com.object.moveavailable.enemy.Oneal;
import com.object.tile.Brick;
import com.object.tile.Grass;
import com.object.tile.Portal;
import com.object.tile.Wall;
import com.object.tile.item.BombItem;
import com.object.tile.item.FlameItem;
import com.object.tile.item.Item;
import com.object.tile.item.SpeedItem;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * class quản lý Map.
 */
public class Maplevel implements  Render {

    public int x;
    public int y;
    public int level;

    public char[][] mapChar;
    public Base[][] obj;
    public Sprite[][] map;
    public boolean[][] temp;

    public Image staticMap;

    public List<Enemy> enemies;
    public List<Brick> bricks;
    public List<Portal> portals;
    public List<Item> items;

    private int numOfBrick = 0;

    /**
    *   r <=> y (dọc)
    *   c <=> x (ngang)
    */
    public Maplevel(int level, int r, int c) {
        this.x = c;
        this.y = r;
        this.level = level;

        // mảng các object
        obj = new Base[r][c];

        // mảng hình ảnh.
        map = new Sprite[r][c];

        //mảng ký tự đọc từ map.
        mapChar = new char[r][c];

        // mảng lưu vị trí và kiểm soát các Item,Brick,Enemy,Portal.
        items = new ArrayList<>();
        bricks = new ArrayList<>();
        enemies = new ArrayList<>();
        portals = new ArrayList<>();

        // temp là mảng có vật cản
        temp = new boolean[r][c];

    }

    @Override
    public void update(){
        bricks.removeIf(Brick::isDestroyed);
    }

    @Override
    public void render(ScreenGame screenGame) {
        for (Brick a:bricks){
            if(a.isDestroyed()){
                a.render(screenGame);
            }
        }
    }

    @Override
    public void render(ScreenGame screenGame, int now) {

    }

    /**
     * hàm load các obj dựa trên map char dc đọc từ file txt.
     */
    public void loadObj() {
        for (int i = 0; i < y; i++) {
            for (int t = 0; t < x; t++) {
                temp[i][t] = false;
                switch (mapChar[i][t]) {
                    case '#':
//                        map.map[i][]
                        obj[i][t] = new Wall(t, i, Sprite.wall);
                        temp[i][t] = true;
                        break;
                    case '*':
                        obj[i][t] = new Brick(t, i, Sprite.brick);
                        bricks.add((Brick) obj[i][t]);
//                        temp[i][t] = true;
                        break;
                    case 'x':
                        obj[i][t] = new Portal(t, i, Sprite.portal);
                        portals.add((Portal) obj[i][t]);
                        break;
                    case 'p':
//                        obj[i][t] = new Bomber(i, t, Sprite.player_right);
                        obj[i][t] = new Grass(t,i,Sprite.grass);
                        break;
                    case '1':
                        obj[i][t] = new Balloon(t, i, Sprite.balloom_right1);
                        enemies.add((Enemy) obj[i][t]);
                        break;
                    case '2':
                        obj[i][t] = new Oneal(t, i, Sprite.oneal_right1);
                        enemies.add((Enemy) obj[i][t]);
                        break;
                    case 'b':
                        obj[i][t] = new BombItem(t, i, Sprite.powerup_bombpass);
                        items.add((Item) obj[i][t]);
                        break;
                    case 'f':
                        obj[i][t] = new FlameItem(t, i, Sprite.powerup_flames);
                        items.add((Item) obj[i][t]);
                        break;
                    case 's':
                        obj[i][t] = new SpeedItem(t, i, Sprite.powerup_speed);
                        items.add((Item) obj[i][t]);
                        break;
                    default:
                        obj[i][t] = new Grass(t, i, Sprite.grass);
                        break;
                }
                if(obj[i][t] instanceof Wall){
                    map[i][t] = Sprite.wall;
                }else {
                    map[i][t] = Sprite.grass;
                }
            }
        }
        staticMap =  ImageMerger.createStaticImage(map);
        numOfBrick = bricks.size();
    }

    public boolean remove(){
        boolean res = numOfBrick != bricks.size();
        numOfBrick = bricks.size();
        return res;
    }
}

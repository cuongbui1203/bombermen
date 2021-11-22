package com.map;

import com.graphics.ImageMerger;
import com.graphics.Render;
import com.graphics.ScreenGame;
import javafx.scene.image.Image;


public class Layer implements Render{

    private Image brickImage;
    private Maplevel map;

    public Layer() {
    }


    public void load() {
        map.loadObj();
        brickImage = ImageMerger.createStaticImage3(map.bricks, map.y, map.x);
    }

    public void setMap(Maplevel map) {
        this.map = map;
    }

    public Image getBrickImage() {
        return brickImage;
    }

    public Maplevel getMap() {
        return map;
    }


    @Override
    public void render(ScreenGame screenGame, int now) {
    }

    @Override
    public void update() {
        map.update();
        if(map.remove())
            brickImage = ImageMerger.createStaticImage3(map.bricks, map.y, map.x);

    }
    @Override
    public void render(ScreenGame screenGame) {
        map.render(screenGame);
    }

}

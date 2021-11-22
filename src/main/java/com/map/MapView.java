package com.map;

import com.graphics.ScreenGame;
import com.object.moveavailable.character.Bomber;
import javafx.scene.shape.Rectangle;

/**
 * class kiểm soát thành phần dc vẽ lên màn hình.
 * <p>tạo độ sử dụng là pixel.</p>
 */
public class MapView {

    private final Rectangle view;

    private Layer layerMap;

    public MapView(double w, double h){
        view = new Rectangle(0,0,w,h);
        layerMap = new Layer();
    }

    public void load(int level){
        layerMap.setMap(LoadMap.load());
        layerMap.load();
    }


    /**
     * cập nhật tạo độ cua mapView trên map gốc.
     * @param bomber nhân vật của người chơi.
     */
    public void update(Bomber bomber) {
        view.setX(Coordinates.tileToPixel( bomber.getXOffset()));
        view.setY(Coordinates.tileToPixel( bomber.getYOffset()));
        layerMap.update();
//        layerMap.update();
//        System.out.println(bomber.getxOffset()+","+bomber.getyOffset());
    }

    public void render(ScreenGame screenGame) {
        screenGame.drawStaticImage(layerMap.getMap().staticMap,
                view.getX(), view.getY(),view.getWidth(),view.getHeight(),
                0,0,view.getWidth(), view.getHeight());

        screenGame.drawStaticImage(layerMap.getBrickImage(),
                view.getX(), view.getY(),view.getWidth(),view.getHeight(),
                0,0,view.getWidth(), view.getHeight());

        layerMap.render(screenGame);
    }

    /**
     * trả về Width.
     * @return đơn vị tọa độ.
     */
    public float getWidth(){
        return (float) Coordinates.pixelToTile1(view.getWidth());
    }

    /**
     * trả về Height.
     * @return đơn vị tọa độ.
     */
    public float getHeight(){
        return (float) Coordinates.pixelToTile1(view.getHeight());
    }

    public void setLayerMap(Layer layerMap) {
        this.layerMap = layerMap;
    }

    public Maplevel getMap() {
        return layerMap.getMap();
    }

    public float getGocX(){
        return (float) Coordinates.pixelToTile1(view.getX());
    }

    public float getGocY(){
        return (float) Coordinates.pixelToTile1(view.getY());
    }

}

package com.graphics;

import com.hs.ConstGame;
import com.map.MapView;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;

/**
 * class quản lý việc vẽ lên màn hình của game.
 */
public class ScreenGame {
    private final GraphicsContext gc;
    private MapView map;
    private WritableImage write;

    public ScreenGame(Canvas canvas) {
        gc = canvas.getGraphicsContext2D();
    }

    public void setMap(MapView map) {
        this.map = map;
    }

    public void drawSprite(Sprite sprite, double x, double y) {
        if (x >= map.getGocX() && x <= map.getGocX() + map.getWidth()
                && y >= map.getGocY() && y <= map.getHeight() + map.getGocY()) {
            gc.drawImage(sprite.getFxImage(),
                    calculateX((float) x) * ConstGame.InfoObject.SCALED_SIZE,
                    calculateY((float) y) * ConstGame.InfoObject.SCALED_SIZE);
        }
    }

    public void drawStaticImage(Image image, double x, double y) {
        if ((x >= map.getGocX() ||
                x + 1 <= map.getGocX() + map.getWidth()) &&
                (y >= map.getGocY() ||
                        y + 1 <= map.getHeight() + map.getGocY())) {
            gc.drawImage(image,
                    calculateX((float) x) * ConstGame.InfoObject.SCALED_SIZE,
                    calculateY((float) y) * ConstGame.InfoObject.SCALED_SIZE);
        }
    }

    public void drawStaticImage(Image image, double sx, double sy, double sw, double sh,
                                double dx, double dy, double dw, double dh) {
        gc.drawImage(image, sx, sy, sw, sh, dx, dy, dw, dh);
    }

    /**
     * hàm xóa màn hình.
     */
    public void clearScreen() {
        gc.fillRect(0, 0, map.getWidth(), map.getHeight());
    }


    /**
     * tính toán tọa độ x.
     *
     * @param x tọa độ x của vật thể trên map gốc.
     * @return tạo độ x của vật thể trên mapView.
     */
    private float calculateX(float x) {
        return x - map.getGocX();
    }

    /**
     * tính toán tọa độ y.
     *
     * @param y tọa độ y của vật thể trên map gốc.
     * @return tạo độ y của vật thể trên mapView.
     */
    private float calculateY(float y) {
        return y - map.getGocY();
    }

    public void setWritableImage(WritableImage write) {
        this.write = write;
    }
}

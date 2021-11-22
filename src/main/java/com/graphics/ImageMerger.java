package com.graphics;

import com.hs.ConstGame;
import com.object.Base;
import com.object.tile.Brick;
import javafx.scene.image.*;

import java.util.List;

/**
 * class tạo hình ảnh tĩnh.
 */
public class ImageMerger {
    /**
     * Gộp các Sprite(hình ảnh) của map tĩnh.
     *
     * @param sprites mảng các hình ảnh cần render.
     * @return một hình ảnh tĩnh đã được gộp chung vào nhau.
     */
    public static Image createStaticImage(Sprite[][] sprites) {
        int r = sprites.length;
        int c = sprites[0].length;
        int size = ConstGame.InfoObject.DEFAULT_SIZE;
        WritableImage wr = new WritableImage(
                size * c,
                size * r);

        PixelWriter pw = wr.getPixelWriter();

        for (int i = 0; i < r; i++) {
            for (int t = 0; t < c; t++) {
                for (int x = 0; x < ConstGame.InfoObject.DEFAULT_SIZE; x++) {
                    for (int y = 0; y < ConstGame.InfoObject.DEFAULT_SIZE; y++) {
                        if (sprites[i][t]._pixels[x + y * size] == ConstGame.InfoObject.TRANSPARENT_COLOR) {
                            pw.setArgb(x + t * size, y + i * size, 0);
                        } else {
                            pw.setArgb(x + t * size, y + i * size, sprites[i][t]._pixels[x + y * ConstGame.InfoObject.DEFAULT_SIZE]);
                        }
                    }
                }
            }
        }

        Image input = new ImageView(wr).getImage();
//        return input;
        return Sprite.resample(input, ConstGame.InfoObject.SCALED_SIZE / ConstGame.InfoObject.DEFAULT_SIZE);
    }

    public static WritableImage createStaticWritableImage(Sprite[][] sprites) {
        int r = sprites.length;
        int c = sprites[0].length;
        int size = ConstGame.InfoObject.DEFAULT_SIZE;
        WritableImage wr = new WritableImage(
                size * c,
                size * r);

        PixelWriter pw = wr.getPixelWriter();

        for (int i = 0; i < r; i++) {
            for (int t = 0; t < c; t++) {
                for (int x = 0; x < ConstGame.InfoObject.DEFAULT_SIZE; x++) {
                    for (int y = 0; y < ConstGame.InfoObject.DEFAULT_SIZE; y++) {
                        if (sprites[i][t]._pixels[x + y * size] == ConstGame.InfoObject.TRANSPARENT_COLOR) {
                            pw.setArgb(x + t * size, y + i * size, 0);
                        } else {
                            pw.setArgb(x + t * size, y + i * size, sprites[i][t]._pixels[x + y * ConstGame.InfoObject.DEFAULT_SIZE]);
                        }
                    }
                }
            }
        }

        return wr;
    }

    public static Image createStaticImage2(Sprite[][] sprites) {
        int r = sprites.length;
        int c = sprites[0].length;
        int size = ConstGame.InfoObject.DEFAULT_SIZE;
        WritableImage wr = new WritableImage(
                size * c,
                size * r);

        PixelWriter pw = wr.getPixelWriter();

        for (int i = 0; i < r; i++) {
            for (int t = 0; t < c; t++) {
                for (int x = 0; x < ConstGame.InfoObject.DEFAULT_SIZE; x++) {
                    for (int y = 0; y < ConstGame.InfoObject.DEFAULT_SIZE; y++) {
                        if (sprites[i][t] != null) {
                            if (sprites[i][t]._pixels[x + y * size] == ConstGame.InfoObject.TRANSPARENT_COLOR) {
                                pw.setArgb(x + t * size, y + i * size, 0);
                            } else {
                                pw.setArgb(x + t * size, y + i * size, sprites[i][t]._pixels[x + y * ConstGame.InfoObject.DEFAULT_SIZE]);
                            }
                        } else {
                            pw.setArgb(x + t * size, y + i * size, 0);
                        }
                    }
                }
            }
        }

        Image input = new ImageView(wr).getImage();
//        return input;
        return Sprite.resample(input, ConstGame.InfoObject.SCALED_SIZE / ConstGame.InfoObject.DEFAULT_SIZE);
    }

    /**
     * tạo lớp đầu tiên cho render.
     *
     * @param obj mảng các vật thể cần vẽ.
     * @param w   Chiều rộng(của map).
     * @param h   Chiều cao(của map).
     * @return trả về hỉnh ảnh sau khi gộp(đã phóng to).<p>Có thể render ra canvas(màn hình) được luôn.</p>
     */
    public static Image createStaticImage3(List<Brick> obj, int w, int h) {
        int r = h;
        int c = w;
        int size = ConstGame.InfoObject.DEFAULT_SIZE;
        WritableImage wr = new WritableImage(
                size * r,
                size * c);

        PixelWriter pw = wr.getPixelWriter();

        for (int i = 0; i < r * size; i++) {
            for (int t = 0; t < c * size; t++) {
                pw.setArgb(i, t, 0);
            }
        }

        for (Base a : obj) {
            for (int i = 0; i < size; i++) {
                for (int t = 0; t < size; t++) {
                    pw.setArgb((int) (a.getX() * size) + t,
                            (int) (a.getY() * size) + i,
                            a.getSprite()._pixels[t + i * size]);
                }
            }
        }

        return Sprite.resample(new ImageView(wr).getImage(),
                ConstGame.InfoObject.SCALED_SIZE / ConstGame.InfoObject.DEFAULT_SIZE);

    }

    public static Image mergerStaticImage(Image dsc, Base obj) {
        WritableImage wr = new WritableImage((int) dsc.getWidth(), (int) dsc.getHeight());
        PixelWriter pw = wr.getPixelWriter();
        int size = ConstGame.InfoObject.DEFAULT_SIZE;
        int scale = ConstGame.InfoObject.SCALED;
        final int xOffset = (int) (obj.getX() * size);
        final int yOffset = (int) (obj.getY() * size);
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                final int argb = obj.getSprite()._pixels[x + y * size];
                if (argb != 0) {
                    for (int i = 0; i < scale; i++) {
                        for (int t = 0; t < scale; t++) {
                            pw.setArgb(xOffset + x * t, yOffset + y * i, argb);
                        }
                    }
                }
            }
        }

        return new ImageView(wr).getImage();
    }


    /**
     * Xóa 1 vật thể khỏi staticImage.
     *
     * @param src Ảnh tĩnh gốc muốn xóa.
     * @param obj vật thể muốn xóa.
     * @return Hình ảnh đã xóa vật thể đi./
     */
    public static Image removeStaticObj(Image src, Base obj) {
        WritableImage wr = new WritableImage((int) src.getWidth(), (int) src.getHeight());
        PixelWriter pw = wr.getPixelWriter();
        PixelReader reader = src.getPixelReader();
        int size = ConstGame.InfoObject.SCALED_SIZE;
        for (int y = 0; y < (int) (src.getHeight()); y++) {
            for (int x = 0; x < (int) (src.getWidth()); x++) {
                if (x >= obj.getX() * size &&
                        x <= obj.getX() * size + size &&
                        y >= obj.getY() * size &&
                        y <= obj.getY() * size + size)
                    pw.setArgb(x, y, 0);
                else {
                    pw.setArgb(x, y, reader.getArgb(x, y));
                }
            }
        }
        return new ImageView(wr).getImage();
    }

    /**
     * Gộp các layer hình ảnh với nhau để chuẩn bị render.
     *
     * @param src hình ảnh muốn gộp    (lớp bên trên).
     * @param des hình ảnh dc gộp lên  (lớp bên dưới).
     * @return hình ảnh sau khi gộp
     */
    public static Image mergerLayerImage(Image src, Image des) {

        final int W = (int) des.getWidth();
        final int H = (int) des.getHeight();

        WritableImage output = new WritableImage(W, H);

        PixelReader readerSrc = src.getPixelReader();
        PixelReader readerDes = des.getPixelReader();

        PixelWriter writer = output.getPixelWriter();

        for (int y = 0; y < H; y++) {
            for (int x = 0; x < W; x++) {
                final int argb1 = readerDes.getArgb(x, y);
                final int argb2 = readerSrc.getArgb(x, y);

                writer.setArgb(x, y, argb2 == 0 ? argb1 : argb2);
            }
        }

        return output;
    }

    /**
     * vẽ 1 vật thể lên WritableImage cho trc.
     *
     * @param wr  ảnh tĩnh toàn map.
     * @param obj vật thể vẽ.
     * @return ảnh tĩnh dc vẽ thêm vật thể vào.
     */
    public WritableImage mergerObject(WritableImage wr, Base obj) {
        PixelWriter pw = wr.getPixelWriter();
        int size = ConstGame.InfoObject.DEFAULT_SIZE;
        for (int x = 0; x < ConstGame.InfoObject.DEFAULT_SIZE; x++) {
            for (int y = 0; y < ConstGame.InfoObject.DEFAULT_SIZE; y++) {
                if (obj.getSprite()._pixels[x + y * size] == ConstGame.InfoObject.TRANSPARENT_COLOR) {
                    pw.setArgb(x + (int) (obj.getX() * size),
                            y + (int) (obj.getY() * size),
                            0);
                } else {
                    pw.setArgb(x + (int) (obj.getX() * size), y + (int) (obj.getY() * size), obj.getSprite()._pixels[x + y * size]);
                }
            }
        }
        return wr;
    }
}

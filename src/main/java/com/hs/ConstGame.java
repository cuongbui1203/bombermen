package com.hs;

/**
 * các hằng số của game.
 */
public class ConstGame {
    /**
     * hằng số liên quan đến các thông số cơ bản của game
     * như tên,dường đẫn đến các file.
     */
    public static class InfoGame {
        public static final String NAME = "BomberMan";

        public static final int SIZE_TEXTURE = 256;
        public static final String PATH_TEXTURE = "/textures/classic.png";
        public static final String PATH_FXML = "guiGame.fxml";

        public static final String PATH_LOAD_MAP = "src/main/resources/levels/Level%d.txt";

        private static final int width = 17;
        public static final int WIDTH = InfoObject.SCALED_SIZE * width;

        private static final int height = 10;
        public static final int HEIGHT = InfoObject.SCALED_SIZE * height;

    }

    /**
     * hằng số liên quan đến thông tin của các vật thể trong game.
     */
    public static class InfoObject {
        public static final int DEFAULT_SIZE = 16;
        public static final int SCALED = 3;
        public static final int SCALED_SIZE = DEFAULT_SIZE * SCALED;
        public static final int TRANSPARENT_COLOR = 0xffff00ff;

        public static final int BOM_EXPLOSION_TIME = 3; // second.

        public static final float SPEED_ENEMY = 1.0F; // m/s.

        public static final float SPEED_CHARACTER = 0.02F; // m/s.

    }

    public static class Direction {
        public static final int UP = 1;
        public static final int DOWN = 2;
        public static final int LEFT = 3;
        public static final int RIGHT = 4;
    }
}

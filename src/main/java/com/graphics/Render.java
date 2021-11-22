package com.graphics;

public interface Render {
    void update();

    void render(ScreenGame screenGame);

    void render(ScreenGame screenGame,int now);
}

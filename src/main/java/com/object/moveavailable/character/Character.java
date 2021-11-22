package com.object.moveavailable.character;

import com.graphics.Sprite;
import com.hs.ConstGame;
import com.object.moveavailable.MoveAvailable;

import java.util.ConcurrentModificationException;

public abstract class Character extends MoveAvailable {
    public Character(float x, float y, Sprite sprite) {
        super(x, y, sprite);
    }
}

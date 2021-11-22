package com.object.moveavailable.enemy.ai;

import java.util.Random;

public abstract class Ai {
    Random random;
    public Ai(){
        random = new Random();
    }
    public abstract int calculateMove();
}

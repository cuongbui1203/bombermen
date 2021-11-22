package com.object.moveavailable.enemy.ai;

public class AiLow extends Ai {
    @Override
    public int calculateMove() {
        return Math.abs(random.nextInt()) % 4 + 1;
    }
}

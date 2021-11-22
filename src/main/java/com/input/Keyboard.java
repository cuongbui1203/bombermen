package com.input;

import javafx.scene.input.KeyEvent;

/**
 * calss xử lý sự kiện bàn phím.
 */
public class Keyboard {

    public boolean up = false;
    public boolean down = false;
    public boolean left = false;
    public boolean right = false;
    public boolean attack = false;

    public Keyboard(){
        System.out.println(this);
    }

    /**
     * hàm gọi khi phím dc bấm.
     * @param e even dc gọi.
     */
    public void keyPressed(KeyEvent e) {
        switch (e.getCode()) {
            case W, UP -> up = true;
            case S, DOWN -> down = true;
            case A, LEFT -> left = true;
            case D, RIGHT -> right = true;
            case SPACE -> attack = true;
        }
    }


    /**
     * hàm gọi khi phím dc nhả.
     * @param e even dc gọi.
     */
    public void keyReleased(KeyEvent e) {
        switch (e.getCode()) {
            case UP, W -> up = false;
            case S, DOWN -> down = false;
            case A, LEFT -> left = false;
            case D, RIGHT -> right = false;
            case SPACE -> attack = false;
        }
    }

}

package com.gui;

import com.hs.ConstGame;
import com.input.Keyboard;
import com.main.MainGame;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;

/**
 *class diều khiển và hiển thị giao diện.
 */
public class GuiController extends Application {
    @FXML
    Canvas canvas;
    @FXML
    Button btn;

    private static final Keyboard input = new Keyboard();

    private MainGame game;

    public static void main(String[] args) {
        launch();
    }

    @FXML
    private void initGame() {
        game = new MainGame(canvas, input);
        btn.setDisable(true);
    }
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GuiController.class.getResource(ConstGame.InfoGame.PATH_FXML));
        Scene scene = new Scene(fxmlLoader.load(), ConstGame.InfoGame.WIDTH, ConstGame.InfoGame.HEIGHT + 40);

        scene.setOnKeyPressed(keyEvent -> input.keyPressed(keyEvent));

        scene.setOnKeyReleased(keyEvent -> input.keyReleased(keyEvent));

        stage.setTitle(ConstGame.InfoGame.NAME);
//        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();

    }

    @Override
    public void stop() {
        System.exit(1);
    }
}

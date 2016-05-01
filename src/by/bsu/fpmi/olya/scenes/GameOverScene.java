package by.bsu.fpmi.olya.scenes;

import by.bsu.fpmi.olya.engine.Constants;
import by.bsu.fpmi.olya.engine.Game;
import by.bsu.fpmi.olya.engine.Scene;
import by.bsu.fpmi.olya.garphics.Texture;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by Lenovo on 01.05.2016.
 */
public class GameOverScene extends Scene {
    private Texture gameOverTexture;

    public GameOverScene(Game game, Texture gameOverTexture) {
        super(game);
        this.gameOverTexture = gameOverTexture;
    }

    @Override
    public void update(long nanosPassed) {
        for (KeyEvent event : game.getInputManager().getKeyPressedEvents()) {
            if (event.getKeyCode() == KeyEvent.VK_ENTER) {
                game.setScene(new MenuScene(game));
            }
        }
    }

    @Override
    public void draw(Graphics2D g) {
        gameOverTexture.draw(g, 0, 0);
    }
}

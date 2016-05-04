package by.bsu.fpmi.olya.scenes;

import by.bsu.fpmi.olya.constants.GameConstants;
import by.bsu.fpmi.olya.engine.Game;
import by.bsu.fpmi.olya.garphics.Texture;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by Lenovo on 27.04.2016.
 */
public class LevelPassedScene extends GameOverScene {

    private int score;
    private int target;
    private boolean passedSuccessful;

    public LevelPassedScene(Game game, Texture gameOverTexture, int target, int score, boolean passedSuccessful) {
        super(game, gameOverTexture);
        this.score = score;
        this.target = target;
        this.passedSuccessful = passedSuccessful;
    }

    @Override
    public void update(long nanosPassed) {
        for (KeyEvent event : game.getInputManager().getKeyPressedEvents()) {
            if (event.getKeyCode() == KeyEvent.VK_ENTER) {
                if (passedSuccessful) {
                    game.setScene(new MainScene(game, game.getLevelsManager().getCurrent()));
                } else {
                    game.setScene(new MenuScene(game));
                }
            }
        }
    }

    @Override
    public void draw(Graphics2D g) {
        super.draw(g);
        g.setFont(new Font("Georgia", Font.BOLD, 46));
        g.setColor(Color.WHITE);
        String status = score + " / " + target;
        g.drawString(status, (GameConstants.SCREEN_WIDTH * GameConstants.CELL_WIDTH - status.length() * 20) / 2,
                GameConstants.SCREEN_HEIGHT * GameConstants.CELL_HEIGHT * 3 / 5);
    }
}

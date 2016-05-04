package by.bsu.fpmi.olya.scenes;

import by.bsu.fpmi.olya.constants.GameConstants;
import by.bsu.fpmi.olya.engine.Game;
import by.bsu.fpmi.olya.engine.Scene;
import by.bsu.fpmi.olya.levels.Direction;
import by.bsu.fpmi.olya.garphics.Texture;
import by.bsu.fpmi.olya.constants.TextureConstants;
import by.bsu.fpmi.olya.levels.builders.LevelBuilder;
import by.bsu.fpmi.olya.levels.Level;
import by.bsu.fpmi.olya.managers.TimeManager;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by Lenovo on 23.04.2016.
 */
public class MainScene extends Scene {

    private Level level;

    private int currentLevelPosition;
    private int levelDelta;
    private Direction sheepXDirection;
    private Direction sheepYDirection;

    TimeManager timeManager;

    public MainScene(Game game, LevelBuilder levelBuilder) {
        super(game);
        sheepXDirection = Direction.RIGHT;
        sheepYDirection = Direction.REST;
        level = levelBuilder.build();
        timeManager = new TimeManager(GameConstants.DELAY);
    }

    @Override
    public void update(long nanosPassed) {
        if (timeManager.hasTimePassed(nanosPassed)) {
            applyInput();
            currentLevelPosition = level.update(currentLevelPosition, levelDelta, sheepXDirection, sheepYDirection/*, sheep*/);
            sheepXDirection = level.getXDirection();
            sheepYDirection = level.getYDirection();
            levelDelta = 0;

            if (level.getProgress() == Level.Progress.PASSED_FAIL){
                Texture texture = new Texture(TextureConstants.D_RESOURCES_TEXTURES + "\\" +
                        TextureConstants.D_MESSAGES + "\\" +
                        TextureConstants.MS_LEVEL_PASSED_FAIL);
                game.setScene(new LevelPassedScene(game, texture, level.getTarget(), level.getScore(), false));

            }
            if (level.getProgress() == Level.Progress.PASSED_SUCCESSFUL){
                if (game.getLevelsManager().goToNextLevel()){
                    Texture texture = new Texture(TextureConstants.D_RESOURCES_TEXTURES + "\\" +
                            TextureConstants.D_MESSAGES + "\\" +
                            TextureConstants.MS_LEVEL_PASSED_SUCCESS);
                    game.setScene(new LevelPassedScene(game, texture, level.getTarget(), level.getScore(), true));
                } else {
                    Texture texture = new Texture(TextureConstants.D_RESOURCES_TEXTURES + "\\" +
                            TextureConstants.D_MESSAGES + "\\" +
                            TextureConstants.MS_FINISH_SCENE);
                    game.setScene(new GameOverScene(game, texture));
                }

            }

        }
    }

    @Override
    public void draw(Graphics2D g) {
        currentLevelPosition = level.draw(g, currentLevelPosition/*, levelDelta, sheep*/);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Georgia", Font.PLAIN, 24));
        g.drawString("Level " + (game.getLevelsManager().getCurrentLevel() + 1),
                (GameConstants.SCREEN_WIDTH - 6) * GameConstants.CELL_WIDTH,
                GameConstants.CELL_HEIGHT * 2);



    }

    private void applyInput() {
        for (KeyEvent event : game.getInputManager().getKeyPressedEvents()) {
            switch (event.getKeyCode()) {
                case KeyEvent.VK_UP:
                    if (sheepYDirection == Direction.REST) {
                        sheepYDirection = Direction.UP;
                    } else {
                        sheepYDirection = sheepYDirection == Direction.REST ? Direction.REST : Direction.DOWN;
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    sheepXDirection = Direction.RIGHT;
                    levelDelta -= Direction.RIGHT.deltaX();
                    break;
                case KeyEvent.VK_DOWN:
                    sheepYDirection = Direction.DOWN;
                    break;
                case KeyEvent.VK_LEFT:
                    sheepXDirection = Direction.LEFT;
                    levelDelta -= Direction.LEFT.deltaX();
                    break;
                case KeyEvent.VK_ESCAPE:
                    game.setScene(new MenuScene(game));
                    break;
            }
        }

        for (KeyEvent event : game.getInputManager().getKeyReleasedEvents()) {
            switch (event.getKeyCode()) {
                case KeyEvent.VK_UP:
                    if (sheepYDirection == Direction.UP){
                        sheepYDirection = Direction.DOWN;
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    break;
                case KeyEvent.VK_DOWN:
                    break;
                case KeyEvent.VK_LEFT:
                    break;
            }
        }
    }

}

package by.bsu.fpmi.olya.scenes;

import by.bsu.fpmi.olya.engine.Constants;
import by.bsu.fpmi.olya.engine.Game;
import by.bsu.fpmi.olya.engine.Scene;
import by.bsu.fpmi.olya.entity.Direction;
import by.bsu.fpmi.olya.garphics.Texture;
import by.bsu.fpmi.olya.garphics.TextureConstants;
import by.bsu.fpmi.olya.levels.builders.LevelBuilder;
import by.bsu.fpmi.olya.levels.structure.Level;
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
        timeManager = new TimeManager(Constants.DELAY);
    }

    @Override
    public void update(long nanosPassed) {
        if (timeManager.hasTimePassed(nanosPassed)) {
            applyInput();
            currentLevelPosition = level.update(currentLevelPosition, levelDelta, sheepXDirection, sheepYDirection/*, sheep*/);
            sheepXDirection = level.getXDirection();
            sheepYDirection = level.getYDirection();
            levelDelta = 0;

        }
    }

    @Override
    public void draw(Graphics2D g) {
        currentLevelPosition = level.draw(g, currentLevelPosition/*, levelDelta, sheep*/);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Georgia", Font.PLAIN, 24));
        g.drawString("Level " + (game.getLevelsManager().getCurrentLevel() + 1),
                (Constants.SCREEN_WIDTH - 6) * Constants.CELL_WIDTH,
                Constants.CELL_HEIGHT * 2);

        if (level.getProgress() == Level.Progress.PASSED_FAIL){
            Texture texture = new Texture(TextureConstants.D_RESOURCES + "\\" +
                                                    TextureConstants.D_LANDSCAPES + "\\" +
                                                    TextureConstants.L_LEVEL_PASSED_FAIL);
            game.setScene(new LevelPassedScene(game, texture, level.getTarget(), level.getScore(), false));

        }
        if (level.getProgress() == Level.Progress.PASSED_SUCCESSFUL){
            if (game.getLevelsManager().goToNextLevel()){
                Texture texture = new Texture(TextureConstants.D_RESOURCES + "\\" +
                        TextureConstants.D_LANDSCAPES + "\\" +
                        TextureConstants.L_LEVEL_PASSED_SUCCESS);
                game.setScene(new LevelPassedScene(game, texture, level.getTarget(), level.getScore(), true));
            } else {
                Texture texture = new Texture(TextureConstants.D_RESOURCES + "\\" +
                        TextureConstants.D_LANDSCAPES + "\\" +
                        TextureConstants.L_FINISH_SCENE);
                game.setScene(new GameOverScene(game, texture));
            }

        }

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

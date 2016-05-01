package by.bsu.fpmi.olya.scenes;

import by.bsu.fpmi.olya.engine.Constants;
import by.bsu.fpmi.olya.engine.Game;
import by.bsu.fpmi.olya.engine.Scene;
import by.bsu.fpmi.olya.garphics.Texture;
import by.bsu.fpmi.olya.garphics.TextureConstants;
import by.bsu.fpmi.olya.levels.builders.LevelBuilder;
import by.bsu.fpmi.olya.levels.builders.SimpleLevelBuilder;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by Lenovo on 29.04.2016.
 */
public class MenuScene extends Scene {

    public enum Button {
        NEW_GAME,
        CONTINUE,
        EXIT;

        public Button getUpper(){
            switch (this){
                case NEW_GAME:
                    return EXIT;
                case CONTINUE:
                    return NEW_GAME;
                case EXIT:
                    return CONTINUE;
                default:
                    return NEW_GAME;
            }
        }

        public Button getLower(){
            switch (this){
                case NEW_GAME:
                    return CONTINUE;
                case CONTINUE:
                    return EXIT;
                case EXIT:
                    return NEW_GAME;
                default:
                    return NEW_GAME;
            }
        }
    }

    private Texture backGround;

    private MenuButton newGameLabel;
    private MenuButton continueLabel;
    private MenuButton exitLabel;

    private Button selectedButton;

    public MenuScene(Game game) {
        super(game);

        String directoryPath = TextureConstants.D_RESOURCES + "\\" + TextureConstants.D_MENU + "\\";
        this.backGround = new Texture(directoryPath + TextureConstants.M_MENU_BK_GROUND);

        newGameLabel = new MenuButton(new Texture(directoryPath + TextureConstants.M_NEW_GAME_A),
                                    new Texture(directoryPath + TextureConstants.M_NEW_GAME_P),
                                    /*new Texture(directoryPath + TextureConstants.M_NEW_GAME_NA),*/
                                    MenuButton.State.ACTIVE);
        continueLabel = new MenuButton(new Texture(directoryPath + TextureConstants.M_CONTINUE_A),
                                    new Texture(directoryPath + TextureConstants.M_CONTINUE_P),
                                    /*new Texture(directoryPath + TextureConstants.M_NEW_GAME_NA),*/
                                    MenuButton.State.PASSIVE);
        exitLabel = new MenuButton(new Texture(directoryPath + TextureConstants.M_EXIT_A),
                                    new Texture(directoryPath + TextureConstants.M_EXIT_P),
                                   /*new Texture(directoryPath + TextureConstants.M_NEW_GAME_NA),*/
                                    MenuButton.State.PASSIVE);

        selectedButton = Button.NEW_GAME;
        applySelection();
    }

    @Override
    public void update(long nanosPassed) {
        Button prevSelectedButton = selectedButton;
        for (KeyEvent event : game.getInputManager().getKeyPressedEvents()) {
            switch (event.getKeyCode()) {
                case KeyEvent.VK_UP:
                    selectedButton = selectedButton.getUpper();
                    break;
                case KeyEvent.VK_DOWN:
                    selectedButton = selectedButton.getLower();
                    break;
                case KeyEvent.VK_ENTER:
                    pressButton();
                    break;
            }
        }
        if (selectedButton != prevSelectedButton){
            applySelection();
        }
    }

    @Override
    public void draw(Graphics2D g) {
        backGround.draw(g, 0, 0);
        newGameLabel.draw(g, (backGround.size().width - newGameLabel.size().width) / (2 * Constants.CELL_WIDTH),
                backGround.size().height * 2 / (5 * Constants.CELL_HEIGHT));
        continueLabel.draw(g, (backGround.size().width - continueLabel.size().width) / (2 * Constants.CELL_WIDTH),
                backGround.size().height * 2 / (5 * Constants.CELL_HEIGHT) +
                        newGameLabel.size().height / Constants.CELL_HEIGHT + 2);
        exitLabel.draw(g, (backGround.size().width - exitLabel.size().width) / (2 * Constants.CELL_WIDTH),
                backGround.size().height * 2 / (5 * Constants.CELL_HEIGHT) +
                        newGameLabel.size().height / Constants.CELL_HEIGHT + 2 +
                        continueLabel.size().height / Constants.CELL_HEIGHT + 2);

    }

    private void applySelection(){
        newGameLabel.setState(MenuButton.State.PASSIVE);
        continueLabel.setState(MenuButton.State.PASSIVE);
        exitLabel.setState(MenuButton.State.PASSIVE);
        switch (selectedButton){
            case NEW_GAME:
                newGameLabel.setState(MenuButton.State.ACTIVE);
                break;
            case CONTINUE:
                continueLabel.setState(MenuButton.State.ACTIVE);
                break;
            case EXIT:
                exitLabel.setState(MenuButton.State.ACTIVE);
                break;
            default:
                newGameLabel.setState(MenuButton.State.ACTIVE);
        }
    }

    private void pressButton(){
        switch (selectedButton){
            case NEW_GAME:
                if (game.getLevelsManager() != null){
                    game.getLevelsManager().setCurrentLevel(0);
                    game.setScene(new MainScene(game, game.getLevelsManager().getCurrent()));
                }
                break;
            case CONTINUE:
                game.setScene(new MainScene(game, game.getLevelsManager().getCurrent()));
                break;
            case EXIT:
                game.stop();
                break;
        }
    }

}

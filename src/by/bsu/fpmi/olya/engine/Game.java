package by.bsu.fpmi.olya.engine;

import by.bsu.fpmi.olya.managers.LevelsManager;
import by.bsu.fpmi.olya.managers.InputManager;

/**
 * Created by Lenovo on 22.04.2016.
 */
public interface Game {

    void start();
    void pause();
    void stop();

    InputManager getInputManager();
    LevelsManager getLevelsManager();

    void setScene(Scene scene);


}

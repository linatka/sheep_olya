package by.bsu.fpmi.olya;

import by.bsu.fpmi.olya.engine.Game;
import by.bsu.fpmi.olya.engine.GameBuilder;
import by.bsu.fpmi.olya.levels.builders.SimpleLevelBuilder;
import by.bsu.fpmi.olya.scenes.MainScene;
import by.bsu.fpmi.olya.scenes.MenuScene;

import java.awt.*;

/**
 * Created by Lenovo on 23.04.2016.
 */
public class Runner {
    public static void main(String[] args) {
        Game game = GameBuilder.build(new Dimension(832, 760));
        game.setScene(new MenuScene(game));
        game.start();
    }
}

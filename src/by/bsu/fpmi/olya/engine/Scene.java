package by.bsu.fpmi.olya.engine;

import java.awt.*;

/**
 * Created by Lenovo on 22.04.2016.
 */
public abstract class Scene {

    protected Game game;

    public Scene(Game game) {
        this.game = game;
    }

    public abstract void update(long nanosPassed);
    public abstract void draw(Graphics2D g);

}

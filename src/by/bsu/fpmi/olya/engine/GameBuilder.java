package by.bsu.fpmi.olya.engine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by Lenovo on 23.04.2016.
 */
public class GameBuilder {

    public static Game build(Dimension screenSize) {

        JFrame frame = new JFrame();
        frame.setFocusable(false);

        frame.setSize(screenSize);

        frame.setResizable(false);
        frame.setLayout(new BorderLayout());

        GameCanvas game = new GameCanvas(screenSize){
            @Override
            public void stop(){
                super.stop();
                frame.setVisible(false);
            }
        };

        frame.add(game);
        frame.setVisible(true);

        game.createBufferStrategy(3);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                game.pause();
            }
        });

        frame.requestFocus();
        game.requestFocus();
        return game;
    }
}

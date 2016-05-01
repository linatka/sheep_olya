package by.bsu.fpmi.olya.engine;

import by.bsu.fpmi.olya.managers.LevelsManager;
import by.bsu.fpmi.olya.managers.InputManager;
import by.bsu.fpmi.olya.managers.OlyaLevelsManager;

import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by Lenovo on 22.04.2016.
 */
public class GameCanvas extends Canvas implements Game, Runnable {

    private Thread gameThread;
    private AtomicBoolean running;
    private InputManager inputManager;
    private Scene scene;
    private LevelsManager levelsManager;

    public GameCanvas(Dimension screenSize) {
        setSize(screenSize);
        running = new AtomicBoolean(false);

        initInputHandler();
        initFocusHandler();

        levelsManager = new OlyaLevelsManager();

    }

    void initInputHandler(){
        inputManager = new InputManager();
        addKeyListener(inputManager);
    }

    void initFocusHandler(){
        addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                start();
            }

            @Override
            public void focusLost(FocusEvent e) {
                pause();
            }
        });
    }

    @Override
    public void start() {
        if (running.compareAndSet(false, true)) {
            gameThread = new Thread(this);
            gameThread.start();
        }
    }
    @Override
    public void pause() {
        if (running.compareAndSet(true, false)) {
            try {
                gameThread.join();
            } catch (InterruptedException exception) {
                throw new RuntimeException(exception);//!!!!!!!!!!!!!!!!!!!
            }
        }
    }

    @Override
    public void stop(){
        if (gameThread.isInterrupted()){
            gameThread.interrupt();
        } else {
            start();
            gameThread.interrupt();
        }
    }

    @Override
    public InputManager getInputManager() {
        return inputManager;
    }

    @Override
    public LevelsManager getLevelsManager(){
        return levelsManager;
    }

    @Override
    public void setScene(Scene scene) {
        this.scene = scene;
    }

    @Override
    public void run() {
        long previousIterationTime = System.nanoTime();

        while (running.get()) {
            if (scene == null) {
                continue;
            }
            long now = System.nanoTime();
            long nanosPassed = now - previousIterationTime;
            previousIterationTime = now;
            Graphics2D g = (Graphics2D)getBufferStrategy().getDrawGraphics();
            scene.update(nanosPassed);
            scene.draw(g);
            getBufferStrategy().show();
            //System.out.println("thread is running");
        }
    }
}

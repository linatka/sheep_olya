package by.bsu.fpmi.olya.managers;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Lenovo on 22.04.2016.
 */
public class InputManager implements KeyListener {

    private final Collection<KeyEvent> keyPressedEvents;
    private final Collection<KeyEvent> keyReleasedEvents;


    public InputManager() {
        keyPressedEvents = new ArrayList<KeyEvent>();
        keyReleasedEvents = new ArrayList<KeyEvent>();
    }

    @Override
    public void keyTyped(KeyEvent event) {
    }

    @Override
    public synchronized void keyPressed(KeyEvent event) {
        keyPressedEvents.add(event);
    }

    public synchronized Collection<KeyEvent> getKeyPressedEvents() {
        Collection<KeyEvent> events = new ArrayList<KeyEvent>(keyPressedEvents);
        keyPressedEvents.clear();
        return events;
    }

    @Override
    public synchronized void keyReleased(KeyEvent event) {
        keyReleasedEvents.add(event);
    }

    public synchronized Collection<KeyEvent> getKeyReleasedEvents() {
        Collection<KeyEvent> events = new ArrayList<KeyEvent>(keyReleasedEvents);
        keyReleasedEvents.clear();
        return events;
    }
}

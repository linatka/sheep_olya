package by.bsu.fpmi.olya.managers;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by Lenovo on 02.05.2016.
 */
public class SoundManager {

    private final Map<String, String> soundMap;
    private final Map<String, Clip> loadedSounds;

    public SoundManager(){
        soundMap = new HashMap<>();
        loadedSounds = new HashMap<>();
    }

    public void setActionSound(String action, String soundFileName){
        soundMap.put(action, soundFileName);
    }

    public synchronized void playActionSound(String action) {

        Clip clip;
        if ((clip = loadedSounds.get(action)) != null && !clip.isRunning()){
            clip.start();
            return;
        }

        String soundFileName;
        if ((soundFileName = soundMap.get(action)) != null){
                    try {
                        clip = AudioSystem.getClip();
                        AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File("resources\\sounds\\sound_menu.wav"));
                        clip.open(inputStream);
                        clip.start();
                        clip.loop(Clip.LOOP_CONTINUOUSLY);
                        loadedSounds.put(action, clip);
                    } catch (LineUnavailableException e) {
                        System.err.println(e.getMessage());
                    } catch (UnsupportedAudioFileException e){
                        System.err.println("Unsupported audio file '" + soundFileName +"'.");
                    } catch (IOException e){
                        System.err.println(e.getMessage());
                    }
        }
    }

    public void stopActionSound(String action){
        Clip clip;
        if ((clip = loadedSounds.get(action)) != null && clip.isRunning()){
            clip.stop();
        }
    }

    public void stopAll(){
        loadedSounds.forEach((action, clip) ->{
            if (clip.isRunning()){
                clip.stop();
            }
        });
    }
}

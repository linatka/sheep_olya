package by.bsu.fpmi.olya.managers;

import by.bsu.fpmi.olya.levels.builders.LevelBuilder;

import java.util.ArrayList;

/**
 * Created by Lenovo on 01.05.2016.
 */
public class LevelsManager {

    private ArrayList<LevelBuilder> levelBuilders;
    private int currentLevel;

    public LevelsManager(){
        levelBuilders = new ArrayList<>();
        currentLevel = 0;
    }

    public LevelBuilder get(int i){
        if (0 <= i && i < levelBuilders.size()){
            return levelBuilders.get(i);
        }
        return null;
    }

    public LevelBuilder nextTo(int i){
        if (0 <= i && i < levelBuilders.size() - 1){
            return levelBuilders.get(i + 1);
        }
        return null;
    }

    public LevelBuilder getCurrent(){
        return levelBuilders.get(currentLevel);
    }

    public LevelBuilder nextToCurrent(){
        if (currentLevel < levelBuilders.size() - 1){
            return levelBuilders.get(currentLevel + 1);
        }
        return null;
    }

    public void add(LevelBuilder levelBuilder){
        levelBuilders.add(levelBuilder);
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public boolean setCurrentLevel(int currentLevel) {
        if (0 <= currentLevel && currentLevel < levelBuilders.size()){
            this.currentLevel = currentLevel;
            return true;
        }
        return false;
    }

    public boolean goToNextLevel(){
        if (currentLevel + 1 < levelBuilders.size()){
            currentLevel++;
            return true;
        } else {
            currentLevel = 0;
            return false;
        }
    }
}

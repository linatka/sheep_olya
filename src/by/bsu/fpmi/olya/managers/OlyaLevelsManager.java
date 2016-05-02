package by.bsu.fpmi.olya.managers;

import by.bsu.fpmi.olya.levels.builders.SecondLevelBuilder;
import by.bsu.fpmi.olya.levels.builders.SimpleLevelBuilder;

import java.util.ArrayList;

/**
 * Created by Lenovo on 01.05.2016.
 */
public class OlyaLevelsManager extends LevelsManager{
    public OlyaLevelsManager(){
        add(new SecondLevelBuilder());
        add(new SimpleLevelBuilder());
    }
}
package by.bsu.fpmi.olya.levels.builders;

import by.bsu.fpmi.olya.engine.Constants;
import by.bsu.fpmi.olya.entity.Enemy;
import by.bsu.fpmi.olya.entity.Sheep;
import by.bsu.fpmi.olya.garphics.Texture;
import by.bsu.fpmi.olya.garphics.TextureConstants;
import by.bsu.fpmi.olya.levels.Landscape;
import by.bsu.fpmi.olya.levels.Prize;
import by.bsu.fpmi.olya.levels.structure.Level;
import by.bsu.fpmi.olya.levels.structure.LevelInitializer;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Lenovo on 25.04.2016.
 */
public class SimpleLevelBuilder extends LevelBuilder{

    @Override
    public Level build(){

        final int FLOOR = 3;

        Sheep sheep = new Sheep(buildPath(TextureConstants.D_RESOURCES,
                                            TextureConstants.D_OBJECTS,
                                            TextureConstants.D_SIMPLE_LEVEL_OLYA),
                Constants.DEFAULT_SHEEP_X, Constants.SCREEN_HEIGHT - FLOOR);
        sheep.setY(sheep.getY() - sheep.HEIGHT);

        Map<Level.Attribute, Texture> attributes = new HashMap<>();
        attributes.put(Level.Attribute.HEALTH_0, new Texture(buildPath(TextureConstants.D_RESOURCES,
                                                                        TextureConstants.D_ATTRIBUTES,
                                                                        TextureConstants.A_HEALTH_0)));
        attributes.put(Level.Attribute.HEALTH_1, new Texture(buildPath(TextureConstants.D_RESOURCES,
                                                                        TextureConstants.D_ATTRIBUTES,
                                                                        TextureConstants.A_HEALTH_1)));
        attributes.put(Level.Attribute.HEALTH_2, new Texture(buildPath(TextureConstants.D_RESOURCES,
                                                                        TextureConstants.D_ATTRIBUTES,
                                                                        TextureConstants.A_HEALTH_2)));
        attributes.put(Level.Attribute.HEALTH_3, new Texture(buildPath(TextureConstants.D_RESOURCES,
                                                                        TextureConstants.D_ATTRIBUTES,
                                                                        TextureConstants.A_HEALTH_3)));
        attributes.put(Level.Attribute.PRIZES, new Texture(buildPath(TextureConstants.D_RESOURCES,
                                                                        TextureConstants.D_ATTRIBUTES,
                                                                        TextureConstants.A_APPLE)));

        LevelInitializer initializer = new LevelInitializer(Constants.SCREEN_WIDTH * 10,
                new Texture(buildPath(TextureConstants.D_RESOURCES,
                                        TextureConstants.D_LANDSCAPES,
                                        TextureConstants.L_SKY)), sheep, attributes);

        initializer.addStructure(new Landscape(initializer.getLength(), FLOOR,
                new Texture(buildPath(TextureConstants.D_RESOURCES,
                                        TextureConstants.D_LANDSCAPES,
                                        TextureConstants.L_GRASS)), true), 0, Constants.SCREEN_HEIGHT - FLOOR);
        initializer.addStructure(getFlowerGrassBlock(20, 2, true), 40, Constants.SCREEN_HEIGHT - FLOOR - 15);
        initializer.addStructure(getFlowerGrassBlock(20, 2, true), 80, Constants.SCREEN_HEIGHT - FLOOR - 15);
        initializer.addStructure(getFlowerGrassBlock(40, 2, true), 100, Constants.SCREEN_HEIGHT - FLOOR - 25);
        initializer.addStructure(getFlower(), 48, Constants.SCREEN_HEIGHT - 8);
        for (int i = 50; i < Constants.SCREEN_HEIGHT * 8; i += 50){
            initializer.addStructure(getApple(), i, Constants.SCREEN_HEIGHT - 10);
        }
        initializer.addEnemy(getWolf(110, Constants.SCREEN_HEIGHT - FLOOR, 60));

        return new Level(initializer);
    }



}

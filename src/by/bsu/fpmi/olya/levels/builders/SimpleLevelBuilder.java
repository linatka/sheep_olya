package by.bsu.fpmi.olya.levels.builders;

import by.bsu.fpmi.olya.constants.GameConstants;
import by.bsu.fpmi.olya.constants.PathBuilder;
import by.bsu.fpmi.olya.entity.Sheep;
import by.bsu.fpmi.olya.garphics.Texture;
import by.bsu.fpmi.olya.constants.TextureConstants;
import by.bsu.fpmi.olya.levels.structures.Landscape;
import by.bsu.fpmi.olya.levels.Level;
import by.bsu.fpmi.olya.levels.LevelInitializer;

import java.util.Map;

/**
 * Created by Lenovo on 25.04.2016.
 */
public class SimpleLevelBuilder extends LevelBuilder{

    @Override
    public Level build(){

        final int FLOOR = 3;

        Sheep sheep = getSimpleSheep();
        sheep.setY(sheep.getY() - sheep.HEIGHT - FLOOR);

        Map<Level.Attribute, Texture> attributes = getDefaultAttributesMap();

        LevelInitializer initializer = new LevelInitializer(GameConstants.SCREEN_WIDTH * 10,
                new Texture(PathBuilder.buildPath(TextureConstants.D_RESOURCES_TEXTURES,
                                        TextureConstants.D_LANDSCAPES,
                                        TextureConstants.L_SKY)), sheep, attributes);

        initializer.addStructure(new Landscape(initializer.getLength(), FLOOR,
                new Texture(PathBuilder.buildPath(TextureConstants.D_RESOURCES_TEXTURES,
                                        TextureConstants.D_LANDSCAPES,
                                        TextureConstants.L_GRASS)), true), 0, GameConstants.SCREEN_HEIGHT - FLOOR);
        initializer.addStructure(getFlowerGrassBlock(20, 2, true), 40, GameConstants.SCREEN_HEIGHT - FLOOR - 15);
        initializer.addStructure(getFlowerGrassBlock(20, 2, true), 80, GameConstants.SCREEN_HEIGHT - FLOOR - 15);
        initializer.addStructure(getFlowerGrassBlock(40, 2, true), 100, GameConstants.SCREEN_HEIGHT - FLOOR - 25);
        initializer.addStructure(getFlower(), 48, GameConstants.SCREEN_HEIGHT - 8);
        for (int i = 50; i < GameConstants.SCREEN_HEIGHT * 8; i += 50){
            initializer.addStructure(getApple(), i, GameConstants.SCREEN_HEIGHT - 10);
        }
        initializer.addEnemy(getWolf(110, GameConstants.SCREEN_HEIGHT - FLOOR, 60));

        return new Level(initializer);
    }



}

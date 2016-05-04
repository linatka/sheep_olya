package by.bsu.fpmi.olya.levels.builders;

import by.bsu.fpmi.olya.constants.GameConstants;
import by.bsu.fpmi.olya.constants.PathBuilder;
import by.bsu.fpmi.olya.entity.Enemy;
import by.bsu.fpmi.olya.entity.Sheep;
import by.bsu.fpmi.olya.garphics.Texture;
import by.bsu.fpmi.olya.constants.TextureConstants;
import by.bsu.fpmi.olya.levels.LevelException;
import by.bsu.fpmi.olya.levels.structures.ComplexLandscape;
import by.bsu.fpmi.olya.levels.structures.Landscape;
import by.bsu.fpmi.olya.levels.structures.Prize;
import by.bsu.fpmi.olya.levels.Level;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Lenovo on 29.04.2016.
 */
public abstract class LevelBuilder {

    public abstract Level build();

    public Landscape getFlowerGrassBlock(int width, int height, boolean isRigid){
        return new Landscape(width, height, new Texture(PathBuilder.buildPath(TextureConstants.D_RESOURCES_TEXTURES,
                TextureConstants.D_LANDSCAPES,
                TextureConstants.L_FLOWER_GRASS)), isRigid);
    }

    public Prize getFlower(){
        return new Prize("Flower", new Texture(PathBuilder.buildPath(TextureConstants.D_RESOURCES_TEXTURES,
                TextureConstants.D_LANDSCAPES,
                TextureConstants.P_FLOWER)), 10);
    }

    public Prize getApple(){
        return new Prize("Apple", new Texture(PathBuilder.buildPath(TextureConstants.D_RESOURCES_TEXTURES,
                TextureConstants.D_LANDSCAPES,
                TextureConstants.P_APPLE)), 20);
    }

    public Enemy getWolf(int x, int bottom, int motionDistance){
        Enemy enemy = new Enemy("Wolf", PathBuilder.buildPath(TextureConstants.D_RESOURCES_TEXTURES,
                TextureConstants.D_OBJECTS,
                TextureConstants.D_ENEMIES,
                TextureConstants.D_WOLF), x, 0, motionDistance);
        enemy.incY(bottom - enemy.HEIGHT);
        return enemy;
    }

//    public String buildPath(String ...args){
//        if (args.length != 0){
//            StringBuilder sb = new StringBuilder();
//            for (int i = 0; i < args.length - 1; i++){
//                sb.append(args[i]);
//                sb.append(File.separator);
//            }
//            sb.append(args[args.length - 1]);
//            return sb.toString();
//        }
//        return "";
//    }

    public Sheep getSimpleSheep(){
        return new Sheep(PathBuilder.buildPath(TextureConstants.D_RESOURCES_TEXTURES,
                TextureConstants.D_OBJECTS,
                TextureConstants.D_SIMPLE_LEVEL_OLYA),
                GameConstants.DEFAULT_SHEEP_X, GameConstants.SCREEN_HEIGHT);
    }

    public Map<Level.Attribute, Texture> getDefaultAttributesMap(){
        Map<Level.Attribute, Texture> attributes = new HashMap<>();
        attributes.put(Level.Attribute.HEALTH_0, new Texture(PathBuilder.buildPath(TextureConstants.D_RESOURCES_TEXTURES,
                TextureConstants.D_ATTRIBUTES,
                TextureConstants.A_HEALTH_0)));
        attributes.put(Level.Attribute.HEALTH_1, new Texture(PathBuilder.buildPath(TextureConstants.D_RESOURCES_TEXTURES,
                TextureConstants.D_ATTRIBUTES,
                TextureConstants.A_HEALTH_1)));
        attributes.put(Level.Attribute.HEALTH_2, new Texture(PathBuilder.buildPath(TextureConstants.D_RESOURCES_TEXTURES,
                TextureConstants.D_ATTRIBUTES,
                TextureConstants.A_HEALTH_2)));
        attributes.put(Level.Attribute.HEALTH_3, new Texture(PathBuilder.buildPath(TextureConstants.D_RESOURCES_TEXTURES,
                TextureConstants.D_ATTRIBUTES,
                TextureConstants.A_HEALTH_3)));
        attributes.put(Level.Attribute.PRIZES, new Texture(PathBuilder.buildPath(TextureConstants.D_RESOURCES_TEXTURES,
                TextureConstants.D_ATTRIBUTES,
                TextureConstants.A_APPLE)));
        return attributes;
    }

    public ComplexLandscape getHome() throws LevelException{
        return new ComplexLandscape(new Texture(PathBuilder.buildPath(TextureConstants.D_RESOURCES_TEXTURES,
                TextureConstants.D_LANDSCAPES,
                TextureConstants.L_HOME)), false, "home");
    }

    public Landscape getBlock(){
        return new Landscape(1, GameConstants.SCREEN_HEIGHT,
                new Texture(PathBuilder.buildPath(TextureConstants.D_RESOURCES_TEXTURES,
                        TextureConstants.D_LANDSCAPES,
                        TextureConstants.L_BLOCK)), true);
    }

}

package by.bsu.fpmi.olya.levels.builders;

import by.bsu.fpmi.olya.entity.Enemy;
import by.bsu.fpmi.olya.garphics.Texture;
import by.bsu.fpmi.olya.garphics.TextureConstants;
import by.bsu.fpmi.olya.levels.Landscape;
import by.bsu.fpmi.olya.levels.Prize;
import by.bsu.fpmi.olya.levels.structure.Level;

import java.io.File;

/**
 * Created by Lenovo on 29.04.2016.
 */
public abstract class LevelBuilder {

    public abstract Level build();

    public Landscape getFlowerGrassBlock(int width, int height, boolean isRigid){
        return new Landscape(width, height, new Texture(buildPath(TextureConstants.D_RESOURCES,
                TextureConstants.D_LANDSCAPES,
                TextureConstants.L_FLOWER_GRASS)), isRigid);
    }

    public Prize getFlower(){
        return new Prize("Flower", new Texture(buildPath(TextureConstants.D_RESOURCES,
                TextureConstants.D_LANDSCAPES,
                TextureConstants.P_FLOWER)), 10);
    }

    public Prize getApple(){
        return new Prize("Apple", new Texture(buildPath(TextureConstants.D_RESOURCES,
                TextureConstants.D_LANDSCAPES,
                TextureConstants.P_APPLE)), 20);
    }

    public Enemy getWolf(int x, int bottom, int motionDistance){
        Enemy enemy = new Enemy("Wolf", buildPath(TextureConstants.D_RESOURCES,
                TextureConstants.D_OBJECTS,
                TextureConstants.D_ENEMIES,
                TextureConstants.D_WOLF), x, 0, motionDistance);
        enemy.incY(bottom - enemy.HEIGHT);
        return enemy;
    }

    public String buildPath(String ...args){
        if (args.length != 0){
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < args.length - 1; i++){
                sb.append(args[i]);
                sb.append(File.separator);
            }
            sb.append(args[args.length - 1]);
            return sb.toString();
        }
        return "";
    }

}

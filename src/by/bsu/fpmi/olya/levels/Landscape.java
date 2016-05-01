package by.bsu.fpmi.olya.levels;

import by.bsu.fpmi.olya.garphics.Texture;
import by.bsu.fpmi.olya.levels.structure.LevelStructure;

/**
 * Created by Lenovo on 24.04.2016.
 */
public class Landscape extends LevelStructure {

    public Landscape(/*StructureType type,*/ int width, int height, Texture texture, boolean isRigid) {
        //this.type = type;
        super(texture, width, height, isRigid);
    }
}
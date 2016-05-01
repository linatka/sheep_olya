package by.bsu.fpmi.olya.levels.structure;

import by.bsu.fpmi.olya.garphics.Texture;

/**
 * Created by Lenovo on 24.04.2016.
 */
public abstract class LevelStructure {
    private Texture texture;
    private int width;
    private int height;
    private boolean isRigid;

//    public LevelStructure(Texture texture, int width, int height) {
//        this.texture = texture;
//        this.width = width;
//        this.height = height;
//    }

    public LevelStructure(Texture texture, int width, int height, boolean isRigid) {
        this.texture = texture;
        this.width = width;
        this.height = height;
        this.isRigid = isRigid;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isRigid() {
        return isRigid;
    }

    public void setRigid(boolean rigid) {
        isRigid = rigid;
    }
}

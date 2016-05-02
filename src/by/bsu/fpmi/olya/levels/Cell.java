package by.bsu.fpmi.olya.levels;

import by.bsu.fpmi.olya.garphics.Texture;

/**
 * Created by Lenovo on 24.04.2016.
 */


public class Cell{
    public enum CellType{
        PRIZE,
        LANDSCAPE,
        ENEMY
    }

    private CellType type;
    private Texture texture;
    private boolean isRigid;

    public Cell(CellType type, Texture texture, boolean isRigid) {
        this.type = type;
        this.texture = texture;
        this.isRigid = isRigid;
    }

    public CellType getType() {
        return type;
    }

    public void setType(CellType type) {
        this.type = type;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public boolean isRigid() {
        return isRigid;
    }

    public void setRigid(boolean rigid) {
        isRigid = rigid;
    }
}

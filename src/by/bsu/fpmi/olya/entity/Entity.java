package by.bsu.fpmi.olya.entity;

import by.bsu.fpmi.olya.constants.GameConstants;
import by.bsu.fpmi.olya.garphics.Texture;
import by.bsu.fpmi.olya.levels.Direction;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Lenovo on 25.04.2016.
 */
public abstract class Entity {

    public enum MotionControlMode {
        AUTO,
        CONTROLLED;
    }

    public final int WIDTH;
    public final int HEIGHT;

    private int x;
    private int y;

    private Direction yDirection;
    private Direction xDirection;
    private final Map<Direction, Texture> textures;

    private MotionControlMode controlMode;

    public Entity(String path, int x, int y, MotionControlMode controlMode){
        textures = new HashMap<>();
        textures.put(Direction.LEFT, new Texture(path + "\\" + "left.png"));
        textures.put(Direction.RIGHT, new Texture(path + "\\" + "right.png"));
        this.x = x;
        this.y = y;
        WIDTH = textures.get(Direction.LEFT).size().width / GameConstants.CELL_WIDTH;
        HEIGHT = textures.get(Direction.LEFT).size().height / GameConstants.CELL_HEIGHT;
        xDirection = Direction.RIGHT;
        yDirection = Direction.REST;
        this.controlMode = controlMode;
    }

    public int getX() {
        return x;
    }

    public int incX(int inc){
        x += inc;
        return x;
    }

    public int getY() {
        return y;
    }

    public int incY(int inc){
        y += inc;
        if (y > GameConstants.SCREEN_HEIGHT - HEIGHT){
            y = GameConstants.SCREEN_HEIGHT - HEIGHT;
            yDirection = Direction.REST;
        }
        if (y < GameConstants.SCREEN_HEIGHT - GameConstants.MAX_JUMPING_HEIGHT){
            y = GameConstants.SCREEN_HEIGHT - GameConstants.MAX_JUMPING_HEIGHT;
            yDirection = Direction.DOWN;
        }
        return y;
    }

    public Texture getTexture(Direction direction) {
        return textures.get(direction);
    }

    public void setTexture(Texture texture, Direction direction) {
        this.textures.put(direction, texture);
    }

    public Direction getXDirection() {
        return xDirection;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setXDirection(Direction xDirection) {
        this.xDirection = xDirection;
    }

    public Direction getYDirection() {
        return yDirection;
    }

    public void setYDirection(Direction yDirection) {
        this.yDirection = yDirection;
    }

    public MotionControlMode getControlMode() {
        return controlMode;
    }

    public void draw(Graphics2D g){
        textures.get(xDirection).draw(g, x, y);
    }

    public boolean isIntersection(int x, int y, int width, int height){
        return  !((x + width < this.x || x > this.x + this.WIDTH)
                || (y + height < this.y || y > this.y + this.HEIGHT));
    }
}

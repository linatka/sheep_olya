package by.bsu.fpmi.olya.entity;

import by.bsu.fpmi.olya.garphics.Texture;

import java.awt.*;

/**
 * Created by Lenovo on 25.04.2016.
 */
public class Enemy extends Entity {

    private String name;
    private int motionDistance;
    private int initX;
    private int initY;


    public Enemy(String name, String path, int x, int y, int motionDistance) {
        super(path, x, y, MotionControlMode.AUTO);
        this.name = name;
        this.motionDistance = motionDistance;
        this.initX = x;
        this.initY = y;
    }

    @Override
    public int incX(int inc) {
        if (getX() + inc < initX || getX() + inc > initX + motionDistance){
            setXDirection(getXDirection().oppositeDirection());
            inc = getXDirection().deltaX();
        }
        return super.incX(inc);
    }

    @Override
    public int incY(int inc) {
        return super.incY(inc);
    }

    public int getMotionDistance() {
        return motionDistance;
    }

    public void setMotionDistance(int motionDistance) {
        this.motionDistance = motionDistance;
    }

    public int getInitX() {
        return initX;
    }

    public int getInitY() {
        return initY;
    }

    public void move(){
        incX(-getXDirection().deltaX());
        incY(-getYDirection().deltaY());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void draw(Graphics2D g, int x, int y) {
        int prevX = getX();
        int prevY = getY();
        setX(x);
        setY(y);
        super.draw(g);
        setX(prevX);
        setY(prevY);

    }
}

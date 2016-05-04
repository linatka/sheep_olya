package by.bsu.fpmi.olya.entity;

import by.bsu.fpmi.olya.engine.Constants;
import by.bsu.fpmi.olya.levels.Direction;

/**
 * Created by Lenovo on 23.04.2016.
 */
public class Sheep extends Entity{

    private int health;
    private int prize;


    public Sheep(String path, int x, int y) {
        super(path, x, y, MotionControlMode.CONTROLLED);
        this.health = Constants.DEFAULT_HEALTH;
    }

    public int getHealth() {
        return health;
    }

    public boolean getInjured(Direction dangerDirection){
        setXDirection(dangerDirection.oppositeDirection());
        health--;
        return health < 0;
    }

    public int treat(){
        return ++health;
    }

    public int getPrize() {
        return prize;
    }

    public void incPrize(int inc) {
        this.prize += inc;
    }
}

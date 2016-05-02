package by.bsu.fpmi.olya.levels;

/**
 * Created by Lenovo on 23.04.2016.
 */
public enum Direction {

    LEFT,
    RIGHT,
    UP,
    DOWN,
    REST;

    public int deltaX(){
        switch (this){
            case LEFT:
                return 1;
            case RIGHT:
                return -1;
            default:
                return 0;
        }
    }

    public int deltaY(){
        switch (this){
            case UP:
                return -1;
            case DOWN:
                return 1;
            default:
                return 0;
        }
    }

    public Direction nexBy() {
        switch (this) {
            case UP:
                return DOWN;
            case DOWN:
                return REST;
            case REST:
                return DOWN;
            default:
                return this;
        }
    }

    public Direction oppositeDirection() {
        switch (this) {
            case LEFT:
                return RIGHT;
            case RIGHT:
                return LEFT;
            case UP:
                return DOWN;
            case DOWN:
                return UP;
            default:
                return this;
        }
    }
}


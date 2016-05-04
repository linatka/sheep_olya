package by.bsu.fpmi.olya.engine;

import java.awt.*;

/**
 * Created by Lenovo on 02.05.2016.
 */
public class GameDimension extends Dimension{

    public enum Unit {
        PIXEL,
        CELL
    }
//
//    // values in pixels //
//    private int width;
//    private int height;

    public GameDimension(int width, int height, Unit unit){
        switch (unit){
            case PIXEL:
                this.width = width;
                this.height = height;
                break;
            case CELL:
                this.width = width * Constants.CELL_WIDTH;
                this.height = height * Constants.CELL_HEIGHT;
                break;
        }
    }

    public GameDimension(Dimension dimension){
        super(dimension);
    }

    public int getWidthInCell(){
        return width / Constants.CELL_WIDTH;
    }

    public int getHeightInCell(){
        return height / Constants.CELL_HEIGHT;
    }

    public void setWidth(int width, Unit unit){
        switch(unit){
            case PIXEL:
                this.width = width;
                break;
            case CELL:
                this.width = width * Constants.CELL_WIDTH;
                break;
        }
    }

    public void setHeight(int height, Unit unit){
        switch(unit){
            case PIXEL:
                this.height = height;
                break;
            case CELL:
                this.height = height * Constants.CELL_WIDTH;
                break;
        }
    }

}

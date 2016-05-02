package by.bsu.fpmi.olya.scenes;

import by.bsu.fpmi.olya.engine.GameDimension;
import by.bsu.fpmi.olya.garphics.Texture;

import java.awt.*;

/**
 * Created by Lenovo on 29.04.2016.
 */
public class MenuButton {

    public enum State{
        ACTIVE,
        //NOT_AVAILABLE,
        PASSIVE
    }

    private Texture activeTexture;
    private Texture passiveTexture;
    //private Texture notAvailableTexture;
    private State state;

    public MenuButton(Texture activeTexture, Texture passiveTexture, /*Texture notAvailableTexture,*/
                     State state) {
        this.activeTexture = activeTexture;
        this.passiveTexture = passiveTexture;
        //this.notAvailableTexture = notAvailableTexture;
        this.state = state;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void draw(Graphics2D g, int x, int y){
        switch (state){
            case ACTIVE:
                activeTexture.draw(g, x, y);
                break;
            case PASSIVE:
                passiveTexture.draw(g, x, y);
                break;
//            case NOT_AVAILABLE:
//                notAvailableTexture.draw(g, x, y);
//                break;
            default:
        }
    }

    public GameDimension size(){
        if (state == State.PASSIVE){
            return passiveTexture.size();
        }
        return activeTexture.size(); // replace for current state size
    }
}

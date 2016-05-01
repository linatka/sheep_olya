package by.bsu.fpmi.olya.levels;

import by.bsu.fpmi.olya.entity.Sheep;
import by.bsu.fpmi.olya.garphics.Texture;
import by.bsu.fpmi.olya.levels.structure.LevelStructure;

/**
 * Created by Lenovo on 23.04.2016.
 */
public class Prize extends LevelStructure {
    private String name;
    //private boolean isHealing;
    private int price;

    public Prize(String name, Texture texture,/* boolean isHealing,*/ int price) {
        super(texture, 1, 1, false);
        this.name = name;
        //this.isHealing = isHealing;
        this.price = price;
    }

//    public boolean isHealing() {
//        return isHealing;
//    }
//
//    public void setHealing(boolean healing) {
//        this.isHealing = healing;
//    }

    public double getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void applyTo(Sheep sheep){
//        if (isHealing){
//            sheep.treat();
//        }
        sheep.incPrize(price);
    }
}

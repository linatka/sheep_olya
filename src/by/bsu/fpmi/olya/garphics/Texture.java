package by.bsu.fpmi.olya.garphics;

import by.bsu.fpmi.olya.engine.Constants;
import by.bsu.fpmi.olya.managers.TextureManager;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Lenovo on 23.04.2016.
 */
public class Texture {

    private final static Map<String, TextureManager> sourcesMap = new HashMap<>();
    private TextureManager manager;
    //private String fileName;

    public Texture(String fileName){
        //this.fileName = fileName;
        TextureManager mappedManager = sourcesMap.get(fileName);
        if (mappedManager == null){
            try {
                manager = new TextureManager();
                manager.setImage(ImageIO.read(new File(fileName)));
                sourcesMap.put(fileName, manager);
                System.out.println(fileName);
            } catch (IOException e){
                System.err.println(e.getMessage());
            }
        } else {
            manager = mappedManager;
            manager.addReference();
        }
    }

    @Override
    protected void finalize() throws Throwable {
        if (manager.removeReference()){
            super.finalize();
        }
    }

    public void draw(Graphics g, int x, int y){
        g.drawImage(manager.getImage(), x * Constants.CELL_WIDTH, y * Constants.CELL_HEIGHT, null);
    }

    public Dimension size(){
        return manager.size();
    }

}

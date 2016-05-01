package by.bsu.fpmi.olya.managers;

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
public class TextureManager extends ResourcesManager{

    private BufferedImage image;

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public Dimension size(){
        return new Dimension(image.getWidth(), image.getHeight());
    }
}

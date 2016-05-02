package by.bsu.fpmi.olya.levels.structures;

import by.bsu.fpmi.olya.engine.Constants;
import by.bsu.fpmi.olya.garphics.Texture;
import by.bsu.fpmi.olya.garphics.TextureConstants;
import by.bsu.fpmi.olya.levels.Level;
import by.bsu.fpmi.olya.levels.LevelException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.PixelGrabber;
import java.io.File;
import java.io.IOException;

/**
 * Created by Lenovo on 17.04.2016.
 */
public class TexturesProducer {

    private Texture sourceTexture;
    private String filesName;

    public TexturesProducer(Texture texture, String filesName){
        this.sourceTexture = texture;
        this.filesName = filesName;
    }

    public Texture[][] produce() throws LevelException {

        int hPiecesCount = sourceTexture.size().getWidthInCell();
        int vPiecesCount = sourceTexture.size().getHeightInCell();

        Texture[][] cells = new Texture[hPiecesCount][vPiecesCount];

        for (int i = 0; i < hPiecesCount; i++) {
            for (int j = 0; j < vPiecesCount; j++) {
                int[] pixels = new int[Constants.CELL_WIDTH * Constants.CELL_HEIGHT * 3];
                PixelGrabber pg = new PixelGrabber(sourceTexture.getImage(),
                        i * Constants.CELL_WIDTH, j * Constants.CELL_HEIGHT,
                        Constants.CELL_WIDTH, Constants.CELL_HEIGHT, pixels,
                        0, Constants.CELL_WIDTH * 3);
                try {
                    pg.grabPixels();
                } catch (InterruptedException e) {
                    throw new LevelException("Cell producing time error (Interrupted exception).");
                }
                if ((pg.getStatus() & ImageObserver.ABORT) != 0) {
                    throw new LevelException("Cell producing error: PixelGrabber can't parse image.");
                }

                String path = TextureConstants.D_RESOURCES + "\\" +
                        TextureConstants.D_TEMP + "\\" + filesName + i + j +".png";
                try {
                    File file = new File(path);
//                    if (!file.exists()){
//                        if (!file.createNewFile()){
//                            throw new LevelException("Cell producing error: can't create temp textures files");
//                        };
//                    }
                    ImageIO.write(getImageFromArray(pixels, Constants.CELL_WIDTH, Constants.CELL_HEIGHT), "png",
                           file);
                } catch (IOException e){
                    throw new LevelException("Cell producing error: can't write to temp texture file.");
                }
                cells[i][j] = new Texture(path);
            }

        }
        return cells;
    }

    private BufferedImage getImageFromArray(int[] pixels, int width, int height) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        image.setRGB(0, 0, width, height, pixels, 0, width * 3);
        return image;
    }

}

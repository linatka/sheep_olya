package by.bsu.fpmi.olya.levels.structures;

import by.bsu.fpmi.olya.constants.GameConstants;
import by.bsu.fpmi.olya.garphics.Texture;
import by.bsu.fpmi.olya.constants.TextureConstants;
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
                int[] pixels = new int[GameConstants.CELL_WIDTH * GameConstants.CELL_HEIGHT * 3];
                PixelGrabber pg = new PixelGrabber(sourceTexture.getImage(),
                        i * GameConstants.CELL_WIDTH, j * GameConstants.CELL_HEIGHT,
                        GameConstants.CELL_WIDTH, GameConstants.CELL_HEIGHT, pixels,
                        0, GameConstants.CELL_WIDTH * 3);
                try {
                    pg.grabPixels();
                } catch (InterruptedException e) {
                    throw new LevelException("Cell producing time error (Interrupted exception).");
                }
                if ((pg.getStatus() & ImageObserver.ABORT) != 0) {
                    throw new LevelException("Cell producing error: PixelGrabber can't parse image.");
                }

                String path = TextureConstants.D_RESOURCES_TEXTURES + "\\" +
                        TextureConstants.D_TEMP + "\\" + filesName + i + j +".png";
                try {
                    File file = new File(path);
                    if (!file.exists()){
                    ImageIO.write(getImageFromArray(pixels, GameConstants.CELL_WIDTH, GameConstants.CELL_HEIGHT), "png",
                            file);
                        System.out.println("Create new file home__");
                    }

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

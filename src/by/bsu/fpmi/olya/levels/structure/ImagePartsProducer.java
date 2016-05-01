package by.bsu.fpmi.olya.levels.structure;

import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.PixelGrabber;

/**
 * Created by Lenovo on 17.04.2016.
 */
public class ImagePartsProducer {

    private BufferedImage sourceImage;

    public ImagePartsProducer(BufferedImage sourceImage){
        this.sourceImage = sourceImage;
    }

    public BufferedImage getSourceImage() {
        return sourceImage;
    }

    public void setSourceImage(BufferedImage sourceImage) {
        this.sourceImage = sourceImage;
    }

    public BufferedImage[][] produce(int hPiecesCount, int vPiecesCount) {
        BufferedImage[][] images = new BufferedImage[hPiecesCount][vPiecesCount];

        int width = sourceImage.getWidth() / hPiecesCount;
        int height = sourceImage.getHeight() / vPiecesCount;

        for (int i = 0; i < hPiecesCount; i++) {
            for (int j = 0; j < vPiecesCount; j++) {
                int[] pixels = new int[width * height * 3];
                PixelGrabber pg = new PixelGrabber(sourceImage, i * width, j * height, width, height, pixels, 0, width * 3);
                try {
                    pg.grabPixels();
                } catch (InterruptedException e) {
                    System.err.println(e.getMessage());
                }
                if ((pg.getStatus() & ImageObserver.ABORT) != 0) {
                    System.err.println("ImagePartsProducer error");
                }
                images[i][j] = getImageFromArray(pixels, width, height);
            }
        }
        return images;
    }

    private BufferedImage getImageFromArray(int[] pixels, int width, int height) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        image.setRGB(0, 0, width, height, pixels, 0, width * 3);
        return image;
    }

}

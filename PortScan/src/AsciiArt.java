import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class AsciiArt {

    public static  void printBanner()
            throws Exception {


        File file = new File("Jasmine.png");
        System.out.println(file.getAbsolutePath());
    BufferedImage Image = ImageIO.read(file);


    int newWidth = 150;
    int newHeight = 50;

    BufferedImage resized = new BufferedImage(
            newWidth,
            newHeight,
            BufferedImage.TYPE_INT_RGB
    );

        Graphics2D g = resized.createGraphics();

        g.drawImage(
                Image,
                0,
                0,
                newWidth,
                newHeight,
                null
        );

        g.dispose();

        Image = resized;



    char[] ascii = {

        '@',
                '#',
                'S',
                '%',
                '?',
                '*',
                '+',
                ';',
                ':',
                ',',
                '.',
                ' '

    };





        for (int y = 0; y < newHeight; y++ ) {


            for (int x = 0; x < newWidth; x++) {
                int rgb = Image.getRGB(x, y);
                int red = (rgb >> 16) & 0xff;
                int green = (rgb >> 8) & 0xff;
                int blue = rgb & 0xff;

                int brightness = (red + green + blue) / 3;
                int index = brightness * (ascii.length - 1) / 255;
                System.out.print(ascii[index]);
            }
            System.out.println();
        }
    }


}


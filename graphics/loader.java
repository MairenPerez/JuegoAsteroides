package graphics;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;


public class loader {
    public static BufferedImage ImageLoader(String path) {

        // Cargamos la imagen desde el archivo
        try {
            return ImageIO.read(loader.class.getResource(path));
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }
}

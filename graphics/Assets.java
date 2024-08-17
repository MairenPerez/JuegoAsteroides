package graphics;

import java.awt.image.BufferedImage;

public class Assets {
    public static BufferedImage player;

    public static void init() {
        player = loader.ImageLoader("/res/ships/playerShip1_blue.png");
    }
}

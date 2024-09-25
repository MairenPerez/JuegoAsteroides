package gameObjects;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import math.Vector2D;

public class Player extends MovingObject{
        
        private Vector2D heading;

        public Player(Vector2D position, Vector2D velocity, BufferedImage texture) {
        super(position, velocity, texture);
        
    }

        @Override
        public void update() {
            if (input.KeyBoard.UP) {
                position.setY(position.getY() - 3);
            }
            if (input.KeyBoard.LEFT) {
                position.setX(position.getX() - 3);
            }
            if (input.KeyBoard.RIGHT) {
                position.setX(position.getX() + 3);
            }
        }
    
        @Override
        public void draw(Graphics g) {
            g.drawImage(texture, (int) position.getX(), (int) position.getY(), null);
        }
    
}

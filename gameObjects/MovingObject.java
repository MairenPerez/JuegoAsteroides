package gameObjects;

import java.awt.image.BufferedImage;
import java.awt.geom.AffineTransform;
import math.Vector2D;

// Import the Vector2D class
public abstract class MovingObject extends GameObject {

    protected Vector2D velocity;
    protected AffineTransform at;
    protected double angle;

    public MovingObject(Vector2D position, Vector2D velocity, BufferedImage texture) {
        super(position, texture);
        this.velocity = velocity;
        this.at = new AffineTransform();
        this.angle = 0;
    }
}

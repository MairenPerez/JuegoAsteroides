package math;

public class Vector2D {

    private double x,y; // Coordenadas del vector
    
    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector2D(){
        x = 0;
        y = 0;
    }


    // Getters y Setters 

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }    

}

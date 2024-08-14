package main;

import javax.swing.JFrame;

public class Window extends JFrame {
    public static final int WIDTH = 800, HEIGHT = 600;

    public Window() {
        setTitle("Juego de naves"); 
        setSize(WIDTH, HEIGHT);  
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        setResizable(false); // No se puede redimensionar
        setLocationRelativeTo(null); // Centrar la ventana
        setVisible(true); // Hacemos la ventana visible   
    }

    public static void main(String[] args) {
        new Window(); 
    }
}

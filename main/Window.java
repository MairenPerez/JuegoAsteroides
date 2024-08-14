package main;

import javax.swing.JFrame;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Window extends JFrame implements Runnable {
    public static final int WIDTH = 800, HEIGHT = 600;
    private Canvas canvas;
    private Thread thread; // Hilo de ejecución
    private boolean running = false; // Variable para saber si el juego está corriendo

    // Para dibujar
    private BufferStrategy bs; 
    private Graphics g;

    public Window() {
        setTitle("Juego de naves"); 
        setSize(WIDTH, HEIGHT);  
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        setResizable(false); // No se puede redimensionar
        setLocationRelativeTo(null); // Centrar la ventana
        setVisible(true); // Hacemos la ventana visible   

        // Creamos el objeto Canvas

        canvas = new Canvas(); 

        canvas.setPreferredSize(new Dimension(WIDTH, HEIGHT)); // Establecemos las dimensiones del canvas
        canvas.setMaximumSize(new Dimension(WIDTH, HEIGHT)); 
        canvas.setMinimumSize(new Dimension(WIDTH, HEIGHT)); 
        canvas.setFocusable(true); // Para que el canvas pueda recibir eventos

        // Agragamos el Canvas a la ventana creada anteriormente
        add(canvas);
    }

    public static void main(String[] args) {
        new Window().start();
    }

    int x = 0;
    private void update(){
        x++; // Incrementamos un pixel
    }

    private void draw(){
        bs = canvas.getBufferStrategy(); // Obtenemos la estrategia de buffers del canvas

        if (bs == null) {
            canvas.createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics(); // Obtenemos el objeto Graphics

        // Dibujamos los elementos

        g.clearRect(0, 0, WIDTH, HEIGHT);
        g.drawRect(x,0, 100, 100);

        //...............................................
        g.dispose();
        bs.show(); // Mostramos el buffer
    }


    @Override
    public void run() {
        while (running) {
            update(); // Actualizamos los elementos
            draw(); // Dibujamos los elementos
        }

        stop(); // Detenemos el hilo
    }

    private void start() {
        thread = new Thread(this); 
        thread.start(); // Llamamos al método run
        running = true; // Indicamos que el juego está corriendo
    }

    private void stop() {
      try {
        thread.join();
        running = false; // Indicamos que el juego no está corriendo
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
    }
}

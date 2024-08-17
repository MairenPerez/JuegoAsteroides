package main;

import javax.swing.JFrame;

import graphics.Assets;
import states.GameState;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.Color;

public class Window extends JFrame implements Runnable {
    public static final int WIDTH = 800, HEIGHT = 600;
    private Canvas canvas;
    private Thread thread; // Hilo de ejecución
    private boolean running = false; // Variable para saber si el juego está corriendo

    // Para dibujar
    private BufferStrategy bs; 
    private Graphics g;

    // Fotogramas por segundo
    private final int FPS = 60;
    private double TARGETTIME = 1000000000 / FPS; // 1 segundo en nanosegundos
    private double delta = 0; // Diferencia de tiempo entre un fotograma y otro
    private int AVERAGEFPS = FPS; // Promedio de FPS que se muestra en pantalla

    private GameState gameState;

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


    private void update(){
       gameState.update();
    }

    private void draw(){
        bs = canvas.getBufferStrategy(); // Obtenemos la estrategia de buffers del canvas

        if (bs == null) {
            canvas.createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics(); // Obtenemos el objeto Graphics

        // Dibujamos los elementos

        g.setColor(Color.black);

        g.fillRect(0, 0, WIDTH, HEIGHT);

        gameState.draw(g);

        g.drawString("" + AVERAGEFPS, 10, 10);


        //...............................................
        g.dispose();
        bs.show(); // Mostramos el buffer
    }

    private void init(){
        Assets.init(); // Inicializamos los elementos
        gameState = new GameState(); // Creamos un nuevo estado de juego
    }

    @Override
    public void run() {

        long now = 0; // Tiempo actual 
        long lastTime = System.nanoTime(); // Tiempo actual del sistema en nanosegundos 
        int frames = 0; // Contador de fotogramas
        long time = 0; // Tiempo transcurrido

        init(); // Inicializamos los elementos

        while (running) {
            now = System.nanoTime(); // Actualizamos el tiempo actual
            delta += (now - lastTime) / TARGETTIME; // Calculamos la diferencia de tiempo entre un fotograma y otro
            time += (now - lastTime); // Calculamos el tiempo transcurrido
            lastTime = now; // Actualizamos el tiempo actual

            // Si la diferencia de tiempo es mayor o igual a 1
            if (delta >= 1) {
                update(); // Actualizamos los elementos
                draw(); // Dibujamos los elementos
                delta--; // Disminuimos la diferencia de tiempo
                frames++; // Incrementamos el contador de fotogramas
                System.out.println(frames);
            }

            if (time >= 1000000000) { // Si ha pasado un segundo ...
                AVERAGEFPS = frames; // Actualizamos el promedio de fotogramas
                frames = 0; // Reiniciamos el contador de fotogramas
                time = 0; // Reiniciamos el tiempo transcurrido

            }
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

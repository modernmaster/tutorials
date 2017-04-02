package uk.co.jamesmcguigan.patterns.structural.flyweight;


import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;

class ColorBox extends Canvas implements Runnable {
    private static final int MAX_COLOUR = 1000;
    private static Color[] colors = {Color.black, Color.blue, Color.cyan,
            Color.darkGray, Color.gray, Color.green, Color.lightGray, Color.red,
            Color.magenta, Color.orange, Color.pink, Color.white, Color.yellow};
    private int pause;
    private Color curColor = getColor();

    ColorBox(final int p) {
        pause = p;
        new Thread(this).start();
    }

    private static Color getColor() {
        return colors[(int) (Math.random() * MAX_COLOUR) % colors.length];
    }

    public void run() {
        while (true) {
            curColor = getColor();
            repaint();
            try {
                Thread.sleep(pause);
            } catch (InterruptedException e) {
            }
        }
    }

    public void paint(final Graphics g) {
        g.setColor(curColor);
        g.fillRect(0, 0, getWidth(), getHeight());
    }
}

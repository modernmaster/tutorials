package uk.co.jamesmcguigan.patterns.structural.decorator;

public class TextField implements Widget {
    private int width, height;

    public TextField(final int w, final int h) {
        width = w;
        height = h;
    }

    public void draw() {
        System.out.println("TextField: " + width + ", " + height);
    }
}


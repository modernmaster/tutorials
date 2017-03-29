package uk.co.jamesmcguigan.patterns.structural.decorator;

public class TextField implements Widget {
    private int width, height;
    public TextField( int w, int h ) {
        width  = w;
        height = h;
    }
    public void draw() {
        System.out.println( "TextField: " + width + ", " + height );
    }
}


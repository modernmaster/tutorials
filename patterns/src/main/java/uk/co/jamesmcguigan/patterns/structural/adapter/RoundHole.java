package uk.co.jamesmcguigan.patterns.structural.adapter;

public class RoundHole {
    private int radius;
    public RoundHole( int r ) {
        radius = r;
        System.out.println( "RoundHole: max SquarePeg is " + r * Math.sqrt(2) );
    }
    public int getRadius() { return radius; }
}


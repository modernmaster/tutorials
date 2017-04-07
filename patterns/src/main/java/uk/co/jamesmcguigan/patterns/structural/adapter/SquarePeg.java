package uk.co.jamesmcguigan.patterns.structural.adapter;

public class SquarePeg {
    private double width;

    public SquarePeg(final double w) {
        width = w;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(final double w) {
        width = w;
    }
}


package uk.co.jamesmcguigan.patterns.creational.abstractfactory;

public abstract class AbstractFactory {
    abstract Color getColor(final String color);

    abstract Shape getShape(final String shape);
}

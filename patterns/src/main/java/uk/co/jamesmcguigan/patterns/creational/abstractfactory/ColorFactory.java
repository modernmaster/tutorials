package uk.co.jamesmcguigan.patterns.creational.abstractfactory;

public class ColorFactory extends AbstractFactory {

    @Override
    public Shape getShape(final String shapeType) {
        return null;
    }

    @Override
    Color getColor(final String color) {

        if (color == null) {
            return null;
        }

        if (color.equalsIgnoreCase("RED")) {
            return new Red();

        } else if (color.equalsIgnoreCase("GREEN")) {
            return new Green();

        } else if (color.equalsIgnoreCase("BLUE")) {
            return new Blue();
        }

        return null;
    }
}

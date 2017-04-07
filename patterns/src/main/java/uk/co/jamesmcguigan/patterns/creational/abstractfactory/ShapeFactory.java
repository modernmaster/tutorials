package uk.co.jamesmcguigan.patterns.creational.abstractfactory;

public class ShapeFactory extends AbstractFactory {

    @Override
    public Shape getShape(final String shapeType) {

        if (shapeType == null) {
            return null;
        }

        if (shapeType.equalsIgnoreCase("CIRCLE")) {
            return new Circle();

        } else if (shapeType.equalsIgnoreCase("RECTANGLE")) {
            return new Rectangle();

        } else if (shapeType.equalsIgnoreCase("SQUARE")) {
            return new Square();
        }

        return null;
    }

    @Override
    Color getColor(final String color) {
        return null;
    }
}

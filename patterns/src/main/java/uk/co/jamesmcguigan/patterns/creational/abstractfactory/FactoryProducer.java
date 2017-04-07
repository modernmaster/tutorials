package uk.co.jamesmcguigan.patterns.creational.abstractfactory;

public class FactoryProducer {

    private FactoryProducer() {}
    public static AbstractFactory getFactory(final String choice) {

        if (choice.equalsIgnoreCase("SHAPE")) {
            return new ShapeFactory();

        } else if (choice.equalsIgnoreCase("COLOR")) {
            return new ColorFactory();
        }

        return null;
    }
}

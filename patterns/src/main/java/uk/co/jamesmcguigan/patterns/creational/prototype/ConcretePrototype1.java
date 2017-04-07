package uk.co.jamesmcguigan.patterns.creational.prototype;

public class ConcretePrototype1 implements Prototype, Command {
    public Object clone() {
        return new ConcretePrototype1();
    }

    public String getName() {
        return "This";
    }

    public void execute() {
        System.out.println("This: execute");
    }
}

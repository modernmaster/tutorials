package uk.co.jamesmcguigan.patterns.creational.prototype;

public class ConcretePrototype3 implements Prototype, Command {
    public Object clone() {
        return new ConcretePrototype3();
    }

    public String getName() {
        return "TheOther";
    }

    public void execute() {
        System.out.println("TheOther: execute");
    }
}

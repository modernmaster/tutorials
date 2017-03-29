package uk.co.jamesmcguigan.patterns.creational.prototype;

public class ConcretePrototype2 implements Prototype, Command {
    public Object clone() {
        return new ConcretePrototype2();
    }
    public String getName() {
        return "That";
    }
    public void execute() {
        System.out.println( "That: execute" );
    }
}
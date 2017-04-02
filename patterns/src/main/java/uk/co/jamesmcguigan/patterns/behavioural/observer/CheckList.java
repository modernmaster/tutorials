package uk.co.jamesmcguigan.patterns.behavioural.observer;

public class CheckList {
    public void byTheNumbers() {  // Template Method design pattern
        localize();
        isolate();
        identify();
    }

    protected void localize() {
        System.out.println("   establish a perimeter");
    }

    protected void isolate() {
        System.out.println("   isolate the grid");
    }

    protected void identify() {
        System.out.println("   identify the source");
    }
}

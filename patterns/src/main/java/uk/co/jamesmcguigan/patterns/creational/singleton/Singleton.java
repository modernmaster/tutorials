package uk.co.jamesmcguigan.patterns.creational.singleton;

public class Singleton {

    private static Singleton instance;

    /**
     * Prevents instantiating by other classes.
     */
    private Singleton() {
    }

    public static synchronized Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}

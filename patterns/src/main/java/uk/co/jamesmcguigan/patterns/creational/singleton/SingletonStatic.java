package uk.co.jamesmcguigan.patterns.creational.singleton;

public class SingletonStatic {

    private final static SingletonStatic instance = new SingletonStatic();

    /**
     * Prevents instantiating by other classes.
     */
    private SingletonStatic() {
    }

    //The singleton instance is initialized during the class loading time.
    // No synchronization is necessary on the getInstance level
    public static SingletonStatic getInstance() {
        return instance;
    }
}



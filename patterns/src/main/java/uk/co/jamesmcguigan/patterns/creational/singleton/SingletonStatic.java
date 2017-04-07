package uk.co.jamesmcguigan.patterns.creational.singleton;

public class SingletonStatic {

    private static final SingletonStatic INSTANCE = new SingletonStatic();

    /**
     * Prevents instantiating by other classes.
     */
    private SingletonStatic() {
    }

    //The singleton INSTANCE is initialized during the class loading time.
    // No synchronization is necessary on the getInstance level
    public static SingletonStatic getInstance() {
        return INSTANCE;
    }
}



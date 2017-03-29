package uk.co.jamesmcguigan.algorithms;

public class Singleton {

    private Singleton() { }

    private static Singleton mySingleton = null;

    public static Singleton getInstance() {
        if(mySingleton==null)
        {
            mySingleton = new Singleton();
            return mySingleton;
        }
        return mySingleton;
    }

    public String sayHello() {
        return "HELLO";
    }
}
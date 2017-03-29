package uk.co.jamesmcguigan.patterns.creational.multiton;

import java.util.HashMap;

public class Multiton{
    private static final HashMap<Object, Multiton> instances = new HashMap<Object, Multiton>();

    private Multiton(){}

    public static Multiton getInstance(Object key){
        synchronized (instances) {
            // Our "per key" singleton
            Multiton instance;
            if ((instance = instances.get(key)) == null) {
                // Lazily create instance and add it to the map
                instance = new Multiton();
                instances.put(key, instance);
            }
            return instance;
        }
    }
}
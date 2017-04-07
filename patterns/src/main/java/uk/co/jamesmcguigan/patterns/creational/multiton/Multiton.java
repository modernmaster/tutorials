package uk.co.jamesmcguigan.patterns.creational.multiton;

import java.util.HashMap;

public class Multiton {
    private static final HashMap<Object, Multiton> INSTANCES = new HashMap<Object, Multiton>();

    private Multiton() {
    }

    public static Multiton getInstance(final Object key) {
        synchronized (INSTANCES) {
            // Our "per key" singleton
            Multiton instance = INSTANCES.get(key);
            if (instance == null) {
                // Lazily create instance and add it to the map
                instance = new Multiton();
                INSTANCES.put(key, instance);
            }
            return instance;
        }
    }
}

package uk.co.jamesmcguigan.patterns.creational.prototype;

public class PrototypesModule {

    private PrototypesModule() {}

    // 2. "registry" of prototypical objs
    private static final int CAPACITY = 9;
    private static Prototype[] prototypes = new Prototype[CAPACITY];
    private static int total = 0;

    // Adds a feature to the prototype attribute of the PrototypesModule class
    // obj  The feature to be added to the prototype attribute
    public static void addPrototype(final Prototype obj) {
        prototypes[total++] = obj;
    }

    public static Object findAndClone(final String name) {
        // 4. The "virtual ctor"
        for (int i = 0; i < total; i++) {
            if (prototypes[i].getName().equals(name)) {
                return prototypes[i].clone();
            }
        }
        System.out.println(name + " not found");
        return null;
    }
}

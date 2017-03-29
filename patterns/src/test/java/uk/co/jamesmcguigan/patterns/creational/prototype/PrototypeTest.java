package uk.co.jamesmcguigan.patterns.creational.prototype;

import org.junit.Before;
import org.junit.Test;

public class PrototypeTest {

    @Before
    public void setup() {
        PrototypesModule.addPrototype( new ConcretePrototype1() );
        PrototypesModule.addPrototype( new ConcretePrototype2() );
        PrototypesModule.addPrototype( new ConcretePrototype3() );
    }

    @Test
    public void testShouldCreateRequestedPrototypes() {

        String[] args = new String[]{"Garbage", "This", "That", "Nothing", "TheOther"};
        Object[] objects = new Object[9];
        int total = 0;

        // 6. Client does not use "new"
        for (int i=0; i < args.length; i++) {
            objects[total] = PrototypesModule.findAndClone( args[i] );
            if (objects[total] != null) total++;
        }
        for (int i=0; i < total; i++) {
            ((Command)objects[i]).execute();
        }
    }
}
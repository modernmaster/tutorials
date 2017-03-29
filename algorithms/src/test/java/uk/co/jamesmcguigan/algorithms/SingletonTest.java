package uk.co.jamesmcguigan.algorithms;

import org.junit.Test;

public class SingletonTest {

    @Test
    public void testShouldGetOneInstance() {
        Singleton singleton = Singleton.getInstance();
        singleton.sayHello();

    }

}
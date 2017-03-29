package uk.co.jamesmcguigan.patterns.creational.factory;

import org.junit.Test;

public class DogFactoryTest {

    @Test
    public void testCreateDog() {
        // create a small dog
        Dog dog = DogFactory.getDog("small");
        dog.speak();

        // create a big dog
        dog = DogFactory.getDog("big");
        dog.speak();

        // create a working dog
        dog = DogFactory.getDog("working");
        dog.speak();
    }
}

package uk.co.jamesmcguigan.patterns.behavioural.mediator;

import org.junit.Test;

public class MediatorTest {
    @Test
    public void testMediatorBetweenProducerAndConsumer() {
        Mediator mb = new Mediator();
        new Producer( mb ).start();
        new Producer( mb ).start();
        new Consumer( mb ).start();
        new Consumer( mb ).start();
        new Consumer( mb ).start();
        new Consumer( mb ).start();
    }
}

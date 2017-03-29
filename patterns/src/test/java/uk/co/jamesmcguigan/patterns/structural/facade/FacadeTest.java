package uk.co.jamesmcguigan.patterns.structural.facade;

import org.junit.Test;

public class FacadeTest {
    @Test
    public void testComputerFacade() {
        Computer computer = new Computer();
        computer.start();
    }
}

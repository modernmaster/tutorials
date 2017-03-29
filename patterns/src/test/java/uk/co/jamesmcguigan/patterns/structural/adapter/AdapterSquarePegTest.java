package uk.co.jamesmcguigan.patterns.structural.adapter;

import org.junit.Test;

public class AdapterSquarePegTest {

    @Test
    public void testShouldMakeRoundholeFitSquarePeg() {
        RoundHole rh = new RoundHole(5);
        SquarePegAdapter spa;

        for (int i = 6; i < 10; i++) {
            spa = new SquarePegAdapter((double) i);
            // The client uses (is coupled to) the new interface
            spa.makeFit(rh);
        }
    }
}

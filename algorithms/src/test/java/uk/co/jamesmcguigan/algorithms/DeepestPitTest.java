package uk.co.jamesmcguigan.algorithms;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DeepestPitTest {

    private DeepestPit deepestPit;

    @Before
    public void setUp() {
        deepestPit = new DeepestPit();
    }

    @Test
    public void testIdentifyDeepestPitInArray() {
        int [] numbers = {8,1,8};


        assertEquals(7, deepestPit.calculatePit(numbers));
    }

    @Test
    public void testIdentifyDeepestPitInWithUnevenArray() {
        int [] numbers = {8,1,7};
        assertEquals(6, deepestPit.calculatePit(numbers));
    }

    @Test
    public void testIdentifyDeepestPitInWithTwoUnevenArrays() {
        int [] numbers = {8,1,7,3,4};
        assertEquals(6, deepestPit.calculatePit(numbers));
    }

    @Test
    public void testIdentifyDeepestPitInWithNoPit() {
        int [] numbers = {1,2,3,4,5,6,7};
        assertEquals(-1, deepestPit.calculatePit(numbers));
    }

    @Test
    public void testIdentifyDeepestPitCodality() {
        int [] numbers = {0,1,3,-2,0,1,0,-3,2,3};
        assertEquals(4, deepestPit.calculatePit(numbers));
    }
}

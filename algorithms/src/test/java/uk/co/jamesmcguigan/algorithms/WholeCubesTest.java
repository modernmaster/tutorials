package uk.co.jamesmcguigan.algorithms;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class WholeCubesTest {

    private WholeCubes wholeCubes;

    @Before
    public void setUp() {
        wholeCubes = new WholeCubes();
    }

    @Test
    public void testReturnNumberofCubes() {
        int a = 8;
        int b = 65;
        int result = wholeCubes.wholeCubesCount(a,b);
        assertEquals(3, result);
    }

    @Test
    public void testReturnNumberofCubes64() {
        int a = 8;
        int b = 64;
        int result = wholeCubes.wholeCubesCount(a,b);
        assertEquals(3, result);
    }

    @Test
    public void testReturnNumberofCubesNegative() {
        int a = -68;
        int b = -8;
        int result = wholeCubes.wholeCubesCount(a,b);
        assertEquals(3, result);
    }

    @Test
    public void testReturnNumberofCubesSame() {
        int a = 2;
        int b = 2;
        int result = wholeCubes.wholeCubesCount(a,b);
        assertEquals(0, result);
    }

    @Test
    public void testReturnNumberofCubesAGreaterB() {
        int a = 3;
        int b = 2;
        int result = wholeCubes.wholeCubesCount(a,b);
        assertEquals(0, result);
    }
}

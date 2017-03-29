package uk.co.jamesmcguigan.algorithms;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ZigZagTest {

    private ZigZag zigZag;
    @Before
    public void setUp() {
        zigZag = new ZigZag();
    }
    @Test
    public void test1() {
        String s = "PAYPALISHIRING";
        String expected = "PAHNAPLSIIGYIR";
        String result = zigZag.convert(s, 3);
        assertEquals(expected, result);
    }
    @Test
    public void test2() {
        String s = "abc";
        String expected = "abc";
        String result = zigZag.convert(s, 1);
        assertEquals(expected, result);
    }
}

package uk.co.jamesmcguigan.algorithms;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StringsTest {
    @Test
    public void testReverseString() {
        assertEquals("zyxwvutsrqponmlkjihgfedcba", new Strings().reverse("abcdefghijklmnopqrstuvwxyz"));
    }

    @Test
    public void testReverse2String() {
        assertEquals("zyxwvutsrqponmlkjihgfedcba", new Strings().reverse2("abcdefghijklmnopqrstuvwxyz"));
    }
}

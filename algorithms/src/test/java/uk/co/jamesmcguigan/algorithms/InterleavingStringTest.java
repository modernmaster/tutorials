package uk.co.jamesmcguigan.algorithms;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class InterleavingStringTest {

    private InterleavingString interleavingString;

    @Before
    public void setUp() {
        interleavingString = new InterleavingString();
    }

    @Test
    @Ignore
    public void test1() {
        String s1 = "aabcc";
        String s2 = "dbbca";
        String s3 = "aadbbcbcac";
        assertTrue(interleavingString.isInterleave(s1,s2,s3));
    }

    @Test
    @Ignore
    public void test2() {
        String s1 = "aabcc";
        String s2 = "dbbca";
        String s3 = "aadbbbaccc";
        assertFalse(interleavingString.isInterleave(s1,s2,s3));
    }
}

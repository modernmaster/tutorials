package uk.co.jamesmcguigan.algorithms.longestcommonprefix;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DivideAndConqueorTest {

    private LongestCommonPrefix longestCommonPrefix;

    @Before
    public void setUp() {
        longestCommonPrefix = new DivideAndConqueor();
    }

    @Test
    public void testFindLongestCommonPrefix() {
        String [] strs = new String[]{"leet","leetcode","leetc","leeds"};
        assertEquals("lee",longestCommonPrefix.longestCommonPrefix(strs));
    }

    @Test
    public void testFindLongestCommonPrefixA() {
        String [] strs = new String[]{"leet","laeetcode","laeetc","laeeds"};
        assertEquals("l",longestCommonPrefix.longestCommonPrefix(strs));
    }

    @Test
    public void testFindLongestCommonNoMatch() {
        String [] strs = new String[]{"leet","aaeetcode","qaeetc","daeeds"};
        assertEquals("",longestCommonPrefix.longestCommonPrefix(strs));
    }
}

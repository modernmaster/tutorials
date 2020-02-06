package uk.co.jamesmcguigan.algorithms;

import java.util.Arrays;
import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class LongestWordTests {

    private LongestWord longestWord;

    @Before
    public void setUp() {
        longestWord = new LongestWord();
    }

    @Test
    public void testExampleOne() {
        LongestWord.Dictionary dict = new LongestWord.Dictionary(new String[]{"to", "toe", "toes", "doe", "dog", "god", "dogs", "book", "banana"});
        assertTrue(new HashSet<String>(Arrays.asList("toe")).equals(longestWord.longestWord("toe", dict)));
        assertTrue(new HashSet<String>(Arrays.asList("toes", "dogs")).equals(longestWord.longestWord("osetdg", dict)));
    }

    @Test
    public void testExampleTwo() {
        LongestWord.Dictionary dict = new LongestWord.Dictionary(new String[]{"toes"});
        assertTrue(new HashSet<String>(Arrays.asList("toes")).equals(longestWord.longestWord("toes", dict)));
    }

    @Test
    public void testExampleThree() {
        LongestWord.Dictionary dict = new LongestWord.Dictionary(new String[]{"toes", "to"});
        assertTrue(new HashSet<String>(Arrays.asList("toes")).equals(longestWord.longestWord("toes", dict)));
    }

    @Test
    public void testExampleFour() {
        LongestWord.Dictionary dict = new LongestWord.Dictionary(new String[]{"toes", "to", "toess"});
        assertTrue(new HashSet<String>(Arrays.asList("toes")).equals(longestWord.longestWord("toes", dict)));
    }

    @Test
    public void testExampleFive() {
        LongestWord.Dictionary dict = new LongestWord.Dictionary(new String[]{"toes", "to", "toess"});
        assertTrue(new HashSet<String>(Arrays.asList("toes")).equals(longestWord.longestWord("to?s", dict)));
    }

    @Test
    public void testExampleSix() {
        LongestWord.Dictionary dict = new LongestWord.Dictionary(new String[]{"toes", "to", "toess"});
        assertTrue(new HashSet<String>(Arrays.asList("toes")).equals(longestWord.longestWord("to?s", dict)));
    }

}

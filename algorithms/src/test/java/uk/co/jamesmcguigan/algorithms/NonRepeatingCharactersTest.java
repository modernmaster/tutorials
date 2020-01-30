package uk.co.jamesmcguigan.algorithms;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class NonRepeatingCharactersTest {

    private NonRepeatingCharacters nonRepeatingCharacters;

    @Before
    public void setUp() {
        nonRepeatingCharacters = new NonRepeatingCharacters();
    }

    @Test
    public void testExampleOne() {
        String testString = "GeeksforGeeks";
        char result = nonRepeatingCharacters.calculate(testString);
        assertThat(result, equalTo('f'));
    }

}

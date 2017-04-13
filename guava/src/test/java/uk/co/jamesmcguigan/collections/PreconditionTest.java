package uk.co.jamesmcguigan.collections;

import org.junit.Test;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkPositionIndex;
import static com.google.common.base.Preconditions.checkState;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

//Avoid passing message arguments that are expensive to compute; your code will always compute them, even though
// they usually won't be needed. If you have such arguments, use the conventional if/throw idiom instead.

public class PreconditionTest {

    @Test(expected = IllegalArgumentException.class)
    public void testCheckArgumentWhenParametersAreInvalid() {
        final int value = 12;
        checkArgument(value > 12);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testCheckPositionIndexWithInvalid() {
        final int[] arrayOfNumbers = {0,1,2,3,4,5};
        checkPositionIndex(10, arrayOfNumbers.length, "Testing index");
    }

    @Test
    public void testCheckPositionIndexWithValid() {
        final int index = 1;
        final int[] arrayOfNumbers = {0,1,2,3,4,5};
        int currentIndex = checkPositionIndex(index , arrayOfNumbers.length, "Testing index");
        assertThat(currentIndex, equalTo(index));
    }

    @Test(expected = NullPointerException.class)
    public void testNullValueOfVariable() {
        final String t = null;
        checkNotNull(t);
    }

    @Test()
    public void testValueOfVariableIsNotNull() {
        final String t = "Jim";
        final String x = checkNotNull(t);
        assertThat(x, equalTo(t));
    }

    @Test()
    public void testCorrectStateOfValue() {
        checkState(true, "Incorrect state");
    }

    @Test(expected = IllegalStateException.class)
    public void testIncorrectStateOfValue() {
        checkState(false, "Incorrect state");
    }


}

package uk.co.jamesmcguigan.algorithms;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class SmallestSubArrayTest {

    private SmallestSubArray smallestSubarray;

    @Before
    public void setup() {
        smallestSubarray = new SmallestSubArray();
    }

    @Test
    public void testExampleOne() throws NotPossibleException {
        int[] input = {1, 4, 45, 6, 0, 19};
        int x = 51;
        int result = smallestSubarray.calculate(input, x);
        assertThat(result, equalTo(3));
//        Minimum length subarray is {4, 45, 6}
    }

    @Test
    public void testExampleTwo() throws NotPossibleException {
        int[] input = {1, 10, 5, 2, 7};
        int x = 9;
        int result = smallestSubarray.calculate(input, x);
        assertThat(result, equalTo(1));
//        Minimum length subarray is {10}
    }

    @Test
    public void testExampleThree() throws NotPossibleException {
        int[] input = {1, 11, 100, 1, 0, 200, 3, 2, 1, 250};
        int x = 280;
        int result = smallestSubarray.calculate(input, x);
        assertThat(result, equalTo(4));
//        Minimum length subarray is {100, 1, 0, 200}
    }

    @Test(expected = NotPossibleException.class)
    public void testExampleFour() throws NotPossibleException {
        int[] input = {1, 2, 4};
        int x = 8;
        int result = smallestSubarray.calculate(input, x);
//        Whole array sum is smaller than 8.
    }

}

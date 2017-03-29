package uk.co.jamesmcguigan.algorithms;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;

/**
 * Created by James McGuigan on 12/09/2015.
 */
public class FibonacciTest {

    @Test
    public void ShouldReturn21ForNEquals8() {
        int result = Fibonacci.GetFibonacciRecursive(8);
        Assert.assertThat(result, equalTo(21));
    }

    @Test
    public void ShouldReturn21ForNEquals8UsingIteration() {
        int result = Fibonacci.GetFibonacciIterative(8);
        Assert.assertThat(result, equalTo(21));
    }

    @Test
    public void ShouldReturn21ForNEquals8UsingMemorization() {
        int result = Fibonacci.GetFibonacciMemorization(8);
        Assert.assertThat(result, equalTo(21));
    }
}

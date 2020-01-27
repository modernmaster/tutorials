package uk.co.jamesmcguigan.algorithms;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class SumFractionsTest {
    private SumFractions sumFractions;

    @Before
    public void setUp() {
        sumFractions = new SumFractions();
    }

    @Test
    public void testExampleOne() {
        String result = sumFractions.calculate(1, 2, 1, 4);
        assertThat(result, equalTo("3/4"));
    }

    @Test
    public void testExampleTwo() {
        String result = sumFractions.calculate(1, 2, 1, 8);
        assertThat(result, equalTo("5/8"));
    }

    @Test
    public void testExampleThree() {
        String result = sumFractions.calculate(3, 2, 1, 4);
        assertThat(result, equalTo("1 3/4"));
    }

    @Test
    public void testExampleFour() {
        String result = sumFractions.calculate(-3, 2, 1, 4);
        assertThat(result, equalTo("-1 1/4"));
    }
}

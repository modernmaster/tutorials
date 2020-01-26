package uk.co.jamesmcguigan.algorithms;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class TrappingRainWaterTest {
    private TrappingRainWater trappingRainWater;
    @Before
    public void setUp() {
        trappingRainWater = new TrappingRainWater();
    }

    @Test
    public void testExampleOne() {
        int [] input = {2, 0, 2};
        int result = trappingRainWater.calculate(input);
        assertThat(result, equalTo(2));
    }

    @Test
    public void testExampleTwo() {
        int [] input = {3, 0, 0, 2, 0, 4};
        int result = trappingRainWater.calculate(input);
        assertThat(result, equalTo(10));
    }

    @Test
    public void testExampleThree() {
        int [] input = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        int result = trappingRainWater.calculate(input);
        assertThat(result, equalTo(6));
    }

}

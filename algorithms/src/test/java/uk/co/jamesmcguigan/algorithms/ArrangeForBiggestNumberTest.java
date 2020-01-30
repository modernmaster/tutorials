package uk.co.jamesmcguigan.algorithms;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class ArrangeForBiggestNumberTest {

    private ArrangeForBiggestNumber arrangeForBiggestNumber;

    @Before
    public void setUp() {
        arrangeForBiggestNumber = new ArrangeForBiggestNumber();
    }

    @Test
    public void testExampleOne() {
        Integer[] givenNumbers = {54, 546, 548, 60};
        long result = arrangeForBiggestNumber.calculate(givenNumbers);
        assertThat(result, equalTo(6054854654L));
    }

    @Test
    public void testExampleTwo() {
        Integer[] givenNumbers = {1, 34, 3, 98, 9, 76, 45, 4};
        long result = arrangeForBiggestNumber.calculate(givenNumbers);
        assertThat(result, equalTo(998764543431L));
    }
}

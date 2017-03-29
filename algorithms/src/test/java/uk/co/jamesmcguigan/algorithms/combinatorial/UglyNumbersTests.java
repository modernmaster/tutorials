package Combinatorial;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;

/**
 * Created by James McGuigan on 13/09/2015.
 */
public class UglyNumbersTests {
    @Test
    public void RunUglyNumbers(){
        UglyNumbers uglyNumbers = new UglyNumbers();
        boolean result = uglyNumbers.isUgly(15);
        Assert.assertThat(result,equalTo(true));
    }
    @Test
    public void RunUglyNumbersLarge(){
        UglyNumbers uglyNumbers = new UglyNumbers();
        boolean result = uglyNumbers.isUgly(301);
        Assert.assertThat(result,equalTo(false));
    }
    @Test
    public void RunUglyNumbersLargeTrue(){
        UglyNumbers uglyNumbers = new UglyNumbers();
        boolean result = uglyNumbers.isUgly(300);
        Assert.assertThat(result,equalTo(true));
    }
    @Test
    public void NthUglyNumber(){
        UglyNumbers uglyNumbers = new UglyNumbers();
        int result = uglyNumbers.nthUglyNumber(300);
        Assert.assertThat(result,equalTo(12));
    }
}

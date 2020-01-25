package uk.co.jamesmcguigan.algorithms.highestaveragescore;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class HighestAverageScoreTest {

    private HighestAverageScore highestAverageScore;

    @Before
    public void setUp() {
        highestAverageScore = new HighestAverageScore();
    }

    @Test
    public void testExampleOne() {
        String [] [] scores = new String[][] {
                {"jerry","65"},{"bob","91"}, {"jerry","23"}, {"Eric","83"}
        };
        Student highestAverage = highestAverageScore.calculate(scores);
        assertThat(highestAverage.calculateAverageScore(), equalTo(91d));
        assertThat(highestAverage.getName(), equalTo("bob"));
    }

}

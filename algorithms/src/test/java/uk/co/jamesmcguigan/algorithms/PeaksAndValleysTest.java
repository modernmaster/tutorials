package uk.co.jamesmcguigan.algorithms;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class PeaksAndValleysTest {

    private SortAlgorithm peakAndValley;
    private SortAlgorithm peakAndValleyOptimal;

    @Before
    public void setUp () {
        peakAndValley = new PeakAndValleySubOptimal();
        peakAndValleyOptimal = new PeakAndValleyOptimal();
    }

    @Test
    public void testForPeaksAndValleysSubOptimal() {
        int [] unsortedElements = {5,3,1,2,3};
        int [] expectedSortedElements = new int[]{2,1,3,3,5};
        int [] sortedElements = peakAndValley.sort(unsortedElements);
        assertArrayEquals(expectedSortedElements, sortedElements);
    }

    @Test
    public void testForPeaksAndValleysOptimal() {
        int [] unsortedElements = {5,3,1,2,3};
        int [] expectedSortedElements = new int[]{3,5,1,3,2};
        int [] sortedElements = peakAndValleyOptimal.sort(unsortedElements);
        assertArrayEquals(expectedSortedElements, sortedElements);
    }
}

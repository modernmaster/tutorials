package Sorting;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.equalTo;

/**
 * Created by James McGuigan on 12/09/2015.
 */
public class MergeSortTests {

    @Test
    public void ShouldSortArrayCorrectly()
    {
        Integer[] a = {2, 6, 3, 5, 1};
        Comparable[] result = MergeSort.mergeSort(a);
        Assert.assertThat(a,equalTo(result));
    }


}

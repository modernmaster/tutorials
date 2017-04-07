package uk.co.jamesmcguigan.algorithms.sorting;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

import static org.hamcrest.CoreMatchers.equalTo;

public class MergeSortTests {

    @Test
    public void ShouldSortArrayCorrectly()
    {
        Integer[] a = {2, 6, 3, 5, 1};
        Comparable[] result = MergeSort.mergeSort(a);
        Assert.assertThat(a,equalTo(result));
    }


}

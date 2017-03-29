package Sorting;

import org.junit.Test;

/**
 * Created by James McGuigan on 12/09/2015.
 */
public class QuickSortTests {

    @Test
    public void ShouldSortOutArrayCorrectly(){
        QuickSort quickSort = new QuickSort();
        int[] input = {24,2,45,20,56,75,2,56,99,53,12};
        quickSort.sort(input);
        for(int i:input){ System.out.print(i);
            System.out.print(" ");
        }
    }
}

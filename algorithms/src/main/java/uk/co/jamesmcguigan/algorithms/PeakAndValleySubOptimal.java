package uk.co.jamesmcguigan.algorithms;

import java.util.Arrays;

public class PeakAndValleySubOptimal implements SortAlgorithm {
    public int[] sort(final int[] unsortedElements) {
        //{5,3,1,2,3}
        //{5,1,3,2,3}
        Arrays.sort(unsortedElements);

        for (int i = 1; i < unsortedElements.length; i+=2){
            swap(unsortedElements, i, i-1);
        }
        return unsortedElements;
//        return new int[]{5,1,3,2,3};
    }

    private void swap (final int[] sort, final int a, final int b) {
        int tmpa = sort[a];
        sort[a] = sort[b];
        sort[b] = tmpa;
    }
}



//    private int[] subOptimalPeakAndTrough(int[] unsorted) {
//        Arrays.sort(unsorted);
//        for(int i = 1; i<unsorted.length;i+=2) {
//            if(unsorted[i] > unsorted[i-1]) {
//                swap(unsorted, i, i-1);
//            }
//        }
//        return unsorted;
//    }

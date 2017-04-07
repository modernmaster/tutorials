package uk.co.jamesmcguigan.algorithms.sorting;

public class QuickSort {

    private int[] array;
    private int length;

    public void sort(final int[] inputArr) {
        if (inputArr == null || inputArr.length == 0) {
            return;
        }
        this.array = inputArr;
        length = inputArr.length;
        quickSort(0, length - 1);
    }

    private void quickSort(final int lowerIndex, final int higherIndex) {
        int i = lowerIndex;
        int j = higherIndex;
        // calculate pivot number, I am taking pivot as middle index number
        int pivot = array[lowerIndex + (higherIndex - lowerIndex) / 2];
        // Divide into two arrays
        while (i <= j) {
            /** * In each iteration, we will identify a number from left side which * is greater then the pivot value,
             *  and also we will identify a number * from right side which is less then the pivot value. Once the search *
             *  is done, then we exchange both numbers. */
            while (array[i] < pivot) {
                i++;
            }
            while (array[j] > pivot) {
                j--;
            }
            if (i <= j) {
                exchangeNumbers(i, j);
                // move index to next position on both sides
                i++;
                j--;
            }
        }
        // call quickSort() method recursively
        if (lowerIndex < j) {
            quickSort(lowerIndex, j);
        }
        if (i < higherIndex) {
            quickSort(i, higherIndex);
        }
    }

    private void exchangeNumbers(final int i, final int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}

package uk.co.jamesmcguigan.algorithms.sorting;

public class RadixSort {

    private RadixSort() {}
    public static int[] sort(final int[] old) {

        int[] tmpOld = old;
        // Loop for every bit in the integers
        for (int shift = Integer.SIZE - 1; shift > -1; shift--) {
            // The array to put the partially sorted array into
            int[] tmp = new int[tmpOld.length];
            // The number of 0s
            int j = 0;

            // Move the 0s to the new array, and the 1s to the old one
            for (int i = 0; i < tmpOld.length; i++) {
                // If there is a 1 in the bit we are testing, the number will be negative
                boolean move = tmpOld[i] << shift >= 0;

                // If this is the last bit, negative numbers are actually lower
                //if (shift == 0 ? !move : move) {
                if (shift != 0) {
                    tmp[j] = tmpOld[i];
                    j++;
                } else {
                    // It's a 1, so stick it in the old array for now
                    tmpOld[i - j] = tmpOld[i];
                }
            }

            // Copy over the 1s from the old array
            for (int i = j; i < tmp.length; i++) {
                tmp[i] = tmpOld[i - j];
            }

            // And now the tmp array gets switched for another round of sorting
            tmpOld = tmp;
        }

        return tmpOld;
    }

}

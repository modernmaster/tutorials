package uk.co.jamesmcguigan.algorithms.sorting;

public class MergeSort {

    private MergeSort() {
    }

    public static Comparable[] mergeSort(final Comparable[] a) {
        Comparable[] tmp = new Comparable[a.length];
        mergeSort(a, tmp, 0, a.length - 1);
        return tmp;
    }

    private static void mergeSort(final Comparable[] a, final Comparable[] tmp, final int left, final int right) {
        if (left < right) {
            int center = (left + right) / 2;
            mergeSort(a, tmp, left, center);
            mergeSort(a, tmp, center + 1, right);
            merge(a, tmp, left, center + 1, right);
        }
    }

    private static void merge(final Comparable[] a, final Comparable[] tmp, final int left,
                              final int right, final int rightEnd) {

        int tmpLeft = left;
        int tmpRight = right;
        int tmpRightEnd = rightEnd;

        int leftEnd = tmpRight - 1;
        int k = left;
        int num = rightEnd - left + 1;

        while (tmpLeft <= leftEnd && tmpRight <= tmpRightEnd) {
            if (a[tmpLeft].compareTo(a[tmpRight]) <= 0) {
                tmp[k++] = a[tmpLeft++];
            } else {
                tmp[k++] = a[tmpRight++];
            }
        }

        while (tmpLeft <= leftEnd) {    // Copy rest of first half
            tmp[k++] = a[tmpLeft++];
        }

        while (tmpRight <= rightEnd) {  // Copy rest of right half
            tmp[k++] = a[tmpRight++];
        }

        // Copy tmp back
        for (int i = 0; i < num; i++, tmpRightEnd--) {
            a[tmpRightEnd] = tmp[tmpRightEnd];
        }
    }
}

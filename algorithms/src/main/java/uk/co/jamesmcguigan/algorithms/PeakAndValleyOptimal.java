package uk.co.jamesmcguigan.algorithms;

public class PeakAndValleyOptimal implements SortAlgorithm {
    public int[] sort(int[] unsortedElements) {
        for(int i = 1; i<unsortedElements.length;i+=2) {
            int maxIndex = getMaxIndex(unsortedElements, i-1, i, i+1);
            swap(unsortedElements, i, maxIndex);
        }
        return unsortedElements;
    }

    private int getMaxIndex(int[] unsorted, int a, int b, int c) {
        if(unsorted[a]>unsorted[b] && unsorted[b]>unsorted[c]){
            return a;
        } else if(unsorted[a]<unsorted[b] && unsorted[b]<unsorted[c]) {
            return c;
        } else if(unsorted[a]>unsorted[b] && unsorted[b]<unsorted[c]) {
            return c;
        } else if(unsorted[a]<unsorted[b] && unsorted[b]>unsorted[c]) {
            return b;
        } else {
            return b;
        }
    }

    private void swap(int[] data, int indexA, int indexB){
        int tmp = data[indexA];
        data[indexA] = data[indexB];
        data[indexB] = tmp;
    }
}








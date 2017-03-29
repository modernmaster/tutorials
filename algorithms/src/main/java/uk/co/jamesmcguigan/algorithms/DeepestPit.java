package uk.co.jamesmcguigan.algorithms;

public class DeepestPit {
    public int calculate_pit(int[] A) {
        int p = 0;
        int q = Integer.MIN_VALUE;
        int r = Integer.MIN_VALUE;
        int depth = -1;

        for (int i = 0; i< A.length; i++) {
            //set p after reset and add covers for q and r
            if (r > Integer.MIN_VALUE && A[i] < A[r]) {
                depth = Math.max(depth, Math.min(A[p] - A[q], A[r] - A[q]));
                p = r;
                q = Integer.MIN_VALUE;
                r = Integer.MIN_VALUE;
            } else if (q > Integer.MIN_VALUE && A[q] < A[i]) {
                r = i;
                //set q, remove cover for r
            } else if (p > Integer.MIN_VALUE && A[p] > A[i]) {
                q = i;
                r = i;
            } else if (A[i] < A[p]) {
                //set p, remove cover for q
                p = i;
                q = i;
            }
            if (r > Integer.MIN_VALUE) {
                depth = Math.max(depth, Math.min(A[p] - A[q], A[r] - A[q]));
            }
        }
        return depth;
    }
}

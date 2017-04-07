package uk.co.jamesmcguigan.algorithms;

public class DeepestPit {
    public int calculatePit(final int[] values) {
        int p = 0;
        int q = Integer.MIN_VALUE;
        int r = Integer.MIN_VALUE;
        int depth = -1;

        for (int i = 0; i < values.length; i++) {
            //set p after reset and add covers for q and r
            if (r > Integer.MIN_VALUE && values[i] < values[r]) {
                depth = Math.max(depth, Math.min(values[p] - values[q], values[r] - values[q]));
                p = r;
                q = Integer.MIN_VALUE;
                r = Integer.MIN_VALUE;
            } else if (q > Integer.MIN_VALUE && values[q] < values[i]) {
                r = i;
                //set q, remove cover for r
            } else if (p > Integer.MIN_VALUE && values[p] > values[i]) {
                q = i;
                r = i;
            } else if (values[i] < values[p]) {
                //set p, remove cover for q
                p = i;
                q = i;
            }
            if (r > Integer.MIN_VALUE) {
                depth = Math.max(depth, Math.min(values[p] - values[q], values[r] - values[q]));
            }
        }
        return depth;
    }
}

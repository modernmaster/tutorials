package uk.co.jamesmcguigan.algorithms;

public class Fibonacci {

    private Fibonacci() {
    }

    //Think about trees.  Count all 1 at the bottom level of the tree.
    public static int calculateFibonacciRecursive(final int n) {
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else {
            return calculateFibonacciRecursive(n - 1) + calculateFibonacciRecursive(n - 2);
        }
    }

    public static int calculateFibonacciMemorization(final int n) {
        return calculateFibonacciMemorization(n, new int[n + 1]);
    }

    private static int calculateFibonacciMemorization(final int n, final int[] cache) {
        if (n == 0 || n == 1) {
            return n;
        }
        if (cache[n] == 0) {
            cache[n] = calculateFibonacciMemorization(n - 1, cache) + calculateFibonacciMemorization(n - 2, cache);
        }
        return cache[n];
    }

    public static int calculateFibonacciIterative(final int n) {

        int parentNumber = 0, grandParentNumber = 0, currentTotal = 0;
        for (int i = 0; i < n; i++) {
            if (i == 0) {
                currentTotal = 1;
            } else {
                currentTotal = parentNumber + grandParentNumber;
            }
            grandParentNumber = parentNumber;
            parentNumber = currentTotal;
        }
        return currentTotal;
    }
}

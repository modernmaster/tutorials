package uk.co.jamesmcguigan.algorithms;

public class Fibonacci {

    //Think about trees.  Count all 1 at the bottom level of the tree.
    public static int GetFibonacciRecursive(int n) {
        if(n==0){
            return 0;
        }
        else if(n==1){
            return 1;
        }
        else{
            return GetFibonacciRecursive(n - 1)+ GetFibonacciRecursive(n - 2);
        }
    }

    public static int GetFibonacciMemorization(int n) {
        return GetFibonacciMemorization(n, new int[n + 1]);
    }

    private static int GetFibonacciMemorization(int n, int [] cache) {
        if(n==0 || n==1) return n;
        if (cache[n] == 0){
            cache[n] = GetFibonacciMemorization(n - 1, cache)+ GetFibonacciMemorization(n - 2,cache);
        }
        return cache[n];
    }

    public static int GetFibonacciIterative(int n) {

        int parentNumber = 0,grandParentNumber = 0,currentTotal = 0;
        for(int i=0;i<n;i++) {
            currentTotal =  i == 0? 1 : parentNumber + grandParentNumber;
            grandParentNumber = parentNumber;
            parentNumber = currentTotal;
        }
        return currentTotal;
    }
}

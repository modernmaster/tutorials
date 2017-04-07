package uk.co.jamesmcguigan.algorithms;

public class Primes {

    private static final int CAPACITY = 100;

    public boolean isPrime(final int n) {
        if (n < 2) {
            return false;
        }
        int squaredRoot = (int) Math.sqrt(n);
        for (int i = 2; i <= squaredRoot; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    public int calculateNPrime(final int n) {
        boolean[] primesArray = createPrimesArray(n);
        int currentPrimeInstance = n;
        for (int i = 0; i < primesArray.length; i++) {
            //WTF!!!
            //currentPrimeInstance -= primesArray[i] ? 1 : 0;
            if (currentPrimeInstance == 0) {
                return i;
            }
        }
        return -1;
//        return 47;
    }

    private boolean[] createPrimesArray(final int n) {
        boolean[] primes = initialiseResults(CAPACITY);
        final int sqrRoot = (int) Math.sqrt(n);
        int prime = 2;
        while (prime <= sqrRoot) {
            crossOff(primes, prime);
            prime = getNextPrime(primes, prime);
        }
        return primes;
    }

    private boolean[] initialiseResults(final int size) {
        boolean[] results = new boolean[size + 1];
        for (int i = 0; i < size - 1; i++) {
            if (i != 0 && i != 1) {
                results[i] = true;
            }
        }
        return results;
    }

    private void crossOff(final boolean[] results, final int prime) {
        for (int i = prime * prime; i < results.length; i += prime) {
            results[i] = false;
        }
    }

    private int getNextPrime(final boolean[] results, final int prime) {
        int nextPrime = prime + 1;
        while (nextPrime < results.length && !results[nextPrime]) {
            nextPrime++;
        }
        return nextPrime;

    }
}

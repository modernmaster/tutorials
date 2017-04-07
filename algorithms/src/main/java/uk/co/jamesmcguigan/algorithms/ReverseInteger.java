package uk.co.jamesmcguigan.algorithms;

public class ReverseInteger {

    private static final int TEN = 10;

    public int reverse(final int x) {
        long k = x;

        //how to deal with negatives
        boolean isNegtive = false;
        if (k < 0) {
            k = 0 - k;
            isNegtive = true;
        }

        //to deal with overflow use 64bit long to store result
        long result = 0;
        while (k != 0) {
            result *= TEN;
            result += k % TEN;
            k /= TEN;
        }

        // if long result is bigger than max int..
        if (result > Integer.MAX_VALUE) {
            return 0;
        }
        if (isNegtive) {
            return 0 - ((int) result);
        }
        return (int) result;
    }
}

package uk.co.jamesmcguigan.algorithms;

public class PalindromeNumber {

    public static final int TEN = 10;

    public boolean isPalindrome(final int number) {
        int tmpNumber = number;
        int n = tmpNumber;
        int rev = 0;
        while (tmpNumber > 0) {
            int dig = tmpNumber % TEN;
            rev = rev * TEN + dig;
            tmpNumber = tmpNumber / TEN;
        }

        return n == rev;
    }
}

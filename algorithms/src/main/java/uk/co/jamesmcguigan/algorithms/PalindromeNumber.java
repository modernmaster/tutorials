package uk.co.jamesmcguigan.algorithms;

public class PalindromeNumber {
    public boolean isPalindrome(int x) {
        int n = x;
        int rev = 0;
        while (x > 0)
        {
            int dig = x % 10;
            rev = rev * 10 + dig;
            x = x / 10;
        }

        return n == rev;
    }
}

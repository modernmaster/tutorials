package uk.co.jamesmcguigan.algorithms;

public class ReverseInteger {
    public int reverse(int x) {
        long k = x;

        //how to deal with negatives
        boolean isNegtive = false;
        if(k < 0){
            k = 0 - k;
            isNegtive = true;
        }

        //to deal with overflow use 64bit long to store result
        long result = 0;
        while(k != 0){
            result *= 10;
            result += k % 10;
            k /= 10;
        }

       // if long result is bigger than max int..
        if(result > Integer.MAX_VALUE) return 0;
        return isNegtive  ? 0 - ((int)result) : (int)result;
    }
}

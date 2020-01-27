package uk.co.jamesmcguigan.algorithms;

public class SmallestSubArray {
    public int calculate(int[] input, int x) throws NotPossibleException {
        int smallestSubArray = 0;
        for (int i = 0; i < input.length; i++) {
            int sum = input[i];
            for (int j = i + 1; j < input.length; j++) {
                int currentLength = j - i;
                if (sum > x) {
                    smallestSubArray = currentLength;
                    break;
                }
                sum += input[j];
            }
        }
        if (smallestSubArray == 0) {
            throw new NotPossibleException("sdfasdf");
        }
        return smallestSubArray;
    }
}

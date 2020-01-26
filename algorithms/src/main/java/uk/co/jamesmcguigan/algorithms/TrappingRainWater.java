package uk.co.jamesmcguigan.algorithms;

public class TrappingRainWater {
    public int calculate(final int[] input) {
        int n = input.length;
        int result = 0;
        int leftMaxPeak = 0, rightMaxPeak = 0;
        int i = 0, j = n - 1;
        while (i <= j) {
            if (input[i] < input[j]) {
                if (input[i] > leftMaxPeak) {
                    //Found peak, update max on left
                    leftMaxPeak = input[i];
                } else {
                    result += leftMaxPeak - input[i];
                }
                i++;
            } else {
                if (input[j] > rightMaxPeak) {
                    //Found peak, update max on right
                    rightMaxPeak = input[j];
                } else {
                    result += rightMaxPeak - input[j];
                }
                j--;
            }
        }
        return result;
    }
}

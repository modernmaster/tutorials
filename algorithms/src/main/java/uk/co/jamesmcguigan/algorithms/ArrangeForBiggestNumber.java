package uk.co.jamesmcguigan.algorithms;

import java.util.Arrays;
import java.util.Comparator;

public class ArrangeForBiggestNumber {
    public long calculate(Integer[] givenNumbers) {

        Arrays.sort(givenNumbers, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                int XY = Integer.parseInt(o1.toString() + o2.toString());
                int YX = Integer.parseInt(o2.toString() + o1.toString());
                if (XY == YX) {
                    return 0;
                }
                return XY < YX ? 1 : -1;
            }
        });
        return buildOutputNumber(givenNumbers);
    }

    private long buildOutputNumber(Integer[] input) {
        StringBuilder sb = new StringBuilder();
        for (Integer l : input) {
            sb.append(l);
        }
        return Long.parseLong(sb.toString());
    }
}

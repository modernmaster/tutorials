package uk.co.jamesmcguigan.algorithms.combinatorial;

import java.util.ArrayList;
import java.util.List;

public class UglyNumbers {

    private static final int TWO = 2;
    private static final int THREE = 3;
    private static final int FIVE = 5;

    public boolean isUgly(final int num) {
        int tmpNum = num;
        int div = TWO * THREE * FIVE;
        while (tmpNum > 0 && div > 1) {
            if (tmpNum % div == 0) {
                tmpNum /= div;
            } else if (div % TWO == 0 && tmpNum % TWO != 0) {
                div /= TWO;
            } else if (div % THREE == 0 && tmpNum % THREE != 0) {
                div /= THREE;
            } else if (div % FIVE == 0 && tmpNum % FIVE != 0) {
                div /= FIVE;
            }
        }
        return tmpNum == 1;
    }

    public int nthUglyNumber(final int n) {
        if (n <= 0) {
            return 0;
        }
        int a = 0, b = 0, c = 0;
        List<Integer> table = new ArrayList<Integer>();
        table.add(1);
        while (table.size() < n) {
            int nextVal = Math.min(table.get(a) * TWO, Math.min(table.get(b) * THREE, table.get(c) * FIVE));
            table.add(nextVal);
            if (table.get(a) * TWO == nextVal) {
                a++;
            }
            if (table.get(b) * THREE == nextVal) {
                b++;
            }
            if (table.get(c) * FIVE == nextVal) {
                c++;
            }
        }
        return table.get(table.size() - 1);
    }
}

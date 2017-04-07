package uk.co.jamesmcguigan.algorithms;

public class ZigZag {
    public String convert(final String s, final int numRows) {
        if (numRows == 1 || s.length() < numRows) {
            return s;
        }
        int currentPos = 0;
        int columns = s.length();
        char[][] rows = new char[numRows][columns];

        for (int j = 0; j < columns; j++) {
            if (j % 2 == 0) {
                for (int i = 0; i < numRows; i++) {
                    if (currentPos >= s.length()) {
                        break;
                    }
                    rows[i][j] = s.charAt(currentPos);
                    currentPos++;
                }
            } else {
                for (int i = numRows - 2; i > 0; i--) {
                    if (currentPos >= s.length()) {
                        break;
                    }
                    rows[i][j] = s.charAt(currentPos);
                    currentPos++;
                }
            }
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < columns; j++) {
                if (rows[i][j] != '\0') {
                    builder.append(rows[i][j]);
                }
            }
        }
        return builder.toString();
    }
}

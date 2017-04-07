package uk.co.jamesmcguigan.algorithms.longestcommonprefix;

public class DivideAndConqueor implements LongestCommonPrefix {
    public String longestCommonPrefix(final String[] strs) {
        int start = 0;
        return longestCommonPrefix(strs, start, strs.length - 1);
    }

    public String longestCommonPrefix(final String[] strs, final int l, final int r) {
        if (l == r) {
            return strs[l];
        } else {
            int mid = (l + r) / 2;
            String left = longestCommonPrefix(strs, l, mid);
            String right = longestCommonPrefix(strs, mid + 1, r);
            return longestCommonPrefix(left, right);
        }
    }

    public String longestCommonPrefix(final String l, final String r) {
        String commonPrefix = "";
        int min = Math.min(l.length(), r.length());

        for (int i = 0; i < min; i++) {
            char currentChar = l.charAt(i);
            if (currentChar == r.charAt(i)) {
                commonPrefix += currentChar;
            } else {
                return commonPrefix;
            }
        }
        return commonPrefix;
    }


}

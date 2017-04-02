package uk.co.jamesmcguigan.algorithms.longestcommonprefix;

public class HorizontalScanning implements LongestCommonPrefix {
    public String longestCommonPrefix(final String[] strs) {
        if (strs.length == 0) {
            return "";
        }
        String commonPrefix = strs[0];
        for (int i = 1; i < strs.length; i++) {
            while (strs[i].indexOf(commonPrefix) == -1) {
                commonPrefix = commonPrefix.substring(0, commonPrefix.length() - 1);
                if (commonPrefix.isEmpty()) {
                    return "";
                }
            }
        }
        return commonPrefix;
    }
}

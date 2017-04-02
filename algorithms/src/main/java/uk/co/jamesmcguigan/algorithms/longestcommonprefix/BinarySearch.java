package uk.co.jamesmcguigan.algorithms.longestcommonprefix;

public class BinarySearch implements LongestCommonPrefix {
    public String longestCommonPrefix(final String[] strs) {
        int shortestLength = findShortestLength(strs);

        int low = 1;
        int high = shortestLength;

        while (low <= high) {
            int midPoint = (low + high) / 2;
            String prefix = strs[0].substring(0, midPoint);
            // if found discard search space before low
            if (containsPrefix(strs, prefix)) {
                low = midPoint + 1;
                // if not discard search space above
            } else {
                high = midPoint - 1;
            }
        }
        //return strs[0].substring(0, (low+high)/2);
        return strs[0].substring(0, high);
    }

    private int findShortestLength(final String[] strs) {
        int shortestLength = Integer.MAX_VALUE;

        for (String item : strs) {
            shortestLength = Math.min(shortestLength, item.length());
        }
        return shortestLength;
    }

    private boolean containsPrefix(final String[] strs, final String prefix) {
        for (int i = 1; i < strs.length; i++) {
            if (!strs[i].startsWith(prefix)) {
                return false;
            }
        }
        return true;
    }
}

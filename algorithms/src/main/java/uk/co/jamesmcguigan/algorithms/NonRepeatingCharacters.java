package uk.co.jamesmcguigan.algorithms;

public class NonRepeatingCharacters {

    public static final int INT = 256;

    public char calculate(final String str) {
        char[] charArray = getCharCountArray(str);
        int index = -1, i;
        for (i = 0; i < str.length(); i++) {
            if (charArray[str.charAt(i)] == 1) {
                index = i;
                break;
            }
        }
        return str.charAt(index);
    }

    private char[] getCharCountArray(final String str) {
        char[] countArray = new char[INT];
        for (int character : str.toCharArray()) {
            countArray[character]++;
        }
        return countArray;
    }
}

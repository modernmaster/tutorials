package uk.co.jamesmcguigan.algorithms;

import java.util.Arrays;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Set;

public class LongestWord {

    public static final int MAX_LENGTH = 11;
    public static final int MIN_LENGTH = 1;

    static class Dictionary {
        private String[] entries;

        Dictionary(final String[] entries) {
            this.entries = entries;
        }

        public boolean contains(final String word) {
            return Arrays.asList(entries).contains(word);
        }
    }

    public Set<String> longestWord(final String letters, final Dictionary dict) {
        if (letters.length() < MIN_LENGTH || letters.length() > MAX_LENGTH) {
            throw new InputMismatchException();
        }
        Set<String> result = new HashSet<>();
        permuteString(letters, "", dict, result);
        return result;
    }

    private void permuteString(final String letters, final String answer, final Dictionary dict, final Set<String> result) {
        if (letters.length() == 0) {
            if (answer.contains("?")) {
                dealWithWildcardCharacters(answer, dict, result);
            } else {
                processLetters(answer, dict, result);
            }
            return;
        }
        for (int i = 0; i < letters.length(); i++) {
            char ch = letters.charAt(i);
            String ros = letters.substring(0, i)
                    + letters.substring(i + 1);
            permuteString(ros, answer + ch, dict, result);
        }
    }

    private void dealWithWildcardCharacters(final String answer, final Dictionary dict, final Set<String> result) {
        int ASCII_A_CODE = 'a';
        int ASCII_Z_CODE = 'z';
        for (int i = ASCII_A_CODE; i < ASCII_Z_CODE; i++) {
            char ch = (char) i;
            String populatedString = answer.replace("?", String.valueOf(ch));
            processLetters(populatedString, dict, result);
        }
    }

    private void processLetters(final String answer, final Dictionary dictionary, final Set<String> result) {
        int currentMaxLength = result.isEmpty() ? 0 : result.toArray()[0].toString().length();
        checkWord(answer, dictionary, result);
        String processedAnswer = answer;
        while (!processedAnswer.equals("") && processedAnswer.length() >= currentMaxLength) {
            processedAnswer = processedAnswer.substring(0, processedAnswer.length() - 1);
            checkWord(processedAnswer, dictionary, result);
        }
    }

    private void checkWord(final String str, final Dictionary dictionary, final Set<String> result) {
        if (dictionary.contains(str)) {
            if (!result.isEmpty()) {
                int maxLength = result.toArray()[0].toString().length();
                if (maxLength > str.length()) {
                    return;
                }
                if (maxLength < str.length()) {
                    result.clear();
                }
            }
            result.add(str);
        }
    }
}

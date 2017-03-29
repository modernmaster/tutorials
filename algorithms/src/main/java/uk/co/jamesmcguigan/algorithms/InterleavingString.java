package uk.co.jamesmcguigan.algorithms;

public class InterleavingString {
    public boolean isInterleave(String s1, String s2, String s3) {
        StringBuilder s3Builder = new StringBuilder(s3);
        int currentS1Pos=0;

        for(int i = 0; i< s3.length(); i++) {
            if(currentS1Pos==s1.length()) {
                break;
            }
            if (s1.charAt(currentS1Pos)==s3.charAt(i)) {
                s3Builder.deleteCharAt(i);
                currentS1Pos++;
            }
        }
        return s3Builder.toString().equals(s2);
    }
}

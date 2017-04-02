package uk.co.jamesmcguigan.algorithms;

import org.junit.Before;
import org.junit.Test;

public class CompressedStringsTest {

    private CompressedStrings compressedStrings;

    @Before
    public void setUp() {
        compressedStrings = new CompressedStrings();
    }

    @Test
    public void doTest() {

        String compressedValue = "a2b3c2a1";
//        int numberOfQuestions = 4;
//        char[] decompressedValue = compressedStrings.expandIt(compressedValue).toCharArray();
//        for (int i = 0; i < numberOfQuestions; i++) {
//            int position = s.nextInt();
//            System.out.println(decompressedValue[position]);
//        }
        System.out.print(compressedStrings.expandIt(compressedValue));
    }
}

import org.junit.Test;

public class CompressedStringsTest {

    @Test
    public void doTest() {

        String compressedValue = "a2b3c2a1";
        int numberOfQuestions = 4;
        char[] decompressedValue = expandIt(compressedValue).toCharArray();

        for (int i = 0; i < numberOfQuestions; i++) {
            int position = s.nextInt();
            System.out.println(decompressedValue[position]);
        }



        CompressedStrings compressedStrings = new CompressedStrings();
        System.out.print(compressedStrings.expandIt("a2b3c2a1"));
    }
}

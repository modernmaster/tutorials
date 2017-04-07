package uk.co.jamesmcguigan.algorithms;

public class OddNumbers {

    public static final int TENTH = 10;

    public boolean isMagic(final int k) {

        int number = k;
        int oddNumber = 0;
        int evenNumber = 0;
        while (number != 0) {
            if (number % 2 != 0) {
                oddNumber++;
            } else {
                evenNumber++;
            }
            number /= TENTH;
        }
        return oddNumber % 2 != 0 && evenNumber % 2 == 0;
    }

}

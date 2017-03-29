package uk.co.jamesmcguigan.algorithms;

public class OddNumbers {

    public boolean isMagic(int k){
        int oddNumber = 0;
        int evenNumber = 0;
        while(k != 0){
            if(k%2!=0) {
                oddNumber++;
            } else {
                evenNumber++;
            }
            k /= 10;
        }
        return oddNumber%2!=0 && evenNumber%2 == 0;
    }

}

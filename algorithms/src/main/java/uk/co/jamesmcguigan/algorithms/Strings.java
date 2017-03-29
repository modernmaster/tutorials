package uk.co.jamesmcguigan.algorithms;

public class Strings {

    public String reverse(String value) {
        return new StringBuilder(value).reverse().toString();
    }

    public String reverse2(String value) {

        StringBuilder sb = new StringBuilder();
        char [] arrau = value.toCharArray();

        for(int i= arrau.length-1;i>=0;i--) {
            sb.append(arrau[i]);
        }
        return sb.toString();
    }


}

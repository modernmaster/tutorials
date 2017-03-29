package uk.co.jamesmcguigan.algorithms;

public class CompressedStrings {

    public String expandIt(String x) {

        StringBuffer stringBuffer = new StringBuffer();
        char[] t = x.toCharArray();

        for (int i = 0; i<t.length;i++){
            if(Character.isLetter(t[i])) {
                stringBuffer.append(t[i]);

            } else {
                int multiple = Integer.parseInt(String.valueOf(t[i])) - 1;
                char value = t[i-1];
                for(int j=0;j<multiple;j++){
                    stringBuffer.append(value);
                }
            }
        }
        return stringBuffer.toString();
    }
}

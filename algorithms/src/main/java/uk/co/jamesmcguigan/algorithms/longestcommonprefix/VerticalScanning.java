package uk.co.jamesmcguigan.algorithms.longestcommonprefix;

public class VerticalScanning implements LongestCommonPrefix {
    public String longestCommonPrefix(String[] strs) {

        if(strs.length==0) {
            return "";
        }
        String commonPrefix = strs[0];
        for(int i = 0; i<commonPrefix.length();i++) {
            for(int j = 1; j<strs.length;j++) {
                if(commonPrefix.charAt(i)!=strs[j].charAt(i)){
                    return commonPrefix.substring(0,i);
                }
            }
        }
        return commonPrefix;
    }
}

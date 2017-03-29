package uk.co.jamesmcguigan.algorithms.longestcommonprefix.trie;

import uk.co.jamesmcguigan.algorithms.longestcommonprefix.LongestCommonPrefix;

public class Trie implements LongestCommonPrefix {
    private Node rootNode;

    public Trie() {
        rootNode = new Node();
    }

    public String longestCommonPrefix(String[] strs) {

        Node node = rootNode;
        String commonPrefix = "";
        String shortestWord = getShortestWord(strs);

        insertWords(strs);

        for (int i = 0; i < shortestWord.length(); i++) {
            char curLetter = shortestWord.charAt(i);
            if(node.getNumberOfChildren()>1 || node.isEnd()) {
                return commonPrefix;
            } else {
                commonPrefix+=curLetter;
                node = node.get(curLetter);
            }
        }
        return commonPrefix;
    }

    private void insertWords(String[] strs) {
        for(String str: strs) {
            insertAWord(str);
        }
    }

    private String getShortestWord(String[] strs) {
        String shortestWord = strs[0];
        for (String str: strs) {
            if(str.length()<shortestWord.length()) {
                shortestWord = str;
            }
        }
        return shortestWord;
    }


    private void insertAWord(String word) {
        Node node = rootNode;
        for (int i = 0; i < word.length(); i++) {
            char currentChar = word.charAt(i);
            if (!node.containsKey(currentChar)) {
                node.put(currentChar, new Node());
            }
            node = node.get(currentChar);
        }
        node.setEnd();
    }

    //Requires wildcard capability

    private Node searchPrefix(String word) {
        Node node = rootNode;
        for (int i = 0; i < word.length(); i++) {
            char curLetter = word.charAt(i);
            if (node.containsKey(curLetter)) {
                node = node.get(curLetter);
            } else {
                return null;
            }
        }
        return node;
    }

    // Returns if the word is in the trie.
    public boolean search(String word) {
        Node node = searchPrefix(word);
        return node != null && node.isEnd();
    }
}

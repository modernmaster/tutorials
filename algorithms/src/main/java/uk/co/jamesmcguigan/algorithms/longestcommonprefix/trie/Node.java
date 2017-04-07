package uk.co.jamesmcguigan.algorithms.longestcommonprefix.trie;

public class Node {
    private final int characterCount = 26;
    // characterCount links to node children
    private Node[] links;
    private int numberOfChildren;
    private boolean isEnd;

    public Node() {
        links = new Node[characterCount];
        numberOfChildren = 0;
    }

    public int getNumberOfChildren() {
        return numberOfChildren;
    }

    public boolean containsKey(final char ch) {
        return links[ch - 'a'] != null;
    }

    public Node get(final char ch) {
        return links[ch - 'a'];
    }

    public void put(final char ch, final Node node) {
        //automatically casts to an int a - a = 0 (first place in array)
        if (links[ch - 'a'] == null) {
            numberOfChildren++;
        }
        links[ch - 'a'] = node;
    }

    public void setEnd() {
        isEnd = true;
    }

    public boolean isEnd() {
        return isEnd;
    }
}

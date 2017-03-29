package uk.co.jamesmcguigan.algorithms.longestcommonprefix.trie;

public class Node {
    // R links to node children
    private Node[] links;
    private int numberOfChildren;

    private final int R = 26;

    private boolean isEnd;

    public Node() {
        links = new Node[R];
        numberOfChildren = 0;
    }

    public int getNumberOfChildren() {
        return numberOfChildren;
    }

    public boolean containsKey(char ch) {
        return links[ch -'a'] != null;
    }
    public Node get(char ch) {
        return links[ch -'a'];
    }

    public void put(char ch, Node node) {
        //automatically casts to an int a - a = 0 (first place in array)
        if(links[ch -'a']==null) {
            numberOfChildren++;
        }
        links[ch -'a'] = node;
    }
    public void setEnd() {
        isEnd = true;
    }
    public boolean isEnd() {
        return isEnd;
    }
}

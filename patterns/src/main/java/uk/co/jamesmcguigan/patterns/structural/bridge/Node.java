package uk.co.jamesmcguigan.patterns.structural.bridge;

class Node {
    private int value;
    private Node prev, next;

    Node(final int i) {
        value = i;
    }

    public int getValue() {
        return value;
    }

    public Node getPrev() {
        return prev;
    }

    public Node getNext() {
        return next;
    }

    public void setPrev(final Node prev) {
        this.prev = prev;
    }

    public void setNext(final Node next) {
        this.next = next;
    }
}

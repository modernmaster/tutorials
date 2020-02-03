package uk.co.jamesmcguigan.datastructures;

public class DoublyLinkedList {

    private static Node first = null;
    private static Node last = null;

    public void addFirst(final String data) {
        Node node = new Node(data);
        if (last == null) {
            last = node;
        }
        if (first == null) {
            first = node;
        } else {
            first.setPrev(node);
            node.setNext(first);
            first = node;
        }
    }

    public void addLast(final String data) {
        Node node = new Node(data);
        if (first == null) {
            first = node;
        }
        if (last == null) {
            last = node;
        } else {
            last.setNext(node);
            node.setPrev(last);
            last = node;
        }
    }

    public String removeFirst() {
        if (first != null) {
            String value = first.getValue();
            first = first.getNext();
            return value;
        }
        throw new NullPointerException();
    }

    public String removeLast() {
        if (last != null) {
            String value = last.getValue();
            last = last.getPrev();
            return value;
        }
        throw new NullPointerException();
    }

    public String peekFirst() {
        if (first != null) {
            return first.getValue();
        }
        throw new NullPointerException();
    }

    public String peekLast() {
        if (last != null) {
            return last.getValue();
        }
        throw new NullPointerException();
    }

    public int size() {
        int count = 0;
        Node curr = last;
        while (curr != null) {
            curr = curr.getPrev();
            count++;
        }
        return count;
    }

    class Node {

        private String value;
        private Node next;
        private Node prev;

        Node(final String value) {
            this.value = value;
        }

        String getValue() {
            return value;
        }

        Node getNext() {
            return next;
        }

        void setNext(final Node next) {
            this.next = next;
        }

        Node getPrev() {
            return prev;
        }

        void setPrev(final Node prev) {
            this.prev = prev;
        }
    }
}

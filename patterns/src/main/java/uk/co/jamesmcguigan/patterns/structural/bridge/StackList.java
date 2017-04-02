package uk.co.jamesmcguigan.patterns.structural.bridge;

public class StackList {
    private Node last;

    public void push(final int i) {
        if (last == null) {
            last = new Node(i);
        } else {
            last.setNext(new Node(i));
            last.getNext().setNext(last);
            last = last.getNext();
        }
    }

    public boolean isEmpty() {
        return last == null;
    }

    public boolean isFull() {
        return false;
    }

    public int top() {
        if (isEmpty()) {
            return -1;
        }
        return last.getValue();
    }

    public int pop() {
        if (isEmpty()) {
            return -1;
        }
        int ret = last.getValue();
        last = last.getPrev();
        return ret;
    }
}

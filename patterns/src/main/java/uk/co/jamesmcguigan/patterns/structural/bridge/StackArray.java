package uk.co.jamesmcguigan.patterns.structural.bridge;

public class StackArray {

    private static final int CAPACITY = 12;
    private int[] items = new int[CAPACITY];
    private int total = -1;

    public void push(final int i) {
        if (!isFull()) {
            items[++total] = i;
        }
    }

    public boolean isEmpty() {
        return total == -1;
    }

    public boolean isFull() {
        return total == CAPACITY - 1;
    }

    public int top() {
        if (isEmpty()) {
            return -1;
        }
        return items[total];
    }

    public int pop() {
        if (isEmpty()) {
            return -1;
        }
        return items[total--];
    }
}

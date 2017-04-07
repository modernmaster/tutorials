package uk.co.jamesmcguigan.datastructures;

import java.util.EmptyStackException;
import java.util.NoSuchElementException;

public class Queue<T> {

    private QueueNode<T> first = null;
    private QueueNode<T> last = null;

    public void add(final T data) {
        QueueNode newNode = new QueueNode<T>(data);
        if (last != null) {
            last.next = newNode;
        }
        last = newNode;
        if (first == null) {
            first = last;
        }
    }

    public T remove() throws EmptyStackException {
        if (first == null) {
            throw new NoSuchElementException();
        }
        T data = first.data;
        first = first.next;
        if (first == null) {
            first = last;
        }
        return data;
    }

    public T peek() throws EmptyStackException {
        if (first == null) {
            throw new EmptyStackException();
        }
        return first.data;
    }

    public boolean isEmpty() {
        return first == null;
    }

    private static class QueueNode<T> {
        private QueueNode<T> next;
        private T data;

        QueueNode(final T data) {
            this.data = data;
        }
    }
}

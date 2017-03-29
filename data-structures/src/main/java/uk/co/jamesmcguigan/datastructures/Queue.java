package uk.co.jamesmcguigan.datastructures;

import java.util.EmptyStackException;
import java.util.NoSuchElementException;

/**
 * Created by James McGuigan on 12/09/2015.
 */
public class Queue<T> {

    private static class QueueNode<T> {
        private QueueNode<T> next;
        private T data;

        public QueueNode(T data) {
            this.data = data;
        }
    }

    private QueueNode<T> first = null;
    private QueueNode<T> last = null;

    public void Add(T data) {
        QueueNode newNode = new QueueNode<T>(data);
        if(last != null) {
            last.next = newNode;
        }
        last = newNode;
        if(first == null){
            first = last;
        }
    }

    public T Remove() throws EmptyStackException{
        if(first==null)throw new NoSuchElementException();
        T data = first.data;
        first = first.next;
        if(first == null){
            first = last;
        }
        return data;
    }

    public T Peek() throws EmptyStackException {
        if(first==null)throw new EmptyStackException();
        return first.data;
    }

    public boolean IsEmpty() {
        return first == null;
    }
}
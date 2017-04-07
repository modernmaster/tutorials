package uk.co.jamesmcguigan.datastructures;

import java.util.EmptyStackException;

/**
 * Created by James McGuigan on 12/09/2015.
 */
public class Stack {

    private StackNode top = null;

    public void Push(final String data) {
        StackNode newNode = new StackNode(data);
        newNode.next = top;
        top = newNode;
    }

    public String pop() throws EmptyStackException {
        if (top == null) {
            throw new EmptyStackException();
        }
        StackNode poppedNode = top;
        top = poppedNode.next;
        return poppedNode.data;
    }

    public String peek() throws EmptyStackException {
        if (top == null) {
            throw new EmptyStackException();
        }
        return top.data;
    }

    public boolean isEmpty() {
        return top == null;
    }

    private static class StackNode {
        private StackNode next;
        private String data;

        StackNode(final String data) {
            this.data = data;
        }
    }
}

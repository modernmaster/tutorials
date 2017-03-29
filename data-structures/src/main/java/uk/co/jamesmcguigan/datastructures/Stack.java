package uk.co.jamesmcguigan.datastructures;

import java.util.EmptyStackException;

/**
 * Created by James McGuigan on 12/09/2015.
 */
public class Stack {

    private static class StackNode {
        private StackNode next;
        private String data;

        public StackNode(String data) {
            this.data = data;
        }
    }

    private StackNode top = null;

    public void Push(String data) {
        StackNode newNode = new StackNode(data);
        newNode.next = top;
        top = newNode;
    }

    public String Pop() throws EmptyStackException{
        if(top==null)throw new EmptyStackException();
        StackNode poppedNode = top;
        top = poppedNode.next;
        return poppedNode.data;
    }

    public String Peek() throws EmptyStackException {
        if(top==null)throw new EmptyStackException();
        return top.data;
    }

    public boolean IsEmpty() {
        return top == null;
    }
}

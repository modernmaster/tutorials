package uk.co.jamesmcguigan.patterns.structural.bridge;

public class StackFifo extends StackArray {
    private StackArray temp = new StackArray();
    public int pop() {
        while ( ! isEmpty())
            temp.push( super.pop() );
        int ret =  temp.pop();
        while ( ! temp.isEmpty())
            push( temp.pop() );
        return ret;
    }
}

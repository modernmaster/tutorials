package uk.co.jamesmcguigan.patterns.structural.bridge;

public class StackHanoi extends StackArray {
    private int totalRejected = 0;
    public int reportRejected()   { return totalRejected; }
    public void push( int in ) {
        if ( ! isEmpty()  &&  in > top())
            totalRejected++;
        else super.push( in );
    }
}

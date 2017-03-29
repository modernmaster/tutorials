package uk.co.jamesmcguigan.patterns.structural.composite;

public class Primitive implements Component {        // 2. "Isa" relationship
    private int value;
    public Primitive( int val ) { value = val; }
    public void traverse()      { System.out.print( value + "  " ); }
}

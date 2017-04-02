package uk.co.jamesmcguigan.patterns.structural.composite;

// Two different kinds of "container" classes.  Most of the "meat" is in the Composite base class.
public class Row extends Composite {
    public Row(final int val) {
        super(val);
    }

    public void traverse() {
        System.out.print("Row");
        super.traverse();
    }
}

package uk.co.jamesmcguigan.patterns.structural.composite;

public abstract class Composite implements Component {      // 2. "Isa" relationship
    private Component[] children = new Component[9];  // 3. Couple to interface
    private int total = 0;
    private int value;

    public Composite(int val) {
        value = val;
    }

    public void add(Component c) {
        children[total++] = c;
    } // 3. Couple to

    public void traverse() {                                  //    interface
        System.out.print(value + "  ");
        for (int i = 0; i < total; i++)
            children[i].traverse();            // 4. Delegation and polymorphism
    }
}

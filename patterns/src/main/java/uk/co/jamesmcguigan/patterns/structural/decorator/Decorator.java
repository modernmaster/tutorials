package uk.co.jamesmcguigan.patterns.structural.decorator;

public abstract class Decorator implements Widget {
    private Widget wid; // 4. "has a" relationship

    public Decorator(final Widget w) {
        wid = w;
    }

    // 5. Delegation
    public void draw() {
        wid.draw();
    }
}

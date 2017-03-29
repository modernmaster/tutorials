package uk.co.jamesmcguigan.patterns.structural.flyweight;

import org.junit.Test;

import java.awt.*;

public class FlyweightTest {

    @Test
    public void testCreationOfColorBoxesSharingResources() {
        int size = 18, pause = 50;
        Frame f = new Frame("ColorBoxes - 1 thread per ColorBox");
        f.setLayout(new GridLayout(size, size));
        for (int i = 0; i < size * size; i++) f.add(new ColorBox(pause));
        f.setSize(500, 400);
        f.setVisible(true);
    }
}

package uk.co.jamesmcguigan.patterns.creational.builder;

import org.junit.Test;

import java.awt.*;

public class StreetMapTest {

    @Test
    public void testStreetMapBuildler() {
        StreetMap map = new StreetMap.Builder(new Point(50, 50), new Point(100,
                100)).landColor(Color.GRAY).waterColor(Color.BLUE.brighter())
                .build();
    }

}

package uk.co.jamesmcguigan.patterns.structural.decorator;

import org.junit.Test;

public class DecoratorTest {

    @Test
    public void testAddingOfResponsibilitiesToBorder() {
        // 8. Client has the responsibility to compose desired configurations
        Widget aWidget = new BorderDecorator(
                new BorderDecorator(
                        new ScrollDecorator(
                                new TextField( 80, 24 ))));
        aWidget.draw();
    }
}

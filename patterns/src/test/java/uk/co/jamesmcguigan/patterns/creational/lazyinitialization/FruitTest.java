package uk.co.jamesmcguigan.patterns.creational.lazyinitialization;

import org.junit.Test;

public class FruitTest {

    @Test
    public void testGettingFruit() {
        Fruit.getFruitByTypeName(FruitType.banana);
        Fruit.showAll();
        Fruit.getFruitByTypeName(FruitType.apple);
        Fruit.showAll();
        Fruit.getFruitByTypeName(FruitType.banana);
        Fruit.showAll();
    }
}

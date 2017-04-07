package uk.co.jamesmcguigan.patterns.behavioural.iterator;

import org.junit.Test;

import java.util.Enumeration;

public class IteratorTest {

    @Test
    public void testIterationThroughData() {
        ClassWithData set = new ClassWithData();
        for (int i = 2; i < 10; i += 2) set.add(i);
        for (int i = 1; i < 9; i++)
            System.out.print(i + "-" + set.isMember(i) + "  ");

        // 3. Clients ask the collection object to create many iterator objects
        ClassWithData.Iterator it1 = set.createIterator();
        ClassWithData.Iterator it2 = set.createIterator();

        // 4. Clients use the first(), isDone(), next(), currentItem() protocol
        System.out.print("\nIterator:    ");
        for (it1.first(), it2.first(); !it1.isDone(); it1.next(), it2.next())
            System.out.print(it1.currentItem() + " " + it2.currentItem() + "  ");

        // Java uses a different collection traversal "idiom" called Enumeration
        System.out.print("\nEnumeration: ");
        for (Enumeration e = set.getHashtable().keys(); e.hasMoreElements(); )
            System.out.print(e.nextElement() + "  ");
        System.out.println();
    }
}
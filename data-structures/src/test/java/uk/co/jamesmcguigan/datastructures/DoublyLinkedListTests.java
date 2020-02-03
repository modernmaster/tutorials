package uk.co.jamesmcguigan.datastructures;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class DoublyLinkedListTests {

    private DoublyLinkedList doublyLinkedList;

    @Before
    public void setUp() {
        doublyLinkedList = new DoublyLinkedList();
    }

    @Test
    public void testExampleOne() {
        doublyLinkedList.addLast("a");
        doublyLinkedList.addLast("b");
        assertThat(doublyLinkedList.size(), equalTo(2));
        assertThat(doublyLinkedList.peekFirst(), equalTo("a"));
    }

}

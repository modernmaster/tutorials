package uk.co.jamesmcguigan.collections;

import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

public class OrderingTests {

    @Test
    public void testNaturalOrderingWithIntegers() {
        List<Integer> expectedNumbers = Lists.newArrayList(1,2,3,4,5,6,7,8,9,10);
        List<Integer> numbers = Lists.newArrayList(10,9,8,7,6,5,4,3,2,1);
        List<Integer> sortedNumbers = Ordering.natural().immutableSortedCopy(numbers);
        assertThat(sortedNumbers, equalTo(expectedNumbers));
    }

    @Test
    public void testNaturalOrderingWithStrings() {
        List<String> expectedStrings = Lists.newArrayList("vvvvvv","wwwwww","xxxxx","yyyyy","zzzz");
        List<String> strings = Lists.newArrayList("zzzz","yyyyy","xxxxx","wwwwww","vvvvvv");
        List<String> sortedStrings = Ordering.natural().immutableSortedCopy(strings);
        assertThat(sortedStrings, equalTo(expectedStrings));
    }

    @Test
    public void testFalseWhenIdentifyingIfSetIsNotOrdered() {
        List<String> strings = Lists.newArrayList("zzzz","yyyyy","xxxxx","wwwwww","vvvvvv");
        assertFalse(Ordering.natural().isOrdered(strings));
    }

    @Test
    public void testTrueWhenIdentifyingIfSetIsOrdered() {
        List<String> strings = Lists.newArrayList("vvvvvv","wwwwww","xxxxx","yyyyy","zzzz");
        assertTrue(Ordering.natural().isOrdered(strings));
    }


    @Test
    public void testComparatorForToStringRepresentationOfObjects() {

        Person p1 = new Person("jim", "jones", 1234);
        Person p2 = new Person("jim", "smith", 1234);
        Person p3 = new Person("john", "smith", 1234);
        List<Person> unorderedPersons = Arrays.asList(p3,p2,p1);

        List<Person> orderedPersons = Ordering.usingToString().greatestOf(unorderedPersons, 1);

        assertThat(orderedPersons.get(0), equalTo(p3));
    }

    @Test
    public void testComparatorForNullsLast() {

        Person p1 = new Person("jim", "jones", 1234);
        Person p2 = new Person("jim", "smith", 1234);
        Person p3 = new Person("john", "smith", 1234);
        List<Person> unorderedPersons = Arrays.asList(p3,p2,p1, null);

        List<Person> orderedPersons = Ordering.usingToString().nullsLast().greatestOf(unorderedPersons, 1);

        assertNull(orderedPersons.get(0));
    }

    @Test
    public void testComparatorForFirstPersonAsToString() {

        Person p1 = new Person("peter", "jones", 1234);
        Person p2 = new Person("john", "smith", 1234);
        List<Person> persons = Arrays.asList(p1,p2);

        List<Person> sortedPersons = Ordering.usingToString().sortedCopy(persons);

        assertThat(p1, equalTo(sortedPersons.get(0)));
    }
}

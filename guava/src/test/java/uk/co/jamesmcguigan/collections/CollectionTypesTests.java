package uk.co.jamesmcguigan.collections;

import com.google.common.collect.*;
import org.junit.Test;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertSame;
import static junit.framework.Assert.assertTrue;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class CollectionTypesTests {
    //Is not a map
    //True collection type
    //Implements iterator

    //Multiset interface with HashMultiSet
    @Test
    public void testMultisetCreationWithCountAndSize() {
        Multiset<String> set = HashMultiset.create();
        set.add("a");
        set.add("b");
        set.add("c");
        set.add("d");
        set.add("a");
        set.add("e", 5);

        assertThat(2, equalTo(set.count("a")));
        assertThat(10, equalTo(set.size()));
        assertTrue(set.contains("e"));
    }

    //ImmutableMap	ImmutableMultiset	No
    @Test(expected = UnsupportedOperationException.class)
    public void testImmutableMultisetCreationIsImmutable() {
        Multiset<String> set = ImmutableMultiset.of("a", "b", "c", "d");
        set.add("b");
    }

    @Test()
    public void testImmutableMultisetCreationWithCountAndSize() {
        Multiset<String> set = ImmutableMultiset.of("a", "b", "c", "d");
        assertThat(1, equalTo(set.count("a")));
        assertThat(4, equalTo(set.size()));
        assertFalse(set.contains("e"));
    }

    //SortedMultiset interface with TreeMultiset and comparator
    @Test
    public void testTreeMultisetSortingWithCountAndSize() {
        SortedMultiset<String> set = TreeMultiset.create();
        set.add("a");
        set.add("b");
        set.add("c");
        set.add("d");
        set.add("e", 5);
        set.add("a");

        assertThat(2, equalTo(set.count("a")));
        assertThat(10, equalTo(set.size()));
        assertThat("e", equalTo(set.elementSet().pollLast()));
    }

    //Multiset with LinkedHashMultiset implementation
    @Test
    public void testLinkedHashMultisetCreationWithCountAndSize() {
        Multiset<String> set = LinkedHashMultiset.create();
        set.add("a");
        set.add("b");
        set.add("c");
        set.add("d");
        set.add("e", 5);
        set.add("a");

        assertThat(2, equalTo(set.count("a")));
        assertThat(10, equalTo(set.size()));
    }

    //ConcurrentHashMap	ConcurrentHashMultiset implementation with Multiset.  Unable for SortedMultiset
    @Test
    public void testConcurrentHashMultisetCreationWithCountAndSize() {
        Multiset<String> set = ConcurrentHashMultiset.create();
        set.add("a");
        set.add("b");
        set.add("c");
        set.add("d");
        set.add("e", 5);
        set.add("a");
        assertThat(2, equalTo(set.count("a")));
        assertThat(10, equalTo(set.size()));
    }


    //Multimap interface
    //https://github.com/google/guava/wiki/NewCollectionTypesExplained#multimap

    //Bimap interface
    //https://github.com/google/guava/wiki/NewCollectionTypesExplained#bimap

    //Table interface
    //https://github.com/google/guava/wiki/NewCollectionTypesExplained#table

    //Rangeset
    //https://github.com/google/guava/wiki/NewCollectionTypesExplained#rangeset
    //A RangeSet describes a set of disconnected, nonempty ranges. When adding a
    //range to a mutable RangeSet, any connected ranges are merged together, and empty ranges are ignored
    @Test
    public void testRangeset() {
        RangeSet<Integer> rangeSet = TreeRangeSet.create();
        rangeSet.add(Range.closed(1, 10)); // {[1, 10]}
        rangeSet.add(Range.closedOpen(11, 15)); // disconnected range: {[1, 10], [11, 15)}
        rangeSet.add(Range.closedOpen(15, 20)); // connected range; {[1, 10], [11, 20)}
        rangeSet.add(Range.openClosed(0, 0)); // empty range; {[1, 10], [11, 20)}
        rangeSet.remove(Range.open(5, 10)); // splits [1, 10]; {[1, 5], [10, 10], [11, 20)}
    }

    //Rangeset
    //https://github.com/google/guava/wiki/NewCollectionTypesExplained#rangeset
    //RangeMap is a collection type describing a mapping from disjoint, nonempty ranges to values.
    // Unlike RangeSet, RangeMap never "coalesces" adjacent mappings, even if adjacent ranges are mapped to the same values    @Test
    @Test
    public void testRangeMap() {
        RangeMap<Integer, String> rangeMap = TreeRangeMap.create();
        rangeMap.put(Range.closed(1, 10), "foo"); // {[1, 10] => "foo"}
        rangeMap.put(Range.open(3, 6), "bar"); // {[1, 3] => "foo", (3, 6) => "bar", [6, 10] => "foo"}
        rangeMap.put(Range.open(10, 20), "foo"); // {[1, 3] => "foo", (3, 6) => "bar", [6, 10] => "foo", (10, 20) => "foo"}
        rangeMap.remove(Range.closed(5, 11)); // {[1, 3] => "foo", (3, 5) => "bar", (11, 20) => "foo"}
    }


}

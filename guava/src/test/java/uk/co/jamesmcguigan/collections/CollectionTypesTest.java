package uk.co.jamesmcguigan.collections;

import com.google.common.collect.*;
import org.joda.time.DateTime;
import org.junit.Test;

import java.util.List;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

public class CollectionTypesTest {
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
    //Great to use when dealing with a set of multiple items
    //Ordering performed at insertion.
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
        //Very powerful filtering mechanism without using streams/map/reduce
        assertThat(1, equalTo(set.subMultiset("d", BoundType.CLOSED, "z", BoundType.OPEN).size()));
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

    //Deals with Map<K, List<V>> or Map<K, Set<v>>
    //Dealis with keys to multiple values
    //a->1
    //a->2
    //b->3
    //Multimap is not a map

    //Implementations...
//    ArrayListMultimap	HashMap	ArrayList
//    HashMultimap	HashMap	HashSet
//    LinkedListMultimap *	LinkedHashMap``*	LinkedList``*
//    LinkedHashMultimap**	LinkedHashMap	LinkedHashSet
//    TreeMultimap	TreeMap	TreeSet
//    ImmutableListMultimap	ImmutableMap	ImmutableList
//    ImmutableSetMultimap	ImmutableMap	ImmutableSet

    @Test
    public void testMultiMapForMultipleKeys() {

        Multimap<String, List<Integer>> map = ArrayListMultimap.create();

        map.put("a", Lists.newArrayList(1, 2, 3, 4));
        map.put("a", Lists.newArrayList(4, 3, 2, 1));
        map.put("b", Lists.newArrayList(1, 2));
        map.put("c", Lists.newArrayList(3, 4));

        //provides latest state of the multimap
        //modifications update all collections
        //get provides an active view
        assertThat(2, equalTo(map.get("a").size()));
        map.get("a").add(Lists.newArrayList(3, 4));
        //mutable when using
        assertThat(3, equalTo(map.get("a").size()));
        //read only
        //map.asMap()
        assertThat(5, equalTo(map.size()));
    }

    //Bimap interface
    //https://github.com/google/guava/wiki/NewCollectionTypesExplained#bimap
//    Key-Value Map Impl	Value-Key Map Impl	Corresponding BiMap
//    HashMap	HashMap	HashBiMap
//    ImmutableMap	ImmutableMap	ImmutableBiMap
//    EnumMap	EnumMap	EnumBiMap
//    EnumMap	HashMap	EnumHashBiMap
    @Test
    public void testMapValuesBackToTheirKeys() {

        BiMap<String, List<Integer>> map = HashBiMap.create();
        map.put("a", Lists.newArrayList(1, 2, 3, 4));
        //overwrites previous key's value
        map.put("a", Lists.newArrayList(4, 3, 2, 1));
        map.put("b", Lists.newArrayList(1, 2));
        map.put("c", Lists.newArrayList(3, 4));

        assertThat(3, equalTo(map.size()));
        map.inverse().put(Lists.newArrayList(5, 6), "d");
        assertThat(4, equalTo(map.size()));
    }


    //Table interface
    //https://github.com/google/guava/wiki/NewCollectionTypesExplained#table
    //Indexing on more than one key is messy... use table
//    HashBasedTable
//    TreeBasedTable
//    ImmutableTable
//    ArrayTable
//    Possible ordering capability but seems difficult
    @Test
    public void testCreationOfMultiIndexValuesInTable() {
        Table<DateTime, String, String> records = HashBasedTable.create();

        DateTime now = DateTime.now();
        records.put(now, "A", "B");
        records.put(DateTime.now(), "C", "D");
        records.put(DateTime.now(), "E", "F");

        assertThat("B", equalTo(records.row(now).get("A")));
        assertThat("A", is(not(equalTo(records.row(now).get("B")))));
//        records.column("Doe");
//        records.rowKeySet().
    }


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

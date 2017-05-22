package uk.co.jamesmcguigan.collections;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;
import com.google.common.collect.Multisets;
import com.google.common.collect.Sets;
import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Multiset;
import com.google.common.primitives.Ints;

public class CollectionUtilitiesTest {

    private Iterable<Integer> concatenated;

    @Before
    public void SetUp() {
        concatenated = Iterables.concat(Ints.asList(1, 1, 2, 3), Ints.asList(4, 5, 6));
    }

    // Factory methods
    @Test
    public void testCreatedOfListsFromFactory() {

        final int size = 5;
        List list = Lists.newArrayList(1, 2, 3, 4, 5);

        assertThat(list.size(), equalTo(size));

    }

    @Test
    public void testCreatedHashMultisetFromFactory() {
        Multiset<String> multiset = HashMultiset.create();

        assertNotNull(multiset);
    }

    // The overwhelming majority of operations in the Iterables class are lazy: they only advance the backing iteration
    // when absolutely necessary. Methods that themselves return Iterables return lazily computed views, rather than explicitly
    // constructing a collection in memory.
    @Test
    public void testGetLastFromIterable() {
        Integer lastAdded = Iterables.getLast(concatenated);

        assertThat(6, equalTo(lastAdded));
    }

    @Test
    public void testGetFirstFromIterable() {
        Integer firstAdded = Iterables.getFirst(concatenated, 0);

        assertThat(1, equalTo(firstAdded));
    }

    @Test
    public void testGetFrequencyForValueFromIterable() {
        Integer frequency = Iterables.frequency(concatenated, 1);

        assertThat(frequency, equalTo(2));
    }

    @Test
    public void testPartitioningOfIterable() {

        Iterable<List<Integer>> partitions = Iterables.partition(concatenated, 3);
        List<Integer> partition = Lists.newArrayList(1, 1, 2);

        assertFalse(Iterables.isEmpty(partitions));
        assertThat(3, equalTo(Iterables.size(partitions)));
        assertThat(partition, equalTo(Iterables.get(partitions, 0)));
    }

    @Test
    public void testReverseOfList() {

        List<Integer> countUp = Ints.asList(1, 2, 3, 4, 5);
        List<Integer> countDown = Lists.reverse(countUp);

        assertThat(countUp, equalTo(Lists.reverse(countDown)));
    }

    @Test
    public void testIntersectionOfSets() {
        Set<String> wordsWithPrimeLength = ImmutableSet.of("one", "two", "three", "six", "seven", "eight");
        Set<String> primes = ImmutableSet.of("two", "three", "five", "seven");

        ImmutableSet<String> intersection = Sets.intersection(primes, wordsWithPrimeLength).immutableCopy(); // contains "two", "three", "seven"
// I can use intersection as a Set directly, but copying it can be more efficient if I use it a lot.
        assertThat(3, equalTo(intersection.size()));
    }

    @Test
    public void testDifferenceBetweenSets() {
        Set<String> wordsWithPrimeLength = ImmutableSet.of("one", "two", "three", "six", "seven", "eight");
        Set<String> primes = ImmutableSet.of("two", "three", "five", "seven");

        ImmutableSet<String> difference = Sets.difference(primes, wordsWithPrimeLength).immutableCopy(); // contains "two", "three", "seven"

        //whats not in the second set
        assertThat(1, equalTo(difference.size()));
    }

    @Test
    public void testSymmetricDifferenceBetweenSets() {
        Set<String> wordsWithPrimeLength = ImmutableSet.of("one", "two", "three", "six", "seven", "eight");
        Set<String> primes = ImmutableSet.of("two", "three", "five", "seven");

        ImmutableSet<String> symmetricDifference = Sets.symmetricDifference(primes, wordsWithPrimeLength).immutableCopy(); // contains "two", "three", "seven"

        //whats not in the second set against first set + //whats not in the first set against second set
        assertThat(4, equalTo(symmetricDifference.size()));
    }


    @Test
    public void testMapDifferences() {

        Map<String, Integer> left = ImmutableMap.of("a", 1, "b", 2, "c", 3);
        Map<String, Integer> right = ImmutableMap.of("b", 2, "c", 4, "d", 5);
        MapDifference<String, Integer> diff = Maps.difference(left, right);

        assertThat(ImmutableMap.of("b", 2), equalTo(diff.entriesInCommon()));
        diff.entriesDiffering(); //{"c" => (3, 4)}
        assertThat(ImmutableMap.of("a", 1), equalTo(diff.entriesOnlyOnLeft())); //{"a" => 1}
        assertThat(ImmutableMap.of("d", 5), equalTo(diff.entriesOnlyOnRight())); //{"d" => 5}
    }

    @Test
    public void testMultisetContainsAndRemoves() {
        Multiset<String> multiset1 = HashMultiset.create();
        multiset1.add("a", 2);

        Multiset<String> multiset2 = HashMultiset.create();
        multiset2.add("a", 5);

        multiset1.containsAll(multiset2); // returns true: all unique elements are contained,
        // even though multiset1.count("a") == 2 < multiset2.count("a") == 5
        Multisets.containsOccurrences(multiset1, multiset2); // returns false

        multiset2.removeAll(multiset1); // multiset2 now contains 3 occurrences of "a"

        multiset2.removeAll(multiset1); // removes all occurrences of "a" from multiset2, even though multiset1.count("a") == 2
        assertTrue(multiset2.isEmpty()); // returns true
    }

}

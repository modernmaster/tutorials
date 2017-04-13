package uk.co.jamesmcguigan.collections;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import org.hamcrest.core.IsCollectionContaining;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ImmutableTest {
//    Collection	JDK	ImmutableCollection
//    List	JDK	ImmutableList
//    Set	JDK	ImmutableSet
//    SortedSet/NavigableSet	JDK	ImmutableSortedSet
//    Map	JDK	ImmutableMap
//    SortedMap	JDK	ImmutableSortedMap
//    Multiset	Guava	ImmutableMultiset
//    SortedMultiset	Guava	ImmutableSortedMultiset
//    Multimap	Guava	ImmutableMultimap
//    ListMultimap	Guava	ImmutableListMultimap
//    SetMultimap	Guava	ImmutableSetMultimap
//    BiMap	Guava	ImmutableBiMap
//    ClassToInstanceMap	Guava	ImmutableClassToInstanceMap
//    Table	Guava	ImmutableTable

    //https://github.com/google/guava/wiki/ImmutableCollectionsExplained

    @Test
    public void testImmutableCollection() {
        List<String> letterStrings = Arrays.asList("a","b","c");
        //Except for sorted collections, order is preserved from construction time. For example,
        ImmutableSet<String> moreLetterStrings = ImmutableSet.<String>builder()
                .addAll(letterStrings)
                .add("d")
                .build();
        assertThat(4, equalTo(moreLetterStrings.size()));
    }

    @Test
    @Ignore
    public void testSafeCopyOfForImmutableLists() {

        ImmutableSet<String> original = ImmutableSet.of("foo", "bar", "baz");
        ImmutableList<String> defensiveCopy = ImmutableList.copyOf(original);
        assertThat(defensiveCopy, IsCollectionContaining.hasItems("foo", "bar", "baz"));
    }

    @Test
    public void testImmutableListWithAsList() {
        ImmutableSet<String> original = ImmutableSet.of("foo", "bar", "baz");
        ImmutableList<String> asList = original.asList();
        assertThat("baz", equalTo(asList.get(2)));
    }
}

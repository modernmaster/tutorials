package uk.co.jamesmcguigan.collections;

import com.google.common.base.Optional;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class StringsTest {

//collections, caching, primitives support, concurrency libraries, common annotations,
//string processing, I/O, and so forth

    @Test
    public void order_list_of_strings_alphabetically_case_insensitive () {

        List<String> TOP_RATED_CENTERS = Lists.newArrayList(
                "Dawson", "Gatski", "Langer", "Hein",
                "Frankie Baggadonuts", "Turner", "Trafton",
                "Stephenson", "Ringo", "Otto", "Webster");


        String topNameAlphabetically = Ordering
                .from(String.CASE_INSENSITIVE_ORDER)
                .min(TOP_RATED_CENTERS);

        assertEquals("Dawson", topNameAlphabetically);
    }

    //Nulls
    //many of Guava's utilities are designed to fail fast in the presence of null rather
    //than allow nulls to be used, so long as there is a null-friendly workaround available
    //Additionally, Guava provides a number of facilities both to make using null easier,
    //when you must, and to help you avoid using null
    @Test
         public void testOptional() {

        Optional<Integer> possible = Optional.of(5);
        Set<Integer> set = possible.asSet();
        assertThat(possible.get(), equalTo(5));
    }

    @Test
    public void testIsPresent() {

        Optional<Integer> possible = Optional.of(5);
        assertTrue(possible.isPresent());
    }


    //Replacing null in list
    @Test
    public void testReplacementOfNulls() {
        assertTrue(Strings.isNullOrEmpty(null));
        assertThat(Strings.emptyToNull(null), equalTo(null));
        assertThat(Strings.nullToEmpty(null), equalTo(""));
    }

    @Test
    @Ignore
    public void testCommonPrefix() {
        final String s1 = "abcdefghijklm";
        final String s2 = "edcbaabcdefmlkjihgm";
        final String expectedCommonSuffix = "m";

        assertThat(Strings.commonPrefix(s1, s2), equalTo(expectedCommonSuffix));
    }

    @Test
    public void testRepeat() {
        final String expected = "jimjimjimjimjim";

        assertThat(Strings.repeat("jim",5), equalTo(expected));
    }

    //Collections.unmodifiableList(Lists.newArrayList())
    // instead of ImmutableList

}

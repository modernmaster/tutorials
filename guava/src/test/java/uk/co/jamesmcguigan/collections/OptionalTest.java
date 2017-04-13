package uk.co.jamesmcguigan.collections;

import com.google.common.base.Optional;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class OptionalTest {

//Besides the increase in readability that comes from giving null a name, the biggest advantage of Optional is its
// idiot-proof-ness. It forces you to actively think about the absent case if you want your program to compile at
// all, since you have to actively unwrap the Optional and address that case. Null makes it disturbingly easy to
// simply forget things, and though FindBugs helps, we don't think it addresses the issue nearly as well.

    @Test
    public void testOptionalIsPresent() {
        int value = 5;
        Optional<Integer> possible = Optional.of(value);
        assertTrue(possible.isPresent());
    }

    @Test
    public void testOptionalGetandOr() {
        int value = 5;
        Optional<Integer> possible = Optional.of(value);
        assertThat(possible.get(), equalTo(value));
        assertThat(possible.or(0), equalTo(value));
    }

    @Test
    public void testOptionalNullWithValue() {
        int value = 5;
        Optional<Integer> possible = Optional.of(value);
        assertThat(possible.get(), equalTo(value));
        assertThat(possible.orNull(), equalTo(value));
    }

    @Test
    public void testOptionalNullWithNoValue() {
        Optional<Integer> possible = Optional.absent();
        assertNull(possible.orNull());
    }
}

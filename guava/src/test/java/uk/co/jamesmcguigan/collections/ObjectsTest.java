package uk.co.jamesmcguigan.collections;

import com.google.common.base.Objects;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class ObjectsTest {
    @Test
    public void testEquals() {

        assertTrue(Objects.equal("a", "a"));
        assertFalse(Objects.equal(null, "a"));
        assertFalse(Objects.equal("a", null));
        assertTrue(Objects.equal(null, null));
    }


//    @Test
//    public void testCompareTo() {
//
//        Person p1 = new Person(lastName, firstName, zipCode);
//        Person p2 = new Person(lastName, firstName, zipCode);
//
//        //Look at guavas ordering class
//        assertThat(p1.compareTo(p2), equalTo(0));
//
//
//    }
}

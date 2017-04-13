package uk.co.jamesmcguigan.collections;

import com.google.common.base.Charsets;
import com.google.common.hash.Funnel;
import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import com.google.common.hash.PrimitiveSink;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class HashingTest {
//Hashes available
//////////////////////////////////////////
//adler32
//combineOrdered
//combineUnordered
//concatenating
//consistenthash
//crc32
//crc32c
//goodFastHash
//md5
//murmur3_128
//murmur3_32
//sha1
//sha256
//sha384
//sha512
//siphash24

    @Test
    public void test(){
        Funnel<Person> personFunnel = new Funnel<Person>() {
            @Override
            public void funnel(Person person, PrimitiveSink into) {
                into
                        .putString(person.getFirstName(), Charsets.UTF_8)
                        .putString(person.getLastName(), Charsets.UTF_8)
                        .putInt(person.getZipCode());
            }
        };

        Hashing.md5();
        HashFunction hf = Hashing.md5();
        HashCode hc = hf.newHasher()
                .putLong(12456789)
                .putString("test", Charsets.UTF_8)
                .putObject(new Person("me","test",1234), personFunnel)
                .hash();
        assertThat("ab24710200e751ae9f6afa5eae866bf4", equalTo(hc.toString()));
    }
}






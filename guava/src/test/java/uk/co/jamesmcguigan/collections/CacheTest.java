package uk.co.jamesmcguigan.collections;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.junit.Test;

import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class CacheTest {

    @Test
    public void testCacheBuilderAndReturnValue() throws ExecutionException {
        LoadingCache<Integer, String> graphs = CacheBuilder.newBuilder()
                .maximumSize(1000)
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .build(
                        new CacheLoader<Integer, String>() {
                            public String load(Integer key) throws Exception {
                                Random random = new Random();
                                return  String.valueOf(random.nextInt(26) + 'a');
                            }
                        });

        String valueInCache = graphs.get(1);
        assertThat(valueInCache, equalTo(graphs.get(1)));
        //return value from cache
        assertThat(valueInCache, equalTo(graphs.get(1)));
        assertThat(valueInCache, equalTo(graphs.get(1)));
        assertThat(valueInCache, equalTo(graphs.get(1)));
    }

}

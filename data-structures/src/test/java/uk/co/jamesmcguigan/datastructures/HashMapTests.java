package uk.co.jamesmcguigan.datastructures;

import org.junit.Test;

/**
 * Created by James McGuigan on 12/09/2015.
 */
public class HashMapTests {

    @Test
    public void ShouldSetupHashMap() {
        HashMap tmhm = new HashMap();

        tmhm.put("Niranjan", "SMTS");
        tmhm.put("Ananth", "SSE");
        tmhm.put("Niranjan", "SMTS1");
        tmhm.put("Chandu", "SSE");

        String value = tmhm.get("Ananth");
        System.out.println(value);
    }
}

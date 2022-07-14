package isb.crypto.hmac;

import isb.crypto.configuration.HMACServiceConfig;
import isb.crypto.hmac.HMACService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes= HMACService.class)
@EnableConfigurationProperties(value = HMACServiceConfig.class)
public class HMACServiceTests {

    @Autowired
    private HMACService hmacService;

    @Test
    public void testGenerateHash() throws NoSuchAlgorithmException, InvalidKeyException {
        String key = "key";
        String data = "data";
        String result = hmacService.generate(data);
        assertNotNull(result);
        assertEquals("6dd229ea35133017bca9e4ef52f34b611e3440bba6ebb800aefac1738b39a6c7", result);
    }

}

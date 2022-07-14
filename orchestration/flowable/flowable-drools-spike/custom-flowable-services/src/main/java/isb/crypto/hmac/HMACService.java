package isb.crypto.hmac;

import isb.crypto.configuration.HMACServiceConfig;
import org.apache.commons.codec.binary.Hex;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Service
public class HMACService implements JavaDelegate {

    private static final String HMAC_SHA_256 = "HmacSHA256";
    private static final String DATA = "data";
    private static final String HASH = "hash";
    @Autowired
    private HMACServiceConfig hmacServiceConfig;

    @Override
    public void execute(DelegateExecution delegateExecution) {
        String data = (String) delegateExecution.getVariable(DATA);
        try {
            String hash = generate(data);
            delegateExecution.setVariable(HASH, hash);
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();
        }
    }

    String generate(String data) throws NoSuchAlgorithmException, InvalidKeyException {
        Mac sha256_HMAC = Mac.getInstance(HMAC_SHA_256);
        String secret = hmacServiceConfig.getSecret();
        SecretKeySpec secret_key = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), HMAC_SHA_256);
        sha256_HMAC.init(secret_key);
        return Hex.encodeHexString(sha256_HMAC.doFinal(data.getBytes(StandardCharsets.UTF_8)));
    }

}

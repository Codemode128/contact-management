package com.shivam.cms.utils;

import jakarta.persistence.AttributeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;

@Component
public class EncryptionUtil implements AttributeConverter<String,String> {
    private String ALGORITHM="AES";

    @Value("${algorithm}")
    private String property;
    private String SECRET="thisissecretkey1";

    private final Key key;
    private final Cipher cipher;

    public EncryptionUtil() throws Exception {
        System.out.println("Property = "+property);
        key = new SecretKeySpec(SECRET.getBytes(), ALGORITHM);
        cipher = Cipher.getInstance(ALGORITHM);
    }
    @Override
    public String convertToDatabaseColumn(String s) {
        try {
            cipher.init(Cipher.ENCRYPT_MODE, key);
            String res =  Base64.getEncoder().encodeToString(cipher.doFinal(s.getBytes()));
            return res;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String convertToEntityAttribute(String s) {
       try {
            cipher.init(Cipher.DECRYPT_MODE, key);
            String res = new String(cipher.doFinal(Base64.getDecoder().decode(s)));
            return res;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    }

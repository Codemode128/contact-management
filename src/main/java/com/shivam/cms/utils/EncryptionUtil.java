package com.shivam.cms.utils;

import jakarta.persistence.AttributeConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;

@Component
public class EncryptionUtil implements AttributeConverter<String,String> {

    @Value("${algorithm}")
    private String ALGORITHM;
    @Value("${key}")
    private String SECRET;

    private  Key key;
    private  Cipher cipher;

    @Override
    public String convertToDatabaseColumn(String s) {
        try {
            key = new SecretKeySpec(SECRET.getBytes(), ALGORITHM);
            cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            return Base64.getEncoder().encodeToString(cipher.doFinal(s.getBytes()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String convertToEntityAttribute(String s) {
       try {
           key = new SecretKeySpec(SECRET.getBytes(), ALGORITHM);
           cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, key);
            return new String(cipher.doFinal(Base64.getDecoder().decode(s)));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    }

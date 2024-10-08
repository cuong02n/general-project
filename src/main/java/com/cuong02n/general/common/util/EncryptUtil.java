package com.cuong02n.general.common.util;

import org.springframework.util.DigestUtils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class EncryptUtil {
    public static String encryptByDES(String message, String key) throws Exception {

        byte[] keyBytes = key.getBytes(StandardCharsets.UTF_8);
        SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, "DES");

        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        System.out.println("key: "+key);
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);

        byte[] encryptedBytes = cipher.doFinal(message.getBytes(StandardCharsets.UTF_8));

        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public static String md5(String plainText) {
        return DigestUtils.md5DigestAsHex(plainText.getBytes());
    }

    public static String getPass(String username, String password) throws Exception {
        String key = md5(username + "." + password);
        return encryptByDES(password, key);
    }

    public static void main(String[] args) throws Exception {
        System.out.println(getPass("20204524", "001202014420"));
    }
}

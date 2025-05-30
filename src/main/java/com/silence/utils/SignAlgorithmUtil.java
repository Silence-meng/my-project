package com.silence.utils;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;

/**
 * @author silence
 * @since 2025/5/30 16:42
 **/
@Slf4j
@UtilityClass
public class SignAlgorithmUtil {

    public String getSign(String appKey, String appSecret, String rand, String tid) {
        byte[] randBytes = rand.getBytes(StandardCharsets.UTF_8);
        if (randBytes.length < 16) {
            String newRands = "0000000000000000" + rand;
            randBytes = newRands.getBytes(StandardCharsets.UTF_8);
        }

        byte[] iv = Arrays.copyOfRange(randBytes, randBytes.length - 16, randBytes.length);
        String msg = ("x-yun-app-key=" + appKey + '&' + "x-yun-rand" + '=' + rand + '&' + "x-yun-tid" + '=' + tid).toUpperCase();
        SecretKeySpec secretKeySpec = new SecretKeySpec(appSecret.getBytes(StandardCharsets.UTF_8), "AES");

        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec ivSpec = new IvParameterSpec(iv);
            cipher.init(1, secretKeySpec, ivSpec);
            byte[] cipherText = cipher.doFinal(msg.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(cipherText);
        } catch (Exception e) {
            log.warn("encrypt ase failed", e);
            return null;
        }
    }
}

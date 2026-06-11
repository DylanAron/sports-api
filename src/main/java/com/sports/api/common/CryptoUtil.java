package com.sports.api.common;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * AES-256-CBC 加密/解密工具
 * 密钥为 32 字节 Base64 编码字符串，前 16 字节用作 IV
 */
public class CryptoUtil {

    private static final String ALGORITHM = "AES/CBC/PKCS5Padding";

    /**
     * AES-256-CBC 加密
     * @param plainText 明文
     * @param base64Key 32字节密钥的Base64编码
     * @return Base64编码的密文
     */
    public static String encrypt(String plainText, String base64Key) {
        if (plainText == null || base64Key == null || base64Key.isBlank()) {
            return plainText;
        }
        try {
            byte[] keyBytes = Base64.getDecoder().decode(base64Key);
            SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
            // 取密钥前16字节作为IV
            byte[] ivBytes = new byte[16];
            System.arraycopy(keyBytes, 0, ivBytes, 0, 16);
            IvParameterSpec ivSpec = new IvParameterSpec(ivBytes);

            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);

            byte[] encrypted = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception e) {
            throw new RuntimeException("AES encrypt failed", e);
        }
    }

    /**
     * AES-256-CBC 解密
     * @param cipherText Base64编码的密文
     * @param base64Key 32字节密钥的Base64编码
     * @return 明文
     */
    public static String decrypt(String cipherText, String base64Key) {
        if (cipherText == null || base64Key == null || base64Key.isBlank()) {
            return cipherText;
        }
        try {
            byte[] keyBytes = Base64.getDecoder().decode(base64Key);
            SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
            // 取密钥前16字节作为IV
            byte[] ivBytes = new byte[16];
            System.arraycopy(keyBytes, 0, ivBytes, 0, 16);
            IvParameterSpec ivSpec = new IvParameterSpec(ivBytes);

            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);

            byte[] decrypted = cipher.doFinal(Base64.getDecoder().decode(cipherText));
            return new String(decrypted, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException("AES decrypt failed", e);
        }
    }
}

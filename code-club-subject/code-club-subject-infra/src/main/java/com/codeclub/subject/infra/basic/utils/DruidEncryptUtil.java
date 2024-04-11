package com.codeclub.subject.infra.basic.utils;

import com.alibaba.druid.filter.config.ConfigTools;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

/**
 * 数据库加密util
 */
public class DruidEncryptUtil {

    private static String publicKey;
    private static String privateKey;

    static {
        String[] keyPair = new String[0];
        try {
            keyPair = ConfigTools.genKeyPair(512);
            privateKey = keyPair[0];
            System.out.println("privateKey: " + privateKey);
            publicKey = keyPair[1];
            System.out.println("publicKey:" + publicKey);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (NoSuchProviderException e) {
            throw new RuntimeException(e);
        }
    }

    public static String encrypt(String plainText) throws Exception {
        String encrypt = ConfigTools.encrypt(privateKey, plainText);
        System.out.println("encrypt: " + encrypt);
        return encrypt;
    }

    public static String decrypt(String encryptText) throws Exception {
        String decrypt = ConfigTools.decrypt(publicKey, encryptText);
        System.out.println("decrypt: " + decrypt);
        return decrypt;
    }

    public static void main(String[] args) throws Exception {
        String encrypt = encrypt("555888ljq");
        System.out.println("encrypt: " + encrypt);
    }
}

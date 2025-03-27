package com.silence;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.junit.Test;

/**
 * @author silence
 * @since 2025/3/27 10:03
 **/
public class JasyptTest {

    @Test
    public void test(){
        //--jasypt.encryptor.password=${JASYPT_PASSWORD}
        //--jasypt.encryptor.algorithm=PBEWithMD5AndDES
        //--jasypt.encryptor.iv-generator-classname=org.jasypt.iv.NoIvGenerator
        //运行参数--jasypt.encryptor.password=${JASYPT_PASSWORD}

        StandardPBEStringEncryptor standardPBEStringEncryptor = new StandardPBEStringEncryptor();
        //盐值
        standardPBEStringEncryptor.setPassword("Vip2024_zwDev");
        //加密明文
        String code = standardPBEStringEncryptor.encrypt("cn4c@2022#dev");
        System.out.println("code=" + code);

        //第二次test,解密第一次test的密文
//        System.out.println("原文:"+standardPBEStringEncryptor.decrypt("zNdGCP+uHI7mX7yqi2Kj23R3JPJbJoQq"));
        System.out.println("原文:"+standardPBEStringEncryptor.decrypt("+bcGPpRfr4YXi3mBSQONyAyPuPmfTPiq"));

    }
}

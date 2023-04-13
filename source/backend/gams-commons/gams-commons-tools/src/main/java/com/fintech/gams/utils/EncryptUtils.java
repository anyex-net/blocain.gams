/*
 * @(#)EncryptUtils.java 2015-4-16 下午2:27:42
 * Copyright 2015 Playguy, Inc. All rights reserved. com.fintech
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.utils;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.exception.ExceptionUtils;

import com.fintech.gams.bean.Digests;
import com.fintech.gams.bean.Encodes;
import com.fintech.gams.consts.CharsetConst;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>File：EncryptUtils.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2015 2015-4-16 下午2:27:42</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Slf4j
public class EncryptUtils
{
    public static final int     HASH_INTERATIONS = 1024;
    
    public static final int     SALT_SIZE        = 8;
    
    private static final String PARSE_KEY        = "gams";
    
    private static final String ENCRYPT_NAME     = "DES";
    
    /**
     * 生成安全的密码，生成随机的16位salt并经过1024次 sha-1 hash
     */
    public static String entryptPassword(String plainPassword)
    {
        String plain = Encodes.unescapeHtml(plainPassword);
        byte[] salt = Digests.generateSalt(SALT_SIZE);
        byte[] hashPassword = Digests.sha1(plain.getBytes(), salt, HASH_INTERATIONS);
        return Encodes.encodeHex(salt) + Encodes.encodeHex(hashPassword);
    }
    
    /**
     * 验证密码
     * @param plainPassword 明文密码
     * @param password 密文密码
     * @return 验证成功返回true
     */
    public static boolean validatePassword(String plainPassword, String password)
    {
        String plain = Encodes.unescapeHtml(plainPassword);
        byte[] salt = Encodes.decodeHex(password.substring(0, 16));
        byte[] hashPassword = Digests.sha1(plain.getBytes(), salt, HASH_INTERATIONS);
        return password.equals(Encodes.encodeHex(salt) + Encodes.encodeHex(hashPassword));
    }
    
    /**
     * 返回经过加密的字符串
     * @param password 要加密码的明文字符串
     * @param algorithm 加密运算法则(可以是MD5、MD2、SHA-256、SHA-1等等)
     * @return String 加密后的字符串
     */
    public static String encrypt(String password, String algorithm)
    {
        String result = null;
        byte[] unencodedPassword = password.getBytes(Charset.forName(CharsetConst.CHARSET_UT));
        MessageDigest md = null;
        try
        {
            md = MessageDigest.getInstance(algorithm);
        }
        catch (NoSuchAlgorithmException e)
        {
            log.error(ExceptionUtils.getStackTrace(e));
        }
        if (null != md)
        {
            md.update(unencodedPassword);
            byte[] encodedPassword = md.digest();
            StringBuffer buf = new StringBuffer();
            int iLen = encodedPassword.length;
            for (int i = 0; i < iLen; i++)
            {
                if ((encodedPassword[i] & 0xff) < 0x10)
                {
                    buf.append("0");
                }
                buf.append(Long.toString(encodedPassword[i] & 0xff, 16));
            }
            result = buf.toString();
        }
        return result;
    }
    
    /**
     * 验证密码
     * @param password 明文密码
     * @param ciphertext 加密密码
     * @param algorithm 加密方式
     * @return
     */
    public static Boolean unEncrypt(String password, String ciphertext, String algorithm)
    {
        return StringUtils.equals(ciphertext, encrypt(password, algorithm));
    }
    
    /**
     * DES加密
     * @param strMing
     * @return
     */
    public static String desEncrypt(String strMing)
    {
        String strMi = "";
        try
        {
            byte[] byteMing = strMing.getBytes(CharsetConst.CHARSET_UT);
            byte[] byteMi = encryptByte(byteMing);
            strMi = Base64.encodeBase64String(byteMi);
        }
        catch (Exception e)
        {
            throw new RuntimeException("Error initializing SqlMap class. Cause: " + e);
        }
        return strMi;
    }
    
    /**
     * DES解密
     * @param strMi
     * @return
     */
    public static String desDecrypt(String strMi)
    {
        String strMing = "";
        try
        {
            byte[] byteMi = Base64.decodeBase64(strMi);
            byte[] byteMing = decryptByte(byteMi);
            strMing = new String(byteMing, CharsetConst.CHARSET_UT);
        }
        catch (UnsupportedEncodingException e)
        {
            log.error(ExceptionUtils.getStackTrace(e));
        }
        return strMing;
    }
    
    static byte[] encryptByte(byte[] byteS)
    {
        byte[] byteFina = null;
        try
        {
            Cipher cipher = Cipher.getInstance(ENCRYPT_NAME);
            cipher.init(Cipher.ENCRYPT_MODE, generatorKey(PARSE_KEY));
            byteFina = cipher.doFinal(byteS);
        }
        catch (Exception e)
        {
            throw new RuntimeException("Error initializing SqlMap class. Cause: " + e);
        }
        return byteFina;
    }
    
    static byte[] decryptByte(byte[] byteD)
    {
        byte[] byteFina = null;
        try
        {
            Cipher cipher = Cipher.getInstance(ENCRYPT_NAME);
            cipher.init(Cipher.DECRYPT_MODE, generatorKey(PARSE_KEY));
            byteFina = cipher.doFinal(byteD);
        }
        catch (Exception e)
        {
            throw new RuntimeException("Error initializing SqlMap class. Cause: " + e);
        }
        return byteFina;
    }
    
    static Key generatorKey(String parseKey)
    {
        Key key = null;
        KeyGenerator generator = null;
        try
        {
            generator = KeyGenerator.getInstance(ENCRYPT_NAME);
        }
        catch (NoSuchAlgorithmException e)
        {
            log.error(ExceptionUtils.getStackTrace(e));
        }
        if (null != generator)
        {
            // generator.init(new SecureRandom(parseKey.getBytes()));//Linux及Solaris下异常
            SecureRandom secureRandom = null;
            try
            {
                secureRandom = SecureRandom.getInstance("SHA1PRNG");
            }
            catch (NoSuchAlgorithmException e)
            {
                log.error(ExceptionUtils.getStackTrace(e));
            }
            secureRandom.setSeed(parseKey.getBytes(Charset.forName(CharsetConst.CHARSET_UT)));
            generator.init(secureRandom);
            key = generator.generateKey();
        }
        return key;
    }
    
    public static void main(String[] args)
    {
        System.out.println(EncryptUtils.desEncrypt("gamsDev"));
        System.out.println(EncryptUtils.desEncrypt("gamsGWTest"));

        System.out.println(EncryptUtils.desDecrypt("dILFQhYd1rueYtOWbEIP7DCWr/SzxotcOl+rn3CA2BI="));
    }
}

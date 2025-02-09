package com.hjj.util;

import org.apache.commons.codec.digest.DigestUtils;

public class MD5 {
    /**
     * @param text 明文
     * @return 密文
     * @throws Exception
     */
    public static String md5(String text) throws Exception {
        return DigestUtils.md5Hex(text);
    }

    /**
     * @param text 明文
     * @param key  盐
     * @return 密文
     * @throws Exception
     */
    public static String md5(String text, String key) throws Exception {
        return DigestUtils.md5Hex(text + key);
    }

    /**
     * @param text 明文
     * @param key  盐(密钥)
     * @param md5  密文
     * @return true/false
     * @throws Exception
     */

    public static boolean verify(String text, String key, String md5) throws Exception {
        String md5Text = md5(text, key);
        return md5Text.equalsIgnoreCase(md5);
    }

    public static void main(String[] args) throws Exception {
        System.out.printf(md5("123456"));
    }
}

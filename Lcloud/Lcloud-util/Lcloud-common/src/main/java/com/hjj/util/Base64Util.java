package com.hjj.util;

import java.util.Base64;

public class Base64Util {
    // 加密
    public static String encode(byte[] data) {
        final Base64.Encoder encoder = Base64.getEncoder();
        return encoder.encodeToString(data);
    }

    // 加密带有URL
    public static String encodeURL(byte[] data) {
        final Base64.Encoder encoder = Base64.getUrlEncoder();
        return encoder.encodeToString(data);
    }

    // 解密
    public static byte[] decode(String decodeText) {
        final Base64.Decoder decoder = Base64.getDecoder();
        return decoder.decode(decodeText);
    }

    // 解密带有URL
    public static byte[] decodeURL(String encodeText) {
        final Base64.Decoder decoder = Base64.getUrlDecoder();
        return decoder.decode(encodeText);
    }
}

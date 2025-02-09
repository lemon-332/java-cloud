package com.hjj.util;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Security;
import java.util.Arrays;

public class AESUtil {
    /**
     * @param buffer    密文/明文
     * @param appSecret 密钥
     * @param mode      加害还是解密
     * @return
     */
    public static byte[] encryptAndDecrypt(byte[] buffer, String appSecret, Integer mode) throws Exception {
        // 1. 加载加密解密的算法处理对象（包含算法，密钥管理）
        Security.addProvider(new BouncyCastleProvider());
        // 2. 根据不同算法创建密钥
        SecretKeySpec secretKeySpec = new SecretKeySpec(appSecret.getBytes("UTF-8"), "AES");
        // 3. 设置加密模式（无论是加密还是解析，模式一直）
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS7Padding", "BC");
        // 4. 初始化加密配置
        cipher.init(mode, secretKeySpec);
        // 5. 执行加密/解密
        return cipher.doFinal(buffer);
    }

    public static void main(String[] args) {
        try {
            byte[] bytes = encryptAndDecrypt("123456".getBytes(), "1234567890123456", Cipher.ENCRYPT_MODE);
            String encode = Base64Util.encode(bytes);
            System.out.println(encode);

            byte[] bytes1 = encryptAndDecrypt(Base64Util.decode(encode), "1234567890123456", Cipher.DECRYPT_MODE);
            System.out.println(new String(bytes1));
        } catch (Exception e) {

        }
    }
}

package com.hjj.util;

import com.alibaba.fastjson.JSON;

import java.util.Map;
import java.util.TreeMap;

public class Signature {

    private String skey;
    private String salt;

    public Signature(String skey, String salt) {
        this.skey = skey;
        this.salt = salt;
    }

    // 加密 验签
    public String security(Map<String, String> dataMap) throws Exception {
        // 1. 将dataMap转化TreeMap 排序
        dataMap = JSON.parseObject(JSON.toJSONString(dataMap), TreeMap.class);
        // 2. 将TreeMap转成json
        String json = JSON.toJSONString(dataMap);
        // 3. 执行MD5摘要加密
        String signature = MD5.md5(json, salt);
        // 4. 摘要内容加到dataMape
        dataMap.put("signature", signature);
        // 5. AES加密dataMap
        return Base64Util.encodeURL(
                AESUtil.encryptAndDecrypt(JSON.toJSONString(dataMap).getBytes("utf-8"), skey, 1)
        );
    }

    // 解密 并对map验签
    public Map<String, String> security(String cipertext) throws Exception {
        // 1. 解密
        String decrypt = new String(AESUtil.encryptAndDecrypt(Base64Util.decodeURL(cipertext), skey, 2), "utf-8");
        // 2. 明文转map 并根据key降序
        Map<String, String> decryptTreeMap = JSON.parseObject(decrypt, TreeMap.class);
        // 3. 验签
        String signature = decryptTreeMap.remove("signature");
        String localSignature = MD5.md5(JSON.toJSONString(decryptTreeMap), salt);

        return signature.equals(localSignature) ? decryptTreeMap : null;
    }

}

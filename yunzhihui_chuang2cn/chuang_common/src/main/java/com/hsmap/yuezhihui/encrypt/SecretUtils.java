package com.hsmap.yuezhihui.encrypt;

import com.hsmap.yuezhihui.common.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

public class SecretUtils {

    public static final Logger LOGGER = LoggerFactory.getLogger(SecretUtils.class);
    public static final String DES = "DES";
    public static final String AES = "AES";

    private static Map<String, Integer> keySize = new HashMap<>();

    static {
        keySize.put(DES, 56);
        keySize.put(AES, 128);
    }

    /**
     * 加密
     * @param data
     * @param key
     * @param algorithm
     * @return
     */
    public static String encrypt(String data, String key, String algorithm) {
        try {
            if (CommonUtil.isEmpty(data) || CommonUtil.isEmpty(key)) {
                LOGGER.info("加密过程中的的data和key为空");
                return null;
            }
            byte[] content = data.getBytes("UTF-8");
            KeyGenerator kgen = KeyGenerator.getInstance(algorithm);
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(key.getBytes());
            kgen.init(keySize.get(algorithm), secureRandom);
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec keySpec = new SecretKeySpec(enCodeFormat, algorithm);
            Cipher cipher = Cipher.getInstance(algorithm);// 创建密码器
            cipher.init(Cipher.ENCRYPT_MODE, keySpec);// 初始化
            byte[] result = cipher.doFinal(content);
            return parseByte2HexStr(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /***
     * 解密
     * @param data
     * @param key
     * @param algorithm
     * @return
     */
    public static String decrypt(String data, String key, String algorithm) {
        try {
            if (CommonUtil.isEmpty(data) || CommonUtil.isEmpty(key)) {
                return null;
            }
            byte[] content = parseHexStr2Byte(data);
            KeyGenerator kgen = KeyGenerator.getInstance(algorithm);
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(key.getBytes());
            kgen.init(keySize.get(algorithm), secureRandom);
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec keySpec = new SecretKeySpec(enCodeFormat, algorithm);
            Cipher cipher = Cipher.getInstance(algorithm);// 创建密码器
            cipher.init(Cipher.DECRYPT_MODE, keySpec);// 初始化
            byte[] result = cipher.doFinal(content);
            return new String(result, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将二进制转换成16进制
     *
     * @param buf
     * @return
     */
    private static String parseByte2HexStr(byte buf[]) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    private static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1) {
            return null;
        }
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }

    public static void main(String[] args) {
        String data = "我是中国人";
        String _key="tang";
        String encrypt = SecretUtils.encrypt(data,_key,SecretUtils.DES);
        String decrypt = SecretUtils.decrypt(encrypt,_key,SecretUtils.DES);
        LOGGER.info("encrypt:{}",encrypt);
        LOGGER.info("decrypt:{}",decrypt);

    }
}

package com.hsmap.yuezhihui.encrypt;

import com.hsmap.yuezhihui.common.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.DigestUtils;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

/***
 * 加密工具类
 */
public class EncryptUtils {
    public static final Logger LOGGER = LoggerFactory.getLogger(RandomCharUtils.class);

    public static String[] chars = new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};


    /**
     * 获取随机字符串
     * @param length
     * @return
     */
    public static String getRandomChar(int length) {
        return RandomCharUtils.randomPassword(length);
    }

    /***
     * 获取随机数字
     * @param length
     * @return
     */
    public static String getRandomNumber(int length) {
        return CommonUtil.getRandomByCount(length);
    }


    /**
     * md5加密
     * @param str
     * @return
     */
    public static String getMd5(String str) {
        String md5 = null;
        try {
            md5 = DigestUtils.md5DigestAsHex(str.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return md5;
    }

    /**
     * 加盐md5
     * @param data
     * @param slat
     * @return
     */
    public static String getMd5(String data,String slat) {
        String base = data +slat;
        String md5 = null;
        try {
            md5 = DigestUtils.md5DigestAsHex(base.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return md5;
    }

    /**
     * DES加密
     * @return
     */
    public static String getDESEncrypt(String data,String key){
        String encrypt = SecretUtils.encrypt(data,key,SecretUtils.DES);
        return encrypt;
    }

    /**
     * DES解密
     * @return
     */
    public static String getDESDecrypt(String data,String key){
        String encrypt = SecretUtils.decrypt(data,key,SecretUtils.DES);
        return encrypt;
    }

    /**
     * AES加密
     * @param data
     * @param key
     * @return
     */
    public static String getAESEncrypt(String data,String key) {
        String encrypt = SecretUtils.encrypt(data,key,SecretUtils.AES);
        return encrypt;
    }

    /**
     * AES解密
     * @param data
     * @param key
     * @return
     */
    public static String getAESDecrypt(String data,String key) {
        String encrypt = SecretUtils.decrypt(data,key,SecretUtils.AES);
        return encrypt;
    }

    /***
     * 生成8位uuid
     * @return
     */
    public static String generateShortUuid() {
        StringBuffer shortBuffer = new StringBuffer();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        for (int i = 0; i < 8; i++) {
            String str = uuid.substring(i * 4, i * 4 + 4);
            int x = Integer.parseInt(str, 16);
            shortBuffer.append(chars[x % 0x3E]);
        }
        return shortBuffer.toString();
    }

    public static void main(String[] args) {
//        String rc =  getRandomChar(32);
//        String rn = getRandomNumber(8);
//        String md5 = getMd5(rc);
//        String s = getMd5(rc,rn);
//        LOGGER.info("rc:{},rn:{},md5:{},s:{}",rc,rn,md5,s);

//        String data = "abcd1234";
//        String _key = "M5P4V4_70796";
//        String desEncrypt = getDESEncrypt(data,_key);
//        String desDecrypt =getDESDecrypt(desEncrypt,_key);
//
//        String aesEncrypt =getAESEncrypt(data,_key);
//        String aesDecrypt =getAESDecrypt(aesEncrypt,_key);
//
//        LOGGER.info("des,原文:{},加密:{},解密:{}",data,desEncrypt,desDecrypt);
//        LOGGER.info("aes,原文:{},加密:{},解密:{}",data,aesEncrypt,aesDecrypt);
//        String pwd = EncryptUtils.getMd5(EncryptUtils.getMd5("{\"mobile\":\"18301634258\",\"type\":0}"),"RPOBACSACYAAIMSROIE9HY4WSPGDMYGH");
//        LOGGER.info("pwd:{}",pwd);
        LOGGER.info("pwd:{}",getMd5("123456","c5J6N7_40910"));

    }

}

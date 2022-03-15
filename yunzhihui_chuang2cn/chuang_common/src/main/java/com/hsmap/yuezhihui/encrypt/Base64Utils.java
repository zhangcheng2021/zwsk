package com.hsmap.yuezhihui.encrypt;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/***
 * Base64加密解密
 */
public class Base64Utils {

    /**
     * 使用基本编码
     *
     * @param str
     * @return
     */
    public static String encoder(String str) {
        return Base64.getEncoder().encodeToString(str.getBytes(StandardCharsets.UTF_8));
    }

    /***
     * 解码
     * @param str
     * @return
     */
    public static String decoder(String str) {
        try {
            String decoderStr = new String(Base64.getDecoder().decode(str), "utf-8");
            return decoderStr;
        } catch (Exception ex) {
            ex.printStackTrace();
            return "";
        }
    }

    public static void main(String[] args) {
        String s = Base64Utils.encoder("1123344");
        System.out.println(s);
    }
}

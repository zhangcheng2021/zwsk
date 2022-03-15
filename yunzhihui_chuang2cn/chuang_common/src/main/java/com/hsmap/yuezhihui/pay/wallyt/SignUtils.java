package com.hsmap.yuezhihui.pay.wallyt;

import io.netty.util.CharsetUtil;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/***
 * 用于对发送的请求报文进行签名
 */
public class SignUtils {
    public final static Logger LOGGER = LoggerFactory.getLogger(SignUtils.class);
    /**
     * 用于对发送的请求报文进行签名
     *
     * @param data
     * @param priKey 创建商户上传的签名公钥, 对应私钥
     * @return 返回该请求体的签名, 需要将其设置在向我们发送的报文的 signature 这个属性中, 可以参照文档给出的报文例子
     * @throws Exception
     */
    public static String signBySHA256WithRSA(String data, String priKey) {
        try {
            Signature signature = Signature.getInstance("SHA256WithRSA");
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.decodeBase64(priKey));
            PrivateKey privateKey = KeyFactory.getInstance("RSA").generatePrivate(keySpec);
            signature.initSign(privateKey);
            signature.update(data.getBytes("UTF-8"));
            return Base64.encodeBase64String(signature.sign());
        } catch (Exception e) {
            LOGGER.error("RSA signature creating error");
            return "";
        }
    }

    /**
     * 用于对响应报文, 或通知报文进行验签
     *
     * @param data 我方向您发送的报文中的"response"或"request"属性下的整个内容,
     * @param sign 我方向您发送的报文中的"signature"字段
     * @param pubKey  新建商户时向您提供的我方签名公钥
     * @return
     * @throws Exception
     */
    public static boolean verifyBySHA256WithRSA(String data, String sign, String pubKey) throws Exception {
        try {
            Signature signature = Signature.getInstance("SHA256WithRSA");
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.decodeBase64(pubKey));
            PublicKey publicKey = KeyFactory.getInstance("RSA").generatePublic(keySpec);
            signature.initVerify(publicKey);
            signature.update(data.getBytes(StandardCharsets.UTF_8));
            return signature.verify(Base64.decodeBase64(sign.getBytes(StandardCharsets.UTF_8)));
        } catch (Exception e) {
            throw new Exception("RSA signature verifying error");
        }
    }

    /**
     * 用于对某个字段进行加密, 例如"authorizationCode"这个字段, 需要先对其进行加密获得密文, 然后带上密文去进行签名
     * @param data "authorizationCode"的明文, e.g. AUTH_xxxxxxxx
     * @param pubKey  新建商户时向您提供的我方加密公钥
     * @return
     * @throws Exception
     */
    public static String encryptByRSA(String data, String pubKey) throws Exception {
        try {
            X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(Base64.decodeBase64(pubKey));
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            Key publicKey = keyFactory.generatePublic(x509KeySpec);
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            return Base64.encodeBase64String(cipher.doFinal(data.getBytes(CharsetUtil.UTF_8)));
        } catch (Exception e) {
            throw new Exception("RSA encryption error");
        }
    }

    /**
     * 用于对字段进行解密, 例如"authorizationCode"这个字段
     * @param cipherText 某个字段的密文
     * @param priKey 创建商户时上传的加密公钥的, 对应私钥
     * @return 入参"cipherText"的明文
     * @throws Exception
     */
    public static String decryptByRSA(String cipherText, String priKey) throws Exception {
        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PrivateKey privateKey = keyFactory.generatePrivate(new PKCS8EncodedKeySpec(Base64.decodeBase64(priKey)));
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            return new String(cipher.doFinal(Base64.decodeBase64(cipherText)));
        } catch (Exception e) {
            throw new Exception("RSA decryption error");
        }
    }
}

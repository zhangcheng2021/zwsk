package com.hsmap.yuezhihui.encrypt;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.util.Base64;

/***
 * des 加密类
 */
public class EncrypDES {

    public static final Logger LOGGER = LoggerFactory.getLogger(EncrypDES.class);

    //KeyGenerator 提供对称密钥生成器的功能，支持各种算法
    private KeyGenerator keygen;
    //SecretKey 负责保存对称密钥
    private SecretKey deskey;
    //Cipher负责完成加密或解密工作
    private Cipher c;
    //该字节数组负责保存加密的结果
    private byte[] cipherByte;

    public EncrypDES() throws NoSuchAlgorithmException, NoSuchPaddingException{
        Security.addProvider(new com.sun.crypto.provider.SunJCE());
        //实例化支持DES算法的密钥生成器(算法名称命名需按规定，否则抛出异常)
        keygen = KeyGenerator.getInstance("DES");
        //生成密钥
        deskey = keygen.generateKey();

        //生成Cipher对象,指定其支持的DES算法
        c = Cipher.getInstance("DES");
    }

    /**
     * 对字符串加密
     *
     * @param str
     * @return
     * @throws InvalidKeyException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     */
    private byte[] Encrytor(String str) throws InvalidKeyException,
            IllegalBlockSizeException, BadPaddingException {
        // 根据密钥，对Cipher对象进行初始化，ENCRYPT_MODE表示加密模式
        c.init(Cipher.ENCRYPT_MODE, deskey);
        byte[] src = str.getBytes();
        // 加密，结果保存进cipherByte
        cipherByte = c.doFinal(src);
        return cipherByte;
    }

    /**
     * 对字符串解密
     *
     * @param buff
     * @return
     * @throws InvalidKeyException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     */
    private byte[] Decryptor(byte[] buff) throws InvalidKeyException,
            IllegalBlockSizeException, BadPaddingException {
        // 根据密钥，对Cipher对象进行初始化，DECRYPT_MODE表示加密模式
        c.init(Cipher.DECRYPT_MODE, deskey);
        cipherByte = c.doFinal(buff);
        return cipherByte;
    }

    /***
     * 加密
     * @return
     */
    public static String encrytor(String msg){
        EncrypDES de1;
        try {
            de1 = new EncrypDES();
            return Base64.getEncoder().encodeToString(de1.Encrytor(msg));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
            return "";
        } catch (BadPaddingException e) {
            e.printStackTrace();
            return "";
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
            return "";
        } catch (InvalidKeyException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 解密
     * @param msg
     * @return
     */
    public static String decryptor(String msg) {
        try {
            EncrypDES de1 = new EncrypDES();
            return new  String(de1.Decryptor(Base64.getDecoder().decode(msg)));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
            return "";
        } catch (BadPaddingException e) {
            e.printStackTrace();
            return "";
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
            return "";
        } catch (InvalidKeyException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * @param args
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     * @throws InvalidKeyException
     */
    public static void main(String[] args) throws Exception {
//        String msg ="郭XX-搞笑相声全集";
//        String encontent =EncrypDES.encrytor(msg);
//        String decryptor = EncrypDES.decryptor(encontent);
//
//        LOGGER.info("明文是:{}",msg);
//        LOGGER.info("加密后:{}",encontent);
//        LOGGER.info("解密后:{}",decryptor);

        EncrypDES de1 = new EncrypDES();
        String msg ="郭XX-搞笑相声全集";
        byte[] encontent = de1.Encrytor(msg);
        byte[] decontent = de1.Decryptor(encontent);
        System.out.println("明文是:" + msg);
        System.out.println("加密后:" + (Base64.getEncoder().encodeToString(encontent)));
        System.out.println("解密后:" + new String(decontent));
    }
}

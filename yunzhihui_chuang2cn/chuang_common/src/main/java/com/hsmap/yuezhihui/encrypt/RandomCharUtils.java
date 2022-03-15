package com.hsmap.yuezhihui.encrypt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.Random;

/***
 * 随机字符生成类
 */
public class RandomCharUtils {

    public static final Logger LOGGER = LoggerFactory.getLogger(RandomCharUtils.class);

    public final static String[] word = {
            "a", "b", "c", "d", "e", "f", "g",
            "h", "j", "k", "m", "n",
            "p", "q", "r", "s", "t",
            "u", "v", "w", "x", "y", "z",
            "A", "B", "C", "D", "E", "F", "G",
            "H", "J", "K", "M", "N",
            "P", "Q", "R", "S", "T",
            "U", "V", "W", "X", "Y", "Z"
    };

    public final static String[] num = {
            "0","1","2", "3", "4", "5", "6", "7", "8", "9"
    };

    public static String randomPassword(int length) {
        StringBuffer stringBuffer = new StringBuffer();
        Random random = new Random(new Date().getTime());
        boolean flag = false;
        //int length = random.nextInt(3) + 8;
        for (int i = 0; i < length; i++) {
            if (flag) {
                stringBuffer.append(num[random.nextInt(num.length)]);
            } else {
                stringBuffer.append(word[random.nextInt(word.length)]);
            }
            flag = !flag;
        }
        return stringBuffer.toString();
    }

    public static void main(String[] args) throws Exception {

        for (int i = 0;i<4;i++) {
            String s = randomPassword(10);
            Thread.sleep(100);
            LOGGER.info("s:{},lengt:{}",s,s.length());
        }
//        System.out.println(randomPassword());
//        Thread.sleep(100);
//        System.out.println(randomPassword());
//        Thread.sleep(100);
//        System.out.println(randomPassword());
    }


}

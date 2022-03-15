package com.hsmap.yuezhihui.http;

public class HttpCommon {


    //判断是啥操作系统
    public  static  String whatOS(final String str){

        String osStr = "未知";

        //这里就简单判断下
        if(str.contains("Windows")){

            osStr = "Windows";
        }
        else if(str.contains("Linux")){

            osStr = "Linux";
        }
        else if (str.contains("Mac")){
            osStr ="mac";

        }

        return osStr;
    }

    //判断是啥浏览器-简单判断下
    public static String whatBrower(final String str){

        String browerStr = "未知";
        if(str.contains("Chrome")){

            browerStr = "Chrome";
        }
        else if(str.contains("Firefox")){

            browerStr = "Firefox";
        }
        else if (str.contains("Safari")){

            browerStr = "Safari";
        }

        return  browerStr;
    }
}

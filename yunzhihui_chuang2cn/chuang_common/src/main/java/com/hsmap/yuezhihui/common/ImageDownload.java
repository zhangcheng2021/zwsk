package com.hsmap.yuezhihui.common;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ImageDownload {


    public static void main(String[] args) {
        for(int i =1;i<30;i++) {
            String url = String.format("https://book.yunzhan365.com/mlpe/hinm/files/mobile/%s.jpg",i);
            String saveFile = String.format("/Users/tangjixiong/Downloads/test_file/%s.jpg",i);
            ImageDownload.httpDownload(url, saveFile);
        }
    }
    public static boolean httpDownload(String httpUrl, String saveFile) {
        // 1.下载网络文件
        int byteRead;
        URL url;
        try {
            url = new URL(httpUrl);
        } catch (MalformedURLException e1) {
            e1.printStackTrace();
            return false;
        }

        try {
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            //2.获取链接
            //  URLConnection conn = url.openConnection();
            if (conn.getResponseCode() == 404) {
                System.out.println("状态码呱啦啦啦啦啦啦啦啦==" + url);
                return false;
            }

            //3.输入流
            InputStream inStream = conn.getInputStream();
            //3.写入文件

            File file = new File(saveFile);
            if (!file.exists()) {
                //先得到文件的上级目录，并创建上级目录，在创建文件
                file.getParentFile().mkdir();
                try {
                    //创建文件
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            FileOutputStream fs = new FileOutputStream(file);

            byte[] buffer = new byte[1024];
            while ((byteRead = inStream.read(buffer)) != -1) {
                fs.write(buffer, 0, byteRead);
            }

            inStream.close();
            fs.close();
            return true;
        } catch (FileNotFoundException e) {
            System.out.println("文件找不到呱啦啦啦啦啦啦啦啦==" + url);
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            System.out.println("IO呱啦啦啦啦啦啦啦啦==" + url);
            e.printStackTrace();
            return false;
        }
    }


}

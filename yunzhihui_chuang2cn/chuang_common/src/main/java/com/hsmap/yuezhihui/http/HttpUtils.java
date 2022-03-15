package com.hsmap.yuezhihui.http;

import com.alibaba.fastjson.JSON;
import com.hsmap.yuezhihui.common.CommonUtil;
import com.hsmap.yuezhihui.encrypt.EncryptUtils;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;
import java.util.Map.Entry;

public class HttpUtils {

    public final static org.slf4j.Logger logger = LoggerFactory.getLogger(HttpUtils.class);

    public static void main(String[] args) {
//        String url = "http://localhost:8082/api/conf/findConfList?type=1";
//        String getResult = HttpUtils.getData(url,null);
//        System.out.println(getResult);
//
//        url = "http://localhost:8082/api/core/login";
//        JSONObject json = new JSONObject();
//        json.put("_PAY_KEY", "==g43sEvsUcbcunFv3mHkIzlHO4iiUIT R7WwXuSVKTK0yugJnZSlr6qNbxsL8OqCUAFyCDCoRKQ882m6cTTi0q9uCJsq JJvxS+8mZVRP/7lWfEVt8/N9mKplUA68SWJEPSXyz4MDeFam766KEyvqZ99d");
//        String postResult = HttpUtils.postDataJson(url, json);
//        System.out.println(postResult);
//
//        url = "http://localhost:8082/api/test/testSendForm?format=json";
//        Map<String, Object> map = new HashMap<>();
//        map.put("name", "测试表单请求");
//        String formResult = HttpUtils.sendform(url, map);
//        System.out.println(formResult);

//        downloadPicture("http://m.360buyimg.com/mobilecms/s750x750_jfs/t1/110361/11/11452/173769/5e8c3049E0db279ef/1c38a85dd9f7ae2f.jpg!q80.dpg", "/Users/tangjixiong/Downloads/tmp/", EncryptUtils.getRandomChar(10) + ".jpg");

    }

    /**
     * 从网络上下载图片
     */
    public static boolean downloadPicture(String url, String dirPath,
                                          String filePath) {

        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpget = new HttpGet(url);
        httpget.setHeader(
                "User-Agent",
                "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.1 (KHTML, like Gecko) Chrome/21.0.1180.79 Safari/537.1");
        httpget.setHeader("Accept",
                "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
        try {
            HttpResponse resp = httpclient.execute(httpget);
            if (HttpStatus.SC_OK == resp.getStatusLine().getStatusCode()) {
                HttpEntity entity = resp.getEntity();

                InputStream in = entity.getContent();
                savePicToDisk(in, dirPath, filePath);
            }

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            httpclient.getConnectionManager().shutdown();
        }
    }


    public static String getData(String url, Map<String, String> headerMap) {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        String result = "";
        try {
            // 通过址默认配置创建一个httpClient实例
            httpClient = HttpClients.createDefault();
            // 创建httpGet远程连接实例
            HttpGet httpGet = new HttpGet(url);
            // 设置请求头信息，鉴权
            if (headerMap != null) {
                for (Map.Entry<String, String> entry : headerMap.entrySet()) {
                    httpGet.setHeader(entry.getKey(), entry.getValue());
                }
            }
            // 设置配置请求参数
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(35000)// 连接主机服务超时时间
                    .setConnectionRequestTimeout(35000)// 请求超时时间
                    .setSocketTimeout(60000)// 数据读取超时时间
                    .build();
            // 为httpGet实例设置配置
            httpGet.setConfig(requestConfig);
            // 执行get请求得到返回对象
            response = httpClient.execute(httpGet);
            // 通过返回对象获取返回数据
            HttpEntity entity = response.getEntity();
            // 通过EntityUtils中的toString方法将结果转换为字符串
            result = EntityUtils.toString(entity);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            close(httpClient, response);
        }
        return result;
    }


    /**
     * form表单提交
     *
     * @param url
     * @param paramMap
     * @return
     */
    public static String sendform(String url, Map<String, Object> paramMap) {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse httpResponse = null;
        String result = "";
        // 创建httpClient实例
        httpClient = HttpClients.createDefault();
        // 创建httpPost远程连接实例
        HttpPost httpPost = new HttpPost(url);
        // 配置请求参数实例
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(35000)// 设置连接主机服务超时时间
                .setConnectionRequestTimeout(35000)// 设置连接请求超时时间
                .setSocketTimeout(60000)// 设置读取数据连接超时时间
                .build();
        // 为httpPost实例设置配置
        httpPost.setConfig(requestConfig);
        // 设置请求头
        httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");
        // 封装post请求参数
        if (null != paramMap && paramMap.size() > 0) {
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            // 通过map集成entrySet方法获取entity
            Set<Entry<String, Object>> entrySet = paramMap.entrySet();
            // 循环遍历，获取迭代器
            Iterator<Entry<String, Object>> iterator = entrySet.iterator();
            while (iterator.hasNext()) {
                Entry<String, Object> mapEntry = iterator.next();
                nvps.add(new BasicNameValuePair(mapEntry.getKey(), mapEntry.getValue().toString()));
            }

            // 为httpPost设置封装好的请求参数
            try {
                httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        try {
            // httpClient对象执行post请求,并返回响应参数对象
            httpResponse = httpClient.execute(httpPost);
            // 从响应对象中获取响应内容
            HttpEntity entity = httpResponse.getEntity();
            result = EntityUtils.toString(entity);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            close(httpClient, httpResponse);

        }
        return result;
    }

    private static void close(CloseableHttpClient httpClient, CloseableHttpResponse httpResponse) {

        if (null != httpResponse) {
            try {
                httpResponse.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (null != httpClient) {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 将图片写到 硬盘指定目录下
     *
     * @param in
     * @param dirPath
     * @param filePath
     */
    private static void savePicToDisk(InputStream in, String dirPath,
                                      String filePath) {

        try {
            File dir = new File(dirPath);
            if (dir == null || !dir.exists()) {
                dir.mkdirs();
            }

            //文件真实路径
            String realPath = dirPath.concat(filePath);
            File file = new File(realPath);
            if (file == null || !file.exists()) {
                file.createNewFile();
            }

            FileOutputStream fos = new FileOutputStream(file);
            byte[] buf = new byte[1024];
            int len = 0;
            while ((len = in.read(buf)) != -1) {
                fos.write(buf, 0, len);
            }
            fos.flush();
            fos.close();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public static String getSign(String str, String token) {
        String sign = EncryptUtils.getMd5(EncryptUtils.getMd5(str), token);
        return sign;
    }


    /**
     * 提交post请求，json格式
     *
     * @param url
     * @param paramMap
     * @return
     */

    public static String postDataJson(String url, Map<String, Object> paramMap) {
        return postDataJson(url, null, paramMap);
    }

    /***
     * 提交post请求，json格式
     * @param url
     * @param headerMap
     * @param paramMap
     * @return
     */
    public static String postDataJson(String url, Map<String, String> headerMap, Map<String, Object> paramMap) {
        HttpEntity entity = new StringEntity(JSON.toJSONString(paramMap), "UTF-8");
        String contentType = "application/json";
        String result = postData(url,headerMap,entity,contentType);
        return result;
    }

    public static String postDataJson(String url, Map<String, String> headerMap, String json) {
        HttpEntity entity = new StringEntity(json, "UTF-8");
        String contentType = "application/json";
        String result = postData(url,headerMap,entity,contentType);
        return result;
    }

    public static String postDataXml(String url, Map<String, String> headerMap, Map<String, String> paramMap) {
        HttpEntity entity = new StringEntity(CommonUtil.arrayToXml(paramMap),"UTF-8");
        String contentType = "application/xml";
        String result = postData(url,headerMap,entity,contentType);
        return result;
    }

    private static String postData(String url,Map<String, String> headerMap,HttpEntity entity,String contentType){
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        HttpPost post = new HttpPost(url);
        String result = "";
        try (CloseableHttpClient closeableHttpClient = httpClientBuilder.build()) {
            if (headerMap != null) {
                for (Map.Entry<String, String> entry : headerMap.entrySet()) {
                    post.setHeader(entry.getKey(), entry.getValue());
                }
            }
            // 修复 POST json 导致中文乱码
            post.setEntity(entity);
            post.setHeader("Content-type", contentType);
            HttpResponse resp = closeableHttpClient.execute(post);
            InputStream respIs = resp.getEntity().getContent();
            byte[] respBytes = IOUtils.toByteArray(respIs);
            result = new String(respBytes, Charset.forName("UTF-8"));

            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }


}

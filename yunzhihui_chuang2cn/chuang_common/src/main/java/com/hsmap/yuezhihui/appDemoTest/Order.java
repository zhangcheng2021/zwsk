package com.hsmap.yuezhihui.appDemoTest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hsmap.yuezhihui.encrypt.EncryptUtils;
import com.hsmap.yuezhihui.http.HttpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/***
 * 抢单
 */
public class Order {

    public final static Logger LOGGER = LoggerFactory.getLogger(Order.class);
    final private static String baseUrl =  "http://127.0.0.1:8888/api/";

    static String token  ="K5X8a8a6_3085997";
    static int userId = 23;
    static int addressId = 20;

    public static void main(String[] args) {
        for(int i=0;i<400;i++){
            try{
                Thread.sleep(100);
                testOrder();
            }catch(Exception e){
                //System.exit(0);//退出程序
            }

        }
    }

    public static void testOrder(){
        String json = getTask();
        LOGGER.info("json:{}",json);
        JSONObject taskJson = JSON.parseObject(json);
        int code = taskJson.getInteger("code");
        if(code == 0){
            JSONObject data = taskJson.getJSONObject("data");
            JSONObject orderJson = data.getJSONObject("order");


            int orderId = orderJson.getInteger("id");
//            int addressId = jsonObject.getInteger("addressId");
            String result = confirm(orderId,addressId);
            LOGGER.info("result:{}",result);

        }

    }

    public static String confirm(int id,int addressid){
        String url =baseUrl+"/order/confirm";

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id",String.valueOf(id));
        paramMap.put("addressId",String.valueOf(addressid));
        Map<String, String>  headerMap = new HashMap<>();
        headerMap.put("userId",String.valueOf(userId));
        headerMap.put("sign",getSign(JSON.toJSONString(paramMap),token));
        String result = postData(url,headerMap,paramMap);
        System.out.println("result:"+result);
        return result;

    }
    public static   String getTask(){



        String url =baseUrl+"/order/taskOrder";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("1111","111111");
        paramMap.put("userId",String.valueOf(userId));
        Map<String, String>  headerMap = new HashMap<>();
        headerMap.put("userId",String.valueOf(userId));
        headerMap.put("sign",getSign(JSON.toJSONString(paramMap),token));

        String result = postData(url,headerMap,paramMap);
        System.out.println("result:"+result);
        return result;

    }

    private static String getSign(String str,String token){
        String sign = EncryptUtils.getMd5(EncryptUtils.getMd5(str), token);
        return sign;
    }



    public static String postData(String url, Map<String, String>  headerMap,Map<String, Object> paramMap) {
        return HttpUtils.postDataJson(url,headerMap,paramMap);
//        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
//        HttpPost post = new HttpPost(url);
//        String result = "";
//        try (CloseableHttpClient closeableHttpClient = httpClientBuilder.build()) {
//            // HttpEntity entity = new StringEntity(jsonStrData);
//            if (headerMap != null) {
//                for (Map.Entry<String, String> entry : headerMap.entrySet()) {
//                    //httpGet.setHeader(entry.getKey(), entry.getValue());
//                    post.setHeader(entry.getKey(), entry.getValue());
//                }
//            }
//            // 修复 POST json 导致中文乱码
//            HttpEntity entity = new StringEntity(JSON.toJSONString(paramMap), "UTF-8");
//            post.setEntity(entity);
//            post.setHeader("Content-type", "application/json");
//            HttpResponse resp = closeableHttpClient.execute(post);
//            try {
//                InputStream respIs = resp.getEntity().getContent();
//                byte[] respBytes = IOUtils.toByteArray(respIs);
//                result = new String(respBytes, Charset.forName("UTF-8"));
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            return result;
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return result;
    }


}

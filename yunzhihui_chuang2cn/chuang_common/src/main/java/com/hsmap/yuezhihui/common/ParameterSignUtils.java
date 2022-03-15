package com.hsmap.yuezhihui.common;

import com.hsmap.yuezhihui.encrypt.EncryptUtils;
import com.hsmap.yuezhihui.encrypt.RandomCharUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/***
 * api参数验签
 */
public class ParameterSignUtils {

    public static final Logger LOGGER = LoggerFactory.getLogger(ParameterSignUtils.class);

    /***
     * 获取签名字符串
     * @param sourceMap
     * @return
     */
    public static String getSign(Map<String, String> sourceMap, String token) {
        Map<String, String> sortMap = sort(sourceMap);
        String str = map2Str(sortMap, token);
        LOGGER.info("str:{}", str);
        String sign = EncryptUtils.getMd5(str, token);
        LOGGER.info("sign:{}", sign);
        return sign;
    }

    /**
     * 将参数转为字符串
     *
     * @return
     */
    private static String map2Str(Map<String, String> map, String token) {
        StringBuffer sb = new StringBuffer();
        map.forEach((k, v) -> {
            sb.append(k);
            sb.append("=");
            sb.append(v);
            sb.append("|");
        });
        sb.append(token);
        return sb.toString();
    }

    /**
     * map排序-升序
     *
     * @param sourceMap
     * @return
     */
    public static Map<String, String> sort(Map<String, String> sourceMap) {
        List<Map.Entry<String, String>> list = new ArrayList<Map.Entry<String, String>>(sourceMap.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, String>>() {
            //升序排序
            public int compare(Map.Entry<String, String> o1,
                               Map.Entry<String, String> o2) {
                return o1.getKey().compareTo(o2.getKey());
            }

        });
        Map<String, String> newsMap = new LinkedHashMap<>();

        for (Map.Entry<String, String> mapping : list) {
            newsMap.put(mapping.getKey(), mapping.getValue());
            //System.out.println(mapping.getKey()+":"+mapping.getValue());
        }
        return newsMap;
    }


    public static Map<String, Object> sortMap(Map<String, Object> sourceMap) {
        List<Map.Entry<String, Object>> list = new ArrayList<>(sourceMap.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Object>>() {
            //升序排序
            public int compare(Map.Entry<String, Object> o1,
                               Map.Entry<String, Object> o2) {
                return o1.getKey().compareTo(o2.getKey());
            }

        });
        Map<String, Object> newsMap = new LinkedHashMap<>();

        for (Map.Entry<String, Object> mapping : list) {
            newsMap.put(mapping.getKey(), mapping.getValue());
            System.out.println(mapping.getKey() + ":" + mapping.getValue());
        }
        return newsMap;
    }

    public static void main(String[] args) {
      /*  ParameterSignUtils mapSort = new ParameterSignUtils();
        Map<String,String> map =new HashMap<>();
        map.put("passwd","DG123456");
        map.put("loginName","2348000000010");
        mapSort.sort(map);

        String sign = ParameterSignUtils.getSign(map,"48566B80BC7A7947");
        LOGGER.info("--->sign:{}",sign);*/
        String token = "48566B80BC7A7947";
        String str = "";
        String str2 = "";
        String sign = EncryptUtils.getMd5(str, token);
        System.out.println(sign);
        String sign2 = EncryptUtils.getMd5(str2, token);
        System.out.println(sign2);
    }
}

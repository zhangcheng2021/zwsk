package com.hsmap.yuezhihui.pay;

import com.hsmap.yuezhihui.common.CommonUtil;
import com.hsmap.yuezhihui.encrypt.EncryptUtils;
import com.hsmap.yuezhihui.http.HttpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/***
 * 信德代
 */
public class XindePayUtils {
    public final static Logger LOGGER = LoggerFactory.getLogger(XindePayUtils.class);
    private final String _CUSTOMER_ID = "12936";
    private final String _KEY  = "00d1f461b34e1c16991690267fe91179ddbbadbb";
    private final String _DAIFU_URL = "http://27.124.36.46/apidaifu";
    private final String _DAIFU_QUERY_URL = "http://27.124.36.47/apidaifu/query";
    private final String _NOTIFY_URL = "http://27.124.36.46";


    public static void main(String[] args) {
        String realname = "唐基雄";
        int money = 100;
        String bank_name = "中国农行银行";
        String card_number = "6228480329549004771";
        String out_trade_no = CommonUtil.getDateStringByPattern("yyyyMMddHHmmss")+"_"+ EncryptUtils.getRandomChar(8);
        int type = 1;
//        String idcard = "430726198801195433";
//        String phone = "13777443718";

        XindePayUtils xindePayUtils = new XindePayUtils();
        xindePayUtils.apidaifu(realname,money,bank_name,card_number,out_trade_no,type);
//        xindePayUtils.daifuQuery("214214321432143214321");



    }

    /***
     * 代付
     * @param realname
     * @param money
     * @param bank_name
     * @param card_number
     * @param out_trade_no
     * @param type
     */
    public String apidaifu(String realname,int money,String bank_name,String card_number,String out_trade_no,int type){
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("customerid",_CUSTOMER_ID);
        paramMap.put("realname",realname);
        paramMap.put("money",money);
        paramMap.put("bank_name",bank_name);
        paramMap.put("card_number",card_number);
        paramMap.put("out_trade_no",out_trade_no);
        paramMap.put("notifyurl",_NOTIFY_URL);
        paramMap.put("type",type);
//        paramMap.put("idcard",idcard);
//        paramMap.put("phone",phone);
        String sign = getDaifuSign(paramMap);
        paramMap.put("sign",sign);
        String result = HttpUtils.sendform(_DAIFU_URL,paramMap);
        LOGGER.info("result:{}",result);
        return  result;
    }

    public String daifuQuery(String out_trade_no){
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("customerid",_CUSTOMER_ID);
        paramMap.put("out_trade_no",out_trade_no);
        String sign  = EncryptUtils.getMd5(_CUSTOMER_ID+out_trade_no+_KEY);
        paramMap.put("sign",sign);
        String result = HttpUtils.sendform(_DAIFU_QUERY_URL,paramMap);
        LOGGER.info("result:{}",result);
        return  result;


    }




    private String getDaifuSign(Map<String,Object> map){
        List<Map.Entry<String, Object>> infoIds = new ArrayList<Map.Entry<String, Object>>(map.entrySet());
        // 对所有传入参数按照字段名的 ASCII 码从小到大排序（字典序）
        Collections.sort(infoIds, new Comparator<Map.Entry<String, Object>>() {

            public int compare(Map.Entry<String, Object> o1, Map.Entry<String, Object> o2) {
                return (o1.getKey()).toString().compareTo(o2.getKey());
            }
        });

        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Object> item : infoIds) {
            if (item.getKey() != null || item.getKey() != "") {
                String key = item.getKey();
                String val = String.valueOf(item.getValue());
                if (!(val == "" || val == null)) {
                    sb.append(key + "=" + val + "&");
                }
            }
        }
        String result = sb.toString()+"&_PAY_KEY="+_KEY;
        LOGGER.info("result:"+result);
        String md5= EncryptUtils.getMd5(result);
        LOGGER.info("md5:"+md5);
        return md5;
    }

}

package com.hsmap.yuezhihui.capture.goods;

import com.hsmap.yuezhihui.capture.goods.bean.GoodsBean;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.math.BigDecimal;

public class JDUtils extends BaseMallUtils {

    public static void main(String[] args) {
//        String url = "https://item.m.jd.com/ware/view.action?wareId=26397170392";
//        String url = "https://item.m.jd.com/ware/view.action?wareId=55818140863&clickUrl=";
        String url = "https://item.jd.com/56347279660.html";
        JDUtils jdUtils = new JDUtils();
        jdUtils.loadHtmlContent(url);
//        String id = jdUtils.getUrl(url);
//        System.out.println(id);
    }


    public GoodsBean loadHtmlContent(String url) {
        String murl = getmurl(url);
        LOGGER.info("murl:{}",murl);

        GoodsBean goodsBean = null;

        try {
            String html = JsoupUtils.jsoupHtml(murl, null);
            Document document = Jsoup.parse(html);
            Element titleElement = document.selectFirst("div[id=\"itemName\"]");
            String title = titleElement.text();
            Element priceElement = document.selectFirst("span[id=\"priceSale\"]");
            String price ="0";
            if(priceElement!= null) {
                price = priceElement.select("em").text();
//                price =price.substring(0,price.indexOf("."));
            }

            Element subheadElement = document.selectFirst("div[id=\"itemDesc\"]");
            String subhead ="";
            if(subheadElement!= null) {
                subhead = subheadElement.text();
            }

            Element imgElement = document.selectFirst("img[id=\"firstImg\"]");
            String imgurl = imgElement.attr("src");

            LOGGER.info("title:{},price:{},subhead:{},imgurl:{}",title,price,subhead,imgurl);
            goodsBean = new GoodsBean();
            goodsBean.setTitle(title);
            goodsBean.setImageUrl(imgurl);
            goodsBean.setContent(title);
            goodsBean.setPrice(new BigDecimal(price));

        }catch(Exception ex){
            ex.printStackTrace();
        }

        return goodsBean;
    }

    private String getmurl (String url ){
        //取出Id
        if(url.indexOf("item.jd.com")>0) {
            String id = url.substring(url.lastIndexOf("/") + 1, url.indexOf(".htm"));
            String murl = "https://item.m.jd.com/ware/view.action?wareId="+id;
            return murl;
        }else{
            return url;
        }
    }
}

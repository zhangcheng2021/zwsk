package com.hsmap.yuezhihui.capture.goods;

import com.hsmap.yuezhihui.capture.goods.bean.GoodsBean;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.math.BigDecimal;

public class TaobaoUtils extends BaseMallUtils {


    public static void main(String[] args) {
//        String url = "https://item.taobao.com/item.htm?ft=t&id=595864191265&ali_trackid=2:mm_26632614_0_0:1586279662_267_1634484068&spm=a21bo.7925890.192091.1&pvid=e5f96fed-3e55-4451-a3b6-03487f9b340a&scm=1007.12846.156652.999999999999999";
//
//        String url = "https://detail.tmall.com/item.htm?spm=a230r.1.0.0.2ae4e18aCRxf6y&id=583573465995&ns=1";
//        String url = "https://item.taobao.com/item.htms?spm=a310p.7395781.1998038982.3&id=559979252785";
        String url ="https://item.taobao.com/item.htm?spm=a219r.lm944.14.15.f5df3989F3n5mn&id=571018181265&ns=1&abbucket=9#detail";
        TaobaoUtils taobaoUtils = new TaobaoUtils();
        taobaoUtils.loadHtmlContent(url);
    }


    public GoodsBean loadHtmlContent(String url) {
        GoodsBean goodsBean = null;
        try {
            String html = JsoupUtils.jsoupHtml(url, null);
            Document document = Jsoup.parse(html);
            Element titleElement = document.selectFirst("h3[class=\"tb-main-title\"]");

            String title = titleElement.text();
            Element priceElement = document.selectFirst("em[class=\"tb-rmb-num\"]");
            String price = priceElement.text();
            price = price.substring(0,price.indexOf("."));
            Element imgElement = document.selectFirst("img[id=\"J_ImgBooth\"]");
            String imgurl = imgElement.attr("src");

            Element contentElement = document.selectFirst("ul[class=\"attributes-list\"]");
            String content = contentElement.text();

            Element subheadElement = document.selectFirst("div[class=\"tb-brand-title\"]");
            String subhead ="";
            if(subheadElement!= null) {
                subhead = subheadElement.text();
            }
            LOGGER.info("title:{},price:{},imgurl:{}", title, price, imgurl);
            LOGGER.info("content:{}", content);
            LOGGER.info("subhead:{}", subhead);
            goodsBean = new GoodsBean();
            goodsBean.setTitle(title);
            goodsBean.setPrice(new BigDecimal(price));
            goodsBean.setImageUrl(imgurl);
            goodsBean.setContent(content);
            return goodsBean;
        }catch(Exception ex){
            ex.printStackTrace();
            return goodsBean;
        }
    }


}

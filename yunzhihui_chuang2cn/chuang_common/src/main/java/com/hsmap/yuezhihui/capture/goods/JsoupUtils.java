package com.hsmap.yuezhihui.capture.goods;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Map;

public class JsoupUtils {
    public static String jsoupHtml(String url, Map<String, String> cookMap) {
        try {

            Connection con2 = Jsoup.connect(url).timeout(300000);
//            con2.header(USER_AGENT, USER_AGENT_VALUE);

            con2.ignoreContentType(true);
            con2.followRedirects(true);
            con2.method(Connection.Method.GET);
            if (cookMap!= null) {
                con2.cookies(cookMap);
            }
            String html = con2.execute().body();
            return html;

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "";

    }



}

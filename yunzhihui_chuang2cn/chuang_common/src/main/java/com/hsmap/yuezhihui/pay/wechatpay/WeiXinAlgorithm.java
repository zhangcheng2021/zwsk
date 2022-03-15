package com.hsmap.yuezhihui.pay.wechatpay;

import com.hsmap.yuezhihui.common.CommonUtil;
import com.hsmap.yuezhihui.encrypt.EncryptUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.InetAddress;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.util.*;
import java.util.Map.Entry;

/**
 * 微信参数算法
 *
 * @author Administrator
 *
 */
public class WeiXinAlgorithm {

	/**
	 * 生成随机字符串
	 *
	 * @return
	 */
	public static String getRandomString(int length) {

		return CommonUtil.getRandomByCount(length);
	}

	/**
	 * 获取IP
	 *
	 * @return
	 */
	public static String getIp() {
		String ip = "";
		try {
			ip = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			e.printStackTrace();
			ip = "127.0.0.1";
		}
		return ip;
	}

	/**
	 * 获取支付所需签名

	 * @throws Exception
	 */
	public static String getPayCustomSign(Map<String, String> bizObj, String key) throws Exception {
		String bizString = FormatBizQueryParaMap(bizObj, false);
		return sign(bizString, key);
	}

	public static String FormatBizQueryParaMap(Map<String, String> paraMap, boolean urlencode) throws Exception {
		String buff = "";
		try {
			List<Entry<String, String>> infoIds = new ArrayList<Entry<String, String>>(paraMap.entrySet());
			Collections.sort(infoIds, new Comparator<Entry<String, String>>() {
				public int compare(Entry<String, String> o1, Entry<String, String> o2) {
					return (o1.getKey()).toString().compareTo(o2.getKey());
				}
			});
			for (int i = 0; i < infoIds.size(); i++) {
				Entry<String, String> item = infoIds.get(i);
				if (item.getKey() != "") {
					String key = item.getKey();
					String val = item.getValue();
					if (urlencode) {
						val = URLEncoder.encode(val, "utf-8");
					}
					buff += key + "=" + val + "&";
				}
			}
			if (buff.isEmpty() == false) {
				buff = buff.substring(0, buff.length() - 1);
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return buff;
	}

	public static String sign(String content, String key) throws Exception {
		String signStr = "";
		signStr = content + "&_PAY_KEY=" + key;
		return EncryptUtils.getMd5(signStr).toUpperCase();
	}


	public static String ArrayToXml(Map<String, String> arr) {
		String xml = "<xml>";
		Iterator<Entry<String, String>> iter = arr.entrySet().iterator();
		while (iter.hasNext()) {
			Entry<String, String> entry = iter.next();
			String key = entry.getKey();
			String val = entry.getValue();
			if (IsNumeric(val)) {
				xml += "<" + key + ">" + val + "</" + key + ">";
			} else
				xml += "<" + key + "><![CDATA[" + val + "]]></" + key + ">";
		}
		xml += "</xml>";
		return xml;
	}

	public static boolean IsNumeric(String str) {
		if (str.matches("\\d *")) {
			return true;
		} else {
			return false;
		}
	}

	// 获得Post过来的数据
	public static String getPostStr(HttpServletRequest request) {
		String buffer = "";
		// ActionContext act = ActionContext.getContext();
		// HttpServletRequest request = (HttpServletRequest)
		// act.get(ServletActionContext.HTTP_REQUEST);
		// HttpServletRequest rtRequest = Servlet
		InputStream is;
		try {
			is = request.getInputStream();
			// 以http请求输入流建立一个BufferedReader对象
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String temp = "";
			while ((temp = br.readLine()) != null) {
				buffer = buffer + temp;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return buffer;
	}

	// public static void main(String[] args) {
	// System.out.println(getPostStr());
	// }

}

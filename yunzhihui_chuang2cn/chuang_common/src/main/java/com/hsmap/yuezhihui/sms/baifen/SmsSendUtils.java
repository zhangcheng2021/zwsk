package com.hsmap.yuezhihui.sms.baifen;

import com.hsmap.yuezhihui.common.CommonUtil;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;


//@Component
public class SmsSendUtils {
//	@Autowired
//	private SysConfig config;



	private final  org.slf4j.Logger LOGGER = LoggerFactory.getLogger(SmsSendUtils.class);
	private  final String banfeiUrl = "http://jiekou.51welink.com/submitdata/Service.asmx/g_Submit";
	private  final int timeout = 30000;
	private static  String BAIFEN_SNAME = "dljy000y";
	private static  String BAIFEN_SPWD = "You88660";
	private  final String BAIFEN_SPRDID = "1012818";

	public SmsSendUtils(String sname,String spwd) {
		BAIFEN_SNAME = sname;
		BAIFEN_SPWD =spwd;

	}
	public void sendMsg(String mobile,String msg) {
//
//		 BAIFEN_SNAME = config.getSmsUserName();
//		BAIFEN_SPWD = config.getSmsPasswd();
		SmsRecord smsRecord = new SmsRecord();
		smsRecord.setTelphone(mobile);
		smsRecord.setContent(msg);
		LOGGER.info("短信发送记录,mobile:{},msg:{}",mobile,msg);
//		SmsSendUtils smsSendUtils =new SmsSendUtils();
		sendMsg(smsRecord);


	}
	public  SmsRecord sendMsg(SmsRecord record) {
		String mobile=record.getTelphone();
		String content=record.getContent();
		try {
			String entity = "sname={sname}&spwd={spwd}&scorpid={scorpid}&sprdid={sprdid}&sdst={tel}&smsg={content}";
			entity = entity.replace("{sname}",BAIFEN_SNAME );
			entity = entity.replace("{spwd}", BAIFEN_SPWD);
			entity = entity.replace("{scorpid}", "");
			entity = entity.replace("{sprdid}",BAIFEN_SPRDID);
			entity = entity.replace("{tel}", mobile);
			entity = entity.replace("{content}", content);

			URL url = new URL(banfeiUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			conn.setRequestProperty("Connection", "Keep-Alive");
			conn.setUseCaches(false);
			conn.setDoOutput(true);
			conn.setConnectTimeout(timeout);

			conn.setRequestProperty("Content-Length", "" + entity.length());
			OutputStreamWriter out = new OutputStreamWriter(
					conn.getOutputStream(), "UTF-8");
			out.write(entity);
			out.flush();
			out.close();
			// 获取响应状态
			if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
				System.out.println("connect failed!");
				return record;
			}
			// 获取响应内容体
			String line, result = "";
			BufferedReader in = new BufferedReader(new InputStreamReader(
					conn.getInputStream(), "utf-8"));
			while ((line = in.readLine()) != null) {
				result += line + "\n";
			}
			LOGGER.info(result);
			in.close();
			if (!CommonUtil.isEmpty(result)) {
				LOGGER.info(result);
				SmsResult smsResult=parseXml(result);
				record.setMsgId(smsResult.getMsgId());
				record.setResultCode(smsResult.getStatus());
				record.setResultMsg(smsResult.getErrorMsg());
				record.setSendTime(new Date());
				return record;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return record;
	}
	private  SmsResult parseXml(String xml) {
		System.out.println("xml:"+xml);
		SmsResult result=new SmsResult();
		try {
			SAXReader saxReader = new SAXReader();
			Document document = saxReader.read(new ByteArrayInputStream(xml
					.getBytes("utf-8")));
			Element root = document.getRootElement();
			LOGGER.info("root:" + root.getName());
			String state = getElementValueByName(root, "State");
			result.setStatus(state);
			String msgId = getElementValueByName(root, "MsgID");
			result.setMsgId(msgId);
			String msgState = getElementValueByName(root, "MsgState");
			result.setErrorMsg(msgState);
			String reserve = getElementValueByName(root, "Reserve");
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return result;
		}
	}

	private  String getElementValueByName(Element root, String elementName) {
		try {
			Element element = root.element(elementName);
			LOGGER.info(element.getText());
			 String value=element.getText();
			return value;
		} catch (Exception ex) {
			ex.printStackTrace();
			return "";
		}
	}

//	public static void main(String[] agrs) {
//		SmsRecord smsRecord=new SmsRecord();
//		smsRecord.setContent("测试正常短信092902329090【油惠卡】");
//		smsRecord.setTelphone("13777443718");
////		SmsSendUtils.sendMsg(smsRecord);
////		String xml = "<CSubmitState xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns=\"http://tempuri.org/\"><State>0</State>  <MsgID>1511171733254513904</MsgID>  <MsgState>提交成功</MsgState>  <Reserve>0</Reserve></CSubmitState>";
////		SmsSendUtils.parseXml(xml);
//		//String content=SmsSendUtils.processContent("ffsfsafs");
//		//log.info(content);
//
//	}
}

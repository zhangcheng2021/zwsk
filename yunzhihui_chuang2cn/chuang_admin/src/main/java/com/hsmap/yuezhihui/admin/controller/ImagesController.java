package com.hsmap.yuezhihui.admin.controller;


import com.hsmap.yuezhihui.config.SysConfig;
import com.hsmap.yuezhihui.utils.upyun.UpyunUtils;
import com.hsmap.yuezhihui.FileUtils;
import com.hsmap.yuezhihui.common.CommonUtil;
import com.hsmap.yuezhihui.encrypt.EncryptUtils;
import com.hsmap.yuezhihui.images.QrCodeUtils;
import com.hsmap.yuezhihui.images.VerifyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/***
 * 图片生成公共方法
 *
 */
@RestController
@RequestMapping("/images")
public class ImagesController extends AdminBaseController {

    @Autowired
    public UpyunUtils upyunUtils;

    @Autowired
    SysConfig sysConfig;


    /***
     *
     * @param httpSession
     * @param response
     * @param type 0短信验证码 1登录验证码 2注册验证码
     */

    @RequestMapping(value = "/verify")
    @ResponseBody
    public void verifyImages(HttpSession httpSession, HttpServletResponse response, @RequestParam(name = "type", defaultValue = "0") int type) {
        try {
            response.setContentType("image/jpeg");//设置相应类型,告诉浏览器输出的内容为图片
            response.setHeader("Pragma", "No-cache");//设置响应头信息，告诉浏览器不要缓存此内容
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expire", 0);
            VerifyUtil randomValidateCode = new VerifyUtil();
            String randomChar = CommonUtil.getRandomByCount(4);
            randomValidateCode.getRandcode(response,randomChar);//输出验证码图片
            //将生成的随机验证码存放到redis中
            if (0 ==type) {
                setSession(httpSession, _SESSION_SMS_VERIFY_IMG,randomChar);
            } else if (1 == type) {
                setSession(httpSession, _SESSION_LOGIN_VERIFY_IMG,randomChar);
            } else if (2 == type) {
                setSession(httpSession, _SESSION_REG_VERIFY_IMG,randomChar);
            }
        } catch (Exception ex) {
            return ;
        }
    }

    @RequestMapping(value = "/qrcode")
    public void getQrcode(String content, HttpServletResponse response) {
        try {
            OutputStream os = response.getOutputStream();
            QrCodeUtils.encode(content,os);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @RequestMapping(value = "/uploadImg")
    public String uploadImg(@RequestParam("file") MultipartFile myfiles, String type,@RequestParam(name = "isThum", defaultValue = "0") int isThum, String gmkerlValue, String gmkerlLity) throws Exception {
        LOGGER.info("type:{}",type);
        if (CommonUtil.isEmpty(type)) {
            type = "images_all";
        }
        if (myfiles == null || myfiles.getSize() <= 0) {
            LOGGER.info("文件不能为空，请选择文件...");
            return setResultError("文件不能为空，请选择文件");
        }
        File file = FileUtils.multipartFileToFile(myfiles);
        String path="/%s/%s/%s/";
        String suffixName=CommonUtil.getFileSuffixName(myfiles.getOriginalFilename());//扩展名
        String fileName = new Date().getTime()+"_"+ EncryptUtils.getRandomChar(6);
        String thumFileName =fileName+"_thum"+suffixName;
        fileName += suffixName;
        LOGGER.info("fileName:{}",fileName);
        path = String.format(path,type,CommonUtil.getDateStringByPattern("yyyyMM"),CommonUtil.getDateStringByPattern("dd"));
        LOGGER.info("path:{}",path);
        upyunUtils.uploadFile(path+fileName,file);
        Map<String,String > map = new HashMap<>();
        if(isThum>0) {
            upyunUtils.uploadFileThum(path + thumFileName, file, gmkerlValue, gmkerlLity);
            map.put("thumPath",path+thumFileName);
        }
        file.delete();

        map.put("path",path+fileName);


        map.put("src", sysConfig.getImagesDomain()+path+fileName);
        map.put("title",sysConfig.getSysname()+"图片");

        String result = setResult(map);
        LOGGER.info("upload result:{}",result);
        return result;


    }

}

package com.hsmap.yuezhihui.utils.upyun;


import com.UpYun;
import com.hsmap.yuezhihui.common.CommonUtil;
import com.hsmap.yuezhihui.config.SysConfig;
import com.upyun.UpException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
public class UpyunUtils {
    @Autowired
    SysConfig sysConfig;
    public final static Logger LOGGER = LoggerFactory.getLogger(UpyunUtils.class);
    public static void main(String[] args) {
        UpyunUtils upyunUtils = new UpyunUtils();
        File file =new File("/Users/tangjixiong/Downloads/newyear_0214.jpg");
        String filePath = "/test/021401.jpg";
        boolean  b = upyunUtils.uploadFile(filePath,file);
        upyunUtils.uploadFileThum("/test/021401_m.jpg",file,null,null);
        LOGGER.info("b:{}",b);
    }


    UpYun initUpyun() {
        String bucketName = sysConfig.getUpyunBucketName();
        String userName = sysConfig.getUpyunUserName();
        String password = sysConfig.getUpyunPassword();
        UpYun upyun = new UpYun(bucketName, userName, password);
        upyun.setDebug(true);
        upyun.setTimeout(60);
        upyun.setApiDomain(UpYun.ED_AUTO);
        return upyun;

    }


    /***
     * 创建目录
     * @param path
     * @return
     */
    public boolean mkdir(String path) {
        UpYun upyun = initUpyun();
        try {
            // boolean upyun.mkDir(path, true);
            boolean b = upyun.mkDir(path, true);
            return b;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UpException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 删除目录
     *
     * @param path
     * @return
     */
    public boolean removeDir(String path) {
        UpYun upyun = initUpyun();
        try {
            boolean b = upyun.rmDir(path);
            return b;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } catch (UpException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获取目录文件列表
     *
     * @param path
     * @param params
     * @return
     */
    public List<UpYun.FolderItem> readDir(String path, Map<String, String> params) {
        UpYun upyun = initUpyun();
        try {
            List<UpYun.FolderItem> folderItemIter = upyun.readDir(path);
            return folderItemIter;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UpException e) {
            e.printStackTrace();
        }
        return null;
    }

    /***
     * 文件上传
     * @param filePath
     * @param file
     * @return
     */
    public boolean uploadFile(String filePath, File file) {
        UpYun upyun = initUpyun();
        try {
            boolean b = upyun.writeFile(filePath, file, true);
            return b;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } catch (UpException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 文件上传
     *
     * @param filePath
     * @param datas
     * @return
     */
    public boolean uploadFile(String filePath, byte[] datas) {
        UpYun upyun = initUpyun();
        try {
            boolean b = upyun.writeFile(filePath, datas, true);
            return b;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UpException e) {
            e.printStackTrace();
        }
        return false;
    }

    /***
     * 获取文件信息
     * @param filePath
     * @return
     */
    public Map<String, String> getFileInfo(String filePath) {
        UpYun upyun = initUpyun();
        try {
            Map<String, String> map = upyun.getFileInfo(filePath);
            return map;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UpException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 文件下载
     *
     * @param filePath
     * @return
     */
    public String readFile(String filePath) {
        UpYun upyun = initUpyun();
        try {
            return upyun.readFile(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UpException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 上传缩略图
     * @param filePath
     * @param localFile
     * @param gmkerlValue
     * @param gmkerlLity
     * @return
     */
    public boolean uploadFileThum(String filePath,File localFile, String gmkerlValue, String gmkerlLity) {
        UpYun upyun = initUpyun();
        Map<String, String> params = new HashMap<>();

        // 设置缩略图类型
        params.put(UpYun.PARAMS.KEY_X_GMKERL_TYPE.getValue(), UpYun.PARAMS.VALUE_FIX_BOTH.getValue());


        // 设置缩略图参数值
        params.put(UpYun.PARAMS.KEY_X_GMKERL_VALUE.getValue(), (CommonUtil.isEmpty(gmkerlValue) ? "150x150" : gmkerlValue));

        // 设置缩略图的质量，默认 95

        params.put(UpYun.PARAMS.KEY_X_GMKERL_QUALITY.getValue(), (CommonUtil.isEmpty(gmkerlLity) ? "95" : gmkerlLity));



        // 上传图片，并同时进行图片处理
        try {
            boolean result = upyun.writeFile(filePath, localFile, true, params);
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UpException e) {
            e.printStackTrace();
        }
        return false;
    }

    /***
     * 下载文件
     * @param filePath
     * @param file 保存文件
     * @return
     */
    public boolean downloadFile(String filePath, File file) {
        UpYun upyun = initUpyun();
        try {
            boolean b = upyun.readFile(filePath, file);
            return b;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UpException e) {
            e.printStackTrace();
        }
        return false;
    }
}

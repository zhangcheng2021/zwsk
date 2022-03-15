package com.hsmap.yuezhihui.admin.controller;

import com.hsmap.yuezhihui.FileUtils;
import com.hsmap.yuezhihui.common.DateUtils;
import com.hsmap.yuezhihui.config.SysConfig;
import com.hsmap.yuezhihui.entity.core.CoreTemplateRelation;
import com.hsmap.yuezhihui.service.core.ICoreTemplateRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 公共Controller
 * 提供文件上传，下载
 */
@Controller
public class CommonController extends AdminBaseController {
    @Autowired
    private ICoreTemplateRelationService coreTemplateRelationService;
    @Autowired
    private SysConfig sysConfig;

    /**
     * 通用下载请求
     *
     * @param code 文件名称
     */
    @GetMapping("/common/dl_template/{code}")
    public void downloadTemplate(@PathVariable("code") String code, HttpServletResponse response, HttpServletRequest request) {
        //查询模板关系
        CoreTemplateRelation template = coreTemplateRelationService.findByCode(code);
        if (template == null) {
            throw new RuntimeException("模板没有配置");
        }
        File d = new File(sysConfig.getDownloadDir() + File.separator + template.getFilePath());
        if (!d.exists()) {
            throw new RuntimeException("模板文件不存在");
        }
        FileInputStream fis = null;
        OutputStream os = null;
        try {
            response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
            setAttachmentResponseHeader(response, template.getDownloadName());
            fis = new FileInputStream(d);
            byte[] b = new byte[1024];
            int length;
            os = response.getOutputStream();
            while ((length = fis.read(b)) > 0) {
                os.write(b, 0, length);
            }
        } catch (IOException e0) {
            LOGGER.error("下载文件失败", e0);
        } catch (Exception e) {
            LOGGER.error("下载文件失败", e);
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e1) {
                    LOGGER.error("下载文件失败", e1);
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e1) {
                    LOGGER.error("下载文件失败", e1);
                }
            }
        }
    }

    /**
     * 通用上传请求
     */
    @PostMapping("/common/upload")
    @ResponseBody
    public String uploadFile(MultipartFile file) throws Exception {
        try {
            if (file == null) {
                return setResultError("文件为空");
            }
            // 上传文件路径
            String filePath = sysConfig.getUploadDir();
            String dateTime = DateUtils.dateTime();
            File dir = new File(filePath + File.separator + DateUtils.dateTime());
            if (!dir.exists()) {
                dir.mkdirs();
            }
            String fileName = file.getOriginalFilename();
            String extension = FileUtils.getExtension(fileName, file.getContentType());
            String newFileName = UUID.randomUUID().toString() + "." + extension;
            String destFile = dir.getAbsolutePath() + File.separator + newFileName;
            file.transferTo(new File(destFile));

            Map<String, String> data = new HashMap<>();
            data.put("originalFilename", fileName); //源文件名称
            data.put("savePath", dateTime + File.separator + newFileName);//最新保存的文件相对地址
            return setResult(data);
        } catch (Exception e) {
            LOGGER.error("", e);
            return setResultError("文件上传失败");
        }
    }


}

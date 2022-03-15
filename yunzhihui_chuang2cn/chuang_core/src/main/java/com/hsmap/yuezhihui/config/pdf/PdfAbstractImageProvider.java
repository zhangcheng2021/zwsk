package com.hsmap.yuezhihui.config.pdf;

import com.itextpdf.text.Image;
import com.itextpdf.tool.xml.pipeline.html.AbstractImageProvider;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class PdfAbstractImageProvider extends AbstractImageProvider {
    private final Logger logger = LoggerFactory.getLogger(PdfConfig.class);

    @Autowired
    private PdfConfig pdfConfig;

    @Override
    public String getImageRootPath() {
        return pdfConfig.getImageRoot();
    }

    @Override
    public Image retrieve(String src) {
        if (StringUtils.isEmpty(src)) {
            return null;
        }
        try {
            //获取png
            Image image = Image.getInstance(new File(pdfConfig.getImageRoot(), src).toURI().toString());
            /**
             * 图片显示位置
             */
            image.setAbsolutePosition(400, 400);
            if (image != null) {
                store(src, image);
                return image;
            }
        } catch (Exception e) {
            logger.error("", e);
        }
        return super.retrieve(src);
    }
}

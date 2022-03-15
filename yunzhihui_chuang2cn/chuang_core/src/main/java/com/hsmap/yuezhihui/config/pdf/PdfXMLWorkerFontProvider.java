package com.hsmap.yuezhihui.config.pdf;

import com.hsmap.yuezhihui.config.pdf.PdfConfig;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.tool.xml.XMLWorkerFontProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PdfXMLWorkerFontProvider extends XMLWorkerFontProvider {
    private final Logger logger = LoggerFactory.getLogger(PdfXMLWorkerFontProvider.class);

    @Autowired
    private PdfConfig pdfConfig;

    @Override
    public Font getFont(String fontname, String encoding, float size, final int style) {
        Font font = null;
        try {
            //仿宋 STFANGSO.TTF
            font = new Font(BaseFont.createFont(pdfConfig.getFontRoot() + "STFANGSO.TTF", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED));
        } catch (Exception e) {
            logger.error("", e);
        }
        return font;
    }
}

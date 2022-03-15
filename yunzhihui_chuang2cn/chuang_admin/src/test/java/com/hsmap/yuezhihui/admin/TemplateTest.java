package com.hsmap.yuezhihui.admin;

import com.hsmap.yuezhihui.config.pdf.PdfConfig;
import com.hsmap.yuezhihui.config.TemplateConfig;
import com.itextpdf.text.PageSize;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TemplateTest extends BootTest {

    @Autowired
    private TemplateConfig templateConfig;

    @Autowired
    private PdfConfig pdfConfig;

    @Test
    public void printHtml() {


        String outputFile = "D:\\temp\\cgj\\tmp5.pdf";
//        Context context = new Context();
//        context.setVariable("name", "张三444");
//        context.setVariable("title", "张山的哦i拿高分333");
//        boolean b = templateConfig.process("pdfs/pdf_score", context, "D:\\temp\\cgj\\s1.html");
//        System.out.println("result:" + b);
        byte[] htmlByte = null;
        try {
            htmlByte = FileCopyUtils.copyToByteArray(new File("D:\\temp\\cgj\\s1.html"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            pdfConfig.generate(outputFile, PageSize.A4, "", true, null, htmlByte);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}

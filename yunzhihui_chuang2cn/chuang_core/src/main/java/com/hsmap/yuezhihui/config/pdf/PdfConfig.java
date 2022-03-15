package com.hsmap.yuezhihui.config.pdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.Pipeline;
import com.itextpdf.tool.xml.XMLWorker;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.itextpdf.tool.xml.html.CssAppliersImpl;
import com.itextpdf.tool.xml.html.Tags;
import com.itextpdf.tool.xml.net.FileRetrieve;
import com.itextpdf.tool.xml.net.ReadingProcessor;
import com.itextpdf.tool.xml.parser.XMLParser;
import com.itextpdf.tool.xml.pipeline.css.CSSResolver;
import com.itextpdf.tool.xml.pipeline.css.CssResolverPipeline;
import com.itextpdf.tool.xml.pipeline.end.PdfWriterPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipelineContext;
import com.itextpdf.tool.xml.pipeline.html.ImageProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.charset.Charset;

@Component
public class PdfConfig {
    private final Logger logger = LoggerFactory.getLogger(PdfConfig.class);

    @Autowired
    private PdfXMLWorkerFontProvider pdfXMLWorkerFontProvider;
    @Autowired
    private PdfAbstractImageProvider pdfAbstractImageProvider;

    @Value("${pdf.imageRoot}")
    private String imageRoot;
    @Value("${pdf.fontRoot}")
    private String fontRoot;
    @Value("${pdf.zipRoot}")
    private String zipRoot;

    public String getZipRoot() {
        return zipRoot;
    }

    public String getImageRoot() {
        return imageRoot;
    }

    public String getFontRoot() {
        return fontRoot;
    }

//    public void setZipRoot(String zipRoot) {
//        zipRoot = zipRoot;
//    }

    public void setImageRoot(String imageRoot) {
        imageRoot = imageRoot;
    }

    public void setFontRoot(String fontRoot) {
        fontRoot = fontRoot;
    }

    public void generate(String targetPdf, Rectangle pageSize, String header, boolean isFooter, File watermark, byte[] htmlBytes)
            throws Exception {

        final String charsetName = "UTF-8";
        Document document = new Document(pageSize);

        OutputStream out = new FileOutputStream(targetPdf);
        /**
         * 设置边距
         */
        // document.setMargins(30, 30, 30, 30);
        PdfWriter writer = PdfWriter.getInstance(document, out);
        /**
         * 添加页码
         */
        //PdfBuilder builder = new PdfBuilder(header, 10, pageSize, watermark, isFooter);
        PdfBuilder builder = new PdfBuilder(header, 10, pageSize, watermark, isFooter, fontRoot);
        writer.setPageEvent(builder);

        document.open();

        /**
         * html内容解析
         */
        HtmlPipelineContext htmlContext =
                new HtmlPipelineContext(new CssAppliersImpl(pdfXMLWorkerFontProvider)) {
                    @Override
                    public HtmlPipelineContext clone() throws CloneNotSupportedException {
                        HtmlPipelineContext context = super.clone();
                        ImageProvider imageProvider = this.getImageProvider();
                        context.setImageProvider(imageProvider);
                        return context;
                    }
                };

        /**
         * 图片解析
         */
        htmlContext.setImageProvider(pdfAbstractImageProvider);
        htmlContext.setAcceptUnknown(true).autoBookmark(true).setTagFactory(Tags.getHtmlTagProcessorFactory());

        /**
         * css解析
         */
        CSSResolver cssResolver = XMLWorkerHelper.getInstance().getDefaultCssResolver(true);
        cssResolver.setFileRetrieve(new FileRetrieve() {
            @Override
            public void processFromStream(InputStream in, ReadingProcessor processor) throws IOException {
                try (InputStreamReader reader = new InputStreamReader(in, charsetName)) {
                    int i = -1;
                    while (-1 != (i = reader.read())) {
                        processor.process(i);
                    }
                } catch (Exception e) {
                    logger.error("", e);
                }
            }

            /**
             * 解析href
             */
            @Override
            public void processFromHref(String href, ReadingProcessor processor) throws IOException {
                InputStream is = new ByteArrayInputStream(href.getBytes());
                try {
                    InputStreamReader reader = new InputStreamReader(is, charsetName);
                    int i = -1;
                    while (-1 != (i = reader.read())) {
                        processor.process(i);
                    }
                } catch (Exception e) {
                    logger.error("", e);
                }

            }
        });

        HtmlPipeline htmlPipeline = new HtmlPipeline(htmlContext, new PdfWriterPipeline(document, writer));
        Pipeline<?> pipeline = new CssResolverPipeline(cssResolver, htmlPipeline);
        XMLWorker worker = null;
        worker = new XMLWorker(pipeline, true);
        XMLParser parser = new XMLParser(true, worker, Charset.forName(charsetName));
        try (InputStream inputStream = new ByteArrayInputStream(htmlBytes)) {
            parser.parse(inputStream, Charset.forName(charsetName));
        }
        document.close();
    }
}

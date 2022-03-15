package com.hsmap.yuezhihui.utils.customize;


import com.hsmap.yuezhihui.entity.review.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Component(value = "excelExport")
public class ExcelExport {
    private final Logger logger = LoggerFactory.getLogger(ExcelExport.class);


    /**
     * 导出组-项目-专家的Excel
     * 序号	答辩人	项目名称	人才类别	推荐地	(评委*)	得分	建议等次
     *
     * @param savePath
     * @param gpsBean
     */
    public void exportGps(String savePath, ExportGpsBean gpsBean) {
        List<ExportGpsBean.Spec> specs = gpsBean.getSpecFirst();
        int specLen = specs.size();
        String[] column0 = new String[]{"序号", "答辩人", "项目名称", "人才类别", "推荐地"};
        int c = 0;
        String[] column1 = new String[specLen * 2];
        for (ExportGpsBean.Spec s : specs) {
            //放两个
            column1[c++] = s.getSpecName();
            column1[c++] = s.getSpecName();
        }
        String[] column2 = new String[]{"得分", "建议等次"};
        String[] title = new String[column0.length + column1.length + column2.length];
        System.arraycopy(column0, 0, title, 0, column0.length);
        System.arraycopy(column1, 0, title, column0.length, column1.length);
        System.arraycopy(column2, 0, title, column0.length + column1.length, column2.length);

        XSSFWorkbook wb = new XSSFWorkbook();

        XSSFCellStyle titleStyle = getStyle(wb);
        titleStyle.getFont().setBold(true);
        titleStyle.setAlignment(HorizontalAlignment.CENTER);
        titleStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        XSSFCellStyle columnStyle = getStyle(wb);

        XSSFSheet sheet = wb.createSheet("数据");
        XSSFRow row;
        XSSFCell cell;
        int len = title.length;
        int currentRowNum = 0;
        //标题
        XSSFCellStyle headerStyle = getStyle(wb);
        headerStyle.getFont().setFontHeightInPoints((short) 14);
        headerStyle.setAlignment(HorizontalAlignment.CENTER);
        headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        row = sheet.createRow(currentRowNum);
        for (int i = 0; i < len; i++) {
            cell = row.createCell(i);
            cell.setCellStyle(headerStyle);
            sheet.setColumnWidth(i, 10 * 274);
        }
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, len - 1));
        cell = row.getCell(0);
        cell.setCellValue(new XSSFRichTextString(gpsBean.getGroupName()));
        currentRowNum++;
        //表头 1
        row = sheet.createRow(currentRowNum);
        currentRowNum++;
        XSSFRow row2 = sheet.createRow(currentRowNum);
        currentRowNum++;
        XSSFCell cell2 = null;
        int n = 0;
        for (int i = 0; i < len; i++) {
            //前5列合并Row和后两列,占2行
            cell = row.createCell(i);
            cell.setCellStyle(titleStyle);
            cell2 = row2.createCell(i);
            cell2.setCellStyle(titleStyle);
            if (i < 5 || i > 4 + specLen * 2) {
                cell.setCellValue(new XSSFRichTextString(title[i]));
                sheet.addMergedRegion(new CellRangeAddress(1, 2, i, i));
            } else {
                if (n % 2 == 1) {
                    cell = row.createCell(i);
                    row.getCell(i - 1).setCellValue(new XSSFRichTextString(title[i]));
                    //合并
                    sheet.addMergedRegion(new CellRangeAddress(1, 1, i - 1, i));
                    row2.getCell(i - 1).setCellValue(new XSSFRichTextString("评分"));
                    //还没创建
                    cell2 = row2.createCell(i);
                    cell2.setCellStyle(titleStyle);
                    cell2.setCellValue(new XSSFRichTextString("评语"));
                } else {
                    cell = row.createCell(i);
                    cell2 = row2.createCell(i);
                    cell.setCellStyle(titleStyle);
                    cell2.setCellStyle(titleStyle);
                }
                n++;
            }
        }
        //数据
        int j = 1, r = 0;
        for (ExportGpsBean.Product ep : gpsBean.getProducts()) {
            row = sheet.createRow(currentRowNum);
            r = 0;
            //序号
            cell = row.createCell(r++);
            cell.setCellStyle(columnStyle);
            cell.setCellValue(j++);
            //答辩人
            cell = row.createCell(r++);
            cell.setCellStyle(columnStyle);
            cell.setCellValue(new XSSFRichTextString(ep.getUserName()));
            //项目名称
            cell = row.createCell(r++);
            cell.setCellStyle(columnStyle);
            cell.setCellValue(new XSSFRichTextString(ep.getProductName()));
            //人才类别
            cell = row.createCell(r++);
            cell.setCellStyle(columnStyle);
            cell.setCellValue(new XSSFRichTextString(ep.getTypeName()));
            //推荐地
            cell = row.createCell(r++);
            cell.setCellStyle(columnStyle);
            cell.setCellValue(new XSSFRichTextString(ep.getRecommend()));
            //专家
            for (ExportGpsBean.Spec s : ep.getSpecs()) {
                cell = row.createCell(r++);
                cell.setCellStyle(columnStyle);
                if (s.getScore() != null) {
                    cell.setCellValue(s.getScore());
                } else {
                    cell.setCellValue(new XSSFRichTextString("-"));
                }
                cell = row.createCell(r++);
                cell.setCellStyle(columnStyle);
                cell.setCellValue(new XSSFRichTextString((s.getSpecRemark() == null || s.getSpecRemark().trim().length() == 0) ? "-" : s.getSpecRemark()));
            }
            //人才类别
            cell = row.createCell(r++);
            cell.setCellStyle(columnStyle);
            cell.setCellValue(new XSSFRichTextString(ep.getScore()));
            //推荐地
            cell = row.createCell(r++);
            cell.setCellStyle(columnStyle);
            cell.setCellValue(new XSSFRichTextString(ep.getLevel()));

            currentRowNum++;
        }
        writeFile(wb, savePath);
    }


    /**
     * 导出查询评审的结果
     * 专业组别	参评总数	入选数	A类	B类	C类	未入选数
     *
     * @param savePath
     * @param resultBean
     */
    public void exportReviewResult(String savePath, ExportReviewResultBean resultBean) {
        String[] column = new String[]{"专业组别", "参评总数", "入选数", "A类", "B类", "C类", "未入选数"};
        XSSFWorkbook wb = new XSSFWorkbook();

        XSSFCellStyle titleStyle = getStyle(wb);
        titleStyle.getFont().setBold(true);
        XSSFCellStyle columnStyle = getStyle(wb);

        XSSFSheet sheet = wb.createSheet("数据");
        XSSFRow row;
        XSSFCell cell;
        int len = column.length;
        int currentRowNum = 0;
        //标题
        XSSFCellStyle headerStyle = getStyle(wb);
        headerStyle.getFont().setFontHeightInPoints((short) 12);
        headerStyle.setAlignment(HorizontalAlignment.CENTER);
        headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        row = sheet.createRow(currentRowNum);
        for (int i = 0; i < len; i++) {
            cell = row.createCell(i);
            cell.setCellStyle(headerStyle);
            sheet.setColumnWidth(i, (i > 0 ? 10 : 20) * 274);//第一列设置大点
        }
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, len - 1));
        cell = row.getCell(0);
        cell.setCellValue(new XSSFRichTextString(resultBean.getBatchName()));
        currentRowNum++;
        //表头
        row = sheet.createRow(currentRowNum);
        currentRowNum++;
        for (int i = 0; i < len; i++) {
            cell = row.createCell(i);
            cell.setCellValue(new XSSFRichTextString(column[i]));
            cell.setCellStyle(titleStyle);
        }

        if (resultBean.getResult() == null || resultBean.getResult().size() == 0) {
            writeFile(wb, savePath);
            return;
        }
        //数据
        int r = 0;
        for (ComplexReviewResultDo rr : resultBean.getResult()) {
            row = sheet.createRow(currentRowNum);
            r = 0;
            //专业组别
            cell = row.createCell(r++);
            cell.setCellStyle(columnStyle);
            cell.setCellValue(new XSSFRichTextString(rr.getGroupName()));
            //参评总数
            cell = row.createCell(r++);
            cell.setCellStyle(columnStyle);
            cell.setCellValue(rr.getCountAll());
            //入选数
            cell = row.createCell(r++);
            cell.setCellStyle(columnStyle);
            cell.setCellValue(rr.getCountIn());
            //A类
            cell = row.createCell(r++);
            cell.setCellStyle(columnStyle);
            cell.setCellValue(rr.getCountA());
            //B类
            cell = row.createCell(r++);
            cell.setCellStyle(columnStyle);
            cell.setCellValue(rr.getCountB());
            //C类
            cell = row.createCell(r++);
            cell.setCellStyle(columnStyle);
            cell.setCellValue(rr.getCountC());
            //未入选数
            cell = row.createCell(r++);
            cell.setCellStyle(columnStyle);
            cell.setCellValue(rr.getCountNo());

            currentRowNum++;
        }
        r = 0;
        row = sheet.createRow(currentRowNum);
        //合计部分
        //专业组别
        cell = row.createCell(r++);
        cell.setCellStyle(columnStyle);
        cell.setCellValue(new XSSFRichTextString("合计"));
        //参评总数
        cell = row.createCell(r++);
        cell.setCellStyle(columnStyle);
        cell.setCellValue(resultBean.getStat().getRowAll());
        //入选数
        cell = row.createCell(r++);
        cell.setCellStyle(columnStyle);
        cell.setCellValue(new XSSFRichTextString(resultBean.getStat().getRowIn()));
        //A类
        cell = row.createCell(r++);
        cell.setCellStyle(columnStyle);
        cell.setCellValue(new XSSFRichTextString(resultBean.getStat().getRowA()));
        //B类
        cell = row.createCell(r++);
        cell.setCellStyle(columnStyle);
        cell.setCellValue(new XSSFRichTextString(resultBean.getStat().getRowB()));
        //C类
        cell = row.createCell(r++);
        cell.setCellStyle(columnStyle);
        cell.setCellValue(new XSSFRichTextString(resultBean.getStat().getRowC()));
        //未入选数
        cell = row.createCell(r++);
        cell.setCellStyle(columnStyle);
        cell.setCellValue(new XSSFRichTextString(resultBean.getStat().getRowNo()));

        writeFile(wb, savePath);
    }

    /**
     * 导出查询评审的结果
     * 专业组别	参评总数	入选数	A类	B类	C类	未入选数
     * 推荐地	申报总数	初审通过数	复审通过数	实际参评数	现场评审入选数	A类	B类	C类
     *
     * @param savePath
     * @param resultBean
     */
    public void exportRecommend(String savePath, ExportRecommendBean resultBean) {
        String[] column = new String[]{"推荐地", "申报总数", "初审通过数", "复审通过数", "实际参评数", "现场评审入选数", "A类", "B类", "C类"};
        XSSFWorkbook wb = new XSSFWorkbook();

        XSSFCellStyle titleStyle = getStyle(wb);
        titleStyle.getFont().setBold(true);
        XSSFCellStyle columnStyle = getStyle(wb);

        XSSFSheet sheet = wb.createSheet("数据");
        XSSFRow row;
        XSSFCell cell;
        int len = column.length;
        int currentRowNum = 0;
        //标题
        XSSFCellStyle headerStyle = getStyle(wb);
        headerStyle.getFont().setFontHeightInPoints((short) 12);
        headerStyle.setAlignment(HorizontalAlignment.CENTER);
        headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        row = sheet.createRow(currentRowNum);
        for (int i = 0; i < len; i++) {
            cell = row.createCell(i);
            cell.setCellStyle(headerStyle);
            sheet.setColumnWidth(i, (i > 0 ? 10 : 15) * 274);//第一列设置大点
        }
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, len - 1));
        cell = row.getCell(0);
        cell.setCellValue(new XSSFRichTextString(resultBean.getRecommendName()));
        currentRowNum++;
        //表头
        row = sheet.createRow(currentRowNum);
        currentRowNum++;
        for (int i = 0; i < len; i++) {
            cell = row.createCell(i);
            cell.setCellValue(new XSSFRichTextString(column[i]));
            cell.setCellStyle(titleStyle);
        }

        if (resultBean.getResult() == null || resultBean.getResult().size() == 0) {
            writeFile(wb, savePath);
            return;
        }
        //数据
        int r = 0;
        for (ComplexRecommendDo rr : resultBean.getResult()) {
            row = sheet.createRow(currentRowNum);
            r = 0;
            //推荐地	申报总数	初审通过数	复审通过数	实际参评数	现场评审入选数	A类	B类	C类
            //推荐地
            cell = row.createCell(r++);
            cell.setCellStyle(columnStyle);
            cell.setCellValue(new XSSFRichTextString(rr.getRecommendName()));
            //申报总数
            cell = row.createCell(r++);
            cell.setCellStyle(columnStyle);
            cell.setCellValue(rr.getCountAll());
            //初审通过数
            cell = row.createCell(r++);
            cell.setCellStyle(columnStyle);
            cell.setCellValue(0);
            //复审通过数
            cell = row.createCell(r++);
            cell.setCellStyle(columnStyle);
            cell.setCellValue(0);
            //实际参评数
            cell = row.createCell(r++);
            cell.setCellStyle(columnStyle);
            cell.setCellValue(0);
            //现场评审入选数
            cell = row.createCell(r++);
            cell.setCellStyle(columnStyle);
            cell.setCellValue(rr.getCountIn());
            //A类
            cell = row.createCell(r++);
            cell.setCellStyle(columnStyle);
            cell.setCellValue(rr.getCountA());
            //B类
            cell = row.createCell(r++);
            cell.setCellStyle(columnStyle);
            cell.setCellValue(rr.getCountB());
            //C类
            cell = row.createCell(r++);
            cell.setCellStyle(columnStyle);
            cell.setCellValue(rr.getCountC());

            currentRowNum++;
        }
        r = 0;
        row = sheet.createRow(currentRowNum);
        //合计部分
        //专业组别
        cell = row.createCell(r++);
        cell.setCellStyle(columnStyle);
        cell.setCellValue(new XSSFRichTextString("合计"));
        //参评总数
        cell = row.createCell(r++);
        cell.setCellStyle(columnStyle);
        cell.setCellValue(resultBean.getStat().getRowAll());
        //初审通过数
        cell = row.createCell(r++);
        cell.setCellStyle(columnStyle);
        cell.setCellValue(0);
        //复审通过数
        cell = row.createCell(r++);
        cell.setCellStyle(columnStyle);
        cell.setCellValue(0);
        //实际参评数
        cell = row.createCell(r++);
        cell.setCellStyle(columnStyle);
        cell.setCellValue(0);
        //入选数
        cell = row.createCell(r++);
        cell.setCellStyle(columnStyle);
        cell.setCellValue(new XSSFRichTextString(resultBean.getStat().getRowIn()));
        //A类
        cell = row.createCell(r++);
        cell.setCellStyle(columnStyle);
        cell.setCellValue(new XSSFRichTextString(resultBean.getStat().getRowA()));
        //B类
        cell = row.createCell(r++);
        cell.setCellStyle(columnStyle);
        cell.setCellValue(new XSSFRichTextString(resultBean.getStat().getRowB()));
        //C类
        cell = row.createCell(r++);
        cell.setCellStyle(columnStyle);
        cell.setCellValue(new XSSFRichTextString(resultBean.getStat().getRowC()));
        writeFile(wb, savePath);
    }

    private XSSFCellStyle getStyle(XSSFWorkbook wb) {
        XSSFFont font = wb.createFont();
        font.setFontHeightInPoints((short) 10);
        font.setFontName("宋体");
        XSSFCellStyle style = wb.createCellStyle();
        style.setFont(font);
        return style;
    }

    /**
     * 写入文件
     *
     * @param wb
     * @param savePath
     */
    private void writeFile(XSSFWorkbook wb, String savePath) {
        FileOutputStream fileOut = null;
        try {
            fileOut = new FileOutputStream(savePath);
            wb.write(fileOut);
        } catch (IOException e) {
            logger.error("", e);
        } finally {
            if (fileOut != null) {
                try {
                    fileOut.close();
                } catch (IOException e) {
                    logger.error("", e);
                }
            }
        }
    }
}

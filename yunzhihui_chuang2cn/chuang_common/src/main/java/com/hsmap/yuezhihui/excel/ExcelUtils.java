package com.hsmap.yuezhihui.excel;

import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.hsmap.yuezhihui.common.CommonUtil;
import com.hsmap.yuezhihui.excel.rowmodel.UserInfoModel;
import org.apache.poi.poifs.filesystem.FileMagic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletOutputStream;
import java.io.*;
import java.util.*;

public class ExcelUtils {

    public static final Logger LOGGER = LoggerFactory.getLogger(ExcelUtils.class);

    public static void main(String[] args) throws FileNotFoundException {

        ExcelUtils excelUtils =new ExcelUtils();
        excelUtils.testReadExcel();
    }

    public void testReadExcel() {
        long startTime = new Date().getTime();
        String fileName = "/Users/tangjixiong/Downloads/111.xlsx";
//        Double.parseDouble("43.0");
        List<UserInfoModel> list = readExcel(fileName, UserInfoModel.class);
        for (UserInfoModel model:list) {
            LOGGER.info("name:{},mobile:{},logincount:{},createtime:{}",model.getName(),model.getMobile(),model.getLoginCount(),model.getCreateTime());
        }

        long endTime = new Date().getTime();
        LOGGER.info("----------> time:"+(endTime-startTime));
    }


    /***
     * 测试写
     */
    public void testWriteExcel(ServletOutputStream out) {
        List<UserInfoModel> list = new ArrayList<>();
        Date startDate = new Date();
        for (int i = 0; i < 100000; i++) {

            UserInfoModel model1 = new UserInfoModel();
            model1.setName("张三" + i);
            model1.setMobile("15010" + CommonUtil.getRandomByCount(6));
            model1.setLoginCount(Double.parseDouble(CommonUtil.getRandomByCount(3)));
            model1.setCreateTime(new Date());
            list.add(model1);
        }
//        ExcelUtils
        ExcelUtils excelUtils = new ExcelUtils();
        String fileName = "/Users/tangjixiong/Downloads/111.xlsx";
        Map columnWidth = new HashMap();
        columnWidth.put(0, 3000);
        columnWidth.put(1, 5000);
        columnWidth.put(2, 3000);
        columnWidth.put(3, 5000);

        ExcelHelper helper = new ExcelHelper(fileName, list, UserInfoModel.class, columnWidth, false);
        excelUtils.writeExcel(helper,out);
        Date endDate = new Date();
        LOGGER.info("time:" + (endDate.getTime() - startDate.getTime()));
    }

//    public void redis

    public void writeExcel(ExcelHelper helper, ServletOutputStream out) {
//        OutputStream out = null;
        try {
//            out = new FileOutputStream(helper.getFileName());

            ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLSX);
            //写第一个sheet, sheet1  数据全是List<String> 无模型映射关系
            Sheet sheet1 = new Sheet(1, 0, helper.getClazz());
            if (helper.isAutoWidth()) {
                sheet1.setAutoWidth(helper.isAutoWidth());
            } else {
                sheet1.setColumnWidthMap(helper.getColumnWidth());
            }

            writer.write(helper.getList(), sheet1);
            writer.finish();
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 从Excel中读取文件，读取的文件是一个DTO类，该类必须继承BaseRowModel
     * 具体实例参考 ： MemberMarketDto.java
     * 参考：https://github.com/alibaba/easyexcel
     * 字符流必须支持标记，FileInputStream 不支持标记，可以使用BufferedInputStream 代替
     * BufferedInputStream bis = new BufferedInputStream(new FileInputStream(...));
     */
    public static <T extends BaseRowModel> List<T> readExcel(final String fileName, final Class<? extends BaseRowModel> clazz) {
        BufferedInputStream inputStream = null;
        try {
            inputStream = new BufferedInputStream(new FileInputStream(fileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (null == inputStream) {
            throw new NullPointerException("the inputStream is null!");
        }

        ExcelListener<T> listener = new ExcelListener<>();
        // 这里因为EasyExcel-1.1.1版本的bug，所以需要选用下面这个标记已经过期的版本

        ExcelTypeEnum type = ExcelTypeEnum.valueOf(inputStream);
        if(type == null){
            throw new NullPointerException("文件魔数非Excel文件");
        }
        ExcelReader reader = new ExcelReader(inputStream, type, null, listener);
        reader.read(new com.alibaba.excel.metadata.Sheet(1, 1, clazz));

        return listener.getRows();
    }


    /**
     * 根据输入流，判断为xls还是xlsx，该方法原本存在于easyexcel 1.1.0 的ExcelTypeEnum中。
     */
    public static ExcelTypeEnum valueOf(InputStream inputStream) {
        try {
            FileMagic fileMagic = FileMagic.valueOf(inputStream);
            if (FileMagic.OLE2.equals(fileMagic)) {
                return ExcelTypeEnum.XLS;
            }
            if (FileMagic.OOXML.equals(fileMagic)) {
                return ExcelTypeEnum.XLSX;
            }
            throw new IllegalArgumentException("excelTypeEnum can not null");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

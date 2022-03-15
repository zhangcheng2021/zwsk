package com.hsmap.yuezhihui.admin;


import com.hsmap.yuezhihui.entity.product.ProductInfoExcelDto;
import com.hsmap.yuezhihui.excel.ExcelUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.util.List;

/**
 * 导入测试
 */
public class ExcelReadTest {
    public static void main(String[] args) {
        String path = "D:\\temp\\cgj\\template\\XMSHLB.xls";
        String path2 = "D:\\temp\\cgj\\template\\XMSHLB.xlsx";
       /* List<ProductInfoExcelDto> dtos = ExcelUtils.readExcel(path, ProductInfoExcelDto.class);
        System.out.println("path1---------------------------------");
        for (ProductInfoExcelDto p : dtos) {
            System.out.println(p);
        }

        List<ProductInfoExcelDto> dtos2 = ExcelUtils.readExcel(path2, ProductInfoExcelDto.class);
        System.out.println("path2---------------------------------");
        for (ProductInfoExcelDto p : dtos2) {
            System.out.println(p);
        }*/

      /* String a =",adfdggs";
        System.out.println(a.substring(0,a.indexOf(",")));
        String b="a,dfdgs";
        System.out.println(b.substring(0,b.indexOf(",")));
        String c="浙江怡辉生物科技有限公司首席科学家";
        System.out.println(c.substring(0,c.indexOf("公司")+2));*/

//      int x = (int)Math.round(7.4);
//        System.out.println(x);

        final DecimalFormat df0 = new DecimalFormat("0.00");
        System.out.println(df0.format(1034.98));
        System.out.println(df0.format(4.9));
        System.out.println(df0.format(10.982));
        System.out.println(df0.format(0.983));
        System.out.println(df0.format(10));
    }
}


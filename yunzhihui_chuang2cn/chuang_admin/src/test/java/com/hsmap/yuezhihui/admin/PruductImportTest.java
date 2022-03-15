package com.hsmap.yuezhihui.admin;

import com.hsmap.yuezhihui.admin.controller.product.ProductController;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class PruductImportTest extends BootTest {

    @Autowired
    private ProductController productController;

    @Test
    public void imports() {
        System.out.println("PruductImportTest.imports");
        //productController.parseXls(3, "junit-err10", "template\\XMSHLB-err10.xlsx");
    }
}

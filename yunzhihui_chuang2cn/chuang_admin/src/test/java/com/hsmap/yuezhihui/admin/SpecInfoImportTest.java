package com.hsmap.yuezhihui.admin;

import com.hsmap.yuezhihui.admin.controller.product.ProductController;
import com.hsmap.yuezhihui.admin.controller.spec.SpecController;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class SpecInfoImportTest extends BootTest {

    @Autowired
    private SpecController specController;

    @Test
    public void imports() {
        System.out.println("SpecInfoImportTest.imports");
//        specController.parseXls("junit-spec1", "专家管理模版.xlsx");
    }
}

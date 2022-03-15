package com.hsmap.yuezhihui.admin;

import com.hsmap.yuezhihui.entity.product.ProductInfo;
import com.hsmap.yuezhihui.service.product.IProductInfoService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Random;

public class AutoDataTest extends BootTest {
    @Autowired
    private IProductInfoService productInfoService;

    @Test
    public void inserts() {
        System.out.println("AutoDataTest.inserts");
        /*int i = 1000, count = i + 1000;
        ProductInfo info;
        Random random = new Random();
        for (; i < count; i++) {
            info = new ProductInfo();
            info.setProductName("申报项目：" + i);
            info.setWork("申报单位：" + i);
            info.setUserName("姓名：" + i);
            info.setSex((random.nextInt(100) % 2));
            info.setMobile("130" + i);
            info.setEmail("130@" + i + ".com");
            info.setTypeId((random.nextInt(100) % 6) + 1);//1-6
            info.setTerritoryId((random.nextInt(100) % 8) + 1);//1-8
            info.setMajorField("方向：" + i);
            info.setBatchId((random.nextInt(100) % 2) + 2);//2,3
            info.setRemark("备注：" + i);
            productInfoService.save(info);
        }*/
    }
}

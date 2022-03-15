package com.hsmap.yuezhihui.admin;

import com.hsmap.yuezhihui.entity.product.ProductInfo;
import com.hsmap.yuezhihui.entity.spec.SpecInfo;
import com.hsmap.yuezhihui.service.product.IProductInfoService;
import com.hsmap.yuezhihui.service.spec.ISpecInfoService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Random;

public class SpecDataTest extends BootTest {
    @Autowired
    ISpecInfoService specInfoService;

    @Test
    public void inserts() {
        System.out.println("SpecDataTest.inserts");
        int i = 1000, count = i + 1000;
        SpecInfo info;
        Random random = new Random();
        for (; i < count; i++) {
//            info = new SpecInfo();
//            info.setUserName("专家姓名：" + i);
//            info.setSex((random.nextInt(100) % 2));
//            info.setAge("年龄：" + i);
//            info.setCompany("申报单位：" + i);
//            info.setRank("职务：" + i);
//            info.setTitleId((random.nextInt(100) % 4)+1);
////            info.setMobile(Phone.getMobile());
//            info.setSpecTypeId((random.nextInt(100) % 4) + 1);//1-6
//            info.setTerritoryId((random.nextInt(100) % 8) + 1);//1-8
//            info.setSubdivision("细分领域：" + i);
//            info.setLevel((random.nextInt(100) % 5) + 1);//2,3
//            info.setCardName("持卡人姓名："+i);
//            info.setIdentityNo("身份证号码："+i);
//            info.setCardNo("银行卡号："+i);
//            info.setBankName("开户行："+i);
//            info.setRemark("备注：" + i);
//            specInfoService.save(info);
        }
    }
}

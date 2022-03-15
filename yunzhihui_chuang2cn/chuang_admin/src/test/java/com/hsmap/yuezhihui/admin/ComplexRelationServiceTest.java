package com.hsmap.yuezhihui.admin;

import com.hsmap.yuezhihui.base.page.PageInfo;
import com.hsmap.yuezhihui.base.page.Pageable;
import com.hsmap.yuezhihui.entity.review.ComplexRelationProductDo;
import com.hsmap.yuezhihui.entity.review.ComplexRelationSpecDo;
import com.hsmap.yuezhihui.service.review.IComplexRelationService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ComplexRelationServiceTest extends BootTest {
    @Autowired
    private IComplexRelationService complexRelationService;

    @Test
    public void queryProduct() {
        ComplexRelationProductDo query = new ComplexRelationProductDo();
        query.setQueryGroupId(104);
        query.setQueryTerritoryId(1);
        query.setQueryUserName("英");
        query.setOrderByClause(" pi.id desc ");
        query.setPageNumber(1);
        query.setPageSize(10);
        List<ComplexRelationProductDo> list = complexRelationService.reviewProductInfoByGroupId(query);
        Assert.assertNotNull(list);
        System.out.println(list.size());
    }

    @Test
    public void querySpec() {
        ComplexRelationSpecDo query = new ComplexRelationSpecDo();
        query.setQueryGroupId(104);
        List<ComplexRelationSpecDo> list = complexRelationService.reviewSpecInfoByGroupId(query);
        Assert.assertNotNull(list);
        System.out.println(list.size());
    }

    @Test
    public void queryHisProductOfPage() {
        Pageable pageable = new Pageable(1, 10);
        ComplexRelationProductDo query = new ComplexRelationProductDo();
        query.setQueryGroupId(104);
        query.setQueryTerritoryId(1);
        query.setOrderByClause(" pi.id desc ");
        query.setPageNumber(1);
        query.setPageSize(10);
        query.setQueryProductReViewStatus(2);
        PageInfo<ComplexRelationProductDo> pageInfo = complexRelationService.reviewHisProductInfoOfPage(query,pageable);
        Assert.assertNotNull(pageInfo);
        System.out.println(pageInfo.getTotalElements());
        System.out.println(pageInfo.getTotalPages());
        System.out.println(pageInfo.getContent());
    }
}

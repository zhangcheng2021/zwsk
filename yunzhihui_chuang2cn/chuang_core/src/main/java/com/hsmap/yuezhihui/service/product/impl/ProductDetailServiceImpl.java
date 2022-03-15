package com.hsmap.yuezhihui.service.product.impl;

import com.hsmap.yuezhihui.base.mapper.BaseMapper;
import com.hsmap.yuezhihui.base.page.PageInfo;
import com.hsmap.yuezhihui.base.page.Pageable;
import com.hsmap.yuezhihui.base.service.BaseServiceImpl;
import com.hsmap.yuezhihui.entity.product.ProductDetail;
import com.hsmap.yuezhihui.entity.product.ProductDetailExample;
import com.hsmap.yuezhihui.mapper.product.ProductDetailMapper;
import com.hsmap.yuezhihui.service.product.IProductDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductDetailServiceImpl extends BaseServiceImpl<ProductDetail,Integer, ProductDetailExample> implements IProductDetailService {

    @Autowired
    ProductDetailMapper ProductDetailMapper;

    @Override
    public BaseMapper<ProductDetail, Integer, ProductDetailExample> getMapper() {
        return ProductDetailMapper;
    }

    @Override
    public PageInfo<ProductDetail> pageList(ProductDetail model, Pageable pageable) {
        ProductDetailExample example  = new ProductDetailExample();
        getBaseExample(example,model);
        example.setPageNumber(pageable.getPageNumber());
        example.setPageSize(pageable.getPageSize());
        return findByPage(example,pageable);
    }

    @Override
    public List<ProductDetail> listByModel(ProductDetail model) {
        ProductDetailExample example  = new ProductDetailExample();
        getBaseExample(example,model);
        return find(example);
    }

    @Override
    public List<ProductDetail> findByAll() {
        ProductDetail model = new ProductDetail();
        return listByModel(model);
    }


    private ProductDetailExample.Criteria getBaseExample(ProductDetailExample example, ProductDetail model) {
        ProductDetailExample.Criteria criteria = example.createCriteria();
        example.setOrderByClause("sort_ desc,id desc");
        criteria.andIsDelEqualTo(0);
        return criteria;
    }
}

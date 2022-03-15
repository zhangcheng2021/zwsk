package com.hsmap.yuezhihui.service.core.impl;

import com.hsmap.yuezhihui.base.mapper.BaseMapper;
import com.hsmap.yuezhihui.base.page.PageInfo;
import com.hsmap.yuezhihui.base.page.Pageable;
import com.hsmap.yuezhihui.base.service.BaseServiceImpl;
import com.hsmap.yuezhihui.common.CommonUtil;
import com.hsmap.yuezhihui.entity.core.CoreTitle;
import com.hsmap.yuezhihui.entity.core.CoreTitleExample;
import com.hsmap.yuezhihui.mapper.core.CoreTitleMapper;
import com.hsmap.yuezhihui.service.core.ICoreTitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CoreTitleServiceImpl extends BaseServiceImpl<CoreTitle,Integer, CoreTitleExample> implements ICoreTitleService {

    @Autowired
    CoreTitleMapper CoreTitleMapper;

    @Override
    public BaseMapper<CoreTitle, Integer, CoreTitleExample> getMapper() {
        return CoreTitleMapper;
    }

    @Override
    public PageInfo<CoreTitle> pageList(CoreTitle model, Pageable pageable) {
        CoreTitleExample example  = new CoreTitleExample();
        getBaseExample(example,model);
        example.setPageNumber(pageable.getPageNumber());
        example.setPageSize(pageable.getPageSize());
        return findByPage(example,pageable);
    }

    @Override
    public List<CoreTitle> listByModel(CoreTitle model) {
        CoreTitleExample example  = new CoreTitleExample();
        getBaseExample(example,model);
        return find(example);
    }

    @Override
    public List<CoreTitle> findByAll() {
        CoreTitle model = new CoreTitle();
        return listByModel(model);
    }


    private CoreTitleExample.Criteria getBaseExample(CoreTitleExample example, CoreTitle model) {
        CoreTitleExample.Criteria criteria = example.createCriteria();
        example.setOrderByClause("sort_ desc,id desc");
        criteria.andIsDelEqualTo(0);
        if(!CommonUtil.isEmpty(model.getName())){
            criteria.andNameLike("%"+model.getName()+"%");
        }

        return criteria;
    }
}

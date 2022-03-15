package com.hsmap.yuezhihui.service.core.impl;

import com.hsmap.yuezhihui.base.mapper.BaseMapper;
import com.hsmap.yuezhihui.base.page.PageInfo;
import com.hsmap.yuezhihui.base.page.Pageable;
import com.hsmap.yuezhihui.base.service.BaseServiceImpl;
import com.hsmap.yuezhihui.common.CommonUtil;
import com.hsmap.yuezhihui.entity.core.CoreProjectType;
import com.hsmap.yuezhihui.entity.core.CoreProjectTypeExample;
import com.hsmap.yuezhihui.mapper.core.CoreProjectTypeMapper;
import com.hsmap.yuezhihui.service.core.ICoreProjectTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;
@Service
public class CoreProjectTypeServiceImpl  extends BaseServiceImpl<CoreProjectType,Integer, CoreProjectTypeExample> implements ICoreProjectTypeService {

    @Autowired
    CoreProjectTypeMapper CoreProjectTypeMapper;

    @Override
    public BaseMapper<CoreProjectType, Integer, CoreProjectTypeExample> getMapper() {
        return CoreProjectTypeMapper;
    }

    @Override
    public PageInfo<CoreProjectType> pageList(CoreProjectType model, Pageable pageable) {
        CoreProjectTypeExample example  = new CoreProjectTypeExample();
        getBaseExample(example,model);
        example.setPageNumber(pageable.getPageNumber());
        example.setPageSize(pageable.getPageSize());
        return findByPage(example,pageable);
    }

    @Override
    public List<CoreProjectType> listByModel(CoreProjectType model) {
        CoreProjectTypeExample example  = new CoreProjectTypeExample();
        getBaseExample(example,model);
        return find(example);
    }

    @Override
    public List<CoreProjectType> findByAll() {
        CoreProjectType model = new CoreProjectType();
        return listByModel(model);
    }


    private CoreProjectTypeExample.Criteria getBaseExample(CoreProjectTypeExample example, CoreProjectType model) {
        CoreProjectTypeExample.Criteria criteria = example.createCriteria();
        example.setOrderByClause("sort_ desc,id desc");
        criteria.andIsDelEqualTo(0);
        if(!CommonUtil.isEmpty(model.getName())){
            criteria.andNameLike("%"+model.getName()+"%");
        }

        return criteria;
    }
}

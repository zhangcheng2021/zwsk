package com.hsmap.yuezhihui.service.core.impl;

import com.hsmap.yuezhihui.base.mapper.BaseMapper;
import com.hsmap.yuezhihui.base.page.PageInfo;
import com.hsmap.yuezhihui.base.page.Pageable;
import com.hsmap.yuezhihui.base.service.BaseServiceImpl;
import com.hsmap.yuezhihui.common.CommonUtil;
import com.hsmap.yuezhihui.entity.core.CoreTerritory;
import com.hsmap.yuezhihui.entity.core.CoreTerritoryExample;
import com.hsmap.yuezhihui.mapper.core.CoreTerritoryMapper;
import com.hsmap.yuezhihui.service.core.ICoreTerritoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CoreTerritoryServiceImpl extends BaseServiceImpl<CoreTerritory,Integer, CoreTerritoryExample> implements ICoreTerritoryService {

    @Autowired
    CoreTerritoryMapper CoreTerritoryMapper;

    @Override
    public BaseMapper<CoreTerritory, Integer, CoreTerritoryExample> getMapper() {
        return CoreTerritoryMapper;
    }

    @Override
    public PageInfo<CoreTerritory> pageList(CoreTerritory model, Pageable pageable) {
        CoreTerritoryExample example  = new CoreTerritoryExample();
        getBaseExample(example,model);
        example.setPageNumber(pageable.getPageNumber());
        example.setPageSize(pageable.getPageSize());
        return findByPage(example,pageable);
    }

    @Override
    public List<CoreTerritory> listByModel(CoreTerritory model) {
        CoreTerritoryExample example  = new CoreTerritoryExample();
        getBaseExample(example,model);
        return find(example);
    }

    @Override
    public List<CoreTerritory> findByAll() {
        CoreTerritory model = new CoreTerritory();
        return listByModel(model);
    }


    private CoreTerritoryExample.Criteria getBaseExample(CoreTerritoryExample example, CoreTerritory model) {
        CoreTerritoryExample.Criteria criteria = example.createCriteria();
        example.setOrderByClause("sort_ desc,id desc");
        criteria.andIsDelEqualTo(0);
        if(!CommonUtil.isEmpty(model.getName())){
            criteria.andNameLike("%"+model.getName()+"%");
        }

        return criteria;
    }
}

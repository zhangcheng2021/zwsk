package com.hsmap.yuezhihui.service.core.impl;

import com.hsmap.yuezhihui.base.mapper.BaseMapper;
import com.hsmap.yuezhihui.base.page.PageInfo;
import com.hsmap.yuezhihui.base.page.Pageable;
import com.hsmap.yuezhihui.base.service.BaseServiceImpl;
import com.hsmap.yuezhihui.common.CommonUtil;
import com.hsmap.yuezhihui.entity.core.CoreTaskInfo;
import com.hsmap.yuezhihui.entity.core.CoreTaskInfoExample;
import com.hsmap.yuezhihui.mapper.core.CoreTaskInfoMapper;
import com.hsmap.yuezhihui.service.core.ICoreTaskInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoreTaskInfoServiceImpl extends BaseServiceImpl<CoreTaskInfo,Integer, CoreTaskInfoExample> implements ICoreTaskInfoService {

    @Autowired
    CoreTaskInfoMapper CoreTaskInfoMapper;

    @Override
    public BaseMapper<CoreTaskInfo, Integer, CoreTaskInfoExample> getMapper() {
        return CoreTaskInfoMapper;
    }

    @Override
    public PageInfo<CoreTaskInfo> pageList(CoreTaskInfo model, Pageable pageable) {
        CoreTaskInfoExample example  = new CoreTaskInfoExample();
        getBaseExample(example,model);
        example.setPageNumber(pageable.getPageNumber());
        example.setPageSize(pageable.getPageSize());
        return findByPage(example,pageable);
    }

    @Override
    public List<CoreTaskInfo> listByModel(CoreTaskInfo model) {
        CoreTaskInfoExample example  = new CoreTaskInfoExample();
        getBaseExample(example,model);
        return find(example);
    }

    @Override
    public List<CoreTaskInfo> findByAll() {
        CoreTaskInfo model = new CoreTaskInfo();
        return listByModel(model);
    }

    @Override
    public List<CoreTaskInfo> findByType(int type) {
        CoreTaskInfo model = new CoreTaskInfo();
        model.setType(type);
        return listByModel(model);
    }


    private CoreTaskInfoExample.Criteria getBaseExample(CoreTaskInfoExample example, CoreTaskInfo model) {
        CoreTaskInfoExample.Criteria criteria = example.createCriteria();
        example.setOrderByClause(" id desc");
        criteria.andIsDelEqualTo(0);
        if(!CommonUtil.isEmpty(model.getName())){
            criteria.andNameLike("%"+model.getName()+"%");
        }
        if(model.getType()!=null && model.getType()>-1){
            criteria.andTypeEqualTo(model.getType());
        }

        return criteria;
    }
}

package com.hsmap.yuezhihui.service.core.impl;

import com.hsmap.yuezhihui.base.mapper.BaseMapper;
import com.hsmap.yuezhihui.base.page.PageInfo;
import com.hsmap.yuezhihui.base.page.Pageable;
import com.hsmap.yuezhihui.base.service.BaseServiceImpl;
import com.hsmap.yuezhihui.common.CommonUtil;
import com.hsmap.yuezhihui.entity.core.CoreTaskDetail;
import com.hsmap.yuezhihui.entity.core.CoreTaskDetailExample;
import com.hsmap.yuezhihui.mapper.core.CoreTaskDetailMapper;
import com.hsmap.yuezhihui.service.core.ICoreTaskDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoreTaskDetailServiceImpl extends BaseServiceImpl<CoreTaskDetail,Integer, CoreTaskDetailExample> implements ICoreTaskDetailService {

    @Autowired
    CoreTaskDetailMapper CoreTaskDetailMapper;

    @Override
    public BaseMapper<CoreTaskDetail, Integer, CoreTaskDetailExample> getMapper() {
        return CoreTaskDetailMapper;
    }

    @Override
    public PageInfo<CoreTaskDetail> pageList(CoreTaskDetail model, Pageable pageable) {
        CoreTaskDetailExample example  = new CoreTaskDetailExample();
        getBaseExample(example,model);
        example.setPageNumber(pageable.getPageNumber());
        example.setPageSize(pageable.getPageSize());
        return findByPage(example,pageable);
    }

    @Override
    public List<CoreTaskDetail> listByModel(CoreTaskDetail model) {
        CoreTaskDetailExample example  = new CoreTaskDetailExample();
        getBaseExample(example,model);
        return find(example);
    }

    @Override
    public List<CoreTaskDetail> findByAll() {
        CoreTaskDetail model = new CoreTaskDetail();
        return listByModel(model);
    }

    @Override
    public List<CoreTaskDetail> findByTaskId(int taskId) {
        CoreTaskDetail model = new CoreTaskDetail();
        model.setTaskId(taskId);
        return listByModel(model);
    }


    private CoreTaskDetailExample.Criteria getBaseExample(CoreTaskDetailExample example, CoreTaskDetail model) {
        CoreTaskDetailExample.Criteria criteria = example.createCriteria();
        example.setOrderByClause("sort_ desc,id desc");
        criteria.andIsDelEqualTo(0);
        if(model.getTaskId()!=null && model.getTaskId()>0){
            criteria.andTaskIdEqualTo(model.getTaskId());
        }


        return criteria;
    }
}

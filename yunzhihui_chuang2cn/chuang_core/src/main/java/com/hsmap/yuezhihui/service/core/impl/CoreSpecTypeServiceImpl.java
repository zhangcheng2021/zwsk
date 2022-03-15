package com.hsmap.yuezhihui.service.core.impl;

import com.hsmap.yuezhihui.base.mapper.BaseMapper;
import com.hsmap.yuezhihui.base.page.PageInfo;
import com.hsmap.yuezhihui.base.page.Pageable;
import com.hsmap.yuezhihui.base.service.BaseServiceImpl;
import com.hsmap.yuezhihui.common.CommonUtil;
import com.hsmap.yuezhihui.entity.core.CoreSpecType;
import com.hsmap.yuezhihui.entity.core.CoreSpecTypeExample;
import com.hsmap.yuezhihui.mapper.core.CoreSpecTypeMapper;
import com.hsmap.yuezhihui.service.core.ICoreSpecTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CoreSpecTypeServiceImpl extends BaseServiceImpl<CoreSpecType,Integer, CoreSpecTypeExample> implements ICoreSpecTypeService {


    private final String _REDIS_SPEC_TYPE_LIST = "_RESSS1_SPEC_TYPE_LIST";
    private final String _REDIS_SPEC_TYPE_OBJ = "_RESSS1_SPEC_TYPE_OBJ_";
    @Autowired
    CoreSpecTypeMapper CoreSpecTypeMapper;



    @Override
    public BaseMapper<CoreSpecType, Integer, CoreSpecTypeExample> getMapper() {
        return CoreSpecTypeMapper;
    }

    @Override
    public PageInfo<CoreSpecType> pageList(CoreSpecType model, Pageable pageable) {
        CoreSpecTypeExample example  = new CoreSpecTypeExample();
        getBaseExample(example,model);
        example.setPageNumber(pageable.getPageNumber());
        example.setPageSize(pageable.getPageSize());
        return findByPage(example,pageable);
    }

    @Override
    public List<CoreSpecType> listByModel(CoreSpecType model) {
        CoreSpecTypeExample example  = new CoreSpecTypeExample();
        getBaseExample(example,model);
        return find(example);
    }

    @Override
    public List<CoreSpecType> findByAll() {
        CoreSpecType model = new CoreSpecType();
        return listByModel(model);
    }

    @Override
    public void pushToRedis() {
        //list
        List<CoreSpecType> list = findByAll();
        setJsonToRedis(_REDIS_SPEC_TYPE_LIST,list);
        //id
        for (CoreSpecType type:list){
            setJsonToRedis(_REDIS_SPEC_TYPE_OBJ+type.getId(),type);
        }

    }

    @Override
    public List<CoreSpecType> findRedisByAll() {
        List<CoreSpecType> list = getListToRedis(_REDIS_SPEC_TYPE_LIST,CoreSpecType.class);
        if(list == null || list.size()<1){
            list=  findByAll();
            pushToRedis();
        }
        return list;
    }

    @Override
    public CoreSpecType findRedisById(int id) {
        CoreSpecType coreSpecType = getObjectByRedis(_REDIS_SPEC_TYPE_OBJ+id,CoreSpecType.class);
        if(coreSpecType == null ){
            coreSpecType = findById(id);
            pushToRedis();
        }
        return coreSpecType;
    }

    @Override
    public Map<Integer, String> list2Map(List<CoreSpecType> list) {
        Map<Integer,String> map = new HashMap<>();
        for(CoreSpecType type:list){
            map.put(type.getId(),type.getName());
        }
        return map;
    }


    private CoreSpecTypeExample.Criteria getBaseExample(CoreSpecTypeExample example, CoreSpecType model) {
        CoreSpecTypeExample.Criteria criteria = example.createCriteria();
        example.setOrderByClause("sort_ desc,id desc");
        criteria.andIsDelEqualTo(0);
        if(!CommonUtil.isEmpty(model.getName())){
            criteria.andNameLike("%"+model.getName()+"%");
        }

        return criteria;
    }
}

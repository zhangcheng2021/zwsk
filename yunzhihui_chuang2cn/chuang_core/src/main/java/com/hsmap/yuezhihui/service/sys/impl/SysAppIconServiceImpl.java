package com.hsmap.yuezhihui.service.sys.impl;

import com.hsmap.yuezhihui.base.mapper.BaseMapper;
import com.hsmap.yuezhihui.base.page.PageInfo;
import com.hsmap.yuezhihui.base.page.Pageable;
import com.hsmap.yuezhihui.base.service.BaseServiceImpl;
import com.hsmap.yuezhihui.common.CommonUtil;
import com.hsmap.yuezhihui.entity.sys.SysAppIcon;
import com.hsmap.yuezhihui.entity.sys.SysAppIconExample;
import com.hsmap.yuezhihui.mapper.sys.SysAppIconMapper;
import com.hsmap.yuezhihui.service.sys.ISysAppIconService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysAppIconServiceImpl  extends BaseServiceImpl<SysAppIcon,Integer, SysAppIconExample> implements ISysAppIconService {

    @Autowired
    SysAppIconMapper mapper;
    @Override
    public BaseMapper<SysAppIcon, Integer, SysAppIconExample> getMapper() {
        return mapper;
    }


    final String _APP_ICON_TYPE_KEY = "_APP_ICON_TYPE_LIST_";

    @Override
    public PageInfo<SysAppIcon> searchList(SysAppIcon model, Pageable pageable) {
        SysAppIconExample example = new SysAppIconExample();
        getBaseExample(example,model);
        example.setPageNumber(pageable.getPageNumber());
        example.setPageSize(pageable.getPageSize());
        return findByPage(example,pageable);
    }

    @Override
    public List<SysAppIcon> findByType(int type) {
        SysAppIcon model = new SysAppIcon();
        model.setType(type);
        SysAppIconExample example = new SysAppIconExample();
        getBaseExample(example,model);
        return find(example);
    }

    @Override
    public void pushActivityByRedis(int type) {
        String key = _APP_ICON_TYPE_KEY +type;
        List<SysAppIcon> list = findByType(type);
        removeRedis(key);
        if(list !=null && list.size()>0) {
            listToRedis(key, list);
        }
    }

    @Override
    public List<SysAppIcon> getListByRedis(int type) {
        String key = _APP_ICON_TYPE_KEY +type;
        List<SysAppIcon> list = getListToRedis(key,SysAppIcon.class);
        return list;
    }



    private  SysAppIconExample.Criteria getBaseExample(SysAppIconExample example,SysAppIcon model){

        SysAppIconExample.Criteria criteria = example.createCriteria();
        criteria.andIsDelEqualTo(0);
        if(!CommonUtil.isEmpty(model.getName())){
            criteria.andNameLike("%"+model.getName()+"%");
        }
        if(model.getType()!=null && model.getType()>-1){
            criteria.andTypeEqualTo(model.getType());
        }
        example.setOrderByClause(" sort_ desc");
        return criteria;
    }
}

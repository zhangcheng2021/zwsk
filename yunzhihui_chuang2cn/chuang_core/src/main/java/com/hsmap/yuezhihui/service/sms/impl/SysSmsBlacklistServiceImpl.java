package com.hsmap.yuezhihui.service.sms.impl;

import com.hsmap.yuezhihui.base.mapper.BaseMapper;
import com.hsmap.yuezhihui.base.page.PageInfo;
import com.hsmap.yuezhihui.base.page.Pageable;
import com.hsmap.yuezhihui.base.service.BaseServiceImpl;
import com.hsmap.yuezhihui.entity.sys.SysSmsBlacklist;
import com.hsmap.yuezhihui.entity.sys.SysSmsBlacklistExample;
import com.hsmap.yuezhihui.mapper.sys.SysSmsBlacklistMapper;
import com.hsmap.yuezhihui.common.CommonUtil;
import com.hsmap.yuezhihui.service.sms.ISysSmsBlacklistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysSmsBlacklistServiceImpl extends BaseServiceImpl<SysSmsBlacklist,Integer, SysSmsBlacklistExample> implements ISysSmsBlacklistService {

    final String _REDIS_TYPE_NAME = "sys_sms_blacklist_type_";
    @Autowired
    SysSmsBlacklistMapper sysSmsBlacklistMapper;

    @Override
    public BaseMapper<SysSmsBlacklist, Integer, SysSmsBlacklistExample> getMapper() {
        return sysSmsBlacklistMapper;
    }


    @Override
    public List<SysSmsBlacklist> findByType(int type) {
        SysSmsBlacklistExample example = new SysSmsBlacklistExample();
        SysSmsBlacklistExample.Criteria criteria =example.createCriteria();
        criteria.andTypeEqualTo(type);
        criteria.andIsDelEqualTo(0);
        List<SysSmsBlacklist> list = find(example);
        return list;
    }

    @Override
    public PageInfo<SysSmsBlacklist> search(SysSmsBlacklist model, Pageable pageable) {
        SysSmsBlacklistExample example = new SysSmsBlacklistExample();
        SysSmsBlacklistExample.Criteria criteria =example.createCriteria();
        if(!CommonUtil.isEmpty(model.getMobile())){
            criteria.andMobileEqualTo(model.getMobile());
        }
        if (model.getType() > 0) {
            criteria.andTypeEqualTo(model.getType());
        }
        criteria.andIsDelEqualTo(0);

        example.setPageNumber(pageable.getPageNumber());
        example.setPageSize(pageable.getPageSize());
        PageInfo<SysSmsBlacklist> pageInfo = findByPage(example,pageable);
        return pageInfo;
    }

    @Override
    public int syncType2Redis(int type) {
        List<SysSmsBlacklist> list = findByType(type);
        if (list == null || list.size() < 1) {
            return 0;
        }
        for (SysSmsBlacklist blacklist:list) {
            if (blacklist == null) {
                LOGGER.info("blacklist为空，不进行redis入栈操作");
                continue;
            }
            if (CommonUtil.isEmpty(blacklist.getMobile())) {
                LOGGER.info("手机号码为空，不进行redis入栈操作");
                continue;

            }
            setRedis(_REDIS_TYPE_NAME+type+"_"+blacklist.getMobile(),blacklist.toJson());
        }

        return list.size();
    }

    @Override
    public boolean mobileIsBlackByTypeRedis(String mobile, int type) {
        Object obj = getRedis(_REDIS_TYPE_NAME+type+"_"+mobile);
        if (obj == null) {
            LOGGER.info("mobile:"+mobile+",type:"+type+",查询结果为空，返回false");
            return false;
        }

        LOGGER.info("mobile:"+mobile+",type:"+type+",查询结果不为空，返回true");

        return true;
    }

    @Override
    public boolean mobileIsBlackByType(String mobile,int type) {
        SysSmsBlacklistExample example =new SysSmsBlacklistExample();
        SysSmsBlacklistExample.Criteria criteria =  example.createCriteria();
        criteria.andTypeEqualTo(type);
        criteria.andMobileEqualTo(mobile);
        List<SysSmsBlacklist> list = find(example);
        boolean b= false;
        if (list != null && list.size() > 1) {
            b=true;
        }
        LOGGER.info("SysSmsBlacklistServiceImpl->mobileIsBlackByType,parameter：mobile:{},type:{},返回:{}",mobile,type,b);
        return b;


    }

}

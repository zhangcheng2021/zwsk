package com.hsmap.yuezhihui.service.sys.impl;

import com.hsmap.yuezhihui.base.mapper.BaseMapper;
import com.hsmap.yuezhihui.base.page.PageInfo;
import com.hsmap.yuezhihui.base.page.Pageable;
import com.hsmap.yuezhihui.base.service.BaseServiceImpl;
import com.hsmap.yuezhihui.entity.sys.SysRole;
import com.hsmap.yuezhihui.entity.sys.SysRoleExample;
import com.hsmap.yuezhihui.mapper.sys.SysRoleMapper;
import com.hsmap.yuezhihui.common.CommonUtil;
import com.hsmap.yuezhihui.service.sys.ISysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SysRoleServiceImpl extends BaseServiceImpl<SysRole,Integer, SysRoleExample> implements ISysRoleService {
    @Autowired
    SysRoleMapper mapper;
    @Override
    public BaseMapper<SysRole, Integer, SysRoleExample> getMapper() {
        return mapper;
    }


    final int _ROLE_AGENCY_TYPE = 1;

    @Override
    public PageInfo<SysRole> searchList(SysRole model, Pageable pageable) {
        SysRoleExample example = new SysRoleExample();
        example.setOrderByClause(" id desc ");
        example.setPageNumber(pageable.getPageNumber());
        example.setPageSize(pageable.getPageSize());
        return findByPage(example,pageable);
    }

    @Override
    public List<Integer> findMenuIds(int roleId) {
        SysRole role = findById(roleId);
        return CommonUtil.idsToArray(role.getMenuIds());
    }

    @Override
    public List<SysRole> list() {
        SysRoleExample example = new SysRoleExample();
//        SysRoleExample.Criteria criteria= example.createCriteria();
        example.setOrderByClause("id ");
        return find(example);
    }

    @Override
    public List<SysRole> list(int status) {

        SysRoleExample example = new SysRoleExample();
        SysRoleExample.Criteria criteria= example.createCriteria();
        criteria.andStatusEqualTo(status);
        example.setOrderByClause("id ");
        return find(example);
    }

    @Override
    public Map<Integer, String> role2Map(List<SysRole> roleList) {
        Map<Integer, String> map =new HashMap<>();
        for (SysRole role:roleList) {
            map.put(role.getId(),role.getName());
        }

        return map ;
    }

    @Override
    public List<SysRole> findByType(int type) {
        SysRoleExample example = new SysRoleExample();
        SysRoleExample.Criteria criteria= example.createCriteria();
        criteria.andTypeEqualTo(type);
        criteria.andStatusEqualTo(0);
        example.setOrderByClause("id ");
        return find(example);
    }

    @Override
    public List<Integer> getIdList(List<SysRole> roleList){
        List<Integer> idList = new ArrayList<>();
        if(roleList== null || roleList.isEmpty()){
            return idList;
        }
        for(SysRole role:roleList){
         idList.add(role.getId());
        }
        return idList;
    }

    @Override
    public int getAgencyTypeValue() {
        return _ROLE_AGENCY_TYPE;
    }
}

package com.hsmap.yuezhihui.service.sys.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hsmap.yuezhihui.base.mapper.BaseMapper;
import com.hsmap.yuezhihui.base.page.PageInfo;
import com.hsmap.yuezhihui.base.page.Pageable;
import com.hsmap.yuezhihui.base.service.BaseServiceImpl;
import com.hsmap.yuezhihui.entity.sys.SysMenuInfo;
import com.hsmap.yuezhihui.entity.sys.SysMenuInfoExample;
import com.hsmap.yuezhihui.mapper.sys.SysMenuInfoMapper;
import com.hsmap.yuezhihui.vo.sys.SysMenuInfoVO;
import com.hsmap.yuezhihui.common.CommonUtil;
import com.hsmap.yuezhihui.service.sys.ISysMenuInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SysMenuInfoServiceImpl extends BaseServiceImpl<SysMenuInfo, Integer, SysMenuInfoExample> implements ISysMenuInfoService {

    @Autowired
    SysMenuInfoMapper sysMenuInfoMapper;
    @Autowired
    SysRoleServiceImpl roleService;

    @Override
    public BaseMapper<SysMenuInfo, Integer, SysMenuInfoExample> getMapper() {
        return sysMenuInfoMapper;
    }

    @Override
    public JSONObject infoAll2Json() {

        JSONObject json = new JSONObject();
        List<SysMenuInfo> list = findByParent(0);
        for (SysMenuInfo info : list) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("text", info.getName());
            jsonObject.put("type", info.getType());
            //
            List<SysMenuInfo> childrenList = findByParent(info.getId());
            jsonObject.put("children", list2JsonArray(childrenList));

        }

        LOGGER.info("SysMenuInfoServiceImpl->infoAll2Json,result:{}", json.toJSONString());

        return json;
    }

    /***
     * 将List转为json
     * @param list
     * @return
     */
    JSONArray list2JsonArray(List<SysMenuInfo> list) {

        JSONArray childrenArray = new JSONArray();
        for (SysMenuInfo childrenInfo : list) {
            JSONObject childrenJson = new JSONObject();
            childrenJson.put("text", childrenInfo.getName());
            childrenJson.put("type", childrenInfo.getType());
            childrenJson.put("name", childrenInfo.getUrl());
            childrenArray.add(childrenJson);
        }
        return childrenArray;


    }

    @Override
    public List<SysMenuInfo> findByParent(int parent) {
        SysMenuInfoExample example = new SysMenuInfoExample();
        SysMenuInfoExample.Criteria criteria = example.createCriteria();
//        criteria.andIsDelEqualTo(0);
        criteria.andParentEqualTo(parent);
        List<SysMenuInfo> list = find(example);
        example.setOrderByClause(" sort_ desc ,id desc");

        LOGGER.info("SysMenuInfoServiceImpl->findByParent,list size:" + (list == null ? 0 : list.size()));
        return list;
    }

    @Override
    public List<SysMenuInfo> findByAll() {
        SysMenuInfoExample example = new SysMenuInfoExample();
        example.setOrderByClause(" sort_ desc ,id desc");
        List<SysMenuInfo> list = find(example);
        LOGGER.info("SysMenuInfoServiceImpl->findByAll,list size:" + (list == null ? 0 : list.size()));
        return list;
    }

    @Override
    public PageInfo<SysMenuInfo> seachList(SysMenuInfo sysMenuInfo, Pageable pageable) {
        SysMenuInfoExample example = new SysMenuInfoExample();
        SysMenuInfoExample.Criteria criteria = example.createCriteria();
//        criteria.andIsDelEqualTo(0);
        if (sysMenuInfo.getParent() > 0) {
            criteria.andParentEqualTo(sysMenuInfo.getParent());
        }
        if (!CommonUtil.isEmpty(sysMenuInfo.getName())) {
            criteria.andNameLike("%" + sysMenuInfo.getName() + "%");
        }
        example.setPageSize(pageable.getPageSize());
        example.setPageNumber(pageable.getPageNumber());
        example.setOrderByClause("sort_ desc,id desc  ");
        PageInfo<SysMenuInfo> page = findByPage(example, pageable);
        LOGGER.info("SysMenuInfoServiceImpl->findByParent,page totalElements:" + (page == null ? 0 : page.getTotalElements()));
        return page;
    }

    @Override
    public Map<Integer, String> obj2Map(List<SysMenuInfo> list) {
        Map<Integer, String> map = new HashMap<>();
        for (SysMenuInfo obj : list) {
            map.put(obj.getId(), obj.getName());

        }
        LOGGER.info("SysMenuInfoServiceImpl->obj2Map,map size:" + (map == null ? 0 : map.size()));
        return map;
    }

    @Override
    public List<SysMenuInfoVO> findVoByList(List<SysMenuInfo> allList) {
        if (allList == null || allList.size() < 1) {
            return null;
        }
        List<SysMenuInfoVO> list = new ArrayList<>();
        List<SysMenuInfo> parentList = getParentList(allList);
        for (SysMenuInfo parent : parentList) {
            SysMenuInfoVO vo = new SysMenuInfoVO();
            BeanUtils.copyProperties(parent, vo);
            List<SysMenuInfo> childList = new ArrayList<>();
            for (SysMenuInfo child : allList) {
                if (child.getParent() == null) {
                    continue;
                }

                if (parent.getId().equals(child.getParent())) {
                    childList.add(child);
                }
            }
            vo.setChildList(childList);
            list.add(vo);
        }
        return list;
    }

    @Override
    public List<SysMenuInfo> findByIds(List<Integer> idList) {
        SysMenuInfoExample example = new SysMenuInfoExample();
        SysMenuInfoExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(idList);
        criteria.andIsDelEqualTo(0);
        example.setOrderByClause("sort_ desc,id desc");
        return find(example);
    }

    @Override
    public List<SysMenuInfoVO> findVoByRoleId(int roleId) {
        List<Integer> menuIdList = roleService.findMenuIds(roleId);

        List<SysMenuInfo> menuInfoList = findByIds(menuIdList);

        List<SysMenuInfoVO> menuInfoVOList = findVoByList(menuInfoList);

        return menuInfoVOList;

    }

    /**
     * 获取一级菜单 如果为空就去系统查询
     *
     * @param list
     * @return
     */
    private List<SysMenuInfo> getParentList(List<SysMenuInfo> list) {

        List<SysMenuInfo> parentList = new ArrayList<>();
        for (SysMenuInfo info : list) {
            LOGGER.info("info name:{}",info.getName());
            if (info.getParent() == 0) {
                LOGGER.info(" parant name:{}",info.getName());
                parentList.add(info);
            }
        }

        //如果为空就去查询一级菜单
        if (parentList == null || parentList.size() < 1) {
            return findByParent(0);
        }
        return parentList;


    }

}

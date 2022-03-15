package com.hsmap.yuezhihui.service.spec.impl;

import com.hsmap.yuezhihui.base.mapper.BaseMapper;
import com.hsmap.yuezhihui.base.page.PageInfo;
import com.hsmap.yuezhihui.base.page.Pageable;
import com.hsmap.yuezhihui.base.service.BaseServiceImpl;
import com.hsmap.yuezhihui.entity.review.ComplexRelationProductDo;
import com.hsmap.yuezhihui.entity.spec.BatchSpecInfo;
import com.hsmap.yuezhihui.entity.spec.GroupSpecInfo;
import com.hsmap.yuezhihui.entity.spec.SpecInfo;
import com.hsmap.yuezhihui.entity.spec.SpecInfoExample;
import com.hsmap.yuezhihui.mapper.spec.SpecInfoMapper;
import com.hsmap.yuezhihui.service.spec.ISpecInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecInfoServiceImpl extends BaseServiceImpl<SpecInfo, Integer, SpecInfoExample> implements ISpecInfoService {

    @Autowired
    SpecInfoMapper SpecInfoMapper;

    @Override
    public BaseMapper<SpecInfo, Integer, SpecInfoExample> getMapper() {
        return SpecInfoMapper;
    }

    @Override
    public PageInfo<SpecInfo> pageList(SpecInfo model, Pageable pageable) {
        SpecInfoExample example = new SpecInfoExample();
        getBaseExample(example, model);
        example.setPageNumber(pageable.getPageNumber());
        example.setPageSize(pageable.getPageSize());
        return findByPage(example, pageable);
    }

    @Override
    public List<SpecInfo> listByModel(SpecInfo model) {
        SpecInfoExample example = new SpecInfoExample();
        getBaseExample(example, model);
        return find(example);
    }

    @Override
    public List<SpecInfo> findByAll() {
        SpecInfo model = new SpecInfo();
        return listByModel(model);
    }

    @Override
    public List<SpecInfo> findByIdList(List<Integer> idList) {
        if(idList==null || idList.size()<1){
            return null;
        }
        SpecInfo model = new SpecInfo();

        SpecInfoExample example = new SpecInfoExample();
        SpecInfoExample.Criteria criteria =  getBaseExample(example, model);
        criteria.andIdIn(idList);


        return find(example);
    }


    private SpecInfoExample.Criteria getBaseExample(SpecInfoExample example, SpecInfo model) {
        SpecInfoExample.Criteria criteria = example.createCriteria();
        example.setOrderByClause("s.sort_ desc,s.id desc");
        criteria.andIsDelEqualTo(0);
        if (model.getTitleId() != null && model.getTitleId() > 0) {
            criteria.andTitleIdEqualTo(model.getTitleId());
        }
        if (model.getSpecTypeId() != null && model.getSpecTypeId() > 0) {
            criteria.andSpecTypeIdEqualTo(model.getSpecTypeId());
        }
        if (model.getTerritoryId() != null && model.getTerritoryId() > 0) {
            criteria.andTerritoryIdEqualTo(model.getTerritoryId());
        }
        if (model.getUserName() != null && model.getUserName().trim().length() > 0) {
            criteria.andUserNameLike("%" + model.getUserName() + "%");
        }
        if (model.getTerritoryName() != null && model.getTerritoryName().trim().length() > 0) {
            criteria.andTerritoryNameLike("%" + model.getTerritoryName() + "%");
        }
        if (model.getLevel() != null && model.getLevel() > 0) {
            criteria.andLevelEqualTo(model.getLevel());
        }
        if (model.getSex() != null && model.getSex() >= 0) {
            criteria.andSexEqualTo(model.getSex());
        }
        return criteria;
    }

    /**
     * 分组下的专家列表
     *
     * @param query
     * @return
     */
    @Override
    public List<GroupSpecInfo> selectGroupSpecInfo(GroupSpecInfo query) {
        return SpecInfoMapper.selectGroupSpecInfo(query);
    }

    /**
     * 批次下的专家列表查询
     * @param query
     * @param pageable
     * @return
     */
    @Override
    public PageInfo<BatchSpecInfo> selectBatchSpecInfoListOfPage(BatchSpecInfo query, Pageable pageable) {
        PageInfo<BatchSpecInfo> page = null;
        long totalElements = SpecInfoMapper.selectBatchSpecInfoCount(query);
        if (totalElements > 0) {
            query.setPageNumber(pageable.getPageNumber());
            query.setPageSize(pageable.getPageSize());
            List<BatchSpecInfo> list = SpecInfoMapper.selectBatchSpecInfoList(query);
            page = new PageInfo<BatchSpecInfo>(pageable, totalElements, list);
        } else {
            page = new PageInfo<BatchSpecInfo>(pageable, 0l, null);
        }
        return page;
    }
}

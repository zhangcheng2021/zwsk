package com.hsmap.yuezhihui.service.spec;

import com.hsmap.yuezhihui.base.page.PageInfo;
import com.hsmap.yuezhihui.base.page.Pageable;
import com.hsmap.yuezhihui.base.service.IBaseService;
import com.hsmap.yuezhihui.entity.spec.BatchSpecInfo;
import com.hsmap.yuezhihui.entity.spec.GroupSpecInfo;
import com.hsmap.yuezhihui.entity.spec.SpecInfo;
import com.hsmap.yuezhihui.entity.spec.SpecInfoExample;

import java.util.List;

/**
 * 专家信息service接口
 */
public interface ISpecInfoService extends IBaseService<SpecInfo, Integer, SpecInfoExample> {

    PageInfo<SpecInfo> pageList(SpecInfo model, Pageable pageable);

    List<SpecInfo> listByModel(SpecInfo model);


    List<SpecInfo> findByAll();

    List<SpecInfo> findByIdList(List<Integer> idList);

    /**
     * 分组下的专家列表
     *
     * @param query
     * @return
     */
    List<GroupSpecInfo> selectGroupSpecInfo(GroupSpecInfo query);

    /**
     * 分组下的专家列表
     *
     * @param query
     * @return
     */
    PageInfo<BatchSpecInfo> selectBatchSpecInfoListOfPage(BatchSpecInfo query, Pageable pageable);

}

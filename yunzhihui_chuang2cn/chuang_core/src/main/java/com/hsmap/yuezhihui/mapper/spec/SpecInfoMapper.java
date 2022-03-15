package com.hsmap.yuezhihui.mapper.spec;

import com.hsmap.yuezhihui.base.mapper.BaseMapper;
import com.hsmap.yuezhihui.entity.spec.BatchSpecInfo;
import com.hsmap.yuezhihui.entity.spec.GroupSpecInfo;
import com.hsmap.yuezhihui.entity.spec.SpecInfo;
import com.hsmap.yuezhihui.entity.spec.SpecInfoExample;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpecInfoMapper extends BaseMapper<SpecInfo,Integer, SpecInfoExample> {
    /**
     * 分组下的专家列表
     * @param query
     * @return
     */
    List<GroupSpecInfo> selectGroupSpecInfo(GroupSpecInfo query);

    /**
     * 分组下的专家列表
     * @param query
     * @return
     */
    List<BatchSpecInfo> selectBatchSpecInfoList(BatchSpecInfo query);

    /**
     * 分组下的专家列表Count
     * @param query
     * @return
     */
    long selectBatchSpecInfoCount(BatchSpecInfo query);
}

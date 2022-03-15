package com.hsmap.yuezhihui.service.core;

import com.hsmap.yuezhihui.base.page.PageInfo;
import com.hsmap.yuezhihui.base.page.Pageable;
import com.hsmap.yuezhihui.base.service.IBaseService;
import com.hsmap.yuezhihui.entity.core.CoreSpecType;
import com.hsmap.yuezhihui.entity.core.CoreSpecTypeExample;

import java.util.List;
import java.util.Map;

/**
 * 专家类型service接口
 */
public interface ICoreSpecTypeService extends IBaseService<CoreSpecType,Integer, CoreSpecTypeExample> {

    PageInfo<CoreSpecType> pageList(CoreSpecType model, Pageable pageable);

    List<CoreSpecType> listByModel(CoreSpecType model);

    List<CoreSpecType> findByAll();

    void  pushToRedis();

    List<CoreSpecType> findRedisByAll();

    CoreSpecType findRedisById(int id);

    Map<Integer,String> list2Map(List<CoreSpecType> list);
}

package com.hsmap.yuezhihui.service.core;

import com.hsmap.yuezhihui.base.page.PageInfo;
import com.hsmap.yuezhihui.base.page.Pageable;
import com.hsmap.yuezhihui.base.service.IBaseService;
import com.hsmap.yuezhihui.entity.core.CoreTaskInfo;
import com.hsmap.yuezhihui.entity.core.CoreTaskInfoExample;

import java.util.List;

/**
 * 导入信息service接口
 */
public interface ICoreTaskInfoService extends IBaseService<CoreTaskInfo,Integer, CoreTaskInfoExample> {

    PageInfo<CoreTaskInfo> pageList(CoreTaskInfo model, Pageable pageable);

    List<CoreTaskInfo> listByModel(CoreTaskInfo model);


    List<CoreTaskInfo> findByAll();

    List<CoreTaskInfo> findByType(int type);

}

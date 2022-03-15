package com.hsmap.yuezhihui.service.core;

import com.hsmap.yuezhihui.base.page.PageInfo;
import com.hsmap.yuezhihui.base.page.Pageable;
import com.hsmap.yuezhihui.base.service.IBaseService;
import com.hsmap.yuezhihui.entity.core.CoreTaskDetail;
import com.hsmap.yuezhihui.entity.core.CoreTaskDetailExample;

import java.util.List;

/**
 * 导入详情service接口
 */
public interface ICoreTaskDetailService extends IBaseService<CoreTaskDetail,Integer, CoreTaskDetailExample> {

    PageInfo<CoreTaskDetail> pageList(CoreTaskDetail model, Pageable pageable);

    List<CoreTaskDetail> listByModel(CoreTaskDetail model);


    List<CoreTaskDetail> findByAll();

    List<CoreTaskDetail> findByTaskId(int taskInfoId);

}

package com.hsmap.yuezhihui.service.core;

import com.hsmap.yuezhihui.base.page.PageInfo;
import com.hsmap.yuezhihui.base.page.Pageable;
import com.hsmap.yuezhihui.base.service.IBaseService;
import com.hsmap.yuezhihui.entity.core.CoreProjectType;
import com.hsmap.yuezhihui.entity.core.CoreProjectTypeExample;

import java.util.List;

/**
 * 项目类型service接口
 */
public interface ICoreProjectTypeService extends IBaseService<CoreProjectType,Integer, CoreProjectTypeExample> {

    PageInfo<CoreProjectType> pageList(CoreProjectType model, Pageable pageable);

    List<CoreProjectType> listByModel(CoreProjectType model);


    List<CoreProjectType> findByAll();

}

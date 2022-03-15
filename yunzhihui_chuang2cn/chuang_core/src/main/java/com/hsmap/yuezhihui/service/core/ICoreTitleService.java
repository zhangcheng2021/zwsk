package com.hsmap.yuezhihui.service.core;

import com.hsmap.yuezhihui.base.page.PageInfo;
import com.hsmap.yuezhihui.base.page.Pageable;
import com.hsmap.yuezhihui.base.service.IBaseService;
import com.hsmap.yuezhihui.entity.core.CoreTitle;
import com.hsmap.yuezhihui.entity.core.CoreTitleExample;

import java.util.List;

/**
 * 专家职称service接口
 */
public interface ICoreTitleService extends IBaseService<CoreTitle,Integer, CoreTitleExample> {

    PageInfo<CoreTitle> pageList(CoreTitle model, Pageable pageable);

    List<CoreTitle> listByModel(CoreTitle model);


    List<CoreTitle> findByAll();

}

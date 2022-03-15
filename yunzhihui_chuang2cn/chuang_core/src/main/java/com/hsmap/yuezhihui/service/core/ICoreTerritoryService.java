package com.hsmap.yuezhihui.service.core;

import com.hsmap.yuezhihui.base.page.PageInfo;
import com.hsmap.yuezhihui.base.page.Pageable;
import com.hsmap.yuezhihui.base.service.IBaseService;
import com.hsmap.yuezhihui.entity.core.CoreTerritory;
import com.hsmap.yuezhihui.entity.core.CoreTerritoryExample;

import java.util.List;

/**
 * 专业领域service接口
 */
public interface ICoreTerritoryService extends IBaseService<CoreTerritory,Integer, CoreTerritoryExample> {

    PageInfo<CoreTerritory> pageList(CoreTerritory model, Pageable pageable);

    List<CoreTerritory> listByModel(CoreTerritory model);


    List<CoreTerritory> findByAll();

}

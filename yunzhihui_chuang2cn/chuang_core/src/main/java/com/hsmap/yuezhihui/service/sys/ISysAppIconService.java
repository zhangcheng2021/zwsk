package com.hsmap.yuezhihui.service.sys;

import com.hsmap.yuezhihui.base.page.PageInfo;
import com.hsmap.yuezhihui.base.page.Pageable;
import com.hsmap.yuezhihui.base.service.IBaseService;
import com.hsmap.yuezhihui.entity.sys.SysAppIcon;
import com.hsmap.yuezhihui.entity.sys.SysAppIconExample;

import java.util.List;

public interface ISysAppIconService  extends IBaseService<SysAppIcon, Integer, SysAppIconExample> {
    PageInfo<SysAppIcon> searchList(SysAppIcon model, Pageable pageable);

    List<SysAppIcon> findByType(int type);

    void pushActivityByRedis(int type);

    List<SysAppIcon> getListByRedis(int type);
}

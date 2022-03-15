package com.hsmap.yuezhihui.service.sys;

import com.hsmap.yuezhihui.base.page.PageInfo;
import com.hsmap.yuezhihui.base.page.Pageable;
import com.hsmap.yuezhihui.base.service.IBaseService;
import com.hsmap.yuezhihui.entity.sys.SysRole;
import com.hsmap.yuezhihui.entity.sys.SysRoleExample;

import java.util.List;
import java.util.Map;

public interface ISysRoleService extends IBaseService<SysRole, Integer, SysRoleExample> {

    PageInfo<SysRole> searchList(SysRole model, Pageable pageable);
    List<Integer> findMenuIds(int roleId);

    List<SysRole> list();

    List<SysRole> list(int status);

    Map<Integer, String> role2Map(List<SysRole> roleList);

    List<SysRole> findByType(int type);

    List<Integer> getIdList(List<SysRole> roleList);

    int getAgencyTypeValue();
}

package com.hsmap.yuezhihui.service.sys;

import com.alibaba.fastjson.JSONObject;
import com.hsmap.yuezhihui.base.page.PageInfo;
import com.hsmap.yuezhihui.base.page.Pageable;
import com.hsmap.yuezhihui.base.service.IBaseService;
import com.hsmap.yuezhihui.entity.sys.SysMenuInfo;
import com.hsmap.yuezhihui.entity.sys.SysMenuInfoExample;
import com.hsmap.yuezhihui.vo.sys.SysMenuInfoVO;

import java.util.List;
import java.util.Map;

public interface ISysMenuInfoService extends IBaseService<SysMenuInfo, Integer, SysMenuInfoExample> {
    public JSONObject infoAll2Json();

    public List<SysMenuInfo> findByParent(int parent);

    public List<SysMenuInfo> findByAll();

    public PageInfo<SysMenuInfo> seachList(SysMenuInfo sysMenuInfo, Pageable pageable);

    public Map<Integer, String> obj2Map(List<SysMenuInfo> list);

    public List<SysMenuInfoVO> findVoByList(List<SysMenuInfo> list);

    public List<SysMenuInfo> findByIds(List<Integer> idList);

    /***
     * 根据角色查看用户菜单
     * @param roleId
     * @return
     */
    public List<SysMenuInfoVO> findVoByRoleId(int roleId);



}

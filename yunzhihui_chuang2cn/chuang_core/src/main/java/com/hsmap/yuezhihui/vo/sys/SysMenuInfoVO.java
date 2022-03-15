package com.hsmap.yuezhihui.vo.sys;

import com.hsmap.yuezhihui.entity.sys.SysMenuInfo;

import java.util.List;

public class SysMenuInfoVO extends SysMenuInfo {


    private List<SysMenuInfo> childList;

    public List<SysMenuInfo> getChildList() {
        return childList;
    }

    public void setChildList(List<SysMenuInfo> childList) {
        this.childList = childList;
    }
}

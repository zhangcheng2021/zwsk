package com.hsmap.yuezhihui.service.sys;

import com.hsmap.yuezhihui.base.service.IBaseService;
import com.hsmap.yuezhihui.entity.sys.SysDict;
import com.hsmap.yuezhihui.entity.sys.SysDictExample;

import java.util.List;

public interface ISysDictService extends IBaseService<SysDict, Integer, SysDictExample> {


    List<SysDict> findByAll(int status);

    List<SysDict> findByCode(String code);

    String getValueByCode(String code);
    boolean saveByCode(String code,String value);

    void setValue2Code(String code, String value);

    void refreshRedis();





}

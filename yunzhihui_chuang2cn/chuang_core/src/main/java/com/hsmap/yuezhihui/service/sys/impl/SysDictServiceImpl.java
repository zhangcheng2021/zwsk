package com.hsmap.yuezhihui.service.sys.impl;

import com.hsmap.yuezhihui.base.mapper.BaseMapper;
import com.hsmap.yuezhihui.base.service.BaseServiceImpl;
import com.hsmap.yuezhihui.entity.sys.SysDict;
import com.hsmap.yuezhihui.entity.sys.SysDictExample;
import com.hsmap.yuezhihui.mapper.sys.SysDictMapper;
import com.hsmap.yuezhihui.service.sys.ISysDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 系统数据字典
 */
@Service
public class SysDictServiceImpl extends BaseServiceImpl<SysDict,Integer, SysDictExample> implements ISysDictService {

    private final String _REDIS_DICT_KEY = "_REDIS_SYS_DICT_KEY_";
    @Autowired
    SysDictMapper mapper;
    @Override
    public BaseMapper<SysDict, Integer, SysDictExample> getMapper() {
        return mapper;
    }


    @Override
    public List<SysDict> findByAll(int status) {
        SysDictExample example = new SysDictExample();
        SysDictExample.Criteria criteria = example.createCriteria();
        criteria.andIsDelEqualTo(status);
        return find(example);
    }

    @Override
    public List<SysDict> findByCode(String code) {
        SysDictExample example = new SysDictExample();
        SysDictExample.Criteria criteria = example.createCriteria();
        criteria.andIsDelEqualTo(0);
        criteria.andDictCodeEqualTo(code);

        return find(example);
    }

    @Override
    public String getValueByCode(String code) {
        String key  = _REDIS_DICT_KEY+code;
        Object obj = getRedis(key);
        String val = "";
        if(obj== null){
            List<SysDict>  list = findByCode(code);
            if(list==null || list.isEmpty()){
                return val;
            }
            SysDict dict = list.get(0);
            val = dict.getDictValue();
            setValue2Code(code,val);
            return val;
        }
        val = (String)obj;
        return val;
    }

    @Override
    public boolean saveByCode(String code, String value) {
        List<SysDict> dictList = findByCode(code);
        if(dictList== null || dictList.size()<1){
            return false;
        }
        if(dictList.size()>1){
            return false;
        }
        SysDict dict = dictList.get(0);
        dict.setDictValue(value);
        update(dict);
        setValue2Code(code,value);
        return true;
    }

    @Override
    public void setValue2Code(String code, String value) {
        String key  = _REDIS_DICT_KEY+code;
        setRedis(key,value);

    }

    @Override
    public void refreshRedis() {
        List<SysDict> list = findByAll(0);
        for (SysDict dict:list) {
            setValue2Code(dict.getDictCode(),dict.getDictValue());
        }
    }
}

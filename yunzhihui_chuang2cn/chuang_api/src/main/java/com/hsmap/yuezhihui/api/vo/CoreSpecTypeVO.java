package com.hsmap.yuezhihui.api.vo;

import com.hsmap.yuezhihui.entity.core.CoreSpecType;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class CoreSpecTypeVO {


    private Integer id;

    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static List<CoreSpecTypeVO> list2vo(List<CoreSpecType> list){
        List<CoreSpecTypeVO> voList = new ArrayList<>();
        for (CoreSpecType bean:list) {
            voList.add(obj2vo(bean));
        }
        return voList;
    }

    public static CoreSpecTypeVO obj2vo(CoreSpecType bean){
        CoreSpecTypeVO vo = new CoreSpecTypeVO();
        BeanUtils.copyProperties(bean,vo);
        return vo;
    }
}

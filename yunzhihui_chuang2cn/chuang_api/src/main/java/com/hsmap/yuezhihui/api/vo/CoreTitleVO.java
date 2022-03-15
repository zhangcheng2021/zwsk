package com.hsmap.yuezhihui.api.vo;

import com.hsmap.yuezhihui.entity.core.CoreTitle;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class CoreTitleVO {


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

    public static List<CoreTitleVO> list2vo(List<CoreTitle> list){
        List<CoreTitleVO> voList = new ArrayList<>();
        for (CoreTitle bean:list) {
            voList.add(obj2vo(bean));
        }
        return voList;
    }

    public static CoreTitleVO obj2vo(CoreTitle bean){
        CoreTitleVO vo = new CoreTitleVO();
        BeanUtils.copyProperties(bean,vo);
        return vo;
    }
}

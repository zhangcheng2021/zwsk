package com.hsmap.yuezhihui.api.vo;

import com.hsmap.yuezhihui.entity.core.CoreProjectType;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class CoreProjectTypeVO {

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

    public static List<CoreProjectTypeVO> list2vo(List<CoreProjectType> list){
        List<CoreProjectTypeVO> voList = new ArrayList<>();
        for (CoreProjectType bean:list) {
            voList.add(obj2vo(bean));
        }
        return voList;
    }

    public static CoreProjectTypeVO obj2vo(CoreProjectType bean){
        CoreProjectTypeVO vo = new CoreProjectTypeVO();
        BeanUtils.copyProperties(bean,vo);
        return vo;
    }
}

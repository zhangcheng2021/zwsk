package com.hsmap.yuezhihui.api.vo;

import com.hsmap.yuezhihui.entity.core.CoreTerritory;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class CoreTerritoryVO {


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

    public static List<CoreTerritoryVO> list2vo(List<CoreTerritory> list){
        List<CoreTerritoryVO> voList = new ArrayList<>();
        for (CoreTerritory bean:list) {
            voList.add(obj2vo(bean));
        }
        return voList;
    }

    public static CoreTerritoryVO obj2vo(CoreTerritory bean){
        CoreTerritoryVO vo = new CoreTerritoryVO();
        BeanUtils.copyProperties(bean,vo);
        return vo;
    }
}

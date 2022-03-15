package com.hsmap.yuezhihui.api.controller;

import com.hsmap.yuezhihui.api.vo.*;
import com.hsmap.yuezhihui.entity.core.CoreProjectType;
import com.hsmap.yuezhihui.entity.core.CoreSpecType;
import com.hsmap.yuezhihui.entity.core.CoreTerritory;
import com.hsmap.yuezhihui.entity.core.CoreTitle;
import com.hsmap.yuezhihui.entity.spec.SpecInfo;
import com.hsmap.yuezhihui.service.core.ICoreProjectTypeService;
import com.hsmap.yuezhihui.service.core.ICoreSpecTypeService;
import com.hsmap.yuezhihui.service.core.ICoreTerritoryService;
import com.hsmap.yuezhihui.service.core.ICoreTitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CoreController extends  ApiBaseController {
    private final String _MODEL_NAME = "/core/";
    @Autowired
    ICoreProjectTypeService projectTypeService;
    @Autowired
    ICoreTerritoryService territoryService;
    @Autowired
    ICoreTitleService titleService;
    @Autowired
    ICoreSpecTypeService specTypeService;

    @RequestMapping(value = _MODEL_NAME + "/projectTypeList")
    public String projectTypeList() {
        Map<String,String> map = new HashMap<>();
        List<CoreProjectType> list = projectTypeService.findByAll();
        //map.put("isStatus",dictService.getValueByCode(_APP_IS_REVIEW));
        return setResult(CoreProjectTypeVO.list2vo(list));
    }

    /**
     * 专业领域
     * @return
     */
    @RequestMapping(value = _MODEL_NAME + "/territoryList")
    public String territoryListList() {
        List<CoreTerritory> list = territoryService.findByAll();
        return setResult(CoreTerritoryVO.list2vo(list));
    }

    @RequestMapping(value = _MODEL_NAME + "/titleList")
    public String titleList() {
        Map<String,String> map = new HashMap<>();
        List<CoreTitle> list = titleService.findByAll();
        //map.put("isStatus",dictService.getValueByCode(_APP_IS_REVIEW));
        return setResult(CoreTitleVO.list2vo(list));
    }

    @RequestMapping(value = _MODEL_NAME + "/specTypeList")
    public String specList() {
        Map<String,String> map = new HashMap<>();
        List<CoreSpecType> list = specTypeService.findByAll();
        //map.put("isStatus",dictService.getValueByCode(_APP_IS_REVIEW));
        return setResult(CoreSpecTypeVO.list2vo(list));
    }



}

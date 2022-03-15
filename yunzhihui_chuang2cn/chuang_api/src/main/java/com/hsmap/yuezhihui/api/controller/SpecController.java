package com.hsmap.yuezhihui.api.controller;

import com.hsmap.yuezhihui.api.vo.SpecInfoVO;
import com.hsmap.yuezhihui.entity.spec.SpecInfo;
import com.hsmap.yuezhihui.service.spec.ISpecInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class SpecController extends  ApiBaseController {
    private final String _MODEL_NAME = "/spec";

    @Autowired
    ISpecInfoService specInfoService;

    @RequestMapping(value = _MODEL_NAME + "/list")
    public String list() {
        Map<String,String> map = new HashMap<>();
        List<SpecInfo> list = specInfoService.findByAll();
        //map.put("isStatus",dictService.getValueByCode(_APP_IS_REVIEW));
        return setResult(SpecInfoVO.list2vo(list));
    }

    @RequestMapping(value = _MODEL_NAME + "/edit")
    public String edit(int id,int sex,String company,int titleId,int typeId,int territoryId,String subdivision,
                       String cardName,String identityNo,String bankName,String cardNo ) {

        SpecInfo specInfo  = specInfoService.findById(id);
        if(specInfo==null ){
            return setResultError("用户不存在");
        }
        specInfo.setSex(sex);
        specInfo.setCompany(company);
        specInfo.setTitleId(titleId);
        specInfo.setSpecTypeId(typeId);
        specInfo.setTerritoryId(territoryId);
        specInfo.setSubdivision(subdivision);
        specInfo.setCardName(cardName);
        specInfo.setIdentityNo(identityNo);
        specInfo.setBankName(bankName);
        specInfo.setCardNo(cardNo);
        specInfoService.update(specInfo);
        return setResult();
    }



}

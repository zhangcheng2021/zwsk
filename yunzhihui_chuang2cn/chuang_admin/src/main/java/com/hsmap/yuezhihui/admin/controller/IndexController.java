package com.hsmap.yuezhihui.admin.controller;

import com.hsmap.yuezhihui.config.SysConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class IndexController extends AdminBaseController {


    @Autowired
    private SysConfig config;


    @RequestMapping("/")
    public ModelAndView hello() {
        ModelAndView mv = forwardView("home/index");
        return mv;
        //return "Hello Spring Boot!"+config.getSysname();
    }

//    /***
//     *
//     * @return
//     */
//    @RequestMapping("/test")
//    public String test(){
////        String date = CommonUtil.getDateStringByPattern(DateUtils.addDate(new Date(),-1),userAssetCollectService.dateFormat());
////        userAssetCollectService.totalUserAsset(1,date,date);
//        UserInvite  model   = new UserInvite();
//        String date = CommonUtil.getDateStringByPattern(Constants._DATE_FORMAT);
//        model.setStartDate(date);
//        model.setEndDate(date);
//        int count  = inviteService.inviteUserCount(model);
//        return setResult(count);
//
//    }





}

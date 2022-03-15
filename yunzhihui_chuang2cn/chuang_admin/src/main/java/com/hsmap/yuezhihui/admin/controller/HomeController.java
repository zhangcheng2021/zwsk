package com.hsmap.yuezhihui.admin.controller;

import com.hsmap.yuezhihui.Constants;
import com.hsmap.yuezhihui.common.CommonUtil;
import com.hsmap.yuezhihui.common.DateUtils;
import com.hsmap.yuezhihui.service.sys.ISysRoleService;
import com.hsmap.yuezhihui.service.sys.ISysUserInfoService;
import com.hsmap.yuezhihui.vo.sys.SysMenuInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

//import com.hsmap
// .yuezhihui.entity.user.UserInvite;

@RestController
public class HomeController extends AdminBaseController {
    //模块名称
    private final String _MODEL_NAME="home";
//
//    @Autowired
//    IArticleBannerService bannerService;

    @Autowired
    ISysRoleService roleService;
    @Autowired
    ISysUserInfoService userInfoService;
//    @Autowired
//    IArticleInfoService articleInfoService;

//    @Autowired
//    IUserSumCollectService userSumCollectService;


    @RequestMapping(value = _MODEL_NAME+"/index")
    public ModelAndView index(HttpSession httpSession){
        List<SysMenuInfoVO> voList = getRoleMenuSession(httpSession);
        LOGGER.info("menu size:{}",voList.size());
        for (SysMenuInfoVO vo:voList) {
            LOGGER.info("vo name:{}",vo.getName());
        }
        ModelAndView mv = createView(_MODEL_NAME+"/index");
        mv.addObject("voList",voList);
        return mv;
    }


    @RequestMapping(value = _MODEL_NAME+"/welcome")
    public ModelAndView welcome(HttpSession httpSession){
        ModelAndView mv = createView(_MODEL_NAME+"/welcome");
//        SysUserInfo userInfoSession =  getSysUserInfoSession(httpSession);
//        UserSumCollect dayCollect = userSumCollectService.getDayCollectByRedis();
//        mv.addObject("dayCollect",dayCollect);
        Date yesterdayDate = DateUtils.addDate(new Date(),-1);
        String yesterdayStr = CommonUtil.getDateStringByPattern(yesterdayDate,Constants._DATE_FORMAT);
//        UserSumCollect yesterdayData= userSumCollectService.findByDate(yesterdayStr);
//        mv.addObject("yesterdayData",yesterdayData);
//        UserSumCollect todayData = userSumCollectService.findByToday();
//        mv.addObject("todayData",todayData);

        return mv;
    }



//    @RequestMapping("/404")
//    public ModelAndView error_404() {
//        ModelAndView mv = createView(_MODEL_NAME+"/404");
//        return mv;
//    }
//
//    @RequestMapping("/500")
//    public ModelAndView error_500() {
//        ModelAndView mv = createView(_MODEL_NAME+"/500");
//        return mv;
//    }


    @RequestMapping(value = _MODEL_NAME+"/test")
    public String test(HttpSession httpSession, int day){

//        Date startDate = DateUtils.addDate(new Date(), day);
//        UserSumCollect
//             userSumCollect = userSumCollectService.totalByDate(CommonUtil.getDateStringByPattern(startDate, "yyyy-MM-dd"));
//
//        userSumCollectService.saveOrUpdate(userSumCollect);
//        UserInvite model   = new UserInvite();
//        for(;day<=-1;day++){
//            Date startDate = DateUtils.addDate(new Date(), day);
//            String date = CommonUtil.getDateStringByPattern(startDate, Constants._DATE_FORMAT);
////            userSumCollectService.totalSumCollect(date,date);
//        }
//        String realname = "唐基雄";
//        int money = 5000;
//        String bank_name = "中国农行银行";
//        String card_number = "6228480329549004771";
//        String out_trade_no = CommonUtil.getDateStringByPattern("yyyyMMddHHmmss")+"_"+ EncryptUtils.getRandomChar(8);
//        LOGGER.info("out_trade_no:{}",out_trade_no);
//        int type = 1;
//        String idcard = "430726198801195433";
//        String phone = "13777443718";

//        XindePayUtils xindePayUtils = new XindePayUtils();
//        xindePayUtils.apidaifu(realname,money,bank_name,card_number,out_trade_no,type);

//
        //String endDate = CommonUtil.getDateStringByPattern(Constants._DATE_FORMAT);
//        model.setStartDate(startDate1);
//        model.setEndDate(date);
//        List<Map<String,Object>> list = inviteService.inviteList(model);
        //UserSumCollect userSumCollect = userSumCollectService.totalData(startDate1,endDate);



//        return setResult(userSumCollect);
//        OrderInfo model = new OrderInfo();
//        model.setStartDate(startDate1);
//        model.setEndDate(endDate);
//        List<Map<String,Object>> list =  orderInfoService.orderPriceGroupUser(model);
//        return setResult(list);
        return "";
    }


}

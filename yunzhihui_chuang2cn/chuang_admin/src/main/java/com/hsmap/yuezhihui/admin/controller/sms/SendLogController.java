package com.hsmap.yuezhihui.admin.controller.sms;

import com.hsmap.yuezhihui.admin.controller.AdminBaseController;
import com.hsmap.yuezhihui.base.page.PageInfo;
import com.hsmap.yuezhihui.base.page.Pageable;
import com.hsmap.yuezhihui.entity.sys.SysSmsSendLog;
import com.hsmap.yuezhihui.service.sms.ISysSmsSendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class SendLogController  extends AdminBaseController {
    @Autowired
    ISysSmsSendService sysSmsSendService;

    //模块名称
    private final String _MODEL_NAME = "sms/send";

    @RequestMapping(value = _MODEL_NAME + "/list")
    public ModelAndView list( String mobile, @RequestParam(name = "pageNumber", defaultValue = "1") int pageNumber) {
        LOGGER.info("list,mobile:{},pageNumber:{}", mobile, pageNumber);
        ModelAndView mv = createView(_MODEL_NAME + "/list");

        SysSmsSendLog sendLog = new SysSmsSendLog();
        sendLog.setMobile(mobile);
        Pageable pageable = getPageable(pageNumber);
        PageInfo<SysSmsSendLog> pageInfo = sysSmsSendService.search(sendLog, pageable);
        mv.addObject("pageInfo", pageInfo);
        mv.addObject("mobile", mobile);
        mv.addObject("_PARENT_MENU_NAME","短信管理");
        mv.addObject("_MENU_NAME","短信记录查询");
        return mv;
    }

    @RequestMapping(value = _MODEL_NAME + "/test")
    public String list() {

//        sysSmsSendService.sendMsg("ST_20200209b4c5Z3Q1","15010339152", CommonUtil.getRandomByCount(4));
//        sysSmsSendService.sendMsg("ST_20200209b4c5Z3Q1","13777443718", CommonUtil.getRandomByCount(4));
//        sysSmsSendService.sendMsg("ST_20200209b4c5Z3Q1","13787863786", CommonUtil.getRandomByCount(4));
//        sysSmsSendService("ST_20200209_W8V0C0F0","13787863786","天发","400-888-9999");
        return setResult();

    }


}

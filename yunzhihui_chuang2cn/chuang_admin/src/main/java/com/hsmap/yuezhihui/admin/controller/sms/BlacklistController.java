package com.hsmap.yuezhihui.admin.controller.sms;

import com.hsmap.yuezhihui.admin.controller.AdminBaseController;
import com.hsmap.yuezhihui.base.page.PageInfo;
import com.hsmap.yuezhihui.base.page.Pageable;
import com.hsmap.yuezhihui.common.CommonUtil;
import com.hsmap.yuezhihui.entity.sys.SysSmsBlacklist;
import com.hsmap.yuezhihui.service.sms.ISysSmsBlacklistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/***
 * 短信黑名单管理
 */
@RestController
public class BlacklistController extends AdminBaseController {

    //模块名称
    private final String _MODEL_NAME = "sms/black";

    @Autowired
    ISysSmsBlacklistService sysSmsBlacklistService;

    @RequestMapping(value = _MODEL_NAME + "/list/{type}")
    public ModelAndView list(@PathVariable int type, String mobile, @RequestParam(name = "pageNumber", defaultValue = "1") int pageNumber) {
        LOGGER.info("list type:{},mobile:{},pageNumber:{}", type, mobile, pageNumber);
        ModelAndView mv = createView(_MODEL_NAME + "/list");

        SysSmsBlacklist blacklist = new SysSmsBlacklist();
        blacklist.setMobile(mobile);
        blacklist.setType(type);
        Pageable pageable = getPageable(pageNumber);
        PageInfo<SysSmsBlacklist> pageInfo = sysSmsBlacklistService.search(blacklist, pageable);
        mv.addObject("pageInfo", pageInfo);
        mv.addObject("type", type);
        mv.addObject("mobile", mobile);
        mv.addObject("_PARENT_MENU_NAME","短信管理");
        mv.addObject("_MENU_NAME","短信"+(type==1?"黑":"白")+"管理");
        return mv;
    }


    @RequestMapping(value = _MODEL_NAME + "/addInput/{type}")
    public ModelAndView addInput(@PathVariable int type) {
        ModelAndView mv = createView(_MODEL_NAME + "/add");
        //List<SysMenuInfo> parentList = sysMenuInfoService.findByParent(0);
        mv.addObject("type", type);
        return mv;
    }

    @RequestMapping(value =  _MODEL_NAME + "/save")
    public String save(String mobile, int type, String remark) {
        LOGGER.info("mobile:" + mobile + ",type:" + type);

        if (type != 1 && type != 2) {
            return setResultError("名单类型不正确");
        }
        if (CommonUtil.isEmpty(mobile)) {
            return setResultError("手机号码不能为空");
        }

        boolean b = sysSmsBlacklistService.mobileIsBlackByType(mobile, type);
        if (b) {
            return setResultError("手机号码已经存在");

        }
        SysSmsBlacklist blacklist = new SysSmsBlacklist();
        blacklist.setType(type);
        blacklist.setMobile(mobile);
        blacklist.setRemark(remark);
        int count = sysSmsBlacklistService.save(blacklist);
        //同步一下redis数据
        if (count > 0) {
            sysSmsBlacklistService.syncType2Redis(type);

        }

        return setResult();
    }


    @RequestMapping(value = _MODEL_NAME + "/editInput")
    public ModelAndView editInput(int id) {
        ModelAndView mv = createView(_MODEL_NAME + "/edit");
        SysSmsBlacklist blacklist = sysSmsBlacklistService.findById(id);
        mv.addObject("blacklist", blacklist);
        return mv;
    }

    @RequestMapping(value = _MODEL_NAME +"/edit")
    public String edit(int id, String mobile, String remark) {

        if (CommonUtil.isEmpty(mobile)) {
            return setResultError("手机号码不能为空");
        }
        SysSmsBlacklist sysSmsBlacklist = sysSmsBlacklistService.findById(id);
        if (sysSmsBlacklist == null) {
            return setResultError("根据ID查询数据为空，请确认");
        }

        sysSmsBlacklist.setMobile(mobile);
        sysSmsBlacklist.setRemark(remark);
        sysSmsBlacklistService.update(sysSmsBlacklist);
        sysSmsBlacklistService.syncType2Redis(sysSmsBlacklist.getType());
        return setResult();
    }

    @RequestMapping(value = _MODEL_NAME +"/delete")
    public String delete(int id) {
        LOGGER.info("delete  id:{}",id);

        try {
            SysSmsBlacklist sysSmsBlacklist = sysSmsBlacklistService.findById(id);
            if (sysSmsBlacklist == null) {
                return setResultError("根据ID查询数据为空，请确认");
            }

            sysSmsBlacklistService.deleteById(id);

            sysSmsBlacklistService.syncType2Redis(sysSmsBlacklist.getType());
            return setResult();
        }catch(Exception ex){
            ex.printStackTrace();
            return setResultError();
        }

    }


}

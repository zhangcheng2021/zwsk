package com.hsmap.yuezhihui.admin.controller.sms;

import com.hsmap.yuezhihui.admin.controller.AdminBaseController;
import com.hsmap.yuezhihui.base.page.PageInfo;
import com.hsmap.yuezhihui.base.page.Pageable;
import com.hsmap.yuezhihui.common.CommonUtil;
import com.hsmap.yuezhihui.encrypt.EncryptUtils;
import com.hsmap.yuezhihui.entity.sys.SysSmsTemplate;
import com.hsmap.yuezhihui.service.sms.ISysSmsTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/***
 * 短信模板管理
 */
@RestController
public class TemplateController  extends AdminBaseController {

    //模块名称
    private final String _MODEL_NAME = "sms/template";
    @Autowired
    ISysSmsTemplateService sysSmsTemplateService;

    @RequestMapping(value = _MODEL_NAME + "/list")
    public ModelAndView list(String name,String content,String remark, @RequestParam(name = "pageNumber", defaultValue = "1") int pageNumber) {
        LOGGER.info("list pageNumber:{}", pageNumber);
        ModelAndView mv = createView(_MODEL_NAME + "/list");
        Pageable pageable = getPageable(pageNumber);

        SysSmsTemplate template =new SysSmsTemplate();

        PageInfo<SysSmsTemplate> pageInfo = sysSmsTemplateService.list(template, pageable);
        mv.addObject("pageInfo", pageInfo);
        mv.addObject("_PARENT_MENU_NAME","短信管理");
        mv.addObject("_MENU_NAME","短信模板管理");
        return mv;
    }



    @RequestMapping(value = _MODEL_NAME + "/addInput")
    public ModelAndView addInput() {
        ModelAndView mv = createView(_MODEL_NAME + "/add");
        return mv;
    }

    @RequestMapping(value =  _MODEL_NAME + "/save")
    public String save(String name,String content,String remark) {
        if (CommonUtil.isEmpty(name)) {
            return setResultError("模板名称不能为空");
        }
        if (CommonUtil.isEmpty(content)) {
            return setResultError("模板内容不能为空");
        }

        String code = "ST_"+CommonUtil.getDateStringByPattern("yyyyMMdd")+"_"+EncryptUtils.getRandomChar(8).toUpperCase();
        SysSmsTemplate template = new SysSmsTemplate();
        template.setCode(code);
        template.setName(name);
        template.setContent(content);
        template.setRemark(remark);
        template.setStatus(0);
        sysSmsTemplateService.save(template);

        sysSmsTemplateService.syncAll2Redis(0);
        return setResult();
    }


    @RequestMapping(value = _MODEL_NAME + "/editInput")
    public ModelAndView editInput(int id) {
        ModelAndView mv = createView(_MODEL_NAME + "/edit");
        SysSmsTemplate template = sysSmsTemplateService.findById(id);
        mv.addObject("template", template);
        return mv;
    }

    @RequestMapping(value = _MODEL_NAME +"/edit")
    public String edit(int id, String name,String content,String remark) {
        if (CommonUtil.isEmpty(name)) {
            return setResultError("模板名称不能为空");
        }
        if (CommonUtil.isEmpty(content)) {
            return setResultError("模板内容不能为空");
        }
        SysSmsTemplate template = sysSmsTemplateService.findById(id);
        if (template == null) {
            return setResultError("根据ID查询数据为空，请确认");
        }

        template.setName(name);
        template.setContent(content);
        template.setRemark(remark);
        sysSmsTemplateService.update(template);

        sysSmsTemplateService.syncAll2Redis(0);
        return setResult();
    }

    @RequestMapping(value = _MODEL_NAME +"/delete")
    public String delete(int id) {
        LOGGER.info("delete  id:{}",id);

        try {
            SysSmsTemplate template = sysSmsTemplateService.findById(id);
            if (template == null) {
                return setResultError("根据ID查询数据为空，请确认");
            }

            template.setStatus( -1);
            sysSmsTemplateService.update(template);

            sysSmsTemplateService.syncAll2Redis(0);

            return setResult();
        }catch(Exception ex){
            ex.printStackTrace();
            return setResultError();
        }

    }
}

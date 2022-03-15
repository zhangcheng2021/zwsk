package com.hsmap.yuezhihui.admin.controller.core;

import com.alibaba.fastjson.JSON;
import com.hsmap.yuezhihui.admin.controller.AdminBaseController;
import com.hsmap.yuezhihui.base.page.PageInfo;
import com.hsmap.yuezhihui.base.page.Pageable;
import com.hsmap.yuezhihui.entity.core.CoreSpecType;
import com.hsmap.yuezhihui.service.core.ICoreSpecTypeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
@RestController
public class SpecTypeController extends AdminBaseController {

    //模块名称
    private final String _MODEL_NAME = "core/specType";

    @Autowired
    ICoreSpecTypeService specTypeService;



    @RequestMapping(value = _MODEL_NAME + "/list")
    public ModelAndView list(String name, String content, String remark, @RequestParam(name = "pageNumber", defaultValue = "1") int pageNumber) {
        LOGGER.info("list pageNumber:{}", pageNumber);
        ModelAndView mv = createView(_MODEL_NAME + "/list");
        Pageable pageable = getPageable(pageNumber);
        CoreSpecType model =new CoreSpecType();
        PageInfo<CoreSpecType> pageInfo = specTypeService.pageList(model, pageable);
        mv.addObject("pageInfo", pageInfo);
        mv.addObject("_PARENT_MENU_NAME","基础数据");
        mv.addObject("_MENU_NAME","专家类型管理");
        return mv;
    }



    @RequestMapping(value = _MODEL_NAME + "/addInput")
    public ModelAndView addInput() {
        ModelAndView mv = createView(_MODEL_NAME + "/add");
        return mv;
    }
    @RequestMapping(value = _MODEL_NAME + "/add")
    public String save(HttpServletRequest request, @RequestBody String json) {
        LOGGER.info("json:{}", json);

        CoreSpecType model = JSON.parseObject(json, CoreSpecType.class);
        specTypeService.save(model);
        return setResult();
    }


    @RequestMapping(value = _MODEL_NAME + "/editInput")
    public ModelAndView editInput(int id) {
        ModelAndView mv = createView(_MODEL_NAME + "/edit");
        CoreSpecType model = specTypeService.findById(id);
        mv.addObject("model", model);
        return mv;
    }

    @RequestMapping(value = _MODEL_NAME + "/edit")
    public String edit(@RequestBody String json) {
        LOGGER.info("json:{}", json);
        CoreSpecType model =  JSON.parseObject(json, CoreSpecType.class);

        CoreSpecType info = specTypeService.findById(model.getId());
        if (info == null) {
            return setResultError("根据ID查询数据为空，请确认");
        }
        BeanUtils.copyProperties(model, info);
        specTypeService.update(info);
        return setResult();
    }

    @RequestMapping(value = _MODEL_NAME + "/delete")
    public String delete(int id) {
        LOGGER.info("delete  id:{}", id);
        CoreSpecType info = specTypeService.findById(id);
        if (info == null) {
            return setResultError("根据ID查询数据为空，请确认");
        }
        info.setIsDel(-1);
        specTypeService.update(info);
        return setResult();
    }
}

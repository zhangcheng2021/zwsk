package com.hsmap.yuezhihui.admin.controller.sys;

import com.alibaba.fastjson.JSON;
import com.hsmap.yuezhihui.admin.controller.AdminBaseController;
import com.hsmap.yuezhihui.base.page.PageInfo;
import com.hsmap.yuezhihui.base.page.Pageable;
import com.hsmap.yuezhihui.common.CommonUtil;
import com.hsmap.yuezhihui.config.SysConfig;
import com.hsmap.yuezhihui.entity.sys.SysAppIcon;
import com.hsmap.yuezhihui.enumcode.sys.AppIconTypeEnum;
import com.hsmap.yuezhihui.service.sys.ISysAppIconService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@RestController
public class SysAppIconController  extends AdminBaseController {
    //模块名称
    private final String _MODEL_NAME = "sys/appicon/";

    @Autowired
    ISysAppIconService appIconService;

    @Autowired
    SysConfig sysConfig;

    @RequestMapping(value = _MODEL_NAME + "list")
    public ModelAndView list(@RequestParam(name = "pageNumber", defaultValue = "1") int pageNumber,
                             String name, @RequestParam(name = "type_", defaultValue = "-1") int type_
    ) {
        LOGGER.info("type_:{}",type_);
        ModelAndView mv = createView(_MODEL_NAME + "/list");
        SysAppIcon model = new SysAppIcon();
        model.setType(type_);
        if(!CommonUtil.isEmpty(name)){
            model.setName(name);
        }
        Pageable pageable = getPageable(pageNumber);
        PageInfo<SysAppIcon> pageInfo =appIconService.searchList(model,pageable);
        mv.addObject("pageInfo", pageInfo);
        mv.addObject("map",AppIconTypeEnum.map);
        mv.addObject("model", model);
        mv.addObject("_PARENT_MENU_NAME", "系统管理");
        mv.addObject("_MENU_NAME", "app图标管理");
        mv.addObject("_IMAGES_DOMAIN", sysConfig.getImagesDomain());
        return mv;
    }


    @RequestMapping(value = _MODEL_NAME + "addInput")
    public ModelAndView addInput() {
        ModelAndView mv = createView(_MODEL_NAME + "/add");
        mv.addObject("map", AppIconTypeEnum.map);
        return mv;
    }

    @RequestMapping(value = _MODEL_NAME + "add")
    public String save(HttpServletRequest request, @RequestBody String json) {
        LOGGER.info("json:{}", json);

        SysAppIcon appIcon = JSON.parseObject(json, SysAppIcon.class);
        appIconService.save(appIcon);
        appIconService.pushActivityByRedis(appIcon.getType());
        //articleAdvertSpaceService.pushActivityByRedis();
        return setResult();
    }


    @RequestMapping(value = _MODEL_NAME + "editInput")
    public ModelAndView editInput(int id) {
        ModelAndView mv = createView(_MODEL_NAME + "/edit");

        SysAppIcon appIcon = appIconService.findById(id);
        mv.addObject("model", appIcon);
        mv.addObject("map", AppIconTypeEnum.map);
        return mv;
    }

    @RequestMapping(value = _MODEL_NAME + "edit")
    public String edit(@RequestBody String json) {
        LOGGER.info("json:{}", json);
        SysAppIcon appIcon =  JSON.parseObject(json, SysAppIcon.class);

        SysAppIcon info = appIconService.findById(appIcon.getId());
//        goodsInfoService.save(goodsInfo);

        if (info == null) {
            return setResultError("根据ID查询数据为空，请确认");
        }
        BeanUtils.copyProperties(appIcon, info);
        appIconService.update(info);
        appIconService.pushActivityByRedis(info.getType());
        return setResult();
    }

    @RequestMapping(value = _MODEL_NAME + "delete")
    public String delete(int id) {
        LOGGER.info("delete  id:{}", id);
        SysAppIcon info = appIconService.findById(id);
        if (info == null) {
            return setResultError("根据ID查询数据为空，请确认");
        }
        info.setIsDel(-1);
        appIconService.update(info);
        appIconService.pushActivityByRedis(info.getType());
        return setResult();
    }

    /***
     * 启用/停用
     * @param id
     * @param status
     * @return
     */
    @RequestMapping(value = _MODEL_NAME + "dispark")
    public String dispark(int id,int status) {
        LOGGER.info("dispark  id:{},status:{}", id,status);
        SysAppIcon info = appIconService.findById(id);
        if (info == null) {
            return setResultError("根据ID查询数据为空，请确认!");
        }
        info.setIsDispark(status);
        appIconService.update(info);
        appIconService.pushActivityByRedis(info.getType());
        return setResult();
    }

    @RequestMapping(value = _MODEL_NAME + "syncRedis")
    public String syncRedis() {
        LOGGER.info("syncRedis start");
        try {
            AppIconTypeEnum.map.forEach((k,v)->
            {
                appIconService.pushActivityByRedis(k);
            });

        } catch (Exception ex) {
            ex.printStackTrace();
            return setResultError();
        }
        return setResult();

    }

}

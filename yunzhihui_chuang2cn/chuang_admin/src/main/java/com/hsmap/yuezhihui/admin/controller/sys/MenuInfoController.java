package com.hsmap.yuezhihui.admin.controller.sys;

import com.hsmap.yuezhihui.admin.controller.AdminBaseController;
import com.hsmap.yuezhihui.base.page.PageInfo;
import com.hsmap.yuezhihui.common.CommonUtil;
import com.hsmap.yuezhihui.entity.sys.SysMenuInfo;
import com.hsmap.yuezhihui.service.sys.ISysMenuInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class MenuInfoController extends AdminBaseController {

    //模块名称
    private final String _MODEL_NAME="sys/menu";

    @Autowired
    private ISysMenuInfoService sysMenuInfoService;

    @RequestMapping(value = _MODEL_NAME+"/list")
    public ModelAndView list(Model model, String name, String parentId, @RequestParam(name = "pageNumber",defaultValue = "1") int pageNumber){
        LOGGER.info("MenuInfoController->list,name:{},parentId:{},pageNumber:{}",name,parentId,pageNumber);
        ModelAndView mv = createView(_MODEL_NAME+"/list");
        SysMenuInfo menuInfo = new SysMenuInfo();
        menuInfo.setName(name);

        menuInfo.setParent(CommonUtil.isEmpty(parentId)?0:Integer.parseInt(parentId));

        PageInfo<SysMenuInfo> pageInfo =sysMenuInfoService.seachList(menuInfo,getPageable(pageNumber));
        List<SysMenuInfo> parentList = sysMenuInfoService.findByParent(0);
        mv.addObject("parent",parentId);
        mv.addObject("pageInfo",pageInfo);
        mv.addObject("name",name);

        mv.addObject("parentMap",sysMenuInfoService.obj2Map(parentList));
        mv.addObject("_PARENT_MENU_NAME","系统管理");
        mv.addObject("_MENU_NAME","菜单管理");
        return mv;
    }

    @RequestMapping(value = _MODEL_NAME+"/addInput")
    public ModelAndView addInput() {
        ModelAndView mv = createView(_MODEL_NAME+"/add");
        List<SysMenuInfo> parentList = sysMenuInfoService.findByParent(0);
        mv.addObject("parentMap",sysMenuInfoService.obj2Map(parentList));
        return mv;
    }


    @RequestMapping(value = _MODEL_NAME+"/add")
    public String add(String name,String url,String  parentId ) {
        LOGGER.info("MenuInfoController->add,name:{},url:{},parent:{}",name,url,parentId);
        SysMenuInfo info = new SysMenuInfo();
        info.setName(name);
        info.setUrl(url);
        info.setParent(Integer.parseInt(parentId));
        int count = sysMenuInfoService.save(info);
        if (count > 0) {
            return setResult(info);
        } else {
            return setResultError("数据保存过程中发生错误");
        }
    }

    @RequestMapping(value = _MODEL_NAME+"/editInput")
    public ModelAndView editInput(int id) {
        ModelAndView mv = createView(_MODEL_NAME+"/edit");
        SysMenuInfo menuInfo = sysMenuInfoService.findById(id);
        List<SysMenuInfo> parentList = sysMenuInfoService.findByParent(0);
        mv.addObject("parentMap",sysMenuInfoService.obj2Map(parentList));
        mv.addObject("menuInfo",menuInfo);
        return mv;
    }


    @RequestMapping(value = _MODEL_NAME+"/edit")
    public String edit(int id,String name,String url,int parent) {
        SysMenuInfo info = sysMenuInfoService.findById(id);
        if (info == null) {
            return setResultError("对象不存在，请确认");
        }
        info.setName(name);
        info.setUrl(url);
        info.setParent(parent);
        int count = sysMenuInfoService.update(info);
        LOGGER.info("MenuInfoController->edit,count:{}",count);
        return setResult(info);
    }


    @RequestMapping(value = _MODEL_NAME+"/delete")
    public String delete(Integer id,Integer  status) {
        SysMenuInfo info = sysMenuInfoService.findById(id);
        if (info == null) {
            return setResultError("对象不存在，请确认");
        }
        info.setIsDel(status);
        int count = sysMenuInfoService.update(info);
        LOGGER.info("MenuInfoController->delete,count:{}",count);
        return setResult(info);
    }


}

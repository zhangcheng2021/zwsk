package com.hsmap.yuezhihui.admin.controller.sys;

import com.hsmap.yuezhihui.admin.controller.AdminBaseController;
import com.hsmap.yuezhihui.base.page.PageInfo;
import com.hsmap.yuezhihui.entity.sys.SysRole;
import com.hsmap.yuezhihui.vo.sys.SysMenuInfoVO;
import com.hsmap.yuezhihui.service.sys.ISysMenuInfoService;
import com.hsmap.yuezhihui.service.sys.ISysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class RoleController  extends AdminBaseController {

    //模块名称
    private final String _MODEL_NAME="sys/role";
    @Autowired
    private ISysRoleService sysRoleService;
    @Autowired
    private ISysMenuInfoService sysMenuInfoService;
    @RequestMapping(value = _MODEL_NAME+"/list")
    public ModelAndView list(@RequestParam(name = "pageNumber",defaultValue = "1") int pageNumber){
        ModelAndView mv = createView(_MODEL_NAME+"/list");
//        List<SysRole> roleList = sysRoleService.list();
//        List<SysMenuInfoVO> menu
        SysRole model = new SysRole();
        PageInfo<SysRole> pageInfo = sysRoleService.searchList(model,getPageable(pageNumber));
        mv.addObject("pageInfo",pageInfo);
        mv.addObject("_PARENT_MENU_NAME","系统管理");
        mv.addObject("_MENU_NAME","角色管理");
        return mv;
    }

    @RequestMapping(value = _MODEL_NAME+"/addInput")
    public ModelAndView addInput() {
        ModelAndView mv = createView(_MODEL_NAME+"/add");
        List<SysMenuInfoVO> pageInfo = sysMenuInfoService.findVoByList(sysMenuInfoService.findByAll());

        mv.addObject("pageInfo",pageInfo);
        return mv;
    }

    @RequestMapping(value = _MODEL_NAME+"/add")
    public String add(String name,String menuIds,String remark) {
        LOGGER.info("sysrole add name:{},menuIds:{},remark:{}",name,menuIds,remark);
        SysRole sysRole = new SysRole();
        sysRole.setName(name);
        sysRole.setMenuIds(menuIds);
        sysRole.setRemark(remark);
        sysRoleService.save(sysRole);
        return setResult();
    }




    @RequestMapping(value = _MODEL_NAME+"/editInput")
    public ModelAndView editInput(Integer id) {
        ModelAndView mv = createView(_MODEL_NAME+"/edit");
        SysRole role = sysRoleService.findById(id);
        List<SysMenuInfoVO> pageInfo = sysMenuInfoService.findVoByList(sysMenuInfoService.findByAll());
        mv.addObject("pageInfo",pageInfo);
        mv.addObject("sysRole",role);
        return mv;
    }

    @RequestMapping(value = _MODEL_NAME+"/edit")
    public String edit(Integer  id,String name,String menuIds,String remark) {
        LOGGER.info("sysrole edit,id:{},name:{},menuIds:{},remark:{}",id,name,menuIds,remark);
        SysRole sysRole = sysRoleService.findById(id);
        if (sysRole == null) {
            return setResultError("不是有效Id");
        }
        sysRole.setName(name);
        sysRole.setMenuIds(menuIds);
        sysRole.setRemark(remark);
        sysRoleService.update(sysRole);
        return setResult(sysRole);
    }

    @RequestMapping(value = _MODEL_NAME+"/delete")
    public String delete(Integer  id,Integer status) {
        LOGGER.info("sysrole delete,id:{},status:{}",id,status);
        SysRole sysRole = sysRoleService.findById(id);
        if (sysRole == null) {
            return setResultError("不是有效Id");
        }
        sysRole.setStatus(status);
        sysRoleService.update(sysRole);
        return setResult(sysRole);
    }







}

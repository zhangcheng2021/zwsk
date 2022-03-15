package com.hsmap.yuezhihui.admin.controller.sys;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hsmap.yuezhihui.admin.controller.AdminBaseController;
import com.hsmap.yuezhihui.base.page.PageInfo;
import com.hsmap.yuezhihui.common.CommonUtil;

import com.hsmap.yuezhihui.entity.review.ReviewBatch;
import com.hsmap.yuezhihui.entity.review.ReviewGroup;
import com.hsmap.yuezhihui.entity.sys.SysRole;
import com.hsmap.yuezhihui.entity.sys.SysUserInfo;
import com.hsmap.yuezhihui.service.review.IReviewBatchService;
import com.hsmap.yuezhihui.service.review.IReviewGroupService;
import com.hsmap.yuezhihui.service.sys.ISysDictService;
import com.hsmap.yuezhihui.service.sys.ISysRoleService;
import com.hsmap.yuezhihui.service.sys.ISysUserInfoService;
import com.hsmap.yuezhihui.vo.account.LoginInfoVO;
import org.apache.commons.collections.list.TreeList;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;
import java.util.stream.Collectors;

@RestController
public class SysUserInfoController extends AdminBaseController {

    //模块名称
    private final String _MODEL_NAME="sys/userinfo";

    @Autowired
    ISysUserInfoService sysUserInfoService;
    //评审分组
    @Autowired
    private IReviewGroupService reviewGroupService;

    @Autowired
    ISysRoleService sysRoleService;
    @Autowired
    ISysDictService sysDictService;
    @Autowired
    IReviewBatchService batchService;

    @RequestMapping(value = _MODEL_NAME+"/list")
    public ModelAndView list(Model model, String loginName, @RequestParam(name = "pageNumber",defaultValue = "1") int pageNumber){
        LOGGER.info("SysUserInfoController->list,name:{},pageNumber:{}",loginName,pageNumber);
        ModelAndView mv = createView(_MODEL_NAME+"/list");
        SysUserInfo userInfo = new SysUserInfo();
        userInfo.setLoginName(loginName);


        PageInfo<SysUserInfo> pageInfo =sysUserInfoService.search(userInfo,getPageable(pageNumber));
        List<SysRole> roleList = sysRoleService.list(0);
        mv.addObject("pageInfo",pageInfo);
        mv.addObject("roleMap",sysRoleService.role2Map(roleList));
        mv.addObject("loginName",loginName);
        mv.addObject("_PARENT_MENU_NAME","系统管理");
        mv.addObject("_MENU_NAME","系统用户管理");
        return mv;
    }

    @RequestMapping(value = _MODEL_NAME+"/addInput")
    public ModelAndView addInput() {
        ModelAndView mv = createView(_MODEL_NAME+"/add");
        List<SysRole> roleList = sysRoleService.findByType(sysRoleService.getAgencyTypeValue());
        mv.addObject("roleList",roleList);
        return mv;
    }


    @RequestMapping(value = _MODEL_NAME+"/add")
    public String add(String loginName,String passwd,int role,String userName,String phone,String email,String address,String remark,String qq,String weixin) {
        LOGGER.info("SysUserInfoController->list,loginName:{},passwd:{},role:{},userName:{},phone:{},email:{},address:{},remark:{}",loginName,passwd,role,userName,phone,email,address,remark);
        if (CommonUtil.isEmpty(loginName)) {
            LOGGER.info("loginName为空");
            return setResultError("登录名不能为空");
        }
        if (CommonUtil.isEmpty(passwd)) {
            LOGGER.info("passwd为空");
            return setResultError("密码不能为空");
        }

        String  token  = sysUserInfoService.getNewToken();
        LOGGER.info("create token success,token:{}",token);
        String _passwd = sysUserInfoService.encryptPasswd(passwd,token);
        LOGGER.info("原密码{},加密完成，加密后为:{}",passwd,_passwd);

        SysUserInfo  sysUserInfo =sysUserInfoService.findByLoginName(loginName);
        if(sysUserInfo !=null){
            LOGGER.info("loginName:{},已存在");
            return setResultError("用户名已存在");
        }
        sysUserInfo =new SysUserInfo();
        sysUserInfo.setLoginName(loginName);
        sysUserInfo.setPasswd(_passwd);
        sysUserInfo.setRole(role);
        sysUserInfo.setUserName(userName);
        sysUserInfo.setPhone(phone);
        sysUserInfo.setEmail(email);
        sysUserInfo.setAddress(address);
        sysUserInfo.setRemark(remark);
        sysUserInfo.setQq(qq);
        sysUserInfo.setWeixin(weixin);
        sysUserInfo.setToken(token);
//        sysUserInfo.setChannel("sys");
        sysUserInfo.setChannel("");//空组数据域
        sysUserInfoService.save(sysUserInfo);
        return setResult(sysUserInfo);
    }

    @RequestMapping(value = _MODEL_NAME+"/editInput")
    public ModelAndView editInput(int id) {
        ModelAndView mv = createView(_MODEL_NAME+"/edit");
        List<SysRole> roleList = sysRoleService.list(0);
        SysUserInfo  userInfo = sysUserInfoService.findById(id);
        mv.addObject("userInfo",userInfo);
        mv.addObject("roleList",roleList);
        List<SysUserInfo> parentList = sysUserInfoService.findbyAll(0);
        mv.addObject("parentList",parentList);

        return mv;
    }


    @RequestMapping(value = _MODEL_NAME+"/edit")
    public String edit(Integer id,int role,String userName,String phone,String email,String address,String remark,String qq,String weixin,String  batchRate) {
        LOGGER.info("edit,id:{},batchRate:{}",id,batchRate);
        try {
            SysUserInfo sysUserInfo = sysUserInfoService.findById(id);
            if (sysUserInfo == null) {
                return setResultError("对象不存在，请确认");
            }

            sysUserInfo.setRole(role);
            sysUserInfo.setUserName(userName);
            sysUserInfo.setPhone(phone);
            sysUserInfo.setEmail(email);
            sysUserInfo.setAddress(address);
            sysUserInfo.setRemark(remark);
            sysUserInfo.setWeixin(weixin);
            sysUserInfo.setQq(qq);
            sysUserInfoService.update(sysUserInfo);
            return setResult();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return setResultError();
    }

    @RequestMapping(value = _MODEL_NAME+"/delete")
    public String delete(Integer id,Integer  status) {
        SysUserInfo info = sysUserInfoService.findById(id);
        if (info == null) {
            return setResultError("对象不存在，请确认");
        }
        info.setStatus(status);
        sysUserInfoService.update(info);
        return setResult(info);
    }

    @RequestMapping(value = _MODEL_NAME+"/editPasswdInput")
    public ModelAndView editPasswdInput(int id) {
        ModelAndView mv = createView(_MODEL_NAME+"/editPasswd");
        SysUserInfo  userInfo = sysUserInfoService.findById(id);
        mv.addObject("userInfo",userInfo);
        return mv;
    }


    @RequestMapping(value = _MODEL_NAME+"/editPasswd")
    public String editPasswd(int id,String passwd) {

        SysUserInfo userInfo = sysUserInfoService.findById(id);

        if (userInfo == null) {
            return setResultError("对象不存在，请确认");
        }
        String token =sysUserInfoService.getNewToken();
        String _passwd = sysUserInfoService.encryptPasswd(passwd,token);
        userInfo.setPasswd(_passwd);
        userInfo.setToken(token);
        sysUserInfoService.update(userInfo);
        return setResult();
    }

    @RequestMapping(value = _MODEL_NAME+"/online")
    public ModelAndView online(@RequestParam(name = "pageNumber",defaultValue = "1") int pageNumber){
        ModelAndView mv = createView(_MODEL_NAME+"/online");
       List<LoginInfoVO> voList = sysUserInfoService.onlineList();
        mv.addObject("modelList",voList);
        mv.addObject("_PARENT_MENU_NAME","系统管理");
        mv.addObject("_MENU_NAME","在线用户");
        return mv;
    }


    @RequestMapping(value = _MODEL_NAME+"/offline")
    public String offline(int userId) {
        sysUserInfoService.userOffline(userId);

        return setResult();
    }

    @RequestMapping(value = _MODEL_NAME+"/heartbeat")
    public String heartbeat() {
        LOGGER.info("心跳已经接收到.......");
        return setResult("心跳成功");
    }

    @RequestMapping(value = _MODEL_NAME+"/authorize")
    public ModelAndView authorize(String id) {
        ModelAndView mv = createView(_MODEL_NAME + "/authorize");
        SysUserInfo userInfo = sysUserInfoService.findById(Integer.parseInt(id));
        List<ReviewGroup> batchlist = reviewGroupService.findByAll();
        //根据batchId分组
        Map<Object, List<ReviewGroup>> map = batchlist.stream()
                .collect(
                Collectors.groupingBy(
                        batchId -> batchId.getBatchId()
                ));

            List<ReviewGroup> groups =new ArrayList<>();
        for (Map.Entry<Object, List<ReviewGroup>> entry : map.entrySet()) {
            //获取list集合
           List<ReviewGroup> groupList = entry.getValue();
            if(groupList!=null && groupList.size()>0){
                groupList.forEach(item -> {
                    ReviewGroup r = new ReviewGroup();
                    r.setId(item.getId());
                    r.setName(item.getName());
                    r.setBatchId(item.getBatchId());
                    groups.add(r);
                });
            }
        }
        mv.addObject("groupList", groups);
        mv.addObject("reviewGroup", userInfo.getChannel());
        mv.addObject("userId",id);
        return mv;
    }



    @RequestMapping(value = _MODEL_NAME+"/editChannel")
    public String editChannel(Integer id,String batchId) {
        LOGGER.info("edit,id:{},batchId:{}",id,batchId);
        try {
            SysUserInfo sysUserInfo = sysUserInfoService.findById(id);
            if (sysUserInfo == null) {
                return setResultError("对象不存在，请确认");
            }
            sysUserInfo.setChannel(batchId);
            sysUserInfoService.update(sysUserInfo);
            return setResult();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return setResultError();
    }

}

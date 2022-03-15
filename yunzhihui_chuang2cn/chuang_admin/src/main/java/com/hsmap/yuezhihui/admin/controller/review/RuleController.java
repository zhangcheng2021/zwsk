package com.hsmap.yuezhihui.admin.controller.review;

import com.alibaba.fastjson.JSON;
import com.hsmap.yuezhihui.admin.controller.AdminBaseController;
import com.hsmap.yuezhihui.base.page.PageInfo;
import com.hsmap.yuezhihui.base.page.Pageable;
import com.hsmap.yuezhihui.entity.core.CoreProjectType;
import com.hsmap.yuezhihui.entity.review.ReviewRule;
import com.hsmap.yuezhihui.entity.review.ReviewRuleDetail;
import com.hsmap.yuezhihui.service.core.ICoreProjectTypeService;
import com.hsmap.yuezhihui.service.review.IReviewRuleDetailService;
import com.hsmap.yuezhihui.service.review.IReviewRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
@RestController
public class RuleController extends AdminBaseController {

    //模块名称
    private final String _MODEL_NAME = "review/rule";

    @Autowired
    IReviewRuleService ruleService;
    @Autowired
    IReviewRuleDetailService ruleDetailService;
    @Autowired
    ICoreProjectTypeService projectTypeService;

    @RequestMapping(value = _MODEL_NAME + "/list")
    public ModelAndView list(String name, String content, String remark, @RequestParam(name = "pageNumber", defaultValue = "1") int pageNumber) {
        LOGGER.info("list pageNumber:{}", pageNumber);
        ModelAndView mv = createView(_MODEL_NAME + "/list");
        Pageable pageable = getPageable(pageNumber);
        ReviewRule model =new ReviewRule();
        PageInfo<ReviewRule> pageInfo = ruleService.pageList(model, pageable);
        mv.addObject("pageInfo", pageInfo);
        pushProjectTypeList(mv);
        mv.addObject("_PARENT_MENU_NAME","评审管理");
        mv.addObject("_MENU_NAME","评分规则管理");
        return mv;
    }



    @RequestMapping(value = _MODEL_NAME + "/addInput")
    public ModelAndView addInput() {
        ModelAndView mv = createView(_MODEL_NAME + "/add");
        pushProjectTypeList(mv);
        return mv;
    }
    @RequestMapping(value = _MODEL_NAME + "/add")
    public String save(String name,String details,String remark,int projectTypeId,int duration) {
        //ReviewRule model = JSON.parseObject(json, ReviewRule.class);
        //specTypeService.save(model);
        LOGGER.info("name:{},details:{},remark:{},projectTypeId:{},duration:{}",name,details,remark,projectTypeId,duration);
        CoreProjectType coreProjectType = projectTypeService.findById(projectTypeId);
        ReviewRule rule = new ReviewRule();
        rule.setName(name);
        rule.setRemark(remark);
        rule.setProjectTypeId(projectTypeId);
        rule.setProjectTypeName(coreProjectType.getName());
        ruleService.save(rule);
        saveDetailList(details,rule.getId());
        return setResult();
    }


    @RequestMapping(value = _MODEL_NAME + "/editInput")
    public ModelAndView editInput(int id) {
        ModelAndView mv = createView(_MODEL_NAME + "/edit");
        ReviewRule model = ruleService.findById(id);
        List<ReviewRuleDetail> detailList = ruleDetailService.findByRuleId(id);
        mv.addObject("detailList",detailList);
        pushProjectTypeList(mv);
        mv.addObject("model", model);
        return mv;
    }

    @RequestMapping(value = _MODEL_NAME + "/edit")
    public String edit(int id,String name,String details,String remark,int projectTypeId,int duration) {
        CoreProjectType coreProjectType = projectTypeService.findById(projectTypeId);
        ReviewRule rule = ruleService.findById(id);
        rule.setName(name);
        rule.setRemark(remark);
        rule.setProjectTypeId(coreProjectType.getId());
        rule.setProjectTypeName(coreProjectType.getName());
        rule.setDuration(duration);
        ruleService.update(rule);
        updateDetailList(details,id);
        return setResult();
    }

    @RequestMapping(value = _MODEL_NAME + "/delete")
    public String delete(int id) {
        LOGGER.info("delete  id:{}", id);
        ReviewRule info = ruleService.findById(id);
        if (info == null) {
            return setResultError("根据ID查询数据为空，请确认");
        }
        info.setIsDel(-1);
        ruleService.update(info);
        return setResult();
    }


    private void pushProjectTypeList(ModelAndView mv ){
        List<CoreProjectType> projectTypeList  = projectTypeService.findByAll();
        mv.addObject("projectTypeList",projectTypeList);
    }

    private void updateDetailList(String details,int ruleId){
        ruleDetailService.deleteByRuleId(ruleId);
        LOGGER.info("details:{}",details);
        String []detailArray = details.split(",");

        ReviewRuleDetail ruleDetail;
        for(int i=0;i<detailArray.length/3;i++){
            String _type = detailArray[i*3];
            String _content = detailArray[i*3+1];
            int _detailId = Integer.parseInt(detailArray[i*3+2]);
            if(_detailId>0) {
                ruleDetail = ruleDetailService.findById(_detailId);
                if(ruleDetail== null ){
                    ruleDetail =new ReviewRuleDetail();
                }

            }else{
                ruleDetail =new ReviewRuleDetail();
                ruleDetail.setId(_detailId);
            }
            ruleDetail.setRuleId(ruleId);
            ruleDetail.setType(_type);
            ruleDetail.setContent(_content);
            if(ruleDetail.getId()>0){
                ruleDetail.setIsDel(0);
                ruleDetailService.update(ruleDetail);
            }else{
                ruleDetailService.save(ruleDetail);
            }

        }
    }

    private void saveDetailList(String details,int ruleId){
        String []detailArray = details.split(",");
        ReviewRuleDetail ruleDetail;
        for(int i=0;i<detailArray.length/2;i++){
            ruleDetail = new ReviewRuleDetail();
            ruleDetail.setRuleId(ruleId);
            ruleDetail.setType(detailArray[i*2]);
            ruleDetail.setContent(detailArray[i*2+1]);
            ruleDetailService.save(ruleDetail);
        }
    }




}

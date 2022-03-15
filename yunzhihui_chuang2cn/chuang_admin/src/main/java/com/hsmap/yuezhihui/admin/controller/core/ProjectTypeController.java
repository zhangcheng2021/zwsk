package com.hsmap.yuezhihui.admin.controller.core;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hsmap.yuezhihui.admin.controller.AdminBaseController;
import com.hsmap.yuezhihui.base.page.PageInfo;
import com.hsmap.yuezhihui.base.page.Pageable;
import com.hsmap.yuezhihui.entity.core.CoreProjectType;
import com.hsmap.yuezhihui.entity.core.CoreSpecType;
import com.hsmap.yuezhihui.entity.review.ReviewWeight;
import com.hsmap.yuezhihui.service.core.ICoreProjectTypeService;
import com.hsmap.yuezhihui.service.core.ICoreSpecTypeService;
import com.hsmap.yuezhihui.service.review.IReviewWeightService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ProjectTypeController extends AdminBaseController {

    //模块名称
    private final String _MODEL_NAME = "core/projectType";

    @Autowired
    ICoreProjectTypeService projectTypeService;

    @Autowired
    ICoreSpecTypeService specTypeService;

    @Autowired
    IReviewWeightService reviewWeightService;


    @RequestMapping(value = _MODEL_NAME + "/list")
    public ModelAndView list(String name, String content, String remark, @RequestParam(name = "pageNumber", defaultValue = "1") int pageNumber) {
        LOGGER.info("list pageNumber:{}", pageNumber);
        ModelAndView mv = createView(_MODEL_NAME + "/list");
        Pageable pageable = getPageable(pageNumber);
        CoreProjectType model =new CoreProjectType();
        PageInfo<CoreProjectType> pageInfo = projectTypeService.pageList(model, pageable);
        mv.addObject("pageInfo", pageInfo);
        mv.addObject("_PARENT_MENU_NAME","基础数据");
        mv.addObject("_MENU_NAME","项目类型管理");
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

        CoreProjectType model = JSON.parseObject(json, CoreProjectType.class);
        projectTypeService.save(model);
        return setResult();
    }


    @RequestMapping(value = _MODEL_NAME + "/editInput")
    public ModelAndView editInput(int id) {
        ModelAndView mv = createView(_MODEL_NAME + "/edit");
        CoreProjectType model = projectTypeService.findById(id);
        mv.addObject("model", model);
        return mv;
    }

    @RequestMapping(value = _MODEL_NAME + "/edit")
    public String edit(@RequestBody String json) {
        LOGGER.info("json:{}", json);
        CoreProjectType model =  JSON.parseObject(json, CoreProjectType.class);

        CoreProjectType info = projectTypeService.findById(model.getId());
        if (info == null) {
            return setResultError("根据ID查询数据为空，请确认");
        }
        BeanUtils.copyProperties(model, info);
        projectTypeService.update(info);
        return setResult();
    }

    @RequestMapping(value = _MODEL_NAME + "/delete")
    public String delete(int id) {
        LOGGER.info("delete  id:{}", id);
        CoreProjectType info = projectTypeService.findById(id);
        if (info == null) {
            return setResultError("根据ID查询数据为空，请确认");
        }
        info.setIsDel(-1);
        projectTypeService.update(info);
        return setResult();
    }

    @RequestMapping(value = _MODEL_NAME + "/weightInput")
    public ModelAndView weightInput(int id) {
        ModelAndView mv = createView(_MODEL_NAME + "/weight");
        CoreProjectType coreProjectType = projectTypeService.findById(id);

        mv.addObject("coreProjectType",coreProjectType);
//        mv.addObject("weightList",weightList);
//        mv.addObject("specTypeMap",specTypeService.list2Map(specTypeList));
        mv.addObject("specTypeList",getSpecTypeList(id));
        return mv;
    }

    @RequestMapping(value = _MODEL_NAME + "/weight")
    public String weight(@RequestBody String json) {
        JSONArray array = JSONArray.parseArray(json);
        for(int i=0;i<array.size();i++){
            JSONObject jsonObject = array.getJSONObject(i);
            int sid = jsonObject.getInteger("sid");
            int pid = jsonObject.getInteger("pid");
            int weight = jsonObject.getInteger("weight");
            ReviewWeight reviewWeight = reviewWeightService.getRedisBySpecAndProject(sid,pid);
            if(reviewWeight == null ){
                reviewWeight = new ReviewWeight();
                reviewWeight.setId(0);
                reviewWeight.setProjectTypeId(String.valueOf(pid));
                reviewWeight.setSpecTypeId(String.valueOf(sid));reviewWeight.setWeight(weight);
                reviewWeightService.save(reviewWeight);
            }else{
                reviewWeight.setWeight(weight);
                reviewWeightService.update(reviewWeight);
            }
            reviewWeightService.pushAllToRedis();
        }

        return setResult();

    }

    private List<CoreSpecType> getSpecTypeList(int id){
        List<CoreSpecType> specTypeList=specTypeService.findRedisByAll();
        List<CoreSpecType> _list =new ArrayList<>();
        for(CoreSpecType type:specTypeList){
            int weight = reviewWeightService.getRedisWeightBySpecAndProject(type.getId(),id);
            type.setWeight(weight);
            _list.add(type);
        }
        return _list;
    }
}

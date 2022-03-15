package com.hsmap.yuezhihui.admin.controller.review;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hsmap.yuezhihui.admin.controller.AdminBaseController;
import com.hsmap.yuezhihui.base.page.PageInfo;
import com.hsmap.yuezhihui.base.page.Pageable;
import com.hsmap.yuezhihui.common.CommonUtil;
import com.hsmap.yuezhihui.entity.core.CoreSpecType;
import com.hsmap.yuezhihui.entity.core.CoreTerritory;
import com.hsmap.yuezhihui.entity.core.CoreTitle;
import com.hsmap.yuezhihui.entity.review.*;
import com.hsmap.yuezhihui.entity.spec.BatchSpecInfo;
import com.hsmap.yuezhihui.entity.spec.SpecInfo;
import com.hsmap.yuezhihui.service.core.ICoreSpecTypeService;
import com.hsmap.yuezhihui.service.core.ICoreTerritoryService;
import com.hsmap.yuezhihui.service.core.ICoreTitleService;
import com.hsmap.yuezhihui.service.review.IReviewBatchService;
import com.hsmap.yuezhihui.service.review.IReviewBatchSpecialistService;
import com.hsmap.yuezhihui.service.spec.ISpecInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ReviewBatchSpecController extends AdminBaseController {

    //模块名称
    private final String _MODEL_NAME = "review/batch";

    @Autowired
    ICoreTitleService titleService;

    @Autowired
    ICoreTerritoryService territoryService;

    @Autowired
    ICoreSpecTypeService specTypeService;
    @Autowired
    IReviewBatchService reviewBatchService;
    @Autowired
    IReviewBatchSpecialistService batchSpecialistService;

    @Autowired
    ISpecInfoService specInfoService;

    /**
     * @param id
     * @param pageNumber
     * @desc 专家列表
     * @Return: org.springframework.web.servlet.ModelAndView
     * @author: 刹那访华
     * @date: 2021/5/24 22:22
     * @Version V1.1.0
     */
    @RequestMapping(value = _MODEL_NAME + "/specListInput")
    public ModelAndView list(int id, @RequestParam(name = "pageNumber", defaultValue = "1") int pageNumber) {
        ModelAndView mv = createView(_MODEL_NAME + "/specList");
        ReviewBatchSpecialist model = new ReviewBatchSpecialist();
        model.setBatchId(id);
        Pageable pageable = getPageable(pageNumber);
        PageInfo<ReviewBatchSpecialist> pageInfo = batchSpecialistService.pageList(model, pageable);
        mv.addObject("pageInfo", pageInfo);
        mv.addObject("_PARENT_MENU_NAME", "评审批次管理");
        mv.addObject("_MENU_NAME", "专家列表");
        mv.addObject("batchId", id);
        return mv;
    }

    /**
     * @param query
     * @param pageNumber
     * @desc 选择专家
     * @Return: org.springframework.web.servlet.ModelAndView
     * @author: 刹那访华
     * @date: 2021/5/24 22:27
     * @Version V1.1.0
     */
    @RequestMapping(value = _MODEL_NAME + "/selectSpecList")
    public ModelAndView selectSpecList(Integer id, SpecInfo query, @RequestParam(name = "pageNumber", defaultValue = "1") int pageNumber) {
        LOGGER.info("list pageNumber:{}", pageNumber);
        ModelAndView mv = createView(_MODEL_NAME + "/selectSpecList");
        Pageable pageable = getPageable(pageNumber);
        PageInfo<SpecInfo> pageInfo = specInfoService.pageList(query, pageable);
        //职称
        wrapTitle(mv);
        //专业领域
        wrapTerritory(mv);
        //专家类别
        wrapSpecType(mv);
        mv.addObject("pageInfo", pageInfo);
        mv.addObject("_PARENT_MENU_NAME", "专家数据");
        mv.addObject("_MENU_NAME", "选择专家");
        mv.addObject("query", query);
        mv.addObject("batchId", id);
        return mv;
    }

    /**
     * @param id
     * @desc 添加专家
     * @Return: org.springframework.web.servlet.ModelAndView
     * @author: 刹那访华
     * @date: 2021/5/24 22:23
     * @Version V1.1.0
     */
    @RequestMapping(value = _MODEL_NAME + "/addSpecInput")
    public ModelAndView addSpecInput(@RequestParam("id") int id) {
        ModelAndView mv = createView(_MODEL_NAME + "/specAdd");
        mv.addObject("batchId", id);
        //职称
        wrapTitle(mv);
        //专业领域
        wrapTerritory(mv);
        //专家类别
        wrapSpecType(mv);
        return mv;
    }

    @RequestMapping(value = _MODEL_NAME + "/addSpec")
    public String addSpec(@RequestParam("id") int batchId, @RequestBody String json) {
        LOGGER.info("json:{}", json);
        SpecInfo model = JSON.parseObject(json, SpecInfo.class);
        ReviewBatchSpecialist model2 = JSON.parseObject(json, ReviewBatchSpecialist.class);
        try {
            specInfoService.save(model);
            model2.setSpecId(model.getId());
            model2.setBatchId(batchId);
            model2.setTitleName(model.getTitleName());
            model2.setTerritoryId(model2.getTerritoryId());
            model2.setSpecName(model2.getUserName());
            model2.setSpecTypeId(model.getSpecTypeId());
            model2.setSpecTypeName(model.getSpecTypeName());
            batchSpecialistService.save(model2);
        } catch (Exception e) {
            return setResultError("新增失败，请重试或联系管理员");
        }

        return setResult();
    }

    /**
     * @param json
     * @desc 添加选择专家
     * @Return: java.lang.String
     * @author: 刹那访华
     * @date: 2021/5/24 22:23
     * @Version V1.1.0
     */
    @RequestMapping(value = _MODEL_NAME + "/addSelectSpec")
    public String addSelectSpec(@RequestParam("id") int batchId, @RequestBody String json) {
        LOGGER.info("json:{}", json);

        ReviewBatch reviewBatch = reviewBatchService.findById(batchId);
        List<Integer> idsList = getIdListByJson(json);

        List<ReviewBatchSpecialist> exist = batchSpecialistService.findByIdList(idsList, batchId);
        if (exist.size() > 0) {
            return setResultError("批次专家已存在，请不要重复添加");
        }

        List<SpecInfo> list = specInfoService.findByIdList(idsList);
        ReviewBatchSpecialist batchSpecInfo = null;
        for (SpecInfo info : list) {
            batchSpecInfo = new ReviewBatchSpecialist();
            // BeanUtils.copyProperties(info,batchSpecInfo);
            batchSpecInfo.setBatchId(reviewBatch.getId());
            batchSpecInfo.setBatchName(reviewBatch.getName());
            batchSpecInfo.setSpecId(info.getId());
            batchSpecInfo.setSpecName(info.getUserName());
            batchSpecInfo.setTerritoryId(info.getTerritoryId());
            batchSpecInfo.setTerritoryName(info.getTerritoryName());
            batchSpecialistService.save(batchSpecInfo);

        }
        reviewBatch.setSpecCount(reviewBatch.getSpecCount() + list.size());
        reviewBatchService.update(reviewBatch);
        return setResult();
    }


    @RequestMapping(value = _MODEL_NAME + "/remove")
    public String remove(int id) {
        LOGGER.info("delete  id:{}", id);
        ReviewBatchSpecialist info = batchSpecialistService.findById(id);
        if (info == null) {
            return setResultError("根据ID查询数据为空，请确认");
        }
        info.setIsDel(-1);

        try {
            batchSpecialistService.update(info);
        } catch (Exception e) {
            return setResultError("移除失败，请重试或联系管理员");
        }
        return setResult();
    }

    /**
     * 封装职称
     */
    private void wrapTitle(ModelAndView mv) {
        CoreTitle model = new CoreTitle();
        List<CoreTitle> titleList = titleService.listByModel(model);
        mv.addObject("CoreTitle", titleList);
    }

    /**
     * 封装专业领域
     */
    private void wrapTerritory(ModelAndView mv) {
        CoreTerritory model = new CoreTerritory();
        List<CoreTerritory> territoryList = territoryService.listByModel(model);
        mv.addObject("coreTerritory", territoryList);
    }

    /**
     * 封装专家类别
     */
    private void wrapSpecType(ModelAndView mv) {
        CoreSpecType model = new CoreSpecType();
        List<CoreSpecType> specTypeList = specTypeService.listByModel(model);
        mv.addObject("CoreSpecType", specTypeList);
    }

    private List<Integer> getIdListByJson(String json) {
        JSONArray jsonArray = JSONArray.parseArray(json);
        List<Integer> idsList = new ArrayList<>();
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            String id = jsonObject.getString("id");
            if (CommonUtil.isEmpty(id)) {
                continue;
            }
            idsList.add(Integer.parseInt(id));
        }
        return idsList;
    }

    /**
     * 校验专家是否存在
     */
    public boolean checkSpec(List<Integer> idsList) {
        List<ReviewBatchSpecialist> list = batchSpecialistService.findByIdList(idsList);
        if (list.size() > 0) {
            return true;
        }

        return false;
    }
}

package com.hsmap.yuezhihui.admin.controller.review;

import com.hsmap.yuezhihui.admin.controller.AdminBaseController;
import com.hsmap.yuezhihui.base.page.PageInfo;
import com.hsmap.yuezhihui.base.page.Pageable;
import com.hsmap.yuezhihui.config.SysConfig;
import com.hsmap.yuezhihui.entity.core.CoreProjectType;
import com.hsmap.yuezhihui.entity.core.CoreTerritory;
import com.hsmap.yuezhihui.entity.product.GroupProductInfo;
import com.hsmap.yuezhihui.entity.review.ComplexRelationProductDo;
import com.hsmap.yuezhihui.entity.review.ReviewBatch;
import com.hsmap.yuezhihui.entity.review.ReviewGroup;
import com.hsmap.yuezhihui.entity.review.ReviewRecordDetail;
import com.hsmap.yuezhihui.entity.spec.SpecInfo;
import com.hsmap.yuezhihui.service.core.ICoreProjectTypeService;
import com.hsmap.yuezhihui.service.core.ICoreTerritoryService;
import com.hsmap.yuezhihui.service.review.IComplexRelationService;
import com.hsmap.yuezhihui.service.review.IReviewBatchService;
import com.hsmap.yuezhihui.service.review.IReviewGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ReviewHistoryController extends AdminBaseController {

    //模块名称
    private final String _MODEL_NAME = "review/history";

    @Autowired
    private SysConfig sysConfig;

    @Autowired
    private IComplexRelationService complexRelationService;
    //专业领域
    @Autowired
    private ICoreTerritoryService coreTerritoryService;
    //人才类型
    @Autowired
    private ICoreProjectTypeService coreProjectTypeService;
    //申报批次
    @Autowired
    private IReviewBatchService reviewBatchService;
    //评审分组
    @Autowired
    private IReviewGroupService reviewGroupService;

    /**
     * 首页
     *
     * @param query
     * @param pageNumber
     * @return
     */
    @RequestMapping(value = _MODEL_NAME + "/list")
    public ModelAndView list(ComplexRelationProductDo query, @RequestParam(name = "pageNumber", defaultValue = "1") int pageNumber) {
        ModelAndView mv = createView(_MODEL_NAME + "/list");
        Pageable pageable = getPageable(pageNumber);
        query.setQueryProductReViewStatus(2);
        PageInfo<ComplexRelationProductDo> pageInfo = complexRelationService.reviewHisProductInfoOfPage(query, pageable);
        mv.addObject("pageInfo", pageInfo);
        mv.addObject("_PARENT_MENU_NAME", "评审管理");
        mv.addObject("_MENU_NAME", "历史评审管理");
        //专业领域
        wrapTerritory(mv);
        //人才类型
        wrapProjectType(mv);
        //申报批次
        wrapReviewBatch(mv);
        if (query.getQueryBatchId() != null && query.getQueryBatchId() > 0) {
            ReviewGroup model = new ReviewGroup();
            model.setBatchId(query.getQueryBatchId());
            List<ReviewGroup> reviewGroupList = reviewGroupService.listByModel(model);
            mv.addObject("reviewGroup", reviewGroupList);
        } else {
            //没有批次，不查询组
            mv.addObject("reviewGroup", new ArrayList<ReviewGroup>());
        }
        mv.addObject("query", query);
        return mv;
    }


    /**
     * 批次级联组数据
     *
     * @param batchId
     * @return
     */
    @PostMapping(value = _MODEL_NAME + "/cascadeGroup/{batchId}")
    @ResponseBody
    public String cascadeGroup(@PathVariable("batchId") Integer batchId) {
        ReviewGroup model = new ReviewGroup();
        model.setBatchId(batchId);
        List<ReviewGroup> reviewGroupList = reviewGroupService.listByModel(model);
        return setResult(reviewGroupList);
    }

    /**
     * 详情页
     *
     * @param id
     * @return
     */
    @RequestMapping(value = _MODEL_NAME + "/detail/{id}")
    public ModelAndView detail(@PathVariable("id") Integer id, @RequestParam(value = "currentSpecId", defaultValue = "0") Integer currentSpecId) {
        LOGGER.info("currentSpecId:" + currentSpecId);
        ModelAndView mv = createView(_MODEL_NAME + "/detail");
        ComplexRelationProductDo query = new ComplexRelationProductDo();
        query.setQueryProductId(id);
        List<ComplexRelationProductDo> productDos = complexRelationService.reviewHisProductInfo(query);
        if (productDos == null || productDos.size() == 0) {
            return getErrorPage2("没有查询到项目信息");
        }
        ComplexRelationProductDo dos = productDos.get(0);
        mv.addObject("detail", dos);
        mv.addObject("_PARENT_MENU_NAME", "评审管理");
        mv.addObject("_MENU_NAME", "历史评审管理 / " + dos.getPi_user_name());

        //查询项目的审核专家列表
        List<SpecInfo> specInfoList = complexRelationService.productSpecListById(id);
        mv.addObject("specInfoList", specInfoList);
        //第一次进入
        if (currentSpecId == 0 && specInfoList != null && specInfoList.size() > 0) {
            currentSpecId = specInfoList.get(0).getId();//取列表的第一个作为页面的第一次选中数据
        }

        //查询项目的打分规则
        List<ReviewRecordDetail> recordDetailList = complexRelationService.productScoreListById(id);

        SpecInfo specReviewInfo = null;
        if (currentSpecId > 0) {
            //查询专家的评论信息以及签名
            specReviewInfo = complexRelationService.productSpecInfoById(id, currentSpecId);
            //正常情况不会为Null
            if (specReviewInfo == null)
                specReviewInfo = new SpecInfo();
            //查询评论项和评分
//            recordDetailList = complexRelationService.productSpecScoreListById(id, currentSpecId);
            mv.addObject("specScore", specReviewInfo.getRecordScore() != null ? specReviewInfo.getRecordScore() : 0);
        } else {
            specReviewInfo = new SpecInfo();
            mv.addObject("specScore", "-");
        }
        String signUrl = sysConfig.signUrl() + "/";
        if (specReviewInfo.getSignUrl() == null || specReviewInfo.getSignUrl().trim().length() == 0) {
            signUrl += "empty_sign.png";
        } else {
            signUrl += specReviewInfo.getSignUrl();
        }
        mv.addObject("signUrl", signUrl);
        mv.addObject("specReviewInfo", specReviewInfo);
        mv.addObject("recordDetailList", recordDetailList);
        mv.addObject("currentSpecId", currentSpecId);
        return mv;
    }

    /**
     * 封装专业领域
     */
    private void wrapTerritory(ModelAndView mv) {
        CoreTerritory model = new CoreTerritory();
        List<CoreTerritory> territoryList = coreTerritoryService.listByModel(model);
        mv.addObject("coreTerritory", territoryList);
    }

    /**
     * 封装人才类型
     */
    private void wrapProjectType(ModelAndView mv) {
        CoreProjectType model = new CoreProjectType();
        List<CoreProjectType> projectTypeList = coreProjectTypeService.listByModel(model);
        mv.addObject("coreProjectType", projectTypeList);
    }

    /**
     * 封装申报批次
     */
    private void wrapReviewBatch(ModelAndView mv) {
        ReviewBatch model = new ReviewBatch();
        List<ReviewBatch> reviewBatchList = reviewBatchService.listByModel(model);
        mv.addObject("reviewBatch", reviewBatchList);
    }

}

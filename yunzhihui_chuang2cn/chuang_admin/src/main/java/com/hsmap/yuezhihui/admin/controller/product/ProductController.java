package com.hsmap.yuezhihui.admin.controller.product;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hsmap.yuezhihui.admin.controller.AdminBaseController;
import com.hsmap.yuezhihui.base.page.PageInfo;
import com.hsmap.yuezhihui.base.page.Pageable;
import com.hsmap.yuezhihui.config.SysConfig;
import com.hsmap.yuezhihui.entity.core.CoreProjectType;
import com.hsmap.yuezhihui.entity.core.CoreTaskDetail;
import com.hsmap.yuezhihui.entity.core.CoreTaskInfo;
import com.hsmap.yuezhihui.entity.core.CoreTerritory;
import com.hsmap.yuezhihui.entity.product.ProductInfo;
import com.hsmap.yuezhihui.entity.product.ProductInfoExcelDto;
import com.hsmap.yuezhihui.entity.review.ReviewBatch;
import com.hsmap.yuezhihui.excel.ExcelUtils;
import com.hsmap.yuezhihui.service.core.ICoreProjectTypeService;
import com.hsmap.yuezhihui.service.core.ICoreTaskDetailService;
import com.hsmap.yuezhihui.service.core.ICoreTaskInfoService;
import com.hsmap.yuezhihui.service.core.ICoreTerritoryService;
import com.hsmap.yuezhihui.service.product.IProductInfoService;
import com.hsmap.yuezhihui.service.review.IReviewBatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ProductController extends AdminBaseController {
    //模块名称
    private final String _MODEL_NAME = "product/info";
    @Autowired
    private IProductInfoService productInfoService;
    //专业领域
    @Autowired
    private ICoreTerritoryService coreTerritoryService;
    //人才类型
    @Autowired
    private ICoreProjectTypeService coreProjectTypeService;
    //申报批次
    @Autowired
    private IReviewBatchService reviewBatchService;
    @Autowired
    private SysConfig sysConfig;
    @Autowired
    private ICoreTaskInfoService coreTaskInfoService;
    @Autowired
    private ICoreTaskDetailService coreTaskDetailService;

    @RequestMapping(value = _MODEL_NAME + "/list")
    public ModelAndView list(ProductInfo query, @RequestParam(name = "pageNumber", defaultValue = "1") int pageNumber) {
        LOGGER.debug("list pageNumber:{}", pageNumber);
        ModelAndView mv = createView(_MODEL_NAME + "/list");
        Pageable pageable = getPageable(pageNumber);
        PageInfo<ProductInfo> pageInfo = productInfoService.pageList(query, pageable);
        mv.addObject("pageInfo", pageInfo);
        //专业领域
        wrapTerritory(mv);
        //人才类型
        wrapProjectType(mv);
        // 申报批次
        wrapReviewBatch(mv);
        mv.addObject("_PARENT_MENU_NAME", "申报审核");
        mv.addObject("_MENU_NAME", "申报审核");
        mv.addObject("query", query);
        return mv;
    }


    @RequestMapping(value = _MODEL_NAME + "/addInput")
    public ModelAndView addInput() {
        ModelAndView mv = createView(_MODEL_NAME + "/add");
        //专业领域
        wrapTerritory(mv);
        //人才类型
        wrapProjectType(mv);
        // 申报批次
        wrapReviewBatch(mv);
        return mv;
    }

    @RequestMapping(value = _MODEL_NAME + "/add")
    @ResponseBody
    public String save(ProductInfo info) {
        LOGGER.debug("ProductInfo.add", info);
        //info.setIsDel(0);
        //info.setCreateTime(new Date());
        //info.setWork(info.getWorkCompany());//工作单位和录入的workCompany一样
        try {
            productInfoService.save(info);
        } catch (Exception e) {
            return setResultError("新增失败，请重试或联系管理员");
        }
        return setResult();
    }


    @RequestMapping(value = _MODEL_NAME + "/editInput")
    public ModelAndView editInput(int id) {
        ModelAndView mv = createView(_MODEL_NAME + "/edit");
        ProductInfo model = productInfoService.findById(id);
        //专业领域
        wrapTerritory(mv);
        //人才类型
        wrapProjectType(mv);
        // 申报批次
        wrapReviewBatch(mv);
        mv.addObject("model", model);
        return mv;
    }

    @RequestMapping(value = _MODEL_NAME + "/edit")
    @ResponseBody
    public String edit(ProductInfo info) {
        //info.setWork(info.getWorkCompany());//工作单位和录入的workCompany一样
        try {
            productInfoService.update(info);
        } catch (Exception e) {
            return setResultError("修改失败，请重试或联系管理员");
        }
        return setResult();
    }

    @RequestMapping(value = _MODEL_NAME + "/delete")
    @ResponseBody
    public String delete(int id) {
        LOGGER.info("delete  id:{}", id);
        ProductInfo info = productInfoService.findById(id);
        if (info == null) {
            return setResultError("根据ID查询数据为空，请确认");
        }
        info.setIsDel(-1);
        productInfoService.update(info);
        return setResult();
    }

    /**
     * 申报审核导入页面
     *
     * @return
     */
    @RequestMapping(value = _MODEL_NAME + "/imports")
    public ModelAndView imports() {
        ModelAndView mv = createView(_MODEL_NAME + "/imports");
        //申报批次
        wrapReviewBatch(mv);
        //导入记录
        CoreTaskInfo taskInfoQuery = new CoreTaskInfo();
        taskInfoQuery.setType(0);//0项目1专家
        taskInfoQuery.setIsDel(0);
        List<CoreTaskInfo> taskInfoList = coreTaskInfoService.listByModel(taskInfoQuery);
        mv.addObject("taskInfoList", taskInfoList);
        return mv;
    }


    /**
     * Excel解析
     *
     * @param batchId
     * @param taskName
     * @param taskFile
     * @return
     */
    @PostMapping(value = _MODEL_NAME + "/parse_xls")
    @ResponseBody
    public String parseXls(@RequestParam("batchId") Integer batchId,
                           @RequestParam("taskName") String taskName,
                           @RequestParam("taskFile") String taskFile) {
        if (taskName.trim().length() == 0) {
            return setResultError("请填写任务名称");
        }
        ReviewBatch model = new ReviewBatch();
        List<ReviewBatch> reviewBatchList = reviewBatchService.listByModel(model);
        if (reviewBatchList == null) {
            return setResultError("没有录入申报批次维度数据");
        }
        boolean hasBatchId = false;
        for (ReviewBatch r : reviewBatchList) {
            if (r.getId().intValue() == batchId.intValue()) {
                hasBatchId = true;
                break;
            }
        }
        if (!hasBatchId) {
            return setResultError("没有选择报批次");
        }

        //判断TasFile
        File f = new File(sysConfig.getDownloadDir() + File.separator + taskFile);
        if (!f.exists()) {
            LOGGER.error("解析的Excel文件不存在：batchId={},taskName={},taskFile={}", batchId, taskName, taskFile);
            return setResultError("上传的文件不存在");
        }
        LOGGER.info("正在解析的Excel：batchId={},taskName={},taskFile={}", batchId, taskName, taskFile);

        CoreProjectType queryType = new CoreProjectType();
        List<CoreProjectType> projectTypeList = coreProjectTypeService.listByModel(queryType);
        if (projectTypeList == null) {
            return setResultError("没有录入人才类型维度数据");
        }
        CoreTerritory queryTerritory = new CoreTerritory();
        List<CoreTerritory> territoryList = coreTerritoryService.listByModel(queryTerritory);
        if (territoryList == null) {
            return setResultError("没有录入专业领域维度数据");
        }

        //解析Excel
        List<ProductInfoExcelDto> dtos = null;
        try {
            dtos = ExcelUtils.readExcel(f.getAbsolutePath(), ProductInfoExcelDto.class);
        } catch (Exception e) {
            LOGGER.error("解析的Excel失败", e);
            return setResultError("解析的Excel失败");
        }

        //不需要事务，单条失败不能影响全部数据
        CoreTaskInfo task = null;
        try {
            //构建任务表
            task = new CoreTaskInfo();
            task.setType(0);//0项目1专家
            task.setName(taskName);
            task.setBatchId(batchId);
            task.setFileUrl(f.getAbsolutePath());
            task.setTotalCount(dtos.size());
            task.setStatus(2);//状态 0未处理 1可以处理 2正在处理 3完成
            coreTaskInfoService.save(task);
        } catch (Exception e) {
            LOGGER.error("保存任务信息失败", e);
            return setResultError("保存任务信息失败");
        }
        if (task.getId() == null) {
            LOGGER.error("保存任务信息失败");
            return setResultError("保存任务信息失败");
        }
        //校验
        int successCount = 0, failCount = 0;
        if (dtos != null && dtos.size() > 0) {

            //与详情表
            List<CoreTaskDetail> detailList = new ArrayList<>(dtos.size());
            CoreTaskDetail detail = null;
            detail = new CoreTaskDetail();
            for (ProductInfoExcelDto dto : dtos) {
                detail.setTaskId(task.getId());
                detail.setDataJson(JSONObject.toJSONString(dto));

                detailList.add(detail);
                String ret = dto.check(projectTypeList, territoryList);
                ProductInfo info = null;
                if ("".equals(ret)) {
                    info = dto.toEntiry(batchId);
                } else {
                    //如果检测不过
                    detail.setResultStatus("0");
                    detail.setResultError(ret);
                    info = null;//置空
                }
                String databaseErr = "";
                if (info != null) {
                    //保存
                    try {
                        productInfoService.save(info);
                    } catch (Exception e) {
                        LOGGER.error("保存info失败", e);
                        databaseErr = e.getMessage();
                    }
                }
                //判断info是否保存成功
                if (info != null && info.getId() == null) {
                    //保存info失败
                    if (databaseErr == null || "".equals(databaseErr)) {
                        databaseErr = "未知的异常导致信息主表保存失败";
                    }
                    detail.setResultStatus("0");
                    detail.setResultError(databaseErr);
                } else if (info != null && info.getId() != 0) {
                    detail.setResultStatus("1");
                    detail.setResultError(null);
                }
                if ("1".equals(detail.getResultStatus())) {
                    successCount++;
                } else {
                    failCount++;
                }
                try {
                    coreTaskDetailService.save(detail);
                } catch (Exception e) {
                    LOGGER.error("保存detail失败", e);
                }
            }
        }
        //任务处理完成
        CoreTaskInfo finished = new CoreTaskInfo();
        finished.setId(task.getId());
        finished.setStatus(3);
        finished.setErrorCount(failCount);
        finished.setSuccessCount(successCount);
        coreTaskInfoService.update(finished);
        return setResult();
    }

    /**
     * 申报审核详情
     *
     * @return
     */
    @GetMapping(value = _MODEL_NAME + "/detail/{id}")
    public ModelAndView detail(@PathVariable("id") Integer id) {
        ModelAndView mv = createView(_MODEL_NAME + "/detail");
        ProductInfo info = productInfoService.findById(id);
        mv.addObject("detail", info);
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

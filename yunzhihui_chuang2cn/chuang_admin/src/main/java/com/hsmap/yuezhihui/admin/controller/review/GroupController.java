package com.hsmap.yuezhihui.admin.controller.review;


import com.hsmap.yuezhihui.admin.controller.AdminBaseController;
import com.hsmap.yuezhihui.base.page.PageInfo;
import com.hsmap.yuezhihui.base.page.Pageable;
import com.hsmap.yuezhihui.config.SysConfig;
import com.hsmap.yuezhihui.entity.core.CoreProjectType;
import com.hsmap.yuezhihui.entity.core.CoreSpecType;
import com.hsmap.yuezhihui.entity.core.CoreTerritory;
import com.hsmap.yuezhihui.entity.core.CoreTitle;
import com.hsmap.yuezhihui.entity.product.GroupProductInfo;
import com.hsmap.yuezhihui.entity.review.*;
import com.hsmap.yuezhihui.entity.spec.BatchSpecInfo;
import com.hsmap.yuezhihui.entity.spec.GroupSpecInfo;
import com.hsmap.yuezhihui.service.core.ICoreProjectTypeService;
import com.hsmap.yuezhihui.service.core.ICoreSpecTypeService;
import com.hsmap.yuezhihui.service.core.ICoreTerritoryService;
import com.hsmap.yuezhihui.service.core.ICoreTitleService;
import com.hsmap.yuezhihui.service.product.IProductInfoService;
import com.hsmap.yuezhihui.service.review.*;
import com.hsmap.yuezhihui.service.spec.ISpecInfoService;
import com.hsmap.yuezhihui.utils.customize.ExcelExport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
public class GroupController extends AdminBaseController {
    //模块名称
    private final String _MODEL_NAME = "review/group";

    @Autowired
    IReviewBatchService reviewBatchService;
    @Autowired
    IReviewGroupService reviewGroupService;
    @Autowired
    IReviewProductService reviewProductService;
    //专业领域
    @Autowired
    private ICoreTerritoryService coreTerritoryService;
    //人才类型
    @Autowired
    private ICoreProjectTypeService coreProjectTypeService;
    @Autowired
    private IProductInfoService productInfoService;
    @Autowired
    private ISpecInfoService specInfoService;
    @Autowired
    private IReviewSpecialistService reviewSpecialistService;
    @Autowired
    private ICoreTitleService titleService;
    @Autowired
    private ICoreSpecTypeService specTypeService;
    @Autowired
    private ExcelExport excelExport;
    @Autowired
    private IComplexRelationService complexRelationService;
    @Autowired
    private SysConfig sysConfig;


    @RequestMapping(value = _MODEL_NAME + "/groupList")
    public ModelAndView list(@RequestParam(name = "currentGroupId", defaultValue = "0") Integer currentGroupId, @RequestParam(name = "id", defaultValue = "0") int id) {
        //获取有效的评审批次，当前的每次评审段是存在一个有效批次
        ReviewBatch batch = getBatchById(id);
        if (batch == null) {
            return getErrorPage2("当前项目不存在有效的评审批次");
        }
        ModelAndView mv = createView(_MODEL_NAME + "/groupList");
        //分组信息
        List<ReviewGroup> groupList = reviewGroupService.findByBatchId(batch.getId());
        if (groupList == null || groupList.size() == 0) {
            return getErrorPage2("当前项目不存在有效的批次分组");
        }
        //第一次进入，获取第一个作为选中
        ReviewGroup reviewGroup = null;
        if (currentGroupId == 0) {
            reviewGroup = groupList.get(0);
            currentGroupId = reviewGroup.getId();
        } else {
            for (ReviewGroup rg : groupList) {
                if (rg.getId().intValue() == currentGroupId.intValue()) {
                    reviewGroup = rg;
                    break;
                }
            }
        }
        if (reviewGroup == null) {
            return getErrorPage("批次分组不存在，分组ID：" + currentGroupId);
        }

        List<ReviewProduct> productList = reviewProductService.findByGroupId(reviewGroup.getId());
        mv.addObject("groupList", groupList);
        mv.addObject("reviewGroup", reviewGroup);
        mv.addObject("pageInfo", groupList);
        mv.addObject("batchId", batch.getId());
        //处理日期,默认
        String am = "07:00:00 - 12:00:00", pm = "13:00:00 - 16:00:00";
        if (reviewGroup.getAmStartTime() != null && reviewGroup.getAmStartTime().length() > 0) {
            am = reviewGroup.getAmStartTime() + " - " + reviewGroup.getAmEndTime();
        }
        if (reviewGroup.getPmStartTime() != null && reviewGroup.getPmStartTime().length() > 0) {
            pm = reviewGroup.getPmStartTime() + " - " + reviewGroup.getPmEndTime();
        }
        mv.addObject("am", am);
        mv.addObject("pm", pm);
        mv.addObject("_PARENT_MENU_NAME", "评审管理");
        mv.addObject("_MENU_NAME", "评审分组");
        mv.addObject("currentGroupId", currentGroupId);
        //专业领域
        wrapTerritory(mv);
        //人才类型
        wrapProjectType(mv);
        return mv;

    }

    /**
     * 根据分组查询组下的项目列表
     *
     * @param id
     * @return
     */
    @PostMapping(value = _MODEL_NAME + "/group_product/{id}")
    public String groupProductList(@PathVariable("id") Integer id, GroupProductInfo query) {
        query.setQueryGroupId(id);
        List<GroupProductInfo> list = productInfoService.selectGroupProductInfo(query);
        return setResult(list);
    }

    /**
     * 根据分组查询组下的专家列表
     *
     * @param id
     * @return
     */
    @PostMapping(value = _MODEL_NAME + "/group_spec/{id}")
    public String groupSpecList(@PathVariable("id") Integer id) {
        GroupSpecInfo query = new GroupSpecInfo();
        query.setQueryGroupId(id);
        List<GroupSpecInfo> list = specInfoService.selectGroupSpecInfo(query);
        return setResult(list);
    }


    /**
     * 单次调整分组
     *
     * @return
     */
    @RequestMapping(value = _MODEL_NAME + "/groupChangeSingle")
    public ModelAndView groupChangeSingle(@RequestParam(name = "groupId") Integer groupId,
                                          @RequestParam(name = "batchId") Integer batchId,
                                          @RequestParam(name = "reviewProductId") Integer reviewProductId,
                                          @RequestParam(name = "sort") Integer sort) {
        //TODO:评审完成的项目，能够调整组
        ModelAndView mv = createView(_MODEL_NAME + "/groupChangeSingle");
        List<ReviewGroup> groupList = reviewGroupService.findByBatchId(batchId);
        mv.addObject("groupList", groupList);
        mv.addObject("batchId", batchId);
        mv.addObject("groupId", groupId);
        mv.addObject("reviewProductId", reviewProductId);
        mv.addObject("sort", sort);
        return mv;
    }

    /**
     * 保存单次调整分组
     *
     * @return
     */
    @PostMapping(value = _MODEL_NAME + "/groupChangeSingleSave")
    public String groupChangeSingleSave(@RequestParam(name = "groupId") Integer groupId,
                                        @RequestParam(name = "batchId") Integer batchId,
                                        @RequestParam(name = "reviewProductId") Integer reviewProductId,
                                        @RequestParam(name = "sort") Integer sort,
                                        @RequestParam(name = "newGroupId") Integer newGroupId,
                                        @RequestParam(name = "newSort") Integer newSort) {
        //是否存在修改
        if (newGroupId.intValue() == groupId.intValue() && sort.intValue() == newSort.intValue()) {
            return setResult();//直接返回
        }
        //TODO：变更组后，没有减少组的项目数量
       /* ReviewGroup group = new ReviewGroup();
        group.setId(reviewProductId);*/

        ReviewProduct product = new ReviewProduct();
        product.setId(reviewProductId);
        if (newGroupId.intValue() != groupId.intValue()) {
            LOGGER.info(String.format("reviewProductId:%s , 组变更：%s->%s", reviewProductId, groupId, newGroupId));
            product.setGroupId(newGroupId);
        }
        if (sort.intValue() != newSort.intValue()) {
            LOGGER.info(String.format("reviewProductId:%s , 排序变更：%s->%s", reviewProductId, sort, newSort));
            product.setSort(newSort);
        }
        reviewProductService.update(product);
        return setResult("Y");
    }

    /**
     * 批量调整分组
     *
     * @return
     */
    @RequestMapping(value = _MODEL_NAME + "/groupChangeMutil")
    public ModelAndView groupChangeMutil(@RequestParam(name = "groupId") Integer groupId,
                                         @RequestParam(name = "batchId") Integer batchId,
                                         @RequestParam(name = "reviewProductIds") String reviewProductIds) {
        //TODO:评审完成的项目，能够调整组
        if (reviewProductIds.trim().length() == 0) {
            return getErrorPage2("没有选中调整的数据");
        }
        ModelAndView mv = createView(_MODEL_NAME + "/groupChangeMutil");
        List<ReviewGroup> groupList = reviewGroupService.findByBatchId(batchId);
        mv.addObject("groupList", groupList);
        mv.addObject("batchId", batchId);
        mv.addObject("groupId", groupId);
        mv.addObject("reviewProductIds", reviewProductIds.trim());
        return mv;
    }

    /**
     * 保存批量调整分组
     *
     * @return
     */
    @PostMapping(value = _MODEL_NAME + "/groupChangeMutilSave")
    public String groupChangeMutilSave(@RequestParam(name = "groupId") Integer groupId,
                                       @RequestParam(name = "batchId") Integer batchId,
                                       @RequestParam(name = "reviewProductIds") String reviewProductIds,
                                       @RequestParam(name = "newGroupId") Integer newGroupId) {
        //是否存在修改
        if (newGroupId.intValue() == groupId.intValue()) {
            return setResult();//直接返回
        }
        if (reviewProductIds.trim().length() == 0) {
            return setResultError("没有选中调整的数据");
        }
        //TODO：变更组后，没有减少组的项目数量
       /* ReviewGroup group = new ReviewGroup();
        group.setId(reviewProductId);*/
        ReviewProduct product = null;
        for (String s : reviewProductIds.split(",")) {
            LOGGER.info(String.format("reviewProductId:%s , 组变更：%s->%s", s, groupId, newGroupId));
            product = new ReviewProduct();
            product.setId(Integer.parseInt(s));
            product.setGroupId(newGroupId);
            reviewProductService.update(product);
        }
        return setResult("Y");
    }


    /**
     * 移除专家
     *
     * @return
     */
    @PostMapping(value = _MODEL_NAME + "/groupSpecRemove")
    public String groupSpecRemove(@RequestParam(name = "groupId") Integer groupId,
                                  @RequestParam(name = "batchId") Integer batchId,
                                  @RequestParam(name = "specId") Integer specId,
                                  @RequestParam(name = "reviewProductId") Integer reviewProductId) {
        LOGGER.info(String.format("groupId:%s , 移除专家：%s", groupId, reviewProductId));
        //TODO：变更组后，没有减少组的专家数量
        ReviewSpecialist update = new ReviewSpecialist();
        update.setId(reviewProductId);
        update.setIsDel(-1);
        ReviewBatchSpecialist updateBatch = new ReviewBatchSpecialist();
        updateBatch.setBatchId(batchId);
        updateBatch.setIsUse(0);
        updateBatch.setSpecId(specId);
        try {
            reviewSpecialistService.removeGroupSpec(update, updateBatch);
        } catch (Exception e) {
            LOGGER.error("", e);
            setResult("移除专家失败");
        }
        return setResult("Y");
    }


    /**
     * 批量加入专家页面
     *
     * @return
     */
    @RequestMapping(value = _MODEL_NAME + "/groupSpecJoin")
    public ModelAndView groupSpecJoin(@RequestParam(name = "groupId") Integer groupId,
                                      @RequestParam(name = "batchId") Integer batchId,
                                      BatchSpecInfo query, @RequestParam(name = "pageNumber", defaultValue = "1") int pageNumber) {
        LOGGER.info("list pageNumber:{}", pageNumber);
        ModelAndView mv = createView(_MODEL_NAME + "/groupSpecJoin");
        Pageable pageable = getPageable(pageNumber);
        query.setQueryBatchId(batchId);
        PageInfo<BatchSpecInfo> pageInfo = specInfoService.selectBatchSpecInfoListOfPage(query, pageable);
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
        mv.addObject("batchId", batchId);
        mv.addObject("groupId", groupId);
        return mv;
    }


    /**
     * 批量保存专家
     *
     * @return
     */
    @PostMapping(value = _MODEL_NAME + "/groupSpecJoinSave")
    public String groupSpecJoinSave(@RequestParam(name = "groupId") Integer groupId,
                                    @RequestParam(name = "batchId") Integer batchId,
                                    @RequestParam(name = "reviewSpecIds") String reviewSpecIds) {
        if (reviewSpecIds.trim().length() == 0) {
            return setResultError("没有选中专家的数据");
        }
        ReviewSpecialist specialist = null;
        ReviewBatchSpecialist updateSpec = null;
        //reviewBatchSpecId-specId
        List<ReviewSpecialist> specialists = new ArrayList<>();
        List<ReviewBatchSpecialist> updateSpecialists = new ArrayList<>();
        for (String s : reviewSpecIds.split(",")) {
            String[] tmp = s.split("-");
            LOGGER.info(String.format("groupId:%s , 加入专家：%s", groupId, s));
            specialist = new ReviewSpecialist();
            specialist.setGroupId(groupId);
            specialist.setSpecId(Integer.parseInt(tmp[1]));
            specialists.add(specialist);

            //根据ID更新 isUse,可以不用batchId + specId
            updateSpec = new ReviewBatchSpecialist();
            updateSpec.setId(Integer.parseInt(tmp[0]));
//            updateSpec.setBatchId(batchId);
//            updateSpec.setSpecId(Integer.parseInt(tmp[1]));
            updateSpec.setIsUse(1);
            updateSpecialists.add(updateSpec);
        }
        try {
            reviewSpecialistService.saveSpecListUse(updateSpecialists, specialists);
        } catch (Exception e) {
            LOGGER.error("", e);
            setResult("加入专家失败");
        }
        return setResult("Y");
    }

    /**
     * 保存基本信息
     *
     * @return
     */
    @PostMapping(value = _MODEL_NAME + "/groupInfoSave")
    public String groupInfoSave(ReviewGroup group) {
        if (group.getId() == null)
            setResultError("数据错误");
        reviewGroupService.update(group);
        return setResult();
    }


    /**
     * 组评审情况下载
     *
     * @param id 组ID
     */
    @GetMapping(value = _MODEL_NAME + "/down/{id}")
    public void downloadGroupXlsx(@PathVariable("id") Integer id, HttpServletResponse response) {
        //查询模板关系
        ReviewGroup group = reviewGroupService.findById(id);
        if (group == null) {
            throw new RuntimeException("没有组信息");
        }
        ExportGpsBean bean = null;
        try {
            bean = complexRelationService.selectGPSExportInfo(id);
        } catch (Exception e) {
            LOGGER.error("", e);
            throw new RuntimeException("获取组数据异常");
        }
        String xlsdir = sysConfig.getTempPath();
        File f = new File(xlsdir);
        if (!f.exists())
            f.mkdir();
        String filename = UUID.randomUUID().toString() + ".xlsx";
        try {
            excelExport.exportGps(xlsdir + File.separator + filename, bean);
        } catch (Exception e) {
            LOGGER.error("", e);
            throw new RuntimeException("生产Excel失败");
        }
        File file = new File(xlsdir + File.separator + filename);
        if (!file.exists())
            throw new RuntimeException("文件不存在");

        FileInputStream fis = null;
        OutputStream os = null;
        try {
            response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
            setAttachmentResponseHeader(response, group.getName() + ".xlsx");
            fis = new FileInputStream(file);
            byte[] b = new byte[1024];
            int length;
            os = response.getOutputStream();
            while ((length = fis.read(b)) > 0) {
                os.write(b, 0, length);
            }
        } catch (IOException e0) {
            LOGGER.error("下载文件失败", e0);
        } catch (Exception e) {
            LOGGER.error("下载文件失败", e);
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e1) {
                    LOGGER.error("下载文件失败", e1);
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e1) {
                    LOGGER.error("下载文件失败", e1);
                }
            }
        }
    }

    /**
     * 封装专业领域
     */
    private void wrapTerritory(ModelAndView mv) {
        List<CoreTerritory> territoryList = coreTerritoryService.findByAll();
        mv.addObject("coreTerritory", territoryList);
    }

    /**
     * 封装人才类型
     */
    private void wrapProjectType(ModelAndView mv) {
        List<CoreProjectType> projectTypeList = coreProjectTypeService.findByAll();
        mv.addObject("coreProjectType", projectTypeList);
    }

    /**
     * 封装职称
     */
    private void wrapTitle(ModelAndView mv) {
        CoreTitle model = new CoreTitle();
        List<CoreTitle> titleList = titleService.listByModel(model);
        mv.addObject("coreTitle", titleList);
    }

    /**
     * 封装专家类别
     */
    private void wrapSpecType(ModelAndView mv) {
        CoreSpecType model = new CoreSpecType();
        List<CoreSpecType> specTypeList = specTypeService.listByModel(model);
        mv.addObject("coreSpecType", specTypeList);
    }

    private ReviewBatch getBatchById(int id) {
        if (id > 0) {
            ReviewBatch batch = reviewBatchService.findById(id);
            if (batch != null) {
                return batch;
            }
        }
        List<ReviewBatch> reviewBatchList = reviewBatchService.findByStatus(1);
        if (reviewBatchList != null && reviewBatchList.size() > 0) {
            return reviewBatchList.get(0);
        }
        return null;

    }

}

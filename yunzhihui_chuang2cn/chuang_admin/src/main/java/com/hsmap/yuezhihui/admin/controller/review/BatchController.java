package com.hsmap.yuezhihui.admin.controller.review;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.hsmap.yuezhihui.Constants;
import com.hsmap.yuezhihui.admin.controller.AdminBaseController;
import com.hsmap.yuezhihui.base.page.PageInfo;
import com.hsmap.yuezhihui.base.page.Pageable;
import com.hsmap.yuezhihui.common.CommonUtil;
import com.hsmap.yuezhihui.config.SysConfig;
import com.hsmap.yuezhihui.entity.core.CoreProjectType;
import com.hsmap.yuezhihui.entity.core.CoreTerritory;
import com.hsmap.yuezhihui.entity.review.*;
import com.hsmap.yuezhihui.service.core.ICoreProjectTypeService;
import com.hsmap.yuezhihui.service.core.ICoreTerritoryService;
import com.hsmap.yuezhihui.service.product.IProductInfoService;
import com.hsmap.yuezhihui.service.review.*;
import com.hsmap.yuezhihui.utils.customize.ExcelExport;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.acl.Group;
import java.util.*;

@RestController
public class BatchController extends AdminBaseController {

    //模块名称
    private final String _MODEL_NAME = "review/batch";

    @Autowired
    IReviewBatchService batchService;
    @Autowired
    ICoreProjectTypeService projectTypeService;
    @Autowired
    IReviewRuleService ruleService;
    @Autowired
    IReviewBatchRuleService batchRuleService;
    @Autowired
    IReviewGroupService reviewGroupService;
    @Autowired
    ICoreTerritoryService coreTerritoryService;
    @Autowired
    IProductInfoService productInfoService;
    @Autowired
    private IComplexRelationService complexRelationService;
    @Autowired
    private SysConfig sysConfig;
    @Autowired
    private ExcelExport excelExport;

    @RequestMapping(value = _MODEL_NAME + "/list")
    public ModelAndView list(String name, String content, String remark, @RequestParam(name = "pageNumber", defaultValue = "1") int pageNumber) {
        LOGGER.info("list pageNumber:{}", pageNumber);
        ModelAndView mv = createView(_MODEL_NAME + "/list");
        Pageable pageable = getPageable(pageNumber);
        ReviewBatch model = new ReviewBatch();
        PageInfo<ReviewBatch> pageInfo = batchService.pageList(model, pageable);
        mv.addObject("pageInfo", pageInfo);
        mv.addObject("_PARENT_MENU_NAME", "评审管理");
        mv.addObject("_MENU_NAME", "评审批次管理");
        return mv;
    }


    @RequestMapping(value = _MODEL_NAME + "/addInput")
    public ModelAndView addInput() {
        ModelAndView mv = createView(_MODEL_NAME + "/add");
        List<CoreProjectType> projectTypeList = projectTypeService.findByAll();
        List<ReviewRule> ruleList = ruleService.findByAll();
        mv.addObject("projectTypeList", projectTypeList);
        mv.addObject("ruleList", ruleList);
        return mv;
    }

    @RequestMapping(value = _MODEL_NAME + "/add")
    public String save(String name, String reviewDate, String address, String content, String details) {
        ReviewBatch model = new ReviewBatch();
        model.setName(name);
        model.setReviewDate(CommonUtil.parseDate(reviewDate, Constants._DATE_FORMAT));
        model.setAddress(address);
        model.setContent(content);
        batchService.save(model);
        saveBatchRule(model, details);
        return setResult();
    }


    @RequestMapping(value = _MODEL_NAME + "/editInput")
    public ModelAndView editInput(int id) {
        ModelAndView mv = createView(_MODEL_NAME + "/edit");
        ReviewBatch model = batchService.findById(id);
        List<ReviewRule> ruleList = ruleService.findByAll();
        List<ReviewBatchRule> batchRuleList = batchRuleService.findByBatch(id);
        mv.addObject("ruleList", ruleList);
        mv.addObject("batchRuleList", batchRuleList);
        mv.addObject("model", model);
        return mv;
    }

    @RequestMapping(value = _MODEL_NAME + "/edit")
    public String edit(String name, String reviewDate, String address, String content, String details, int id) {
        ReviewBatch model = batchService.findById(id);
        model.setName(name);
        model.setReviewDate(CommonUtil.parseDate(reviewDate, Constants._DATE_FORMAT));
        model.setAddress(address);
        model.setContent(content);
        batchService.update(model);
        batchRuleService.deleteByBatchId(id);
        saveBatchRule(model, details);
        return setResult();
    }


    @RequestMapping(value = _MODEL_NAME + "/delete")
    public String delete(int id) {
        LOGGER.info("delete  id:{}", id);
        ReviewBatch info = batchService.findById(id);
        if (info == null) {
            return setResultError("根据ID查询数据为空，请确认");
        }
        info.setIsDel(-1);
        batchService.update(info);
        return setResult();
    }


    /***
     * 开始审核
     * @param id
     * @return
     */
    @RequestMapping(value = _MODEL_NAME + "/startInput")
    public ModelAndView startInput(int id) {
        ModelAndView mv = createView(_MODEL_NAME + "/start");
        ReviewBatch reviewBatch = batchService.findById(id);
        List<CoreProjectType> projectTypeList = projectTypeService.findByAll();
        List<CoreTerritory> territorieList = coreTerritoryService.findByAll();
        List<ReviewGroup> groupList = getReviewGroupByBatchId(reviewBatch);

        mv.addObject("territorieList", territorieList);
        mv.addObject("groupList", groupList);
        mv.addObject("model", reviewBatch);
        int productCount = reviewGroupService.countProductByBatchId(reviewBatch.getId(), null);
        int territorieCount = territorieList.size();
        mv.addObject("productCount", productCount);
        mv.addObject("territorieCount", territorieCount);

        return mv;
    }

    @RequestMapping(value = _MODEL_NAME + "/start")
    public String start(HttpServletRequest request, @RequestBody String json) {
        LOGGER.debug("json:{}", json);
        List<ReviewGroup> groupList = JSONArray.parseArray(json, ReviewGroup.class);
        reviewGroupService.addGroup(groupList);

        return setResult();
    }

    @RequestMapping(value = _MODEL_NAME + "/restart")
    public String restart(int id) {
        LOGGER.debug("id:{}", id);
        batchService.restart(id);
        return setResult();
    }


    @RequestMapping(value = _MODEL_NAME + "/loadProductCount")
    public String loadProductCount(int id, String tids) {
        LOGGER.debug("loadProductCount  tids:{}", tids);
        int count = reviewGroupService.countProductByBatchId(id, tids);
        //int groupCount = (int) Math.ceil(count / Constants._GROUP_PRODUCT_COUNT) + 1;
        int groupCount = CommonUtil.ceil(count, Constants._GROUP_PRODUCT_COUNT);
        Map<String, Integer> resultMap = new HashMap<>();
        resultMap.put("productCount", count);
        resultMap.put("groupCount", groupCount);
        return setResult(resultMap);
    }

    /***
     * 结束评审
     * @param id
     * @return
     */
    @PostMapping(value = _MODEL_NAME + "/specEndInput")
    public String endInput(@RequestParam("id") Integer id) {
        ReviewBatch reviewBatch = batchService.findById(id);
        if (reviewBatch == null) {
            return setResultError("批次不存在");
        }
        if (reviewBatch.getStatus().intValue() != 1) {
            return setResultError("批次状态错误");
        }
        ReviewBatch update = new ReviewBatch();
        update.setId(id);
        update.setStatus(2);
        batchService.update(update);
        return setResult();
    }

    /***
     * 结束评审
     * @param id
     * @return
     */
    @PostMapping(value = _MODEL_NAME + "/specDeleteInput")
    public String deleteInput(@RequestParam("id") Integer id) {
        ReviewBatch reviewBatch = batchService.findById(id);
        if (reviewBatch == null) {
            return setResultError("批次不存在");
        }
        ReviewBatch update = new ReviewBatch();
        update.setId(id);
        update.setIsDel(1);
        batchService.update(update);
        return setResult();
    }


    private List<ReviewGroup> getReviewGroupByBatchId(ReviewBatch reviewBatch) {
        List<ReviewGroup> groupList = reviewGroupService.findByBatchId(reviewBatch.getId());

        if (groupList != null && groupList.size() > 0) {

            return groupList;
        }
        groupList = new ArrayList<>();
        List<CoreTerritory> territorieList = coreTerritoryService.findByAll();
        for (CoreTerritory territory : territorieList) {
            ReviewGroup group = new ReviewGroup();
            group.setId(0);
            group.setBatchName(reviewBatch.getName());
            group.setBatchId(reviewBatch.getId());
            group.setName(territory.getName() + "组");
            group.setTerritoryIds(territory.getId() + "");
            group.setProductCount(reviewGroupService.countProductByBatchId(reviewBatch.getId(), group.getTerritoryIds()));
            groupList.add(group);
        }
        return groupList;
    }


    private void saveBatchRule(ReviewBatch batch, String details) {
        String[] idArray = details.split(",");
        ReviewBatchRule batchRule = null;
        for (int i = 0; i < idArray.length / 2; i++) {
            int _typeId = Integer.parseInt(idArray[i * 2]);
            CoreProjectType projectType = projectTypeService.findById(_typeId);
            int _ruleId = Integer.parseInt(idArray[i * 2 + 1]);
            ReviewRule reviewRule = ruleService.findById(_ruleId);
            batchRule = new ReviewBatchRule();
            batchRule.setBatchId(batch.getId());
            batchRule.setBatchName(batch.getName());
            batchRule.setProjectTypeId(projectType.getId());
            batchRule.setProjectTypeName(projectType.getName());
            batchRule.setRuleId(reviewRule.getId());
            batchRule.setRuleName(reviewRule.getName());
            batchRuleService.save(batchRule);
        }
    }

    /**
     * 批次下载页面
     *
     * @param id
     * @return
     */
    @RequestMapping(value = _MODEL_NAME + "/downloadPage/{id}")
    public ModelAndView downloadPage(@PathVariable("id") Integer id) {
        ModelAndView mv = createView(_MODEL_NAME + "/downloadPage");
        mv.addObject("batchId", id);
        return mv;
    }

    /**
     * 批次下组的评审结果
     *
     * @param id 批次ID
     * @param id 1：团队 0：个人
     */
    @GetMapping(value = _MODEL_NAME + "/down/{id}/{team}")
    public void downloadReviewResultXlsx(@PathVariable("id") Integer id, @PathVariable("team") Integer team, HttpServletResponse response) {
        //查询模板关系
        ReviewBatch batch = batchService.findById(id);
        if (batch == null) {
            throw new RuntimeException("没有批次信息");
        }
        List<ComplexReviewResultDo> list = null;
        try {
            list = complexRelationService.selectReviewResultExportInfo(id, team);
        } catch (Exception e) {
            LOGGER.error("", e);
            throw new RuntimeException("获取批次数据异常");
        }
        ExportReviewResultBean resultBean = new ExportReviewResultBean();
        ;
        if (list != null && list.size() > 0) {
            for (ComplexReviewResultDo d : list) {
                resultBean.addResult(d);
            }
            resultBean.markStat();
        }
        String xlsdir = sysConfig.getTempPath();
        File f = new File(xlsdir);
        if (!f.exists())
            f.mkdir();
        String filename = UUID.randomUUID().toString() + ".xlsx";
        resultBean.setBatchName(batch.getName() + (team == 1 ? "现场评审团队项目各专业组别建议入选人员分布表" : "现场评审个人项目各专业组别建议入选人员分布表"));
        try {
            excelExport.exportReviewResult(xlsdir + File.separator + filename, resultBean);
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
            setAttachmentResponseHeader(response, batch.getName() + (team == 1 ? "-团体" : "-个人") + ".xlsx");
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
     * 批次下推荐地的评审结果
     *
     * @param id 批次ID
     * @param id 1：团队 0：个人
     */
    @GetMapping(value = _MODEL_NAME + "/downRem/{id}/{team}")
    public void downloadRecommendXlsx(@PathVariable("id") Integer id, @PathVariable("team") Integer team, HttpServletResponse response) {
        //查询模板关系
        ReviewBatch batch = batchService.findById(id);
        if (batch == null) {
            throw new RuntimeException("没有批次信息");
        }
        List<ComplexRecommendDo> list = null;
        try {
            list = complexRelationService.selectRecommendExportInfo(id, team);
        } catch (Exception e) {
            LOGGER.error("", e);
            throw new RuntimeException("获取批次数据异常");
        }
        ExportRecommendBean resultBean = new ExportRecommendBean();
        ;
        if (list != null && list.size() > 0) {
            for (ComplexRecommendDo d : list) {
                resultBean.addResult(d);
            }
            resultBean.markStat();
        }
        String xlsdir = sysConfig.getTempPath();
        File f = new File(xlsdir);
        if (!f.exists())
            f.mkdir();
        String filename = UUID.randomUUID().toString() + ".xlsx";
        resultBean.setRecommendName(batch.getName() + (team == 1 ? "现场评审团队项目各专业组别建议入选人员属地情况分布表" : "现场评审个人项目各专业组别建议入选人员属地情况分布表"));
        try {
            excelExport.exportRecommend(xlsdir + File.separator + filename, resultBean);
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
            setAttachmentResponseHeader(response, batch.getName() + (team == 1 ? "-属地-团体" : "-属地-个人") + ".xlsx");
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

}

package com.hsmap.yuezhihui.admin.controller.spec;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hsmap.yuezhihui.admin.controller.AdminBaseController;
import com.hsmap.yuezhihui.base.page.PageInfo;
import com.hsmap.yuezhihui.base.page.Pageable;
import com.hsmap.yuezhihui.config.SysConfig;
import com.hsmap.yuezhihui.entity.core.*;
import com.hsmap.yuezhihui.entity.spec.SpecInfo;
import com.hsmap.yuezhihui.entity.spec.SpecInfoExcelDto;
import com.hsmap.yuezhihui.excel.ExcelUtils;
import com.hsmap.yuezhihui.service.core.*;
import com.hsmap.yuezhihui.service.spec.ISpecInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@RestController
public class SpecController  extends AdminBaseController {

    //模块名称
    private final String _MODEL_NAME = "spec/info";

    @Autowired
    ISpecInfoService specInfoService;

    @Autowired
    ICoreTitleService titleService;

    @Autowired
    ICoreTerritoryService territoryService;

    @Autowired
    ICoreSpecTypeService specTypeService;

    @Autowired
    private SysConfig sysConfig;
    @Autowired
    private ICoreTaskInfoService coreTaskInfoService;
    @Autowired
    private ICoreTaskDetailService coreTaskDetailService;

    @RequestMapping(value = _MODEL_NAME + "/list")
    public ModelAndView list(SpecInfo query ,@RequestParam(name = "pageNumber", defaultValue = "1") int pageNumber) {
        LOGGER.info("list pageNumber:{}", pageNumber);
        ModelAndView mv = createView(_MODEL_NAME + "/list");
        Pageable pageable = getPageable(pageNumber);
        PageInfo<SpecInfo> pageInfo = specInfoService.pageList(query, pageable);
        //职称
        wrapTitle(mv);
        //专业领域
        wrapTerritory(mv);
        //专家类别
        wrapSpecType(mv);
        mv.addObject("pageInfo", pageInfo);
        mv.addObject("_PARENT_MENU_NAME","专家数据");
        mv.addObject("_MENU_NAME","专家库管理");
        mv.addObject("query", query);
        return mv;
    }



    @RequestMapping(value = _MODEL_NAME + "/addInput")
    public ModelAndView addInput() {
        ModelAndView mv = createView(_MODEL_NAME + "/add");
        //职称
        wrapTitle(mv);
        //专业领域
        wrapTerritory(mv);
        //专家类别
        wrapSpecType(mv);
        return mv;
    }
    @RequestMapping(value = _MODEL_NAME + "/add")
    public String save(HttpServletRequest request, @RequestBody String json) {
        LOGGER.info("json:{}", json);

        SpecInfo model = JSON.parseObject(json, SpecInfo.class);
        try {
            specInfoService.save(model);
        } catch (Exception e) {
            return setResultError("新增失败，请重试或联系管理员");
        }

        return setResult();
    }


    @RequestMapping(value = _MODEL_NAME + "/editInput")
    public ModelAndView editInput(int id) {
        ModelAndView mv = createView(_MODEL_NAME + "/edit");
        SpecInfo model = specInfoService.findById(id);
        //职称
        wrapTitle(mv);
        //专业领域
        wrapTerritory(mv);
        //专家类别
        wrapSpecType(mv);
        mv.addObject("model", model);
        return mv;
    }

    @RequestMapping(value = _MODEL_NAME + "/edit")
    public String edit(@RequestBody String json) {
        LOGGER.info("json:{}", json);
        SpecInfo model =  JSON.parseObject(json, SpecInfo.class);

        SpecInfo info = specInfoService.findById(model.getId());
        if (info == null) {
            return setResultError("根据ID查询数据为空，请确认");
        }
        BeanUtils.copyProperties(model, info);
        try {
            specInfoService.update(info);
        } catch (Exception e) {
            return setResultError("修改失败，请重试或联系管理员");
        }

        return setResult();
    }

    @RequestMapping(value = _MODEL_NAME + "/delete")
    public String delete(int id) {
        LOGGER.info("delete  id:{}", id);
        SpecInfo info = specInfoService.findById(id);
        if (info == null) {
            return setResultError("根据ID查询数据为空，请确认");
        }
        info.setIsDel(-1);

        try {
            specInfoService.update(info);
        } catch (Exception e) {
            return setResultError("删除失败，请重试或联系管理员");
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


    @RequestMapping(value = _MODEL_NAME + "/imports")
    public ModelAndView imports() {
        ModelAndView mv = createView(_MODEL_NAME + "/imports");
        //导入记录
        CoreTaskInfo taskInfoQuery = new CoreTaskInfo();
        taskInfoQuery.setType(1);//0项目1专家
        taskInfoQuery.setIsDel(0);
        List<CoreTaskInfo> taskInfoList = coreTaskInfoService.listByModel(taskInfoQuery);
        mv.addObject("taskInfoList", taskInfoList);
        return mv;
    }

    /**
     * Excel解析
     *
     * @param taskName
     * @param taskFile
     * @return
     */
    @PostMapping(value = _MODEL_NAME + "/parse_xls")
    @ResponseBody
    public String parseXls(@RequestParam("taskName") String taskName,
                           @RequestParam("taskFile") String taskFile) {
        if (taskName.trim().length() == 0) {
            return setResultError("请填写任务名称");
        }
        //判断TasFile
        File f = new File(sysConfig.getDownloadDir() + File.separator + taskFile);
        if (!f.exists()) {
            LOGGER.error("解析的Excel文件不存在：taskName={},taskFile={}",taskName, taskFile);
            return setResultError("上传的文件不存在");
        }
        LOGGER.info("正在解析的Excel：taskName={},taskFile={}", taskName, taskFile);

        CoreTitle queryTitle = new CoreTitle();
        List<CoreTitle> titleList = titleService.listByModel(queryTitle);
        if(titleList==null){
            return setResultError("没有录入专家职称维度数据");
        }

        CoreTerritory queryTerritory = new CoreTerritory();
        List<CoreTerritory> territoryList = territoryService.listByModel(queryTerritory);
        if (territoryList == null) {
            return setResultError("没有录入专业领域维度数据");
        }

        CoreSpecType querySpecType = new CoreSpecType();
        List<CoreSpecType> specTypeList = specTypeService.listByModel(querySpecType);
        if(specTypeList==null){
            return setResultError("没有录入专家类别维度数据");
        }

        //解析Excel
        List<SpecInfoExcelDto> dtos = null;
        try {
            dtos = ExcelUtils.readExcel(f.getAbsolutePath(), SpecInfoExcelDto.class);
        } catch (Exception e) {
            LOGGER.error("解析的Excel失败", e);
            return setResultError("解析的Excel失败");
        }

        //不需要事务，单条失败不能影响全部数据
        CoreTaskInfo task = null;
        try {
            //构建任务表
            task = new CoreTaskInfo();
            task.setType(1);//0项目1专家
            task.setName(taskName);
            task.setBatchId(0);
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
            for (SpecInfoExcelDto dto : dtos) {
                detail.setTaskId(task.getId());
                detail.setDataJson(JSONObject.toJSONString(dto));

                detailList.add(detail);
                String ret = dto.check(titleList, territoryList,specTypeList);
                SpecInfo info = null;
                if ("".equals(ret)) {
                    info = dto.toEntiry();
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
                        specInfoService.save(info);
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


}

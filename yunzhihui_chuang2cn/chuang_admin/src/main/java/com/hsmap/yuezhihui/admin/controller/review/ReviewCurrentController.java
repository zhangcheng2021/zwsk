package com.hsmap.yuezhihui.admin.controller.review;

import com.alibaba.fastjson.JSONArray;
import com.hsmap.yuezhihui.admin.controller.AdminBaseController;
import com.hsmap.yuezhihui.base.page.PageInfo;
import com.hsmap.yuezhihui.base.page.Pageable;
import com.hsmap.yuezhihui.config.SysConfig;
import com.hsmap.yuezhihui.config.TemplateConfig;
import com.hsmap.yuezhihui.config.pdf.PdfConfig;
import com.hsmap.yuezhihui.entity.review.ComplexRelationProductDo;
import com.hsmap.yuezhihui.entity.review.ReviewBatch;
import com.hsmap.yuezhihui.entity.review.ReviewGroup;
import com.hsmap.yuezhihui.entity.review.ReviewRecordDetail;
import com.hsmap.yuezhihui.entity.spec.GroupSpecInfo;
import com.hsmap.yuezhihui.entity.spec.SpecInfo;
import com.hsmap.yuezhihui.service.core.ICoreProjectTypeService;
import com.hsmap.yuezhihui.service.core.ICoreTerritoryService;
import com.hsmap.yuezhihui.service.review.IComplexRelationService;
import com.hsmap.yuezhihui.service.review.IReviewBatchService;
import com.hsmap.yuezhihui.service.review.IReviewGroupService;
import com.hsmap.yuezhihui.service.spec.ISpecInfoService;
import com.itextpdf.text.PageSize;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.context.Context;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 当前的评审
 */
@Controller
public class ReviewCurrentController extends AdminBaseController {

    //模块名称
    private final String _MODEL_NAME = "review/current";
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

    @Autowired
    private ISpecInfoService specInfoService;

    @Autowired
    private TemplateConfig templateConfig;

    @Autowired
    private PdfConfig pdfConfig;

    /**
     * 首页
     *
     * @param query
     * @param pageNumber
     * @return
     */
    @RequestMapping(value = _MODEL_NAME + "/list")
    public ModelAndView list(ComplexRelationProductDo query,
                             @RequestParam(name = "batchId", defaultValue = "0") int batchId,
                             @RequestParam(name = "currentGroupId", defaultValue = "0") int currentGroupId,
                             @RequestParam(name = "pageNumber", defaultValue = "1") int pageNumber) {

        //获取有效的评审批次，当前的每次评审段是存在一个有效批次
        ReviewBatch batch = getBatchById(batchId);
        if (batch == null) {
            return getErrorPage2("当前项目不存在有效的评审批次");
        }
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
                if (rg.getId().intValue() == currentGroupId) {
                    reviewGroup = rg;
                    break;
                }
            }
        }
        if (reviewGroup == null) {
            return getErrorPage("批次分组不存在，分组ID：" + currentGroupId);
        }

        ModelAndView mv = createView(_MODEL_NAME + "/list");
        Pageable pageable = getPageable(pageNumber);
        query.setQueryBatchId(batch.getId());
        query.setQueryGroupId(currentGroupId);
        PageInfo<ComplexRelationProductDo> pageInfo = complexRelationService.reviewHisProductInfoOfPage(query, pageable);
        mv.addObject("pageInfo", pageInfo);
        mv.addObject("groupList", groupList);
        mv.addObject("reviewGroup", reviewGroup);
        mv.addObject("_PARENT_MENU_NAME", "评审管理");
        mv.addObject("_MENU_NAME", "当前评审管理");
        mv.addObject("currentGroupId", currentGroupId);
        mv.addObject("batchId", batch.getId());

        //如果用户只配置了一个组数据，左侧导航栏不展示
        boolean showNav = (groupList.size() > 1);
        mv.addObject("showNav", showNav);

        GroupSpecInfo specQuery = new GroupSpecInfo();
        specQuery.setQueryGroupId(currentGroupId);
        List<GroupSpecInfo> list = specInfoService.selectGroupSpecInfo(specQuery);
        mv.addObject("specList", list);
        return mv;
    }


    /**
     * 详情页
     *
     * @param id
     * @return
     */
    @RequestMapping(value = _MODEL_NAME + "/detail/{id}")
    public ModelAndView detail(@RequestParam(name = "currentGroupId") int currentGroupId,
                               @PathVariable("id") Integer id,
                               @RequestParam(value = "currentSpecId", defaultValue = "0") Integer currentSpecId) {
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
        mv.addObject("_MENU_NAME", "当前评审管理-详情 / " + dos.getPi_user_name());

        //是否完成
        boolean isFinished = dos.getRp_status_() == 2;
        //是否评审
        boolean isReview = true;
        mv.addObject("isFinished", isFinished);
        //查询项目的打分规则
        List<ReviewRecordDetail> recordDetailList = complexRelationService.productScoreListById(id);
        //查询项目的审核专家列表
        if (isFinished) {
            //如果是已完成的，根据record关联专家
            List<SpecInfo> specInfoList = complexRelationService.productSpecListById(id);
            mv.addObject("specInfoList", specInfoList);
            //第一次进入
            if (currentSpecId == 0 && specInfoList != null && specInfoList.size() > 0) {
                currentSpecId = specInfoList.get(0).getId();//取列表的第一个作为页面的第一次选中数据
            }
        } else {
            //根据组关联专家
            GroupSpecInfo specQuery = new GroupSpecInfo();
            specQuery.setQueryGroupId(currentGroupId);
            List<GroupSpecInfo> list = specInfoService.selectGroupSpecInfo(specQuery);
            mv.addObject("specInfoList", list);
            //第一次进入
            if (currentSpecId == 0 && list != null && list.size() > 0) {
                currentSpecId = list.get(0).getId();//取列表的第一个作为页面的第一次选中数据
            }
        }

        SpecInfo specReviewInfo = null;
        if (currentSpecId > 0) {
            //查询专家的评论信息以及签名
            specReviewInfo = complexRelationService.productSpecInfoById(id, currentSpecId);
            if (specReviewInfo == null) {
                specReviewInfo = new SpecInfo();
                isReview = false;
            }
            //查询评论项和评分，改成打总分后，不在需要
            //  recordDetailList = complexRelationService.productSpecScoreListById(id, currentSpecId);
            mv.addObject("specScore", specReviewInfo.getRecordScore() != null ? specReviewInfo.getRecordScore() : "-");
        } else {
            specReviewInfo = new SpecInfo();
            isReview = false;
            mv.addObject("specScore", "-");
        }
        String signUrl = sysConfig.signUrl() + "/";
        if (specReviewInfo.getSignUrl() == null || specReviewInfo.getSignUrl().trim().length() == 0) {
            signUrl += "empty_sign.png";
        } else {
            signUrl += specReviewInfo.getSignUrl();
        }
        mv.addObject("isReview", isReview);
        mv.addObject("signUrl", signUrl);
        mv.addObject("specReviewInfo", specReviewInfo);
        mv.addObject("recordDetailList", recordDetailList != null ? recordDetailList : new ArrayList<>());
        mv.addObject("currentSpecId", currentSpecId);
        mv.addObject("currentGroupId", currentGroupId);
        return mv;
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

    /**
     * PDF
     *
     * @param pid
     * @return
     */
    @RequestMapping(value = _MODEL_NAME + "/pdfs/{pid}")
    @ResponseBody
    public String pdfs(@PathVariable("pid") Integer pid,
                       HttpServletResponse resp,
                       HttpServletRequest req) {
        //存储
        List<String> files = new ArrayList<>();
        Integer[] ids = new Integer[]{pid};
        if (ids.length > 0) {
            for (int id : ids) {
                ComplexRelationProductDo query = new ComplexRelationProductDo();
                query.setQueryProductId(id);
                List<ComplexRelationProductDo> productDos = complexRelationService.reviewHisProductInfo(query);
                if (productDos == null || productDos.size() == 0) {
                    return setResultError("没有查询到项目信息");
                }
                ComplexRelationProductDo dos = productDos.get(0);
                //是否完成
                if (dos.getRp_status_() != 2) {
                    return setResultError("项目评审没有完成");
                }
                //查询项目的打分规则
                List<ReviewRecordDetail> recordDetailList = complexRelationService.productScoreListById(id);

                //如果是已完成的，根据record关联专家
                List<SpecInfo> specInfoList = complexRelationService.productSpecListById(id);
                String pdfHtmlRoot = sysConfig.getTempPdfHtml() + File.separator + dos.getPi_batch_id();
                File pdfHtmlRootFile = new File(pdfHtmlRoot);
                if (!pdfHtmlRootFile.exists())
                    pdfHtmlRootFile.mkdirs();
                String pdfRoot = sysConfig.getTempPdf() + File.separator + dos.getPi_batch_id();
                File pdfRootFile = new File(pdfRoot);
                if (!pdfRootFile.exists())
                    pdfRootFile.mkdirs();

                File pdfHtml = null;
                String pdf = null;

                for (SpecInfo spec : specInfoList) {
                    //查询专家的评论信息以及签名
                    SpecInfo specReviewInfo = complexRelationService.productSpecInfoById(id, spec.getId());
                    if (specReviewInfo == null) {
                        //TODO：记录SQL
                        continue;
                    }
                    String fileName = specReviewInfo.getSignUrl();
                    if (fileName == null || fileName.length() == 0) {
                        fileName = "empty_sign.png";
                    }
                    File tmp = new File(sysConfig.signPath() + fileName);
                    if (!tmp.exists()) {
                        fileName = "empty_sign.png";
                    }
                    dos.getCpt_name_();
                    String title = String.format("绍兴“名士之乡”英才计划%s人才（%s）项目评审打分表", (dos.getCpt_name_().indexOf("创业") > -1 ? "创业" : "创新"), (dos.getCpt_name_().indexOf("团队") > -1 ? "团队" : "个人"));

                    Context context = new Context();
                    context.setVariable("name", dos.getPi_user_name());
                    context.setVariable("title", title);
                    context.setVariable("recordDetailList", recordDetailList);
                    context.setVariable("recordDetailListSize", recordDetailList.size());
                    context.setVariable("remark", specReviewInfo.getRemark());
                    context.setVariable("fileName", fileName);
                    context.setVariable("recordScore", specReviewInfo.getRecordScore());
                    context.setVariable("date", DateFormatUtils.format(specReviewInfo.getCreateTime(), "yyyy年M月dd日"));

                    pdfHtml = new File(pdfHtmlRoot + File.separator + id + "_" + specReviewInfo.getId() + ".html");
                    boolean succ = templateConfig.process("pdfs/pdf_score", context, pdfHtml);

                    if (succ && pdfHtml.exists()) {
                        try {
                            byte[] htmlByte = FileCopyUtils.copyToByteArray(new File(pdfHtmlRoot + File.separator + id + "_" + specReviewInfo.getId() + ".html"));
                            pdf = pdfRoot + File.separator + dos.getPi_user_name() + "_" + specReviewInfo.getUserName() + ".pdf";
                            //生成pdf文件
                            pdfConfig.generate(pdf, PageSize.A4, "", true, null, htmlByte);
                            File f = new File(pdf);
                            //存储文件地址
                            if (f.exists()) {
                                //TODO: 记录SQL
                                LOGGER.info("pdf exists:{}", f.getName());
                                //获取各个文件路径
                                files.add(pdf);
                            } else {
                                //TODO: 记录SQL
                                LOGGER.info("pdf exists:{}", f.getName());
                            }

                        } catch (Exception e) {
                            LOGGER.error("", e);
                        }
                    } else {
                        //TODO：记录SQL
                        LOGGER.info("pdfHtml exists:{}", pdfHtml.getName());
                    }
                }
            }
        } else {
            return setResultError();
        }
//        Thread t1 = new Thread(new Runnable() {
//            public void run() {
//                pdfFile(resp, files);
//
//            }
//        });
//        t1.start();
        pdfFile(resp, files);
        return setResult();
    }
    /* *//**
     * 单个id打包PDF
     *
     * @param id
     * @return
     *//*
    @RequestMapping(value = _MODEL_NAME + "/pdf/{id}")
    @ResponseBody
    public String pdf(@PathVariable(name = "id") int id,
                      HttpServletResponse resp,
                      HttpServletRequest req) {
        //存储地址
        List<String> files = new ArrayList<>();

        ComplexRelationProductDo query = new ComplexRelationProductDo();
        query.setQueryProductId(id);
        List<ComplexRelationProductDo> productDos = complexRelationService.reviewHisProductInfo(query);
        if (productDos == null || productDos.size() == 0) {
            return setResultError("没有查询到项目信息");
        }
        ComplexRelationProductDo dos = productDos.get(0);
        //是否完成
        if (dos.getRp_status_() != 2) {
            return setResultError("项目评审没有完成");
        }
        //查询项目的打分规则
        List<ReviewRecordDetail> recordDetailList = complexRelationService.productScoreListById(id);

        //如果是已完成的，根据record关联专家
        List<SpecInfo> specInfoList = complexRelationService.productSpecListById(id);
        String pdfHtmlRoot = sysConfig.getTempPdfHtml() + File.separator + dos.getPi_batch_id();
        File pdfHtmlRootFile = new File(pdfHtmlRoot);
        if (!pdfHtmlRootFile.exists()){
            pdfHtmlRootFile.mkdirs();}
        String pdfRoot = sysConfig.getTempPdf() + File.separator + dos.getPi_batch_id();
        File pdfRootFile = new File(pdfRoot);
        if (!pdfRootFile.exists()){
            pdfRootFile.mkdirs();}

        File pdfHtml = null;
        String pdf = null;


        for (SpecInfo spec : specInfoList) {
            //查询专家的评论信息以及签名
            SpecInfo specReviewInfo = complexRelationService.productSpecInfoById(id, spec.getId());
            if (specReviewInfo == null) {
                //TODO：记录SQL
                continue;
            }
            String fileName = specReviewInfo.getSignUrl();
            if (fileName == null || fileName.length() == 0) {
                fileName = "empty_sign.png";
            }
            File tmp = new File(sysConfig.signPath() + fileName);
            if (!tmp.exists()) {
                fileName = "empty_sign.png";
            }

            dos.getCpt_name_();
            String title = String.format("绍兴“名士之乡”英才计划%s人才（%s）项目评审打分表", (dos.getCpt_name_().indexOf("创业") > -1 ? "创业" : "创新"), (dos.getCpt_name_().indexOf("团队") > -1 ? "团队" : "个人"));

            Context context = new Context();
            context.setVariable("name", dos.getPi_user_name());
            context.setVariable("title", title);
            context.setVariable("recordDetailList", recordDetailList);
            context.setVariable("recordDetailListSize", recordDetailList.size());
            context.setVariable("remark", specReviewInfo.getRemark());
            context.setVariable("fileName", fileName);
            context.setVariable("recordScore", specReviewInfo.getRecordScore());
            context.setVariable("date", DateFormatUtils.format(specReviewInfo.getCreateTime(), "yyyy年M月dd日"));

            pdfHtml = new File(pdfHtmlRoot + File.separator + id + "_" + specReviewInfo.getId() + ".html");
            boolean succ = templateConfig.process("pdfs/pdf_score", context, pdfHtml);

            if (succ && pdfHtml.exists()) {
                try {
                    byte[] htmlByte = FileCopyUtils.copyToByteArray(new File(pdfHtmlRoot + File.separator + id + "_" + specReviewInfo.getId() + ".html"));
                    pdf = pdfRoot + File.separator + dos.getPi_user_name() + "_" + specReviewInfo.getUserName() + ".pdf";
                    //生成pdf文件
                    pdfConfig.generate(pdf, PageSize.A4, "", true, null, htmlByte);
                    File f = new File(pdf);
                    //存储文件地址
                    if (f.exists()) {
                        //TODO: 记录SQL
                        LOGGER.info("pdf exists:{}", f.getName());
                        //获取各个文件路径
                        files.add(pdf);
                    } else {
                        //TODO: 记录SQL
                        LOGGER.info("pdf exists:{}", f.getName());
                    }

                } catch (Exception e) {
                    LOGGER.error("", e);
                }
            } else {
                //TODO：记录SQL
                LOGGER.info("pdfHtml exists:{}", pdfHtml.getName());
            }
        }

        pdfFile(resp, files);

        return setResult();
    }*/

    /**
     * 下载实现
     *
     * @param resp
     * @param files
     */
    private void pdfFile(HttpServletResponse resp, List<String> files) {
        try {
            //创建压缩文件需要的空的zip包
//            String zipBasePath = "D:\\temp\\cgj\\zip";
            String zipBasePath = pdfConfig.getZipRoot();
            String zipName = "项目评审打分表" + getDateString() + ".zip";
            String zipFilePath = zipBasePath + File.separator + zipName;
            //压缩文件
            File zip = new File(zipFilePath);
            if (!zip.exists()) {
                zip.createNewFile();
            }
            //创建zip文件输出流
            ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zip));
            this.zipFile(zipBasePath, zipName, zipFilePath, files, zos);
            zos.close();
            //设置下载的压缩文件名称
            resp.setHeader("Content-disposition", "attachment;filename=" + zipName);
            //将打包后的文件写到客户端，输出的方法同上，使用缓冲流输出
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(zipFilePath));
            byte[] buff = new byte[bis.available()];
            bis.read(buff);
            bis.close();
            // 清空response
            resp.reset();
            // 设置response的Header
            resp.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(zipName, "UTF-8"));
            OutputStream out = resp.getOutputStream();
            //输出数据文件
            out.write(buff);
            //释放缓存
            out.flush();
            //关闭输出流
            out.close();
        } catch (Exception e) {
            LOGGER.error("", e);
        }
    }

    /**
     * 压缩文件
     *
     * @param zipBasePath 临时压缩文件基础路径
     * @param zipName     临时压缩文件名称
     * @param zipFilePath 临时压缩文件完整路径
     * @param filePaths   需要压缩的文件路径集合
     * @throws IOException
     */
    private String zipFile(String zipBasePath, String zipName, String zipFilePath, List<String> filePaths, ZipOutputStream zos) throws IOException {

        //循环读取文件路径集合，获取每一个文件的路径
        for (String filePath : filePaths) {
            //根据文件路径创建文件
            File inputFile = new File(filePath);
            //判断文件是否存在
            if (inputFile.exists()) {
                //判断是否属于文件，还是文件夹
                if (inputFile.isFile()) {
                    //创建输入流读取文件
                    BufferedInputStream bis = new BufferedInputStream(new FileInputStream(inputFile));
                    //将文件写入zip内，即将文件进行打包
                    zos.putNextEntry(new ZipEntry(inputFile.getName()));
                    //写入文件的方法，同上
                    int size = 0;
                    //设置读取数据缓存大小
                    byte[] buffer = new byte[1024];
                    while ((size = bis.read(buffer)) > 0) {
//                    while ((size = bis.read(buffer))!= -1) {
                        zos.write(buffer, 0, size);
                    }
                    //关闭输入输出流
                    zos.closeEntry();
                    bis.close();
                } else {
                    //如果是文件夹，则使用穷举的方法获取文件，写入zip
                    try {
                        File[] files = inputFile.listFiles();
                        List<String> filePathsTem = new ArrayList<String>();
                        for (File fileTem : files) {
                            filePathsTem.add(fileTem.toString());
                        }
                        return zipFile(zipBasePath, zipName, zipFilePath, filePathsTem, zos);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return null;
    }

    /**
     * 生成zip名
     *
     * @return
     */
    private String getDateString() {
        String dateString = UUID.randomUUID().toString();

        return dateString;
    }
}

package com.hsmap.yuezhihui.api.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hsmap.yuezhihui.api.vo.*;
import com.hsmap.yuezhihui.common.DecimalUtil;
import com.hsmap.yuezhihui.config.SysConfig;
import com.hsmap.yuezhihui.entity.product.ProductInfo;
import com.hsmap.yuezhihui.entity.review.*;
import com.hsmap.yuezhihui.images.FileUtils;
import com.hsmap.yuezhihui.service.product.IProductInfoService;
import com.hsmap.yuezhihui.service.review.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.UUID;

@RestController
public class ReviewController extends ApiBaseController {
    private final String _MODEL_NAME = "/review/";
    @Autowired
    private SysConfig sysConfig;
    @Autowired
    IProductInfoService productInfoService;
    @Autowired
    IReviewBatchService batchService;
    @Autowired
    IReviewGroupService reviewGroupService;

    @Autowired
    IReviewProductService reviewProductService;
    @Autowired
    IReviewSpecialistService reviewSpecialistService;
    @Autowired
    IReviewRuleDetailService ruleDetailService;
    @Autowired
    IReviewRecordService recordService;
    @Autowired
    IReviewRecordDetailService recordDetailService;
    @Autowired
    private IComplexRelationService complexRelationService;

    /**/
    @RequestMapping(value = "/product/productList")
    public String productList() {
        ReviewBatch batch = batchService.findByValid();
//        List<ProductInfo> list = productInfoService.findByBatchId(batch.getId(), null);
        //map.put("isStatus",dictService.getValueByCode(_APP_IS_REVIEW));
        List<ProductInfo> list = productInfoService.selectProductGroupAll(batch.getId());
        return setResult(ProductInfoVO.list2vo(list));
    }


    /**
     * 分组信息表
     *
     * @return
     */
    @RequestMapping(value = _MODEL_NAME + "/groupList")
    public String groupList() {
        ReviewBatch batch = batchService.findByValid();
        List<ReviewGroup> list = reviewGroupService.findByBatchId(batch.getId());
        //map.put("isStatus",dictService.getValueByCode(_APP_IS_REVIEW));
        return setResult(ReviewGroupVO.list2vo(list));
    }


    /**
     * 评审项目列表
     *
     * @return
     */
    @RequestMapping(value = _MODEL_NAME + "/productList")
    public String reviewProductList() {
        List<ReviewProduct> list = reviewProductService.findByAll();
        //map.put("isStatus",dictService.getValueByCode(_APP_IS_REVIEW));
        return setResult(ReviewProductVO.list2vo(list));
    }

    /**
     * 评审专家列表
     *
     * @return
     */
    @RequestMapping(value = _MODEL_NAME + "/specList")
    public String specList() {
        List<ReviewSpecialist> list = reviewSpecialistService.selectAll();
        //map.put("isStatus",dictService.getValueByCode(_APP_IS_REVIEW));
        return setResult(ReviewSpecialistVO.list2vo(list));
    }

    /**
     * 评审专家列表
     *
     * @return
     */
    @RequestMapping(value = _MODEL_NAME + "/ruleList")
    public String ruleDetailList() {
        List<ReviewRuleDetail> list = ruleDetailService.findByAll();
        //map.put("isStatus",dictService.getValueByCode(_APP_IS_REVIEW));
        return setResult(ReviewRuleDetailVO.list2vo(list));
    }

    @RequestMapping(value = _MODEL_NAME + "/pushScore")
    public String pushScore(HttpServletRequest request, @RequestBody String json) {
        LOGGER.info("json:{}", json);
        JSONObject jsonObject = JSONObject.parseObject(json);
        saveScore(jsonObject);
        return setResult();
    }

    @RequestMapping(value = _MODEL_NAME + "/batchPushScore")
    public String batchPushScore(HttpServletRequest request, @RequestBody String json) {
        LOGGER.info("json:{}", json);
        JSONArray jsonArray = JSONObject.parseArray(json);
        for (int i = 0; i < jsonArray.size(); i++) {
            saveScore(jsonArray.getJSONObject(i));
        }
        return setResult();
    }

    private void saveScore(JSONObject jsonObject) {
        //专家ID
        int spceId = jsonObject.getInteger("spceId");
        //项目ID
        int projectId = jsonObject.getInteger("projectId");
        //分组ID
        int groupId = jsonObject.getInteger("groupId");
        //评分规则ID
        int ruleId = jsonObject.getInteger("ruleId");
        String remark = jsonObject.getString("remark");
        //评分细则
        //专家签名 sign_url
        String signImg = jsonObject.getString("signImg");
        String singFileName = UUID.randomUUID().toString() + ".png";
        Integer score = jsonObject.getInteger("score");

        FileUtils.stringFromBase64(signImg, "PNG", sysConfig.signPath() + singFileName);

        //List rule_detail_id  score_
        ReviewRecord reviewRecord = recordService.findByGroupId(groupId, projectId, spceId);
        if (reviewRecord == null) {
            reviewRecord = new ReviewRecord();
            reviewRecord.setId(0);
        }
        reviewRecord.setProjectId(projectId);
        reviewRecord.setGroupId(groupId);
        reviewRecord.setRuleId(ruleId);
        reviewRecord.setSpceId(spceId);
        reviewRecord.setSignUrl(singFileName);
        reviewRecord.setRemark(remark);
        reviewRecord.setScore(score);
        if (reviewRecord.getId() < 1) {
            recordService.save(reviewRecord);
        }

        //int weightScore = recordService.getWeightSorce(reviewRecord);
//        reviewRecord.setWeightScore(weightScore);
        recordService.update(reviewRecord);
    }


    /**
     * form-data 方式批量提交评分
     *
     * @param json
     * @return
     */
    @PostMapping(value = _MODEL_NAME + "/pushBatchScoreForm")
    public String pushBatchScoreForm(@RequestParam("json") String json) {
//        LOGGER.info("entity:{}", scoreVo.toString());
//        saveScoreForm(scoreVo);
        //解码
        String jsonDecode = "";
        try {
            jsonDecode = URLDecoder.decode(json, "utf-8");
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("", e);
        }
        LOGGER.info("json:{}", jsonDecode.substring(0, jsonDecode.length() > 100 ? 100 : jsonDecode.length()));

        JSONArray jsonArray = JSONObject.parseArray(jsonDecode);
        JSONObject jsonObject = null;
        ScoreVo scoreVo = null;
        for (int i = 0; i < jsonArray.size(); i++) {
            jsonObject = jsonArray.getJSONObject(i);
            scoreVo = new ScoreVo();
            scoreVo.setSpceId(jsonObject.getInteger("spceId"));
            scoreVo.setProjectId(jsonObject.getInteger("projectId"));
            scoreVo.setGroupId(jsonObject.getInteger("groupId"));
            scoreVo.setRuleId(jsonObject.getInteger("ruleId"));
            scoreVo.setRemark(jsonObject.getString("remark"));
            //专家签名 sign_url
            scoreVo.setSignImg(jsonObject.getString("signImg"));
            scoreVo.setScore(jsonObject.getInteger("score"));
            LOGGER.info("batch scoreVo:{}", scoreVo.toString2());
            saveScoreForm(scoreVo);
        }
        return setResult();
    }

    /**
     * form-data 方式提交评分
     *
     * @param scoreVo
     * @return
     */
    @RequestMapping(value = _MODEL_NAME + "/pushScoreForm")
    public String pushScore(ScoreVo scoreVo) {
        LOGGER.info("entity:{}", scoreVo.toString());
        saveScoreForm(scoreVo);
        return setResult();
    }

    private void saveScoreForm(ScoreVo scoreVo) {
        //专家ID
        int spceId = scoreVo.getSpceId();
        //项目ID
        int projectId = scoreVo.getProjectId();
        //分组ID
        int groupId = scoreVo.getGroupId();
        //评分规则ID
        int ruleId = scoreVo.getRuleId();
        String remark = scoreVo.getRemark();
        //评分细则
        //专家签名 sign_url
        String signImg = scoreVo.getSignImg();
        String singFileName = UUID.randomUUID().toString() + ".png";
        Integer score = scoreVo.getScore();

        FileUtils.stringFromBase64(signImg, "PNG", sysConfig.signPath() + singFileName);

        //List rule_detail_id  score_
        ReviewRecord reviewRecord = recordService.findByGroupId(groupId, projectId, spceId);
        if (reviewRecord == null) {
            reviewRecord = new ReviewRecord();
            reviewRecord.setId(0);
        }
        reviewRecord.setProjectId(projectId);
        reviewRecord.setGroupId(groupId);
        reviewRecord.setRuleId(ruleId);
        reviewRecord.setSpceId(spceId);
        reviewRecord.setSignUrl(singFileName);
        reviewRecord.setRemark(remark);
        reviewRecord.setScore(score);
        if (reviewRecord.getId() < 1) {
            recordService.save(reviewRecord);
        } else {
            recordService.update(reviewRecord);
        }
        //查询项目组中的未评审的专家个数，更具项目ID
        List<ComplexProductReviewed> revieweds = complexRelationService.selectProductReviewed(projectId);
        boolean allScore = true;
        int max = 0, min = 100, sum = 0;
        for (ComplexProductReviewed c : revieweds) {
            if (c.getScore() == null) {
                allScore = false;
                break;
            }
            if (c.getScore() > max)
                max = c.getScore();
            if (c.getScore() < min)
                min = c.getScore();
            sum += c.getScore();
        }
        ReviewProduct updateProduct = new ReviewProduct();
        updateProduct.setProductId(projectId);
        updateProduct.setGroupId(groupId);
        if (allScore) {
            int r = revieweds.size();
            //更新为以完成，并且打分，
            //R=1，P=R.score = AVG(R.score)
            //R=2, P=AVG(R.score)
            //R>2, (SUM(R.score)-max-min)/(R.size-2)
            double scores = 0.0;//取整
            if (r < 2) {
                scores = DecimalUtil.formatD00(sum * 1.0 / r); //四舍五入
            } else {
                scores = DecimalUtil.formatD00((sum - max - min) * 1.0 / (r - 2)); //四舍五入
            }
            updateProduct.setStatus(2);
            updateProduct.setScore(scores);
        } else {
            //更新为进行中
            updateProduct.setStatus(1);
        }
        //更新ReviewProduct
        reviewProductService.updateByGroupAndProduct(updateProduct);
    }

    private void saveOrUpdateRecordDetail(int detailId, int score, int recordId, int ruleId) {
        ReviewRecordDetail detail = recordDetailService.findByDetailId(recordId, detailId);
        if (detail == null) {
            detail = new ReviewRecordDetail();
            detail.setId(0);
        }
        detail.setRuleDetailId(detailId);
        detail.setRecordId(recordId);
        detail.setScore(score);
        detail.setRuleId(ruleId);
        if (detail.getId() > 0) {
            recordDetailService.update(detail);
        } else {
            recordDetailService.save(detail);
        }

    }


}

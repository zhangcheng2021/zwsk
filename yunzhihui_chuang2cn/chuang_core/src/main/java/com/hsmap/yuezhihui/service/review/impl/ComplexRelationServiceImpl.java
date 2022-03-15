package com.hsmap.yuezhihui.service.review.impl;

import com.hsmap.yuezhihui.base.page.PageInfo;
import com.hsmap.yuezhihui.base.page.Pageable;
import com.hsmap.yuezhihui.entity.review.*;
import com.hsmap.yuezhihui.entity.spec.SpecInfo;
import com.hsmap.yuezhihui.mapper.review.ComplexRelationMapper;
import com.hsmap.yuezhihui.service.review.IComplexRelationService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ComplexRelationServiceImpl implements IComplexRelationService {
    @Autowired
    private ComplexRelationMapper complexRelationMapper;

    /**
     * 评审分组-专家信息的关联查询
     *
     * @param query
     * @return
     */
    @Override
    public List<ComplexRelationSpecDo> reviewSpecInfoByGroupId(ComplexRelationSpecDo query) {
        return complexRelationMapper.reviewSpecInfoByGroupId(query);
    }

    /**
     * 评审分组-项目信息的关联查询
     *
     * @param query
     * @return
     */
    @Override
    public List<ComplexRelationProductDo> reviewProductInfoByGroupId(ComplexRelationProductDo query) {
        return complexRelationMapper.reviewProductInfoByGroupId(query);
    }

    /**
     * 历史评审-项目信息 关联查询
     *
     * @param query
     * @return
     */
    @Override
    public List<ComplexRelationProductDo> reviewHisProductInfo(ComplexRelationProductDo query) {
        return complexRelationMapper.reviewHisProductInfo(query);
    }

    /**
     * 历史评审-项目信息 关联查询Count
     *
     * @param query
     * @return
     */
    @Override
    public int reviewHisProductInfoCount(ComplexRelationProductDo query) {
        return complexRelationMapper.reviewHisProductInfoCount(query);
    }

    /**
     * 历史评审-项目信息 关联查询 ,分页查询
     *
     * @param query
     * @param pageable
     * @return
     */
    @Override
    public PageInfo<ComplexRelationProductDo> reviewHisProductInfoOfPage(ComplexRelationProductDo query, Pageable pageable) {
        PageInfo<ComplexRelationProductDo> page = null;
        long totalElements = complexRelationMapper.reviewHisProductInfoCount(query);
        if (totalElements > 0) {
            query.setPageNumber(pageable.getPageNumber());
            query.setPageSize(pageable.getPageSize());
            query.setOrderByClause(" rp.sort_ asc , rp.duration_ asc ");
            List<ComplexRelationProductDo> list = complexRelationMapper.reviewHisProductInfo(query);
            page = new PageInfo<ComplexRelationProductDo>(pageable, totalElements, list);
        } else {
            page = new PageInfo<ComplexRelationProductDo>(pageable, 0l, null);
        }
        return page;
    }


    /**
     * 项目已经审核过的专家列表，
     * 由于是已经审核完成的数据，所有专家都是已经存在数据的
     *
     * @param productId
     * @return
     */
    @Override
    public List<SpecInfo> productSpecListById(Integer productId) {
        return complexRelationMapper.productSpecListById(productId);
    }

    /**
     * 查询专家的评论、时间、签名信息
     *
     * @param productId
     * @param specId
     * @return
     */
    @Override
    public SpecInfo productSpecInfoById(Integer productId, Integer specId) {
        return complexRelationMapper.productSpecInfoById(productId, specId);
    }

    /**
     * 查询专家对项目的各评审项分数-
     *
     * @param productId
     * @param specId
     * @return
     */
    @Override
    @Deprecated
    public List<ReviewRecordDetail> productSpecScoreListById(Integer productId, Integer specId) {
        return complexRelationMapper.productSpecScoreListById(productId, specId);
    }

    /**
     * 查询项目评分规则
     * productSpecScoreListById 改成打总分，不在单个打分
     * 新加一个项目的打分规则列表
     *
     * @param productId
     * @return
     */
    @Override
    public List<ReviewRecordDetail> productScoreListById(Integer productId) {
        return complexRelationMapper.productScoreListById(productId);
    }

    /**
     * 查询项目被专家审核的情况
     *
     * @param productId
     * @return
     */
    @Override
    public List<ComplexProductReviewed> selectProductReviewed(Integer productId) {
        return complexRelationMapper.selectProductReviewed(productId);
    }

    /**
     * 获取组-用户-专家的数据报表模型
     *
     * @param groupId
     * @return
     */
    @Override
    public ExportGpsBean selectGPSExportInfo(Integer groupId) {
        //源数据
        List<ComplexGPSDo> gpsDos = complexRelationMapper.selectGPSExportInfo(groupId);
        if (gpsDos == null || gpsDos.size() == 0) {
            return null;
        }
        /*
        序号	答辩人	项目名称	人才类别	推荐地	(评委*)	得分	建议等次
        得分 全部评分后，才能有得分
        分数≥85分的为A类，属优先推荐项目；75≤分数＜85分为B类，属推荐项目；65≤分数＜75为C类，属一般推荐项目；65分以下为D类，属不推荐项目。
         1.填充数据
         2.计算得分和等次
        */
        //封装数据为Excel导出模型

        ExportGpsBean gpsBean = new ExportGpsBean();
        gpsBean.setGroupName(gpsDos.get(0).getGroupName());
        int diffId = -1;
        ExportGpsBean.Product product = null;
        for (ComplexGPSDo g : gpsDos) {
            if (diffId == g.getUserId().intValue()) {
                product.addSpec(g.getSpecId(), g.getSpecName(), g.getScore(), g.getSpecRemark());
            } else {
                if (product != null)
                    product.markScore();//非第一创建product,计算上一个product的总分
                product = gpsBean.addProduct(g);
                product.addSpec(g.getSpecId(), g.getSpecName(), g.getScore(), g.getSpecRemark());
                diffId = g.getUserId();
            }
        }
        product.markScore();//算最后一个
        return gpsBean;
    }


    /**
     * 查询评审的结果，统计格式
     *
     * @param batchId 批次ID
     * @param team    1：查询组团队 0：查询组个人
     * @return
     */
    @Override
    public List<ComplexReviewResultDo> selectReviewResultExportInfo(Integer batchId, Integer team) {
        return complexRelationMapper.selectReviewResultExportInfo(batchId, team);
    }

    /**
     * 查询评审的结果，按推荐地
     *
     * @param batchId 批次ID
     * @param team    1：查询组团队 0：查询组个人
     * @return
     */
    @Override
    public List<ComplexRecommendDo> selectRecommendExportInfo(Integer batchId, Integer team) {
        return complexRelationMapper.selectRecommendExportInfo(batchId, team);
    }
}

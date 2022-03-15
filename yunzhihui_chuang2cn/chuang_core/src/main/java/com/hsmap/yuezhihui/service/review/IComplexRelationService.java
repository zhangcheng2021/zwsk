package com.hsmap.yuezhihui.service.review;

import com.hsmap.yuezhihui.base.page.PageInfo;
import com.hsmap.yuezhihui.base.page.Pageable;
import com.hsmap.yuezhihui.entity.review.*;
import com.hsmap.yuezhihui.entity.spec.SpecInfo;

import java.util.List;

public interface IComplexRelationService {
    /**
     * 评审分组-专家信息的关联查询
     *
     * @param query
     * @return
     */
    List<ComplexRelationSpecDo> reviewSpecInfoByGroupId(ComplexRelationSpecDo query);

    /**
     * 评审分组-项目信息的关联查询
     *
     * @param query
     * @return
     */
    List<ComplexRelationProductDo> reviewProductInfoByGroupId(ComplexRelationProductDo query);

    /**
     * 历史评审-项目信息 关联查询
     *
     * @param query
     * @return
     */
    List<ComplexRelationProductDo> reviewHisProductInfo(ComplexRelationProductDo query);

    /**
     * 历史评审-项目信息 关联查询Count
     *
     * @param query
     * @return
     */
    int reviewHisProductInfoCount(ComplexRelationProductDo query);

    /**
     * 历史评审-项目信息 关联查询 ,分页查询
     *
     * @param query
     * @return
     */
    PageInfo<ComplexRelationProductDo> reviewHisProductInfoOfPage(ComplexRelationProductDo query, Pageable pageable);

    /**
     * 项目已经审核过的专家列表，
     * 由于是已经审核完成的数据，所有专家都是已经存在数据的
     *
     * @param productId
     * @return
     */
    List<SpecInfo> productSpecListById(Integer productId);

    /**
     * 查询专家的评论、时间、签名信息
     *
     * @param productId
     * @param specId
     * @return
     */
    SpecInfo productSpecInfoById(Integer productId, Integer specId);

    /**
     * 查询专家对项目的各评审项分数-
     *
     * @param productId
     * @param specId
     * @return
     */
    List<ReviewRecordDetail> productSpecScoreListById(Integer productId, Integer specId);

    /**
     * 查询项目评分规则
     * productSpecScoreListById 改成打总分，不在单个打分
     * 新加一个项目的打分规则列表
     *
     * @param productId
     * @return
     */
    List<ReviewRecordDetail> productScoreListById(Integer productId);

    /**
     * 查询项目被专家审核的情况
     *
     * @param productId
     * @return
     */
    List<ComplexProductReviewed> selectProductReviewed(Integer productId);

    /**
     * 获取组-用户-专家的数据报表模型
     *
     * @param groupId
     * @return
     */
    ExportGpsBean selectGPSExportInfo(Integer groupId);

    /**
     * 查询评审的结果，统计格式
     *
     * @param batchId 批次ID
     * @param team    1：查询组团队 0：查询组个人
     * @return
     */
    List<ComplexReviewResultDo> selectReviewResultExportInfo(Integer batchId, Integer team);

    /**
     * 查询评审的结果，按推荐地
     *
     * @param batchId 批次ID
     * @param team    1：查询组团队 0：查询组个人
     * @return
     */
    List<ComplexRecommendDo> selectRecommendExportInfo(Integer batchId, Integer team);
}

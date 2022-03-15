package com.hsmap.yuezhihui.service.review;

import com.hsmap.yuezhihui.base.page.PageInfo;
import com.hsmap.yuezhihui.base.page.Pageable;
import com.hsmap.yuezhihui.base.service.IBaseService;
import com.hsmap.yuezhihui.entity.review.ReviewWeight;
import com.hsmap.yuezhihui.entity.review.ReviewWeightExample;

import java.util.List;

/**
 * 评审权重service接口
 */
public interface IReviewWeightService extends IBaseService<ReviewWeight,Integer, ReviewWeightExample> {

    PageInfo<ReviewWeight> pageList(ReviewWeight model, Pageable pageable);

    List<ReviewWeight> listByModel(ReviewWeight model);


    List<ReviewWeight> findByAll();

//    private String projectTypeId;
//
//    private String specTypeId;
    List<ReviewWeight> findBySpecTypeId(int specTypeId);

    List<ReviewWeight> findByProjectTypeId(int projectTypeId);


    void pushAllToRedis();

    ReviewWeight getRedisBySpecAndProject(int specTypeId,int projectTypeId);

    int getRedisWeightBySpecAndProject(int specTypeId,int projectTypeId);


}

package com.hsmap.yuezhihui.service.review;

import com.hsmap.yuezhihui.base.page.PageInfo;
import com.hsmap.yuezhihui.base.page.Pageable;
import com.hsmap.yuezhihui.base.service.IBaseService;
import com.hsmap.yuezhihui.entity.review.ReviewGroup;
import com.hsmap.yuezhihui.entity.review.ReviewGroupExample;

import java.util.List;

/**
 * 评审分组service接口
 */
public interface IReviewGroupService extends IBaseService<ReviewGroup,Integer, ReviewGroupExample> {

    PageInfo<ReviewGroup> pageList(ReviewGroup model, Pageable pageable);

    List<ReviewGroup> listByModel(ReviewGroup model);

    List<ReviewGroup> findByAll();

    List<ReviewGroup> findByBatchId(int batchId);

    void addGroup(List<ReviewGroup> groupList);

    int countProductByBatchId(int batchId, String territoryIds);
}

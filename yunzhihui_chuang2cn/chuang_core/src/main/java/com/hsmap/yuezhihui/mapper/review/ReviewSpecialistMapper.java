package com.hsmap.yuezhihui.mapper.review;

import com.hsmap.yuezhihui.base.mapper.BaseMapper;
import com.hsmap.yuezhihui.entity.review.ReviewSpecialist;
import com.hsmap.yuezhihui.entity.review.ReviewSpecialistExample;
import com.hsmap.yuezhihui.entity.review.ReviewSpecialist;
import com.hsmap.yuezhihui.entity.review.ReviewSpecialistExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewSpecialistMapper extends BaseMapper<ReviewSpecialist, Integer, ReviewSpecialistExample> {
    /**
     * 级联查询所有专家
     * @return
     */
    List<ReviewSpecialist> selectAll();
}

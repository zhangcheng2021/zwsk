package com.hsmap.yuezhihui.mapper.spec;

import com.hsmap.yuezhihui.entity.spec.SpecSign;
import com.hsmap.yuezhihui.entity.spec.SpecSignExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SpecSignMapper {
    long countByExample(SpecSignExample example);

    int deleteByExample(SpecSignExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SpecSign record);

    int insertSelective(SpecSign record);

    List<SpecSign> selectByExample(SpecSignExample example);

    SpecSign selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SpecSign record, @Param("example") SpecSignExample example);

    int updateByExample(@Param("record") SpecSign record, @Param("example") SpecSignExample example);

    int updateByPrimaryKeySelective(SpecSign record);

    int updateByPrimaryKey(SpecSign record);
}
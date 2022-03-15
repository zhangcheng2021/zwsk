package com.hsmap.yuezhihui.base.mapper;

import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.List;

/***
 * 公用mapper接口
 * @param <T>
 * @param <ID>
 */
public interface BaseMapper<T,ID extends Serializable,Example> {

//    int add(T model);
//
//
//    int adds(List<T> modelList);
//
//    int update(T model);
//
//
//    int updates(List<T> modelList);
//
//
//    T queryById(ID id);
//
//
//    T queryModelByModel(@Param("model") T model);
//
//
//    List<T> queryList(@Param("model") T model);
//
//
//    List<T> queryListForPageModel(@Param("model") T model, @Param("page") Pageable pb);
//
//
//    int queryCount(@Param("model") T model);

    long countByExample(Example example);

    int deleteByExample(Example example);

    int deleteByPrimaryKey(ID id);

    int insert(T record);

    int insertSelective(T record);

    List<T> selectByExample(Example example);

    T selectByPrimaryKey(ID id);

    int updateByExampleSelective(@Param("record") T record, @Param("example") Example example);

    int updateByExample(@Param("record") T record, @Param("example") Example example);

    int updateByPrimaryKeySelective(T record);

    int updateByPrimaryKey(T record);
}

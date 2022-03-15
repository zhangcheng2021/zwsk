package com.hsmap.yuezhihui.base.service;

import com.alibaba.fastjson.JSON;
import com.hsmap.yuezhihui.common.CommonUtil;
import com.hsmap.yuezhihui.utils.redis.RedisUtil;
import com.hsmap.yuezhihui.Constants;
import com.hsmap.yuezhihui.base.page.PageInfo;
import com.hsmap.yuezhihui.base.page.Pageable;
import com.hsmap.yuezhihui.base.mapper.BaseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public abstract class BaseServiceImpl<T, ID extends Serializable,Example> implements IBaseService<T, ID,Example> {

    public final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    public abstract BaseMapper<T, ID,Example> getMapper();
    public final String _REDIS_KEY_PREFIX ="_RT_AIYI_";

    public Integer _PAGE_COUNT = 10;

    @Autowired
    private RedisUtil redisUtil;

    /***
     * 保存对象
     * @param model
     * @return
     */
    public int save(T model){
        LOGGER.info("services save start");
        try {
            return getMapper().insertSelective(model);
        } catch (Exception ex) {
            ex.printStackTrace();
            return Constants._EXCEPTION_ERROR;
        }
    }

    /***
     * 按Id删除对象
     * @param id
     */
    public int deleteById(ID id){
        LOGGER.info("services deleteById start");
        try {
            return getMapper().deleteByPrimaryKey(id);
        }catch(Exception ex){
            ex.printStackTrace();
            return Constants._EXCEPTION_ERROR;
        }
    }

    /***
     * 按Id更新对象
     * @param model
     */
    public int update(T model){
        LOGGER.info("services update start");
        try {
            return getMapper().updateByPrimaryKeySelective(model);
        }catch(Exception ex){
            ex.printStackTrace();
            return Constants._EXCEPTION_ERROR;
        }

    }

    /**
     * 按Id查看对象
     * @param id
     * @return
     */
    public T findById(ID id){

        LOGGER.info("services findById start");
        try {
            return getMapper().selectByPrimaryKey(id);
        }catch(Exception ex){
            ex.printStackTrace();
            return null;
        }

    }

    /***
     * 按条件查看List
     * @param example
     * @return
     */
    public List<T> find(Example example) {
        LOGGER.info("services find start");
        try {
            return getMapper().selectByExample(example);
        }catch(Exception ex){
            ex.printStackTrace();
            return null;
        }

    }


    /**
     * 按条件查看条数
     * @param example
     * @return
     */
    public long count(Example example){
        LOGGER.info("services count start");
        try {
            return getMapper().countByExample(example);
        }catch(Exception ex){
            ex.printStackTrace();
            return Constants._EXCEPTION_ERROR;
        }
    }

    @Override
    public PageInfo<T> findByPage(Example example, Pageable pageable) {
        long totalElements =  count(example);
        List<T> list  = find(example);
        PageInfo<T> page = new PageInfo<T>(pageable,totalElements,list);
        return page;
    }

    /***
     * 保存到redis中
     * @param key
     * @param object
     * @return
     */
    public boolean setRedis(String key,Object object){
        try {
//            String _key = model.getClass().getValue()+"_"+id;
            key = _REDIS_KEY_PREFIX +key;
            boolean b= redisUtil.set(key,object);
            return b;
        } catch (Exception ex) {
            LOGGER.error("redis入栈时setRedis发生错误："+ex.getMessage());
            ex.printStackTrace();
            return false;
        }
    }




    public boolean setRedis(String key,Object object,Long times){
        try {
            key = _REDIS_KEY_PREFIX+key;
            boolean b= redisUtil.set(key,object,times);
            return b;
        } catch (Exception ex) {
            LOGGER.error("redis入栈时setRedis发生错误："+ex.getMessage());
            ex.printStackTrace();
            return false;
        }

    }

    /***
     * 根据key从redis中获取值
     * @param key
     * @return
     */
    public Object getRedis(String key){
        try {
            key = _REDIS_KEY_PREFIX +key;
            LOGGER.info("redis_key:{}",key);
            Object object= redisUtil.get(key);

            return object;
        } catch (Exception ex) {
            LOGGER.error("redis出栈时getRedis发生错误，_PAY_KEY:"+key);
            ex.printStackTrace();
            return null;
        }
    }



    public boolean removeRedis(String  key){
        try {
            key = _REDIS_KEY_PREFIX+key;
            redisUtil.del(key);
            return true;
        } catch (Exception ex) {
            LOGGER.error("redis删除时removeRedis发生错误，_PAY_KEY:"+key);
            ex.printStackTrace();
            return false;
        }
    }

    public boolean removeAllRedis(String key){

//        delAll

//        Set<Object> keys = redisUtil.sGet(_PAY_KEY + "*");
        key = _REDIS_KEY_PREFIX+key;
        redisUtil.delAll(key);
        return true;
    }

    public void listToRedis(String key,List<T> list){
        String json = JSON.toJSONString(list);
        setRedis(key,json);

    }

    /***
     * 将对象转为json保存在redis中
     * @param key
     * @param object
     * @return
     */
    public boolean setJsonToRedis(String key,Object object){
        key = _REDIS_KEY_PREFIX +key;
        String json = JSON.toJSONString(object);
        boolean b = redisUtil.set(key,json);
        return b;
    }


    /***
     * 将对象转为json保存在redis中
     * @param key
     * @param object
     * @param times
     * @return
     */
    public boolean setJsonToRedis(String key,Object object,Long times){
        key = _REDIS_KEY_PREFIX +key;
        String json = JSON.toJSONString(object);
        boolean b = redisUtil.set(key,json,times);
        return b;
    }
    /***
     * 从redis中获取json并转为对象
     * @param key
     * @param clazz
     * @return
     */
    public T getObjectByRedis(String key,Class clazz) {
        String json = (String)getRedis(key);
        LOGGER.info("json:-------------------------->"+json);
        if(CommonUtil.isEmpty(json)){
            return null;
        }
        return (T) JSON.parseObject(json,clazz);
    }


    @Override
    public List<T> getListToRedis(String key,Class clazz) {
        String json =(String) getRedis(key);
        if(CommonUtil.isEmpty(json)){
            return null;
        }

        List<T> list = JSON.parseArray(json,clazz);
        return list;
    }

    public Date getStartTime(String startDate){
        Date startTime = CommonUtil.parseDate(startDate + " 00:00:00", Constants._DATE_TIME_FORMAT);
        return startTime;
    }

    public  Date getEndTime(String endDate){
        Date endTime = CommonUtil.parseDate(endDate + " 23:59:59", Constants._DATE_TIME_FORMAT);
        return endTime;
    }


    //
//    public int add(T model) {
//        int i = 0;
//        try {
//            i = getMapper().add(model);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//        return i;
//    }
//
//    public int adds(List<T> modelList) {
//        int i = 0;
//        try {
//            i = getMapper().adds(modelList);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//        return i;
//    }
//
//    public int update(T model) {
//        int i = 0;
//        try {
//            i = getMapper().update(model);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//        return i;
//    }
//
//    public int updates(List<T> modelList) {
//        int i = 0;
//        try {
//            i = getMapper().updates(modelList);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//        return i;
//    }
//
//    public T queryById(ID id) {
//        return getMapper().queryById(id);
//    }
//
//    public T queryModelByModel(T model) {
//        return getMapper().queryModelByModel(model);
//    }
//
//    public List<T> queryList(T model) {
//        return getMapper().queryList(model);
//    }
//
//    public PageImpl<T> queryListForPageModel(T model, Pageable pb) {
//        // 查询所有数据count
//        int totalElements = getMapper().queryCount(model);
//        // 查询list分页数据
//        List<T> list = getMapper().queryListForPageModel(model, pb);
//        return new PageImpl(pb, totalElements, list);
//    }
//
//    public int queryCount(T model) {
//        return getMapper().queryCount(model);
//    }

    public T getFirstByList(List<T> list){
        if(list.isEmpty() || list.size()<1){
            return null;
        }
        return list.get(0);
    }
}

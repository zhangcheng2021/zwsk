package com.hsmap.yuezhihui.base.service;


import com.hsmap.yuezhihui.base.page.PageInfo;
import com.hsmap.yuezhihui.base.page.Pageable;

import java.io.Serializable;
import java.util.List;

/**
 * Service层公共接口
 *
 * @param <T>
 * @param <ID>
 */
public interface IBaseService<T, ID extends Serializable, Example> {

    /***
     * 保存对象
     * @param model
     * @return
     */
    public int save(T model);

    /***
     * 按Id删除对象
     * @param id
     */
    public int deleteById(ID id);

    /***
     * 按Id更新对象
     * @param model
     */
    public int update(T model);

    /**
     * 按Id查看对象
     * @param id
     * @return
     */
    public T findById(ID id);

    /***
     * 按条件查看List
     * @param example
     * @return
     */
    public List<T> find(Example example);


    /**
     * 按条件查看条数
     * @param example
     * @return
     */
    public long count(Example example);

    /***
     * 保存到redis中
     * @param key
     * @param object
     * @return
     */
    public boolean setRedis(String key, Object object);

    /***
     * 根据key从redis中获取值
     * @param key
     * @return
     */
    public Object getRedis(String key);

    /**
     * 根据key删除redis的值
     * @param key
     * @return
     */
    public boolean removeRedis(String key);


    public PageInfo<T> findByPage(Example example, Pageable pageable);

    /**
     * List转json存入redis
     * @param key
     * @param list
     */
    public void listToRedis(String key, List<T> list);

    /**
     * 将redis中的json转为list
     * @param key
     * @param clazz
     * @return
     */
    public List<T> getListToRedis(String key, Class clazz);

    /****
     * 将对象转为json保存在redis中
     * @param key
     * @param object
     * @return
     */
    public boolean setJsonToRedis(String key,Object object);

    /***
     * 从redis中获取json并转为对象
     * @param key
     * @param clazz
     * @return
     */
    public T getObjectByRedis(String key,Class clazz);

    /**
     * 获取List中第一个对象
     * @param list
     * @return
     */
    public T getFirstByList(List<T> list);



}

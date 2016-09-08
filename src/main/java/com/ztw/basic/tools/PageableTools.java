package com.ztw.basic.tools;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * Created by zsl-pc on 2016/9/6.
 */
public class PageableTools {

    /**
     * 获取基础分页对象
     * @param page 获取第几页
     * @param size 每页条数
     * @param orderType 排序方式
     * @param orderField 排序字段
     * @return
     */
    public static Pageable basicPage(Integer page, Integer size, String orderType, String orderField) {
        Sort sort = new Sort(Sort.Direction.fromString(orderType), orderField);
        page = (page==null || page<0)?0:page;
        size = (size==null || size<=0)?15:size;
        Pageable pageable = new PageRequest(page, size, sort);
        return pageable;
    }

    /**
     * 获取基础分页对象，每页条数默认15条
     *  - 默认以id降序排序
     * @param page 获取第几页
     * @return
     */
    public static Pageable basicPage(Integer page) {
        return basicPage(page, null, "desc", "id");
    }

    /**
     * 获取基础分页对象，每页条数默认15条
     * @param page 获取第几页
     * @param orderType 排序方式
     * @param orderField 排序字段
     * @return
     */
    public static Pageable basicPage(Integer page, String orderType, String orderField) {
        return basicPage(page, null, orderType, orderField);
    }

    /**
     * 获取基础分页对象，排序方式默认降序
     * @param page 获取第几页
     * @param size 每页条数
     * @param orderField 排序字段
     * @return
     */
    public static Pageable basicPage(Integer page, Integer size, String orderField) {
        return basicPage(page, size, "desc", orderField);
    }

    /**
     * 获取基础分页对象
     *  - 每页条数默认15条
     *  - 排序方式默认降序
     * @param page 获取第几页
     * @param orderField 排序字段
     * @return
     */
    public static Pageable basicPage(Integer page, String orderField) {
        return basicPage(page, null, "desc", orderField);
    }

    /**
     * 获取基础分页对象
     *  - 排序方式默认为降序
     *  - 排序字段默认为id
     * @param page 获取第几页
     * @param size 每页条数
     * @return
     */
    public static Pageable basicPage(Integer page, Integer size) {
        return basicPage(page, size, "desc", "id");
    }
}

package com.hsmap.yuezhihui.base.page;

/**
 * 分页码
 */
public class Pageable {

    private int pageNumber;
    private int pageSize;


    private int index;
    private int length;


    public Pageable(int pageNumber, int pageSize) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;


        this.index = (pageNumber - 1) * pageSize;
        this.length = pageSize;
    }


    public int getPageNumber() {
        return pageNumber;
    }


    public int getPageSize() {
        return pageSize;
    }


    public int getLength() {
        return length;
    }


    public int getIndex() {
        return index;
    }


    /**
     * 下一页
     * @return
     */
    public Pageable next() {
        return new Pageable(pageNumber + 1, pageSize);
    }


    /**
     * 上一页
     * @return
     */
    public Pageable previousOrFirst() {
        if (pageNumber == 1) {
            return this;
        }
        return new Pageable(pageNumber - 1, pageSize);
    }

    /**
     *  首页
     * @return
     */

    public Pageable first() {
        return new Pageable(1, pageSize);
    }
}

package com.hsmap.yuezhihui.base.page;

import java.util.List;

public class PageInfo<T> implements Page<T> {
//    @Expose
    private int number;


//    @Expose
    private int size;


//    @Expose
    private long totalElements;


//    @Expose
    private long totalPages;


//    @Expose
    private List<T> content;


    private transient Pageable pageable;


    public PageInfo(Pageable pageable, long totalElements, List<T> content) {
        number = pageable == null ? -1 : pageable.getPageNumber();
        size = pageable == null ? -1 : pageable.getPageSize();
        this.totalElements = totalElements;
        this.totalPages = pageable == null ? 1 : (long) Math.ceil(Math.ceil((double) totalElements / (double)size));
        this.content = content;
        this.pageable = pageable;
    }


    public int getNumber() {
        return number;
    }


    public int getSize() {
        return size;
    }


    @Override
    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    @Override
    public long getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(long totalPages) {
        this.totalPages = totalPages;
    }

    public List<T> getContent() {
        return content;
    }


    public boolean hasContent() {
        return content != null && content.size() > 0;
    }


    public Pageable nextPageable() {
        return pageable.next();
    }


    public Pageable previousPageable() {
        return pageable.previousOrFirst();
    }


    public boolean isLastPageNumber() {
        return pageable.getPageNumber() == totalElements / pageable.getPageSize() + 1;
    }
}

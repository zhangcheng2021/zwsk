package com.hsmap.yuezhihui.excel;

import com.alibaba.excel.metadata.BaseRowModel;

import java.util.List;
import java.util.Map;

public class ExcelHelper {
    String fileName;
    List<? extends BaseRowModel> list;
    Class<? extends BaseRowModel> clazz;
    Map<Integer,Integer> columnWidth;
    boolean autoWidth;

    public boolean isAutoWidth() {
        return autoWidth;
    }

    public void setAutoWidth(boolean autoWidth) {
        this.autoWidth = autoWidth;
    }
    //    sheet1.setAutoWidth(Boolean.TRUE);


    public ExcelHelper(String fileName,List<? extends BaseRowModel> list,Class<? extends BaseRowModel> clazz, Map<Integer,Integer> columnWidth,boolean autoWidth){
        this.fileName = fileName;
        this.list = list;
        this.clazz = clazz;
        this.columnWidth = columnWidth;
        this.autoWidth = autoWidth;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public List<? extends BaseRowModel> getList() {
        return list;
    }

    public void setList(List<? extends BaseRowModel> list) {
        this.list = list;
    }

    public Class<? extends BaseRowModel> getClazz() {
        return clazz;
    }

    public void setClazz(Class<? extends BaseRowModel> clazz) {
        this.clazz = clazz;
    }

    public Map<Integer, Integer> getColumnWidth() {
        return columnWidth;
    }

    public void setColumnWidth(Map<Integer, Integer> columnWidth) {
        this.columnWidth = columnWidth;
    }


}

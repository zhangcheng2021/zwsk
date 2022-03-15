package com.hsmap.yuezhihui.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.BaseRowModel;

import java.util.ArrayList;
import java.util.List;

/**
 * excel析监听器，
 * 每解析一行会回调invoke()方法。
 * 整个excel解析结束会执行doAfterAllAnalysed()方法
 */
public class ExcelListener<T extends BaseRowModel> extends AnalysisEventListener<T> {

    private final List<T> rows = new ArrayList<>();

//    @Override
//    public void invoke(T o, AnalysisContext analysisContext) {
//        rows.add(o);
////        doSomething(o);//根据自己业务做处理
//
//    }

    private void doSomething(Object object) {
        //1、入库调用接口
    }


    @Override
    public void invoke(T t, AnalysisContext analysisContext) {
        rows.add(t);

    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }

    public List<T> getRows() {
        return rows;
    }
}

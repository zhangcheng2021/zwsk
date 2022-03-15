package com.hsmap.yuezhihui.common;

import org.apache.commons.lang.time.DateFormatUtils;

import java.util.Calendar;
import java.util.Date;

/**
 * 日期操作类
 */
public class DateUtils {


    /***
     * 添加天数
     * @param date
     * @param day
     * @return
     */
    public static Date addDate(Date date, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, day);
        Date newDate = calendar.getTime();
        return newDate;
    }


    public static Date now() {
        Date date = new Date();
        return date;
    }

    /***
     * 添加分钟数
     * @param date
     * @param minute
     * @return
     */
    public static Date addMinute(Date date, int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, minute);
        Date newDate = calendar.getTime();
        return newDate;
    }


    public static Date setHours(int hours) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, hours);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        Date d = c.getTime();
        return d;
    }

    public static int getHours() {
        Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        return hour;
    }

    public static Date setHours(Date date, int hour) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, hour);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        return c.getTime();

    }

    public static String computeDate(Date date, int day, String pattern) {
        Date newDate = addDate(date, day);
        return CommonUtil.getDateStringByPattern(newDate, pattern);
    }

    /**
     * 日期路径 即年/月/日 如2018/08/08
     */
    public static final String datePath() {
        Date now = new Date();
        return DateFormatUtils.format(now, "yyyy/MM/dd");
    }

    /**
     * 日期路径 即年/月/日 如20180808
     */
    public static final String dateTime() {
        Date now = new Date();
        return DateFormatUtils.format(now, "yyyyMMdd");
    }

    public static void main(String[] args) {
        //System.out.println(computeDate(new Date(),-1,"yyyy-MM-dd"));
//        Date date = new Date();
//        System.out.println("data:"+CommonUtil.getDateStringByPattern(date,"yyyy-MM-dd HH:mm:ss"));
//        Date d = addMinute(date,-2);
//
//        System.out.println("data:"+CommonUtil.getDateStringByPattern(d,"yyyy-MM-dd HH:mm:ss"));
//        long startDate = setHours(6).getTime();
//        long endDate = setHours(22).getTime();
//        long nowDate = setHours(20).getTime();
//        long nowDate = new Date().getTime();
//
//        if(startDate<=nowDate && nowDate<=endDate){
//            System.out.println("在营业时间 ");
//        }
        System.out.println(setHours(addDate(new Date(), -2), 10));


    }

}

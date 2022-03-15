package com.hsmap.yuezhihui;

import java.math.BigDecimal;

/***
 * BigDecimal计算
 */
public class BigDecimalUtils {
    /**
     * 加法
     * @param v1
     * @param v2
     * @return
     */
    public static BigDecimal add(BigDecimal v1,BigDecimal v2) {
        BigDecimal v = v1.add(v2).setScale(2, BigDecimal.ROUND_HALF_UP);
        return v;
    }

    /**
     * 加法
     * @param v1
     * @param v2
     * @return
     */
    public static BigDecimal add(long v1,BigDecimal v2) {
        BigDecimal _v1 = new BigDecimal(v1);
        BigDecimal v = add(_v1,v2);
        return v;
    }

    /**
     * 加法
     * @param v1
     * @param v2
     * @return
     */
    public static BigDecimal add(long v1,long v2) {
        BigDecimal _v1 = new BigDecimal(v1);
        BigDecimal _v2 = new BigDecimal(v2);
        BigDecimal v = add(_v1,_v2);
        return v;
    }

    /**
     * 加法
     * @param v1
     * @param v2
     * @return
     */
    public static BigDecimal add(BigDecimal v1,long v2) {
        BigDecimal _v2 = new BigDecimal(v2);
        BigDecimal v = add(v1,_v2);
        return v;
    }

    /**
     * 减法
     * @param v1
     * @param v2
     * @return
     */
    public static BigDecimal subtract(BigDecimal v1,BigDecimal v2){
        BigDecimal v = v1.subtract(v2).setScale(2, BigDecimal.ROUND_HALF_UP);
        return v;
    }
    /**
     * 减法
     * @param v1
     * @param v2
     * @return
     */
    public static BigDecimal subtract(long v1,BigDecimal v2){
        BigDecimal _v1 = new BigDecimal(v1);
        BigDecimal v = subtract(_v1,v2);
        return v;
    }


    /**
     * 减法
     * @param v1
     * @param v2
     * @return
     */
    public static BigDecimal subtract(BigDecimal v1,long v2){
        BigDecimal _v2= new BigDecimal(v2);
        BigDecimal v = subtract(v1,_v2);
        return v;
    }

    /**
     * 减法
     * @param v1
     * @param v2
     * @return
     */
    public static BigDecimal subtract(long v1,long v2){
        BigDecimal _v1= new BigDecimal(v1);
        BigDecimal _v2= new BigDecimal(v2);
        BigDecimal v = subtract(_v1,_v2);
        return v;
    }


    /**
     * 乘法
     * @param v1
     * @param v2
     * @return
     */
    public static BigDecimal multiply(BigDecimal v1,BigDecimal v2) {
        BigDecimal v = v1.multiply(v2).setScale(2, BigDecimal.ROUND_HALF_UP);
        return v;
    }
    /**
     * 乘法
     * @param v1
     * @param v2
     * @return
     */
    public static BigDecimal multiply(long v1,BigDecimal v2) {
        BigDecimal _v1 = new BigDecimal(v1);
        BigDecimal v = multiply(_v1,v2);
        return v;
    }
    /**
     * 乘法
     * @param v1
     * @param v2
     * @return
     */
    public static BigDecimal multiply(BigDecimal v1,long v2) {
        BigDecimal _v2 = new BigDecimal(v2);
        //BigDecimal v = v1.multiply(_v2).setScale(2, BigDecimal.ROUND_HALF_UP);
        BigDecimal v = multiply(v1,_v2);
        return v;
    }

    /**
     * 乘法
     * @param v1
     * @param v2
     * @return
     */
    public static BigDecimal multiply(long v1,long v2) {
        BigDecimal _v1 = new BigDecimal(v1);
        BigDecimal _v2 = new BigDecimal(v2);
        //BigDecimal v = v1.multiply(_v2).setScale(2, BigDecimal.ROUND_HALF_UP);
        BigDecimal v = multiply(_v1,_v2);
        return v;
    }


    /**
     * 除法
     * @param v1
     * @param v2
     * @return
     */
    public static BigDecimal divide(BigDecimal v1,BigDecimal v2){
        BigDecimal v = v1.divide(v2).setScale(2, BigDecimal.ROUND_HALF_UP);
        return v;
    }
    /**
     * 除法
     * @param v1
     * @param v2
     * @return
     */
    public static BigDecimal divide(long v1,BigDecimal v2){
        BigDecimal _v1 = new BigDecimal(v1);
        BigDecimal v = divide(_v1,v2);
        return v;
    }
    /**
     * 除法
     * @param v1
     * @param v2
     * @return
     */
    public static BigDecimal divide(BigDecimal v1,long v2){
        BigDecimal _v2 = new BigDecimal(v2);
        BigDecimal v = divide(v1,_v2);
        return v;
    }
    /**
     * 除法
     * @param v1
     * @param v2
     * @return
     */
    public static BigDecimal divide(long v1,long v2){
        BigDecimal _v2 = new BigDecimal(v2);
        BigDecimal _v1 = new BigDecimal(v1);
        BigDecimal v = divide(v1,v2);
        return v;
    }

    /**
     * 取反
     * @param v1
     * @return
     */
    public static BigDecimal negate(BigDecimal v1) {
        return v1.negate().setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 取反
     * @param v1
     * @return
     */
    public static BigDecimal negate(long v1) {
        BigDecimal _v1 = new BigDecimal(v1);
        return negate(_v1);
    }


    /***
     * 比较，大为1 等于为0 小于为-1
     * @param v1
     * @param v2
     * @return
     */
    public static int compare(BigDecimal v1,BigDecimal v2) {
        v1 = v1.setScale(2, BigDecimal.ROUND_HALF_UP);
        v2 = v2.setScale(2, BigDecimal.ROUND_HALF_UP);
        return v1.compareTo(v2);


    }

    /***
     * 比较，大小为1 等于为0 小于为-1
     * @param v1
     * @param v2
     * @return
     */
    public static int compare(long v1,BigDecimal v2) {
        BigDecimal _v1 = new BigDecimal(v1).setScale(2, BigDecimal.ROUND_HALF_UP);
        v2 = v2.setScale(2, BigDecimal.ROUND_HALF_UP);
        return _v1.compareTo(v2);


    }

    /***
     * 比较，大小为1 等于为0 小于为-1
     * @param v1
     * @param v2
     * @return
     */
    public static int compare(BigDecimal v1,long v2) {
        v1 = v1.setScale(2, BigDecimal.ROUND_HALF_UP);
        BigDecimal _v2 = new BigDecimal(v2).setScale(2, BigDecimal.ROUND_HALF_UP);
        return v1.compareTo(_v2);
    }


    /**
     * long转BigDecimal
     * @param v1
     * @return
     */
    public static BigDecimal parse(long v1){
        BigDecimal _v1 = new BigDecimal(v1);
        return _v1;
    }

    /***
     * 比较，大小为1 等于为0 小于为-1
     * @param v1
     * @param v2
     * @return
     */
    public static int compare(long v1,long v2) {
        BigDecimal _v1 = new BigDecimal(v1).setScale(2, BigDecimal.ROUND_HALF_UP);
        BigDecimal _v2 = new BigDecimal(v2).setScale(2, BigDecimal.ROUND_HALF_UP);
        return _v1.compareTo(_v2);
    }

    /***
     * 格式化BigDecimal 为空显示0  不会空保留2位小数
     * @param value
     * @return
     */
    public static BigDecimal formatValue(BigDecimal value){
        if(value == null){
            return new BigDecimal(0).setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        return value.setScale(2, BigDecimal.ROUND_HALF_UP);

    }



    public static void main(String[] args) {
//        System.out.println(BigDecimalUtils.negate(new BigDecimal(-100.0001)));
//        BigDecimal v1 = new BigDecimal(1000.0110);
//        BigDecimal v2 = new BigDecimal(1000.0220);
//        BigDecimal status = BigDecimalUtils.negate(100);
        System.out.println(BigDecimalUtils.compare(12,0));
    }


}

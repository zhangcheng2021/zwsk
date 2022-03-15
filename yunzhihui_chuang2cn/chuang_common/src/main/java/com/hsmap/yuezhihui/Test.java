package com.hsmap.yuezhihui;

import java.math.BigDecimal;

public class Test {
    public static String[] chars = new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

    public static void main(String[] args) {
        Test  t = new Test();
        int s = t.getMaxSection(BigDecimalUtils.multiply(new BigDecimal(31275.29),new BigDecimal(0.15)));
        System.out.println(s);

        //BigDecimal  b = new BigDecimal(0);
//        Calendar c = Calendar.getInstance();
//        c.set(Calendar.HOUR_OF_DAY, 6);
//        c.set(Calendar.MINUTE, 0);
//        c.set(Calendar.SECOND, 0);
//        Date m6 = c.getTime();
//
//        System.out.println(m6);

//        System.out.println(Integer.parseInt("0010"));
//        BigDecimal rate = new BigDecimal(0.7);
//
//        BigDecimal amount = new BigDecimal(5000);
//
//        BigDecimal b  = new BigDecimal(0);
//        for(int i=0;i<=60;i++){
//            if(i%10==0){
//                rate = subtract(rate, new BigDecimal(0.1));
//
//
//            }
//            BigDecimal t = multiply(divide(multiply(amount,rate),1000),3);
//            b = add(b,t);
//            System.out.println("b:"+b+"----->t:"+t);
//        }
//
//        System.out.println("b:"+b);
//        for(int i=0;i<20;i++) {
//            BigDecimal rate = divide(new BigDecimal(RandomValue.getNum(3, 7)),10);
//          int s =    RandomValue.getNum(3, 7);
//            System.out.println(rate);
//        }

    }




    public int getMaxSection(BigDecimal b) {



        int index = 0;
        int[] array = priceSection();
        for (int big : array) {
            System.out.println("b:" + b + "<====>big:" + big + ",compare(b,big):" + BigDecimalUtils.compare(b, big));
            if (BigDecimalUtils.compare(b, big) == -1) {
                return array[index - 1];
            }
            index++;
        }
        return 0;
    }

    /***
     * 价格区间
     * @return
     */
    private int[] priceSection() {
        int[] iv = {10, 100, 200, 500, 800, 1000, 2000, 3000, 5000,  8000, 10000, 15000, 20000, 30000, 50000, 100000, 200000, 500000, 1000000000};
        return iv;

    }


}

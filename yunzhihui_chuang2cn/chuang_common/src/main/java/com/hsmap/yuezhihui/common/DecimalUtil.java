package com.hsmap.yuezhihui.common;

import java.text.DecimalFormat;

public final class DecimalUtil {
    private static DecimalFormat df00 = new DecimalFormat("0.00");

    public static String format00(Double d) {
        return format00(d, "");
    }

    public static String format00(Double d, String defaultV) {
        if (d == null)
            return "";
        return df00.format(d);
    }


    public static Double formatD00(Double d) {
        return formatD00(d, 0d);
    }

    public static Double formatD00(Double d, Double defaultV) {
        if (d == null)
            return defaultV;
        return Double.valueOf(df00.format(d));
    }
}

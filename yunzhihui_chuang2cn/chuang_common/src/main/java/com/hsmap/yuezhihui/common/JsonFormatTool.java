package com.hsmap.yuezhihui.common;

import com.hsmap.yuezhihui.pay.wallyt.RepaymentBean;
import com.hsmap.yuezhihui.pay.wallyt.WallytPay;

import java.util.List;

public class JsonFormatTool {

    public static void main(String[] args) {
        String str = "{\"request\":[\"{\\\"originalMsgId\\\":\\\"L_20210301020138_jGx122b20\\\",\\\"partnerId\\\":\\\"100530000128\\\",\\\"trxAmount\\\":500,\\\"trxStatus\\\":\\\"SUCCESS\\\",\\\"trxDate\\\":\\\"2021-04-01T13:34:47.000Z\\\",\\\"currency\\\":\\\"NGN\\\",\\\"authorizationCode\\\":\\\"IMZjLkDzloTA1aucQlTWqypSp5or8aT5XBLrZcAnbWn9INdgrxXlElzSNSY7ADlsKaoBWXSWrqs1/FijfkyREX8dIXcPozBsoMZ64yaWYutsCXMkfYR+r+CWnJlEOd1N6IhQ4qQoApZ9z8+eJf7D4EcH2qvUhLaoeRQBbGxEvBhq4aVWejyzJLVeLPuvxvJwcluQhttxIz2ql90Y8LY7jzuVpLXRmV53/7mIORSB8oa0mJSl+VT7z1d+wSvosUWjD8DYnJsgZFtjDdxp1BOkNTj+TV45a69QPLktZthgiZTjRtTPC7tVlVqV92WOMgApWk7aL8cu8KFftRz1ZdxJOA==\\\",\\\"repayChannel\\\":\\\"card\\\"}\"],\"signature\":[\"UMBrOF4pF6m7Fmfvrsn/kSc6N69+i4P5H6Dp6JH80gh9XRUWiOSoSJLQv0HVn4A6aaHZZY3lOK9ON+UYqhNq36Gx3B3lKFcnv+sQK6mBPo+VCyz/evgyKJiMFoihcky7nKzdWc1tAkBhoAkPG7ACPIhyO05meEGoXLFpVhsnU4K5JNMWOFNBk+GStuWjB9m8c3wyCy1U9WkZsyCtqm8RCTNN9CxzXJlY4/kCa2SM0b5KgXEHVX4m7DtxJoAcezfV9AYqiJKMUw2xOyZnpSMlLhGfDZIWExuFHLGrFk12027Yq3aKmhT3y1NIdHOx4JxpFP7ewBei3CMrw5I+MkUp7A==\"]}";
        WallytPay wallytPay = new WallytPay("","");
        List<RepaymentBean> list = wallytPay.repaymentStatus(str);
        RepaymentBean bean = list.get(0);
        System.out.println("code:"+bean.getAuthorizationCode());
        String c= bean.getAuthorizationCode();
        System.out.println("c:"+c);
    }

    public static String formatJson(String jsonStr)
    {
        if (null == jsonStr || "".equals(jsonStr))
        {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        char last = '\0';
        char current = '\0';
        int indent = 0;
        boolean isInQuotationMarks = false;
        for (int i = 0; i < jsonStr.length(); i++)
        {
            last = current;
            current = jsonStr.charAt(i);
            switch (current)
            {
                //遇到符号直接进行转义
                case '"':
                    if (last != '\\')
                    {
                        isInQuotationMarks = !isInQuotationMarks;
                    }
                    sb.append(current);
                    break;
                //遇到大括号或者中括号直接进行 换行并且缩进
                case '{':
                case '[':
                    sb.append(current);
                    if (!isInQuotationMarks)
                    {
                        sb.append('\n');
                        indent++;
                        addIndentBlank(sb, indent);
                    }
                    break;
                case '}':
                case ']':
                    if (!isInQuotationMarks)
                    {
                        sb.append('\n');
                        indent--;
                        addIndentBlank(sb, indent);
                    }
                    sb.append(current);
                    break;
                //遇到单独的逗号进行换行 ，记住在双引号里面的单引号是不进行换行的
                case ',':
                    sb.append(current);
                    if (last != '\\' && !isInQuotationMarks)
                    {
                        sb.append('\n');
                        addIndentBlank(sb, indent);
                    }
                    break;

                default:
                    sb.append(current);
            }
        }

        return sb.toString();
    }

    /**
     * 添加space 即英文状态下三个字符
     * @param sb
     * @param indent
     */
    private static void addIndentBlank(StringBuilder sb, int indent)
    {
        for (int i = 0; i < indent; i++)
        {
            sb.append('\t');
        }
    }
}

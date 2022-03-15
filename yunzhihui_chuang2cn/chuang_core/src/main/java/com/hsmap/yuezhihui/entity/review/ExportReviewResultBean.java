package com.hsmap.yuezhihui.entity.review;

import com.hsmap.yuezhihui.common.DecimalUtil;

import java.util.ArrayList;
import java.util.List;

public class ExportReviewResultBean {

    private String batchName;

    private Stat stat;
    private List<ComplexReviewResultDo> result = new ArrayList<>();

    private int countAll = 0, countA = 0, countB = 0, countC = 0, countNo = 0;

    public void addResult(ComplexReviewResultDo resultDo) {
        result.add(resultDo);
        countAll += resultDo.getCountAll();
        countA += resultDo.getCountA();
        countB += resultDo.getCountB();
        countC += resultDo.getCountC();
        countNo += resultDo.getCountNo();
    }

    public void markStat() {
        stat = new Stat();
        stat.setRowAll("" + countAll);
        int x = countA + countB + countC;
        if (x > 0)
            stat.setRowIn(x + "(" + DecimalUtil.format00(x * 100.0d / countAll) + "%)");
        else
            stat.setRowIn("0");
        if (countA > 0)
            stat.setRowA(countA + "(" + DecimalUtil.format00(countA * 100.0d / countAll) + "%)");
        else
            stat.setRowA("0");
        if (countB > 0)
            stat.setRowB(countB + "(" + DecimalUtil.format00(countB * 100.0d / countAll) + "%)");
        else
            stat.setRowB("0");
        if (countC > 0)
            stat.setRowC(countC + "(" + DecimalUtil.format00(countC * 100.0d / countAll) + "%)");
        else
            stat.setRowC("0");
        if (countNo > 0)
            stat.setRowNo(countNo + "(" + DecimalUtil.format00(countNo * 100.0d / countAll) + "%)");
        else
            stat.setRowNo("0");
    }

    public String getBatchName() {
        return batchName;
    }

    public void setBatchName(String batchName) {
        this.batchName = batchName;
    }

    public Stat getStat() {
        return stat;
    }

    public void setStat(Stat stat) {
        this.stat = stat;
    }

    public List<ComplexReviewResultDo> getResult() {
        return result;
    }

    public void setResult(List<ComplexReviewResultDo> result) {
        this.result = result;
    }

    /**
     * 合计
     */
    public class Stat {
        private String rowAll;
        private String rowIn;
        private String rowA;
        private String rowB;
        private String rowC;
        private String rowNo;

        public String getRowAll() {
            return rowAll;
        }

        public void setRowAll(String rowAll) {
            this.rowAll = rowAll;
        }

        public String getRowIn() {
            return rowIn;
        }

        public void setRowIn(String rowIn) {
            this.rowIn = rowIn;
        }

        public String getRowA() {
            return rowA;
        }

        public void setRowA(String rowA) {
            this.rowA = rowA;
        }

        public String getRowB() {
            return rowB;
        }

        public void setRowB(String rowB) {
            this.rowB = rowB;
        }

        public String getRowC() {
            return rowC;
        }

        public void setRowC(String rowC) {
            this.rowC = rowC;
        }

        public String getRowNo() {
            return rowNo;
        }

        public void setRowNo(String rowNo) {
            this.rowNo = rowNo;
        }
    }
}

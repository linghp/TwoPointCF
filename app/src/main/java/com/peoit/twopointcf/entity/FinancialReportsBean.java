package com.peoit.twopointcf.entity;

/**
 * Created by ling on 2015/9/1.
 * description:
 */
public class FinancialReportsBean {
    public static final int ITEM = 0;
    public static final int SECTION = 1;
    private  int type;
    private  String time;
    private String expenditure;
    private String earning;
    private String remarks;

    public FinancialReportsBean(int type, String time, String expenditure, String earning, String remarks) {
        this.type = type;
        this.time = time;
        this.expenditure = expenditure;
        this.earning = earning;
        this.remarks = remarks;
    }

    public static int getITEM() {
        return ITEM;
    }

    public static int getSECTION() {
        return SECTION;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getExpenditure() {
        return expenditure;
    }

    public void setExpenditure(String expenditure) {
        this.expenditure = expenditure;
    }

    public String getEarning() {
        return earning;
    }

    public void setEarning(String earning) {
        this.earning = earning;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return "FinancialReportsBean{" +
                "type=" + type +
                ", time='" + time + '\'' +
                ", expenditure='" + expenditure + '\'' +
                ", earning='" + earning + '\'' +
                ", remarks='" + remarks + '\'' +
                '}';
    }
}

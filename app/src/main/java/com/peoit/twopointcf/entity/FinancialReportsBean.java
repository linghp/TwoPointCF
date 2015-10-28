package com.peoit.twopointcf.entity;

/**
 * Created by ling on 2015/9/1.
 * description:
 */
public class FinancialReportsBean {
    public static final int ITEM = 0;
    public static final int SECTION = 1;
    private  int type = 0;
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
        /**
         * id : null
         * projectId : null
         * date : null
         * remarks : 往往
         * income : 50000.0
         * expense : 20000.0
         * project : null
         */

        private String id;
        private String projectId;
        private String date;
        private String remarks;
        private String income;
        private String expense;
        private String project;

        public void setId(String id) {
            this.id = id;
        }

        public void setProjectId(String projectId) {
            this.projectId = projectId;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public void setIncome(String income) {
            this.income = income;
        }

        public void setExpense(String expense) {
            this.expense = expense;
        }

        public void setProject(String project) {
            this.project = project;
        }

        public String getId() {
            return id;
        }

        public String getProjectId() {
            return projectId;
        }

        public String getDate() {
            return date;
        }

        public String getRemarks() {
            return remarks;
        }

        public String getIncome() {
            return income;
        }

        public String getExpense() {
            return expense;
        }

        public String getProject() {
            return project;
        }

    @Override
    public String toString() {
        return "FinancialReportsBean{" +
                "id='" + id + '\'' +
                ", projectId='" + projectId + '\'' +
                ", date='" + date + '\'' +
                ", remarks='" + remarks + '\'' +
                ", income='" + income + '\'' +
                ", expense='" + expense + '\'' +
                ", project='" + project + '\'' +
                '}';
    }

    /*
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
    }*/

}

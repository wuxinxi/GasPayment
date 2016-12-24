package com.szxb.bean;

import java.util.List;

/**
 * 作者：Tangren_ on 2016/12/22 0022.
 * 邮箱：wu_tangren@163.com
 * TODO:用一句话概括
 */


public class Bill {

    /**
     * MCHID : 100100100007
     * ORDER_ID : 170a77a78c3943c3ae5d17099cb67c83
     * AMOUNT : 0.01
     * PAYWAY : scan
     * TRANTIME : 2016-12-20 16:53:02
     * TRANTYPE : 0
     * PAYSTS : 4
     * TRANDATE : 2016-12-20
     */

    private List<VarListBean> varList;

    public List<VarListBean> getVarList() {
        return varList;
    }

    public void setVarList(List<VarListBean> varList) {
        this.varList = varList;
    }

    public static class VarListBean {
        private String MCHID;
        private String ORDER_ID;
        private double AMOUNT;
        private String PAYWAY;
        private String TRANTIME;
        private String TRANTYPE;
        private String PAYSTS;
        private String TRANDATE;

        public String getMCHID() {
            return MCHID;
        }

        public void setMCHID(String MCHID) {
            this.MCHID = MCHID;
        }

        public String getORDER_ID() {
            return ORDER_ID;
        }

        public void setORDER_ID(String ORDER_ID) {
            this.ORDER_ID = ORDER_ID;
        }

        public double getAMOUNT() {
            return AMOUNT;
        }

        public void setAMOUNT(double AMOUNT) {
            this.AMOUNT = AMOUNT;
        }

        public String getPAYWAY() {
            return PAYWAY;
        }

        public void setPAYWAY(String PAYWAY) {
            this.PAYWAY = PAYWAY;
        }

        public String getTRANTIME() {
            return TRANTIME;
        }

        public void setTRANTIME(String TRANTIME) {
            this.TRANTIME = TRANTIME;
        }

        public String getTRANTYPE() {
            return TRANTYPE;
        }

        public void setTRANTYPE(String TRANTYPE) {
            this.TRANTYPE = TRANTYPE;
        }

        public String getPAYSTS() {
            return PAYSTS;
        }

        public void setPAYSTS(String PAYSTS) {
            this.PAYSTS = PAYSTS;
        }

        public String getTRANDATE() {
            return TRANDATE;
        }

        public void setTRANDATE(String TRANDATE) {
            this.TRANDATE = TRANDATE;
        }
    }
}

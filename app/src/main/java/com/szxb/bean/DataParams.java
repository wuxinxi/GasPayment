package com.szxb.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/11/4 0004.
 */
public class DataParams {

    /**
     * content : [{"danjia":"0.20","liushuino":"1415","xf_date":"2199-12-26 00:00:00.0","Jihao":"9","xf_time":"23:23:22","gonghao":"微信","qty":"4.00","youpin":"0#","banci":"201610240749"},{"danjia":"0.20","liushuino":"1415","xf_date":"2199-12-26 00:00:00.0","Jihao":"9","xf_time":"23:23:22","gonghao":"微信","qty":"4.00","youpin":"0#","banci":"201610240749"},{"danjia":"0.20","liushuino":"1216","xf_date":"2016-10-24 00:00:00.0","Jihao":"9","xf_time":"08:55:17","gonghao":"未支付","qty":"4.00","youpin":"95#","banci":"201610240749"}]
     * bancino : 201610240749
     * code : 100
     * youpin : 95#
     */

    private String bancino;
    private int code;
    private String youpin;
    /**
     * danjia : 0.20
     * liushuino : 1415
     * xf_date : 2199-12-26 00:00:00.0
     * Jihao : 9
     * xf_time : 23:23:22
     * gonghao : 微信
     * qty : 4.00
     * youpin : 0#
     * banci : 201610240749
     */

    private List<ContentBean> content;

    public String getBancino() {
        return bancino;
    }

    public void setBancino(String bancino) {
        this.bancino = bancino;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getYoupin() {
        return youpin;
    }

    public void setYoupin(String youpin) {
        this.youpin = youpin;
    }

    public List<ContentBean> getContent() {
        return content;
    }

    public void setContent(List<ContentBean> content) {
        this.content = content;
    }

    public static class ContentBean {
        private String danjia;
        private String liushuino;
        private String xf_date;
        private String Jihao;
        private String xf_time;
        private String gonghao;
        private String qty;
        private String youpin;
        private String banci;

        public String getDanjia() {
            return danjia;
        }

        public void setDanjia(String danjia) {
            this.danjia = danjia;
        }

        public String getLiushuino() {
            return liushuino;
        }

        public void setLiushuino(String liushuino) {
            this.liushuino = liushuino;
        }

        public String getXf_date() {
            return xf_date;
        }

        public void setXf_date(String xf_date) {
            this.xf_date = xf_date;
        }

        public String getJihao() {
            return Jihao;
        }

        public void setJihao(String Jihao) {
            this.Jihao = Jihao;
        }

        public String getXf_time() {
            return xf_time;
        }

        public void setXf_time(String xf_time) {
            this.xf_time = xf_time;
        }

        public String getGonghao() {
            return gonghao;
        }

        public void setGonghao(String gonghao) {
            this.gonghao = gonghao;
        }

        public String getQty() {
            return qty;
        }

        public void setQty(String qty) {
            this.qty = qty;
        }

        public String getYoupin() {
            return youpin;
        }

        public void setYoupin(String youpin) {
            this.youpin = youpin;
        }

        public String getBanci() {
            return banci;
        }

        public void setBanci(String banci) {
            this.banci = banci;
        }
    }

}

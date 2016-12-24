package com.szxb.bean;

/**
 * Created by Administrator on 2016/11/7 0007.
 */
public class YouHuiLv {

    /**
     * code : 100
     * youpin : 92#
     * youhuilv : 0
     */

    private int code;
    private String youpin;
    private String youhuilv;

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

    public String getYouhuilv() {
        return youhuilv;
    }

    public void setYouhuilv(String youhuilv) {
        this.youhuilv = youhuilv;
    }

    @Override
    public String toString() {
        return "YouHuiLv{" +
                "code=" + code +
                ", youpin='" + youpin + '\'' +
                ", youhuilv='" + youhuilv + '\'' +
                '}';
    }
}

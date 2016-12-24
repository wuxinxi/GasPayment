package com.szxb.module.swept.action;

/**
 * Created by Tangren on 2016/11/25 0025
 * Email:wu_tangren@163.com
 * TODO:用一句话概括
 */

public interface ISweptModel {
    /**
     * 主扫请求
     *
     * @param auth_code
     * @param listener
     */
    void loadSweptResult(String auth_code, SweptListener listener);

    interface SweptListener {
        void onSuccess(String msg);

        void onFail(int code, String msg);
    }

    /**
     * 主扫订单查询
     *
     * @param out_trade_no
     * @param listener
     */
    void loadOrderCheck(String out_trade_no, SweptOrderCheckListener listener);

    interface SweptOrderCheckListener {
        void onSuccess(String msg);

        void onFail(int code, String msg);
    }
}

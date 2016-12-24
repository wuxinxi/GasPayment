package com.szxb.module.refund.action;

/**
 * 作者：Tangren_ on 2016/12/23 0023.
 * 邮箱：wu_tangren@163.com
 * TODO:用一句话概括
 */


public interface IRefundModel {
    void fetchRefund(String out_trade_no, RefundListener listener);

    interface RefundListener {
        void onRefundSuccess(String msg);

        void onRefundFail(String msg);
    }
}

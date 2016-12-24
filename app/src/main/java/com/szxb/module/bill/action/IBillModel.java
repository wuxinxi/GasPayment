package com.szxb.module.bill.action;

import com.szxb.bean.Bill;

import java.util.List;

/**
 * 作者：Tangren_ on 2016/12/22 0022.
 * 邮箱：wu_tangren@163.com
 * TODO:用一句话概括
 */


public interface IBillModel {
    void loadBillList(String begainTime, String endTime, LoadListener listener);

    interface LoadListener {
        void onSuccessShow(List<Bill.VarListBean> varListBeen);

        void onFailShow(String msg);
    }
}

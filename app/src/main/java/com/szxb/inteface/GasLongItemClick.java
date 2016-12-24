package com.szxb.inteface;

import android.view.View;

import com.szxb.bean.Bill;

import java.util.List;

/**
 * 作者：Tangren_ on 2016/12/22 0022.
 * 邮箱：wu_tangren@163.com
 * TODO:用一句话概括
 */


public interface GasLongItemClick {
    void setItemLongClick(View view, int postion, List<Bill.VarListBean> varListBeen);
}

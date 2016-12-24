package com.szxb.viewholder;

import android.view.View;
import android.widget.TextView;

import com.szxb.R;
import com.szxb.bean.Bill;
import com.szxb.inteface.GasLongItemClick;

import java.util.List;

/**
 * 作者：Tangren_ on 2016/12/22 0022.
 * 邮箱：wu_tangren@163.com
 * TODO:
 */


public class BillGroupItemHolder extends BillNormalItemHolder {
    public TextView group_item_time;

    public BillGroupItemHolder(View itemView, GasLongItemClick click,List<Bill.VarListBean> varListBeen) {
        super(itemView,click,varListBeen);
        group_item_time = (TextView) itemView.findViewById(R.id.group_item_time);
    }
}

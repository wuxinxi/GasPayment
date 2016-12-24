package com.szxb.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.szxb.R;
import com.szxb.bean.Bill;
import com.szxb.inteface.GasLongItemClick;
import com.szxb.utils.DataUtils;
import com.szxb.utils.Utils;
import com.szxb.viewholder.BillGroupItemHolder;
import com.szxb.viewholder.BillNormalItemHolder;

import java.util.List;


/**
 * 作者：Tangren_ on 2016/12/22 0022.
 * 邮箱：wu_tangren@163.com
 * TODO:账单Adapter
 */


public class BillAdapter extends RecyclerView.Adapter {

    private int ITEM_NORMAL = 0;
    private int ITEM_GROUP = 1;

    private List<Bill.VarListBean> varListBeen;
    private GasLongItemClick click;

    public BillAdapter(List<Bill.VarListBean> varListBeen) {
        this.varListBeen = varListBeen;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_NORMAL) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bill_item_view_item, parent, false);
            BillNormalItemHolder holder = new BillNormalItemHolder(view, click,varListBeen);
            return holder;
        } else if (viewType == ITEM_GROUP) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bill_item_view_list, parent, false);
            BillGroupItemHolder holder = new BillGroupItemHolder(view, click,varListBeen);
            return holder;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Bill.VarListBean varListBeans = varListBeen.get(position);
        if (varListBeans == null)
            return;
        if (holder instanceof BillGroupItemHolder)
            bindGroupItemData(varListBeans, (BillGroupItemHolder) holder);
        else {
            BillNormalItemHolder normalItemHolder = (BillNormalItemHolder) holder;
            bindNormalItemData(varListBeans, normalItemHolder.imageType, normalItemHolder.out_trade_no,
                    normalItemHolder.amout, normalItemHolder.time, normalItemHolder.payType, normalItemHolder.paystatic);
        }
    }

    private void bindNormalItemData(Bill.VarListBean varListBeans, ImageView imageType, TextView out_trade_no,
                                    TextView amout, TextView time, TextView payType, TextView paystatic) {
        out_trade_no.setText(varListBeans.getORDER_ID());
        amout.setText(varListBeans.getAMOUNT() + "");
        time.setText(varListBeans.getTRANTIME());
        payType.setText(Utils.payType(varListBeans.getPAYWAY()));
        paystatic.setText(Utils.payState(varListBeans.getPAYSTS()));
        imageType.setImageResource(R.mipmap.pay_icon_weixin);
    }

    private void bindGroupItemData(Bill.VarListBean varListBeans, BillGroupItemHolder holder) {
        bindNormalItemData(varListBeans, holder.imageType, holder.out_trade_no, holder.amout,
                holder.time, holder.payType, holder.paystatic);
        holder.group_item_time.setText(DataUtils.checkDate(varListBeans.getTRANDATE()));
    }

    @Override
    public int getItemCount() {

        return varListBeen.size();
    }

    @Override
    public int getItemViewType(int position) {

        if (position == 0)
            return ITEM_GROUP;
        String currentDate = varListBeen.get(position).getTRANDATE();
        int index = position - 1;
        boolean isDifferent = !varListBeen.get(index).getTRANDATE().equals(currentDate);
        return isDifferent ? ITEM_GROUP : ITEM_NORMAL;
    }

    public void setLongClick(GasLongItemClick click) {
        this.click = click;
    }
}

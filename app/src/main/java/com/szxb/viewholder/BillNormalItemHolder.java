package com.szxb.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.szxb.R;
import com.szxb.bean.Bill;
import com.szxb.inteface.GasLongItemClick;

import java.util.List;

/**
 * 作者：Tangren_ on 2016/12/22 0022.
 * 邮箱：wu_tangren@163.com
 * TODO:用一句话概括
 */


public class BillNormalItemHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener {

    public ImageView imageType;
    public TextView out_trade_no;
    public TextView amout;
    public TextView time;
    public TextView payType;
    public TextView paystatic;

    private GasLongItemClick click;
    private List<Bill.VarListBean> varListBeen;

    public BillNormalItemHolder(View itemView, GasLongItemClick click, List<Bill.VarListBean> varListBeen) {
        super(itemView);
        this.click = click;
        this.varListBeen = varListBeen;
        imageType = (ImageView) itemView.findViewById(R.id.imageType);
        out_trade_no = (TextView) itemView.findViewById(R.id.out_trade_no);
        amout = (TextView) itemView.findViewById(R.id.amout);
        time = (TextView) itemView.findViewById(R.id.time);
        payType = (TextView) itemView.findViewById(R.id.payType);
        paystatic = (TextView) itemView.findViewById(R.id.paystatic);
        itemView.setOnLongClickListener(this);
    }

    @Override
    public boolean onLongClick(View v) {
        if (click != null) {
            click.setItemLongClick(v, getAdapterPosition(), varListBeen);
        }
        return true;
    }
}

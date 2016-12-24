package com.szxb.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.szxb.R;

/**
 * 作者：Tangren_ on 2016/12/22 0022.
 * 邮箱：wu_tangren@163.com
 * TODO:用一句话概括
 */


public class BillEmptyItemHolder extends RecyclerView.ViewHolder {
    public TextView tv_load_empty;

    public BillEmptyItemHolder(View itemView) {
        super(itemView);
        tv_load_empty = (TextView) itemView.findViewById(R.id.tv_load_empty);
    }
}

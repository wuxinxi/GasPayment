package com.szxb.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.szxb.R;
import com.szxb.bean.DataParams;
import com.szxb.utils.Utils;

import java.util.List;

/**
 * Created by Tangren on 2016/12/19 0019
 * Email:wu_tangren@163.com
 * TODO:用一句话概括
 */

public class GasDataAdapter extends BaseAdapter {

    private List<DataParams.ContentBean> datas;
    private Context context;
    private LayoutInflater inflater;

    public GasDataAdapter(Context context, List<DataParams.ContentBean> datas) {
        this.context = context;
        this.datas = datas;
        this.inflater = inflater.from(context);
    }

    @Override
    public int getCount() {
        return datas.size() == 0 ? 0 : datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.items_gas_data, null);
            holder.zongjia = (TextView) convertView.findViewById(R.id.zongjia);
            holder.youpin = (TextView) convertView.findViewById(R.id.youpin);
            holder.jiaoyino = (TextView) convertView.findViewById(R.id.jiaoyino);
            holder.jiaoyinum = (TextView) convertView.findViewById(R.id.jiaoyinum);
            holder.amount = (TextView) convertView.findViewById(R.id.amount);
            holder.paystatic = (TextView) convertView.findViewById(R.id.paystatic);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        DataParams.ContentBean params = datas.get(position);
        holder.jiaoyino.setText(params.getLiushuino());
        holder.youpin.setText(params.getYoupin());
        holder.jiaoyinum.setText(params.getQty());
        holder.amount.setText(params.getDanjia());
        holder.zongjia.setText(Utils.yingshou(params.getDanjia(), params.getQty()));
        holder.paystatic.setText(params.getGonghao());

        if (position == selectItem) {
            convertView.setBackgroundResource(R.drawable.determin_shape);
        } else {
            convertView.setBackgroundColor(Color.TRANSPARENT);
        }

        return convertView;
    }

    public void setSelectItem(int selectItem) {
        this.selectItem = selectItem;
    }

    private int selectItem = -1;

    class ViewHolder {
        public TextView zongjia;
        public TextView youpin;
        public TextView jiaoyino;
        public TextView jiaoyinum;
        public TextView amount;
        public TextView paystatic;
    }
}

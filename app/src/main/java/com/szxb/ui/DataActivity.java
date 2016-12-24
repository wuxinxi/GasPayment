package com.szxb.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.szxb.R;
import com.szxb.adapter.GasDataAdapter;
import com.szxb.base.BaseActivity;
import com.szxb.bean.DataParams;
import com.szxb.bean.YouHuiLv;
import com.szxb.module.data.presenter.IDatePresenter;
import com.szxb.module.data.view.IDataView;
import com.szxb.utils.Utils;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Tangren on 2016/12/20 0020
 * Email:wu_tangren@163.com
 * TODO:用一句话概括
 */

public class DataActivity extends BaseActivity<IDataView, IDatePresenter> implements IDataView,
        SwipeRefreshLayout.OnRefreshListener, AdapterView.OnItemClickListener {
    @InjectView(R.id.youqiangno)
    TextView youqiangno;
    @InjectView(R.id.banci)
    TextView banci;
    @InjectView(R.id.gasYoupin)
    TextView gasYoupin;
    @InjectView(R.id.recyclerView)
    ListView recyclerView;
    @InjectView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @InjectView(R.id.danjia)
    TextView danjia;
    @InjectView(R.id.youhui)
    TextView youhui;
    @InjectView(R.id.yingshou)
    TextView yingshou;
    @InjectView(R.id.shishou)
    TextView shishou;
    @InjectView(R.id.amount)
    TextView amount;
    @InjectView(R.id.ali_pay)
    ImageView aliPay;
    @InjectView(R.id.wechat_pay)
    ImageView wechatPay;
    @InjectView(R.id.bankcard_pay)
    ImageView bankcardPay;
    @InjectView(R.id.tv_load_empty)
    TextView tvLoadEmpty;

    private static final String KEY_NUM = "requestNum";
    private static final String KEY_JIAHO = "jihao";

    private String requestNum;
    private String jihao;
    private int postion;

    private GasDataAdapter mAdapter;
    private List<DataParams.ContentBean> list;

    private String shishou_money = null;
    private String yingshou_money = null;
    private String youhuilv_money = null;
    private String youhui_money = null;


    public static void start(Context context, String requestNum, String jihao) {
        Intent intent = new Intent(context, DataActivity.class);
        intent.putExtra(KEY_NUM, requestNum);
        intent.putExtra(KEY_JIAHO, jihao);
        context.startActivity(intent);
    }

    @Override
    protected IDatePresenter createPresenter() {
        return new IDatePresenter(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.data_main);
        ButterKnife.inject(this);
        requestNum = getIntent().getStringExtra(KEY_NUM);
        jihao = getIntent().getStringExtra(KEY_JIAHO);
        mPresneter.fetch(requestNum, jihao);
        initView();
    }

    private void initView() {
        youqiangno.setText(jihao);
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_green_light);
        swipeRefreshLayout.setOnRefreshListener(this);
        recyclerView.setOnItemClickListener(this);
    }

    @Override
    public void showDatasList(List<DataParams.ContentBean> contentBeans, String bancino, String youpin) {
        swipeRefreshLayout.setRefreshing(false);
        tvLoadEmpty.setVisibility(View.GONE);
        this.list = contentBeans;
        mAdapter = new GasDataAdapter(DataActivity.this, contentBeans);
        recyclerView.setAdapter(mAdapter);
        banci.setText(bancino);
        gasYoupin.setText(youpin);
    }

    @Override
    public void error(int code, String msg) {
        swipeRefreshLayout.setRefreshing(false);
        tvLoadEmpty.setVisibility(View.VISIBLE);
    }

    @Override
    public void showPrice(YouHuiLv youHuiLvs) {
        setControl(youHuiLvs);
    }

    /**
     * Called when a swipe gesture triggers a refresh.
     */
    @Override
    public void onRefresh() {
        initControl();
        mPresneter.fetch(requestNum, jihao);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        this.postion = position;
        mAdapter.setSelectItem(position);
        mAdapter.notifyDataSetInvalidated();
        mPresneter.getYouPin(list.get(position).getYoupin());
    }


    private void setControl(YouHuiLv youHuiLvs) {
        youhui_money = Utils.youhui(list.get(postion).getQty(), youHuiLvs.getYouhuilv());
        shishou_money = Utils.shishou(list.get(postion).getDanjia(), list.get(postion).getQty(), youHuiLvs.getYouhuilv());
        yingshou_money = Utils.yingshou(list.get(postion).getDanjia(), list.get(postion).getQty());
        youhuilv_money = youHuiLvs.getYouhuilv();
        danjia.setText(list.get(postion).getDanjia());
        youhui.setText(youhui_money);
        yingshou.setText(yingshou_money);
        shishou.setText(shishou_money);
        amount.setText(shishou_money);
    }

    //重置
    private void initControl() {
        danjia.setText("0.00");
        youhui.setText("0.00");
        yingshou.setText("0.00");
        shishou.setText("0.00");
        amount.setText("0.00");
    }

    @OnClick({R.id.ali_pay, R.id.wechat_pay, R.id.bankcard_pay})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ali_pay:
                SweptActivity.start(this, "A");
                break;
            case R.id.wechat_pay:

                break;
            case R.id.bankcard_pay:
                Toast.makeText(this, "银行卡支付", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        shishou_money = null;
        yingshou_money = null;
        youhuilv_money = null;
        youhui_money = null;
    }
}

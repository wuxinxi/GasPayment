package com.szxb.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.malinskiy.superrecyclerview.SuperRecyclerView;
import com.szxb.R;
import com.szxb.adapter.BillAdapter;
import com.szxb.base.BaseActivity;
import com.szxb.bean.Bill;
import com.szxb.inteface.GasLongItemClick;
import com.szxb.module.bill.presenter.BillPresenter;
import com.szxb.module.bill.view.IBillView;
import com.szxb.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 作者：Tangren_ on 2016/12/22 0022.
 * 邮箱：wu_tangren@163.com
 * TODO:账单
 */


public class BillActivity extends BaseActivity<IBillView, BillPresenter> implements IBillView,
        SwipeRefreshLayout.OnRefreshListener, GasLongItemClick {

    @InjectView(R.id.mtoolbar)
    Toolbar mtoolbar;
    @InjectView(R.id.recyclerView)
    SuperRecyclerView recyclerView;

    private BillAdapter mAdapter;

    private String indexTime = "2016-01-01";
    private String currentDate;
    private List<Bill.VarListBean> list = new ArrayList<Bill.VarListBean>();
    private static final int RESULT_CODE = 1;

    @Override
    protected BillPresenter createPresenter() {
        return new BillPresenter(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bill_main);
        ButterKnife.inject(this);
        initView();
        mPresneter.fetch(indexTime, Utils.getDate());
    }

    private void initView() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setRefreshingColorResources(android.R.color.holo_blue_light, android.R.color.holo_green_light,
                android.R.color.holo_green_light, android.R.color.holo_green_light);
        recyclerView.setRefreshListener(this);
        mtoolbar.setTitle("账单");
        setSupportActionBar(mtoolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        currentDate = Utils.getDate();
    }

    @Override
    public void onSuccess(List<Bill.VarListBean> varListBeen) {
        this.list = varListBeen;
        mAdapter = new BillAdapter(varListBeen);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLoadingMore(false);
        mAdapter.setLongClick(this);
    }

    @Override
    public void onFail(String msg) {
        recyclerView.setLoadingMore(false);
        recyclerView.setRefreshing(false);
        AlertDialog builder = new AlertDialog.Builder(this)
                .setTitle("刷新")
                .setMessage("网络错误或服务器异常,是否重试?")
                .setNegativeButton("否", null)
                .setPositiveButton("是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mPresneter.fetch(indexTime, currentDate);
                    }
                }).show();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finishActivityFromRight();
        else if (item.getItemId() == R.id.search) {
            Intent intent = new Intent(BillActivity.this, SearchActivity.class);
            startActivityForResult(intent, 0);
            overridePendingTransition(R.anim.base_slide_right_in, R.anim.base_slide_remain);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_bill, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onRefresh() {
        mPresneter.fetch(indexTime, currentDate);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            String beaginTime = data.getStringExtra("startTime");
            String endTime = data.getStringExtra("endTime");
            indexTime = beaginTime;
            currentDate = endTime;
            list.clear();
            mPresneter.fetch(indexTime, currentDate);
            mAdapter.notifyDataSetChanged();
        } else if (resultCode == RESULT_CODE) {
            list.clear();
            mPresneter.fetch(indexTime, currentDate);
            mAdapter.notifyDataSetChanged();
        }
    }


    @Override
    public void setItemLongClick(View view, int postion, List<Bill.VarListBean> varListBeen) {
        RefundActivity.startRefund(BillActivity.this, varListBeen.get(postion).getAMOUNT() + "",
                varListBeen.get(postion).getTRANTIME(), varListBeen.get(postion).getORDER_ID(),
                varListBeen.get(postion).getPAYWAY());
        overridePendingTransition(R.anim.base_slide_right_in, R.anim.base_slide_remain);
    }


}

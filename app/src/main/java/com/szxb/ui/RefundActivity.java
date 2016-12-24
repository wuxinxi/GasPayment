package com.szxb.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.szxb.R;
import com.szxb.base.BaseActivity;
import com.szxb.module.refund.presenter.RefundPresenter;
import com.szxb.module.refund.view.IRefundView;
import com.szxb.utils.Utils;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 作者：Tangren_ on 2016/12/23 0023.
 * 邮箱：wu_tangren@163.com
 * TODO:用一句话概括
 */


public class RefundActivity extends BaseActivity<IRefundView, RefundPresenter> implements IRefundView, AppBarLayout.OnOffsetChangedListener {
    @InjectView(R.id.amout)
    TextView amout;
    @InjectView(R.id.mToolbar)
    Toolbar mToolbar;
    @InjectView(R.id.coll)
    CollapsingToolbarLayout coll;
    @InjectView(R.id.appBar)
    AppBarLayout appBar;
    @InjectView(R.id.timeend)
    TextView timeend;
    @InjectView(R.id.liushuihao)
    TextView liushuihao;
    @InjectView(R.id.payType)
    TextView payType;
    @InjectView(R.id.confirmOrder)
    Button confirmOrder;

    private static final int RESULT_CODE = 1;

    @Override
    protected RefundPresenter createPresenter() {
        return new RefundPresenter(this);
    }

    private static final String KEY_AMOUNT = "amount";
    private static final String KEY_TIME = "time";
    private static final String KEY_NO = "no";
    private static final String KEY_TYPE = "type";

    public static void startRefund(BillActivity context, String amount, String time, String no, String type) {
        Intent intent = new Intent(context, RefundActivity.class);
        intent.putExtra(KEY_AMOUNT, amount);
        intent.putExtra(KEY_TIME, time);
        intent.putExtra(KEY_NO, no);
        intent.putExtra(KEY_TYPE, type);
        context.startActivityForResult(intent, RESULT_CODE);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.refun_main);
        ButterKnife.inject(this);
        amout.setText(getIntent().getStringExtra(KEY_AMOUNT));
        timeend.setText(getIntent().getStringExtra(KEY_TIME));
        liushuihao.setText(getIntent().getStringExtra(KEY_NO));
        payType.setText(Utils.payType(getIntent().getStringExtra(KEY_TYPE)));
        initView();
    }

    private void initView() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        coll.setTitleEnabled(false);
        appBar.addOnOffsetChangedListener(this);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finishActivityFromRight();


        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.confirmOrder)
    public void onClick() {
        mPresneter.fetchRefund(liushuihao.getText().toString());
    }


    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        if (verticalOffset == 0)
            mToolbar.setTitle("");
        else mToolbar.setTitle("撤单");
    }

    @Override
    public void onSuccess(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent();
        intent.putExtra("SUCCESS", "success");
        setResult(RESULT_CODE, intent);
        finish();
        overridePendingTransition(0, R.anim.base_slide_right_out);
    }

    @Override
    public void onFial(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}

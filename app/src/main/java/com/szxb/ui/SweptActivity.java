package com.szxb.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.szxb.R;
import com.szxb.base.BaseActivity;
import com.szxb.module.swept.presenter.ISweptPresenter;
import com.szxb.module.swept.view.ISweptResultView;
import com.uuzuche.lib_zxing.activity.CaptureFragment;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Tangren on 2016/12/20 0020
 * Email:wu_tangren@163.com
 * TODO:用一句话概括
 */

public class SweptActivity extends BaseActivity<ISweptResultView, ISweptPresenter> implements ISweptResultView {
    @InjectView(R.id.fl_my_container)
    FrameLayout flMyContainer;
    @InjectView(R.id.image)
    ImageView image;
    @InjectView(R.id.mtoolbar)
    Toolbar mtoolbar;
    @InjectView(R.id.flashLamp)
    ImageView flashLamp;
    @InjectView(R.id.jianyi)
    TextView jianyi;
    @InjectView(R.id.mainsweep)
    Button mainsweep;
    @InjectView(R.id.checkOrder)
    Button checkOrder;
    @InjectView(R.id.activity_second)
    FrameLayout activitySecond;

    private static String KEY_PAYTYPE="type";
    private CaptureFragment captureFragment;

    public static void start(Context context, String payType) {
        Intent intent = new Intent(context, SweptActivity.class);
        intent.putExtra(KEY_PAYTYPE, payType);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.swept_main);
        ButterKnife.inject(this);

        captureFragment = new CaptureFragment();
        CodeUtils.setFragmentArgs(captureFragment, R.layout.my_camera);
        captureFragment.setAnalyzeCallback(analyzeCallback);
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_my_container, captureFragment).commit();

        intView();
    }

    private void intView() {
        mtoolbar.setTitle("");
        setSupportActionBar(mtoolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    CodeUtils.AnalyzeCallback analyzeCallback = new CodeUtils.AnalyzeCallback() {
        @Override
        public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
            image.setVisibility(View.VISIBLE);
            image.setImageBitmap(mBitmap);
            mPresneter.fatch(result);
        }

        @Override
        public void onAnalyzeFailed() {
            Toast.makeText(SweptActivity.this, "扫码失败！", Toast.LENGTH_SHORT).show();
            finishActivityFromRight();
        }
    };

    @Override
    protected ISweptPresenter createPresenter() {
        return new ISweptPresenter(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finishActivityFromRight();
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick({R.id.flashLamp, R.id.mainsweep, R.id.checkOrder})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.flashLamp:
                break;
            case R.id.mainsweep:
                break;
            case R.id.checkOrder:
                break;
        }
    }

    @Override
    public void onSuccess(String msg) {

    }

    @Override
    public void onFail(int code, String msg) {

    }
}

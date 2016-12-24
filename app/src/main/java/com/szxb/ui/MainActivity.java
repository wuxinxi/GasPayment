package com.szxb.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import com.szxb.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.payment)
    LinearLayout payment;
    @InjectView(R.id.bill)
    LinearLayout bill;
    @InjectView(R.id.setting)
    LinearLayout setting;

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
    }

    @OnClick({R.id.payment, R.id.bill, R.id.setting})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.payment:
                startActivity(new Intent(MainActivity.this, QueryActivity.class));
                overridePendingTransition(R.anim.base_slide_right_in, R.anim.base_slide_remain);
                break;
            case R.id.bill:
                startActivity(new Intent(MainActivity.this, BillActivity.class));
                overridePendingTransition(R.anim.base_slide_right_in, R.anim.base_slide_remain);
                break;
            case R.id.setting:
                startActivity(new Intent(MainActivity.this, Setting.class));
                overridePendingTransition(R.anim.base_slide_right_in, R.anim.base_slide_remain);
                break;
        }
    }
}

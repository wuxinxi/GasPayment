package com.szxb.ui;

import android.content.DialogInterface;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.szxb.R;
import com.szxb.utils.Sputils;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 作者：Tangren_ on 2016/12/23 0023.
 * 邮箱：wu_tangren@163.com
 * TODO:用一句话概括
 */


public class Setting extends AppCompatActivity {
    @InjectView(R.id.mtoolbar)
    Toolbar mtoolbar;
    @InjectView(R.id.safe)
    TextView safe;
    @InjectView(R.id.mch_id)
    TextView mchId;
    @InjectView(R.id.config)
    TextView config;
    @InjectView(R.id.version)
    TextView version;
    @InjectView(R.id.checkUpdate)
    TextView checkUpdate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_main);
        ButterKnife.inject(this);
        initView();
    }

    private void initView() {
        mtoolbar.setTitle("设置");
        setSupportActionBar(mtoolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        overridePendingTransition(0, R.anim.base_slide_right_out);
        return super.onOptionsItemSelected(item);
    }

    @OnClick({R.id.safe, R.id.mch_id, R.id.config, R.id.version, R.id.checkUpdate})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.safe:
                break;
            case R.id.mch_id:
                showMchDialog();
                break;
            case R.id.config:
                break;
            case R.id.version:
                PackageManager manager = getPackageManager();
                int code = 1;
                try {
                    PackageInfo info = manager.getPackageInfo(getPackageName(), 0);//0代表获取版本信息
                    code = info.versionCode;
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                    code = 1;
                }

                Snackbar.make(checkUpdate, "版本号：" + code, Snackbar.LENGTH_SHORT).show();
                break;
            case R.id.checkUpdate:
                Snackbar.make(checkUpdate, "暂无更新", Snackbar.LENGTH_SHORT).show();
                break;
        }
    }

    private void showMchDialog() {
        final Sputils sputils = new Sputils();
        LayoutInflater inflate = LayoutInflater.from(this);
        final View view = inflate.inflate(R.layout.mch_dialog_view, null);
        final EditText mch_id = (EditText) view.findViewById(R.id.mch_id);
        AlertDialog builder = new AlertDialog.Builder(Setting.this)
                .setTitle("商户号")
                .setView(view)
                .setNegativeButton("取消", null)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sputils.put(Setting.this, "mch_id", mch_id.getText().toString());
                    }
                }).show();

    }
}

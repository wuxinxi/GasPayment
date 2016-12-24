package com.szxb.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.fourmob.datetimepicker.date.DatePickerDialog;
import com.szxb.R;
import com.szxb.utils.DataUtils;

import java.util.Calendar;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 作者：Tangren_ on 2016/12/22 0022.
 * 邮箱：wu_tangren@163.com
 * TODO:用一句话概括
 */


public class SearchActivity extends AppCompatActivity implements AppBarLayout.OnOffsetChangedListener,
        DatePickerDialog.OnDateSetListener {
    @InjectView(R.id.info)
    TextView info;
    @InjectView(R.id.mToolbar)
    Toolbar mToolbar;
    @InjectView(R.id.coll)
    CollapsingToolbarLayout coll;
    @InjectView(R.id.appBar)
    AppBarLayout appBar;
    @InjectView(R.id.spinerTime)
    AppCompatSpinner spinerTime;
    @InjectView(R.id.startTime)
    TextView startTime;
    @InjectView(R.id.endTime)
    TextView endTime;
    @InjectView(R.id.query)
    Button query;

    private Calendar calendar;
    private DatePickerDialog datePickerDialog;
    private String timeRanges = "今天";
    private static final String TAG = "SearchActivity";
    private boolean beaginTime = true;
    private String beagin_time;
    private String end_time;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_main);
        ButterKnife.inject(this);
        initView();
        spinnerData();
    }

    private void initView() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        coll.setTitleEnabled(false);
        appBar.addOnOffsetChangedListener(this);

        calendar = Calendar.getInstance();
        datePickerDialog = DatePickerDialog.newInstance(this, calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), true);
    }

    private void spinnerData() {
        final String items[] = getResources().getStringArray(R.array.timeRange);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinerTime.setAdapter(adapter);
        spinerTime.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                timeRanges = items[position];
                JSONObject json = new JSONObject();
                switch (timeRanges) {
                    case "今天":
                        json = DataUtils.getToday();
                        break;
                    case "昨天":
                        json = DataUtils.getYesterday();
                        break;
                    case "本周":
                        json = DataUtils.getTimerval();
                        break;
                    case "上周":
                        json = DataUtils.getLastTimeInterval();
                        break;
                    case "本月":
                        json = DataUtils.getMonth();
                        break;
                    case "上月":
                        json = DataUtils.getLastMonth();
                        break;
                    default:
                        break;
                }
                beagin_time = json.getString("beaginTime");
                end_time = json.getString("endTime");
                if (!timeRanges.equals("请选择时间范围")) {
                    startTime.setEnabled(false);
                    startTime.setBackgroundResource(R.drawable.edit_shape_1_1);
                    endTime.setEnabled(false);
                    endTime.setBackgroundResource(R.drawable.edit_shape_1_1);
                    startTime.setText("");
                    endTime.setText("");
                } else {
                    startTime.setEnabled(true);
                    startTime.setBackgroundResource(R.drawable.edit_shape_1);
                    endTime.setEnabled(true);
                    endTime.setBackgroundResource(R.drawable.edit_shape_1);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        overridePendingTransition(0, R.anim.base_slide_right_out);
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        if (verticalOffset == 0)
            mToolbar.setTitle("");
        else
            mToolbar.setTitle("查询");
    }

    @OnClick({R.id.startTime, R.id.endTime, R.id.query})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.startTime:
                beaginTime = true;
                datePickerDialog.setVibrate(true);
                datePickerDialog.setYearRange(1985, 2028);
                datePickerDialog.setCloseOnSingleTapDay(true);
                datePickerDialog.show(getSupportFragmentManager(), TAG);
                break;
            case R.id.endTime:
                beaginTime = false;
                datePickerDialog.setVibrate(true);
                datePickerDialog.setYearRange(1985, 2028);
                datePickerDialog.setCloseOnSingleTapDay(true);
                datePickerDialog.show(getSupportFragmentManager(), TAG);
                break;
            case R.id.query:
                Intent intent = new Intent();
                intent.putExtra("startTime", beagin_time);
                intent.putExtra("endTime", end_time);
                setResult(RESULT_OK, intent);
                finish();
                overridePendingTransition(0, R.anim.base_slide_right_out);
                break;
        }
    }

    @Override
    public void onDateSet(DatePickerDialog datePickerDialog, int year, int month, int day) {
//        Toast.makeText(SearchActivity.this, "new date:" + year + "-" + month + "-" + day, Toast.LENGTH_LONG).show();

        month=month+1;
        if (beaginTime) {
            startTime.setText(year + "-" + month + "-" + day);
            beagin_time = year + "-" + month + "-" + day;
        } else {
            endTime.setText(year + "-" + month + "-" + day);
            end_time = year + "-" + month + "-" + day;
        }

    }
}

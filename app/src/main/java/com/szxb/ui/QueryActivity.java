package com.szxb.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.szxb.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Tangren on 2016/12/20 0020
 * Email:wu_tangren@163.com
 * TODO:用一句话概括
 */

public class QueryActivity extends AppCompatActivity {

    @InjectView(R.id.qianghao)
    EditText qianghao;
    @InjectView(R.id.clear)
    Button clear;
    @InjectView(R.id.num_del)
    Button numDel;
    @InjectView(R.id.determain)
    Button determain;

    String tempString;
    private Button temp;
    private int[] buttons = new int[]{R.id.num_0, R.id.num_1, R.id.num_2,
            R.id.num_3, R.id.num_4, R.id.num_5,
            R.id.num_6, R.id.num_7, R.id.num_8,
            R.id.num_9};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.query_main);
        ButterKnife.inject(this);
        initView();
    }

    private void initView() {
        for (int i = 0; i < buttons.length; i++) {
            temp = (Button) findViewById(buttons[i]);
            temp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tempString = qianghao.getText().toString();
                    tempString = tempString + String.valueOf(((Button) v).getText());
                    qianghao.setText(tempString);
//                    qianghao.requestFocus();
                    qianghao.setSelection(qianghao.getText().length());
                }
            });
        }
    }


    @OnClick({R.id.clear, R.id.num_del, R.id.determain})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.clear:
                qianghao.setText("");
                break;
            case R.id.num_del:
                delNum();
                break;
            case R.id.determain:
                if (qianghao.getText().toString().isEmpty())
                    return;
                DataActivity.start(QueryActivity.this, "5", qianghao.getText().toString());
                overridePendingTransition(R.anim.base_slide_right_in, R.anim.base_slide_remain);
                break;
        }
    }

    private void delNum() {
        String result = qianghao.getText().toString();
        if (result.equals(""))
            return;
        result = result.substring(0, result.length() - 1);
        qianghao.setText(result);
        qianghao.setSelection(qianghao.getText().length());
    }


    @Override
    protected void onStop() {
        super.onPause();
        qianghao.setText("");
    }
}

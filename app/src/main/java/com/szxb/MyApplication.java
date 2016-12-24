package com.szxb;

import android.app.Application;

import com.szxb.utils.Config;
import com.szxb.utils.Sputils;
import com.yolanda.nohttp.Logger;
import com.yolanda.nohttp.NoHttp;

/**
 * Created by Tangren on 2016/12/20 0020
 * Email:wu_tangren@163.com
 * TODO:用一句话概括
 */

public class MyApplication extends Application {

    public static MyApplication instance;
    private Sputils sputils;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        NoHttp.initialize(this);
        Logger.setDebug(true);
        Logger.setTag("TangRen");
        sputils = new Sputils();
    }

    public synchronized static MyApplication getInstance() {
        if (instance == null)
            instance = new MyApplication();
        return instance;
    }

    public String mch_id() {
        return (String) sputils.get(this, "mch_id", Config.TEST_MCH);
    }
}

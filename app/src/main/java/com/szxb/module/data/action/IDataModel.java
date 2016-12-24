package com.szxb.module.data.action;

import com.szxb.bean.DataParams;
import com.szxb.bean.YouHuiLv;

import java.util.List;

/**
 * Created by Tangren on 2016/12/20 0020
 * Email:wu_tangren@163.com
 * TODO:用一句话概括
 */

public interface IDataModel {
    void loadDatas(String num, String jihao, DateLoadListener listener);

    interface DateLoadListener {
        void showDatasList(List<DataParams.ContentBean> contentBeans, String bancino, String youpin);

        void error(int code, String msg);
    }

    void loadYouPin(String youPin, YoupinLoadListener listener);

    interface YoupinLoadListener {
        void showYoupinInfo(YouHuiLv youHuiLvs);

        void error(int code, String msg);
    }
}

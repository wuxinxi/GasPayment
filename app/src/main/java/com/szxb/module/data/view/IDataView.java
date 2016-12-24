package com.szxb.module.data.view;

import com.szxb.base.BasePresenter;
import com.szxb.base.BaseView;
import com.szxb.bean.DataParams;
import com.szxb.bean.YouHuiLv;

import java.util.List;


/**
 * Created by Tangren on 2016/11/25 0025
 * Email:wu_tangren@163.com
 * TODO:用一句话概括
 */

public interface IDataView extends BaseView {
    void showDatasList(List<DataParams.ContentBean> contentBeans, String bancino, String youpin);

    void error(int code, String msg);

    void showPrice(YouHuiLv youHuiLvs);

    class Generator {
        public static IDataView getDefault() {
            return new IDataView() {
                @Override
                public void showDatasList(List<DataParams.ContentBean> contentBeans, String bancino, String youpin) {

                }

                @Override
                public void error(int code, String msg) {

                }

                @Override
                public void showPrice(YouHuiLv youHuiLvs) {

                }

                @Override
                public void showLoading() {

                }

                @Override
                public void dismissLoading() {

                }

                @Override
                public BasePresenter getPresenter() {
                    return null;
                }
            };
        }

    }
}

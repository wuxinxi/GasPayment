package com.szxb.module.swept.view;

import com.szxb.base.BasePresenter;
import com.szxb.base.BaseView;


/**
 * Created by Tangren on 2016/11/25 0025
 * Email:wu_tangren@163.com
 * TODO:用一句话概括
 */

public interface ISweptResultView extends BaseView {
    void onSuccess(String msg);

    void onFail(int code, String msg);

    class Generator {
        public ISweptResultView getDefault() {
            return new ISweptResultView() {
                @Override
                public void onSuccess(String msg) {

                }

                @Override
                public void onFail(int code, String msg) {

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

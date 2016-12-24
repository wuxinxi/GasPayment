package com.szxb.module.refund.view;

import com.szxb.base.BasePresenter;
import com.szxb.base.BaseView;

/**
 * 作者：Tangren_ on 2016/12/23 0023.
 * 邮箱：wu_tangren@163.com
 * TODO:用一句话概括
 */


public interface IRefundView extends BaseView {
    void onSuccess(String msg);

    void onFial(String msg);

    class Generator {
        private static IRefundView getDefault() {
            return new IRefundView() {
                @Override
                public void onSuccess(String msg) {

                }

                @Override
                public void onFial(String msg) {

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

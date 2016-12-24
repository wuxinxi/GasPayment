package com.szxb.module.swept.presenter;

import com.szxb.base.BasePresenter;
import com.szxb.module.swept.action.ISweptCompl;
import com.szxb.module.swept.action.ISweptModel;
import com.szxb.module.swept.view.ISweptResultView;

/**
 * Created by Tangren on 2016/11/25 0025
 * Email:wu_tangren@163.com
 * TODO:用一句话概括
 */

public class ISweptPresenter extends BasePresenter<ISweptResultView> {

    private ISweptResultView iSweptResultView;
    private ISweptModel iSweptModel = new ISweptCompl();

    public ISweptPresenter(ISweptResultView iSweptResultView) {
        this.iSweptResultView = iSweptResultView;
    }

    public void fatch(String auth_code) {
        if (iSweptModel != null) {
            iSweptResultView.showLoading();
            iSweptModel.loadSweptResult(auth_code, new ISweptModel.SweptListener() {
                @Override
                public void onSuccess(String msg) {
                    iSweptResultView.dismissLoading();
                    if (iSweptResultView != null) {
                        iSweptResultView.onSuccess(msg);
                    }
                }

                @Override
                public void onFail(int code, String msg) {
                    iSweptResultView.dismissLoading();
                    iSweptResultView.onFail(code, msg);
                }
            });
        }
    }

    public void orderCheck(String out_trade_no) {
        if (iSweptModel != null) {
            iSweptResultView.showLoading();
            iSweptModel.loadOrderCheck(out_trade_no, new ISweptModel.SweptOrderCheckListener() {
                @Override
                public void onSuccess(String msg) {
                    iSweptResultView.dismissLoading();
                    if (iSweptResultView != null) {
                        iSweptResultView.onSuccess(msg);
                    }
                }

                @Override
                public void onFail(int code, String msg) {
                    iSweptResultView.dismissLoading();
                    iSweptResultView.onFail(code, msg);
                }
            });
        }
    }

}

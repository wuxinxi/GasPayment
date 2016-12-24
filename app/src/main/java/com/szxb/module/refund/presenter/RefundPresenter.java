package com.szxb.module.refund.presenter;

import com.szxb.base.BasePresenter;
import com.szxb.module.refund.action.IRefundModel;
import com.szxb.module.refund.action.RefundCompl;
import com.szxb.module.refund.view.IRefundView;

/**
 * 作者：Tangren_ on 2016/12/23 0023.
 * 邮箱：wu_tangren@163.com
 * TODO:用一句话概括
 */


public class RefundPresenter extends BasePresenter<IRefundView> {
    private IRefundView view;
    private IRefundModel model = new RefundCompl();

    public RefundPresenter(IRefundView view) {
        this.view = view;
    }

    public void fetchRefund(String out_trade_no) {
        if (model != null) {
            view.showLoading();
            model.fetchRefund(out_trade_no, new IRefundModel.RefundListener() {
                @Override
                public void onRefundSuccess(String msg) {
                    if (view != null) {
                        view.dismissLoading();
                        view.onSuccess(msg);
                    }
                }

                @Override
                public void onRefundFail(String msg) {
                    if (view != null) {
                        view.dismissLoading();
                        view.onFial(msg);
                    }
                }
            });
        }
    }
}

package com.szxb.module.data.presenter;

import com.szxb.base.BasePresenter;
import com.szxb.bean.DataParams;
import com.szxb.bean.YouHuiLv;
import com.szxb.module.data.action.IDataCompl;
import com.szxb.module.data.action.IDataModel;
import com.szxb.module.data.view.IDataView;
import com.szxb.utils.Utils;

import java.util.List;

/**
 * Created by Tangren on 2016/12/20 0020
 * Email:wu_tangren@163.com
 * TODO:用一句话概括
 */

public class IDatePresenter extends BasePresenter<IDataView> {
    private IDataView iDataView;

    IDataModel iDataModel = new IDataCompl();

    public IDatePresenter(IDataView iDataView) {
        this.iDataView = iDataView;
    }

    /**
     * @param num
     * @param jihao
     */
    public void fetch(String num, String jihao) {
        if (iDataModel != null) {
            iDataView.showLoading();
            iDataModel.loadDatas(num, jihao, new IDataModel.DateLoadListener() {
                @Override
                public void showDatasList(List<DataParams.ContentBean> contentBeans, String bancino, String upin) {
                    if (iDataView != null) {
                        iDataView.dismissLoading();
                        iDataView.showDatasList(contentBeans, bancino, upin);
                    }
                }

                @Override
                public void error(int code, String msg) {
                    iDataView.dismissLoading();
                    iDataView.error(code, msg);
                }
            });
        }
    }

    /**
     * @param youPin
     */
    public void getYouPin(String youPin) {
        if (iDataModel != null) {
            iDataView.showLoading();
            iDataModel.loadYouPin(Utils.clearchar(youPin), new IDataModel.YoupinLoadListener() {
                @Override
                public void showYoupinInfo(YouHuiLv youHuiLvs) {
                    iDataView.dismissLoading();
                    iDataView.showPrice(youHuiLvs);
                }

                @Override
                public void error(int code, String msg) {
                    iDataView.dismissLoading();
                    iDataView.error(code, msg);
                }
            });
        }
    }

}

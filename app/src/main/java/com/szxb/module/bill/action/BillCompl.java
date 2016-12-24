package com.szxb.module.bill.action;

import com.google.gson.Gson;
import com.szxb.MyApplication;
import com.szxb.bean.Bill;
import com.szxb.httpclient.CallServer;
import com.szxb.httpclient.HttpListener;
import com.szxb.utils.Config;
import com.yolanda.nohttp.Logger;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.rest.CacheMode;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.Response;

import org.json.JSONObject;

/**
 * 作者：Tangren_ on 2016/12/22 0022.
 * 邮箱：wu_tangren@163.com
 * TODO:用一句话概括
 */


public class BillCompl implements IBillModel {

    @Override
    public void loadBillList(String begainTime, String endTime, LoadListener listener) {
        Request<JSONObject> request = NoHttp.createJsonObjectRequest(Config.BILL_URL, RequestMethod.POST);
        request.setCacheMode(CacheMode.REQUEST_NETWORK_FAILED_READ_CACHE);
        request.set("BEGINTIME", begainTime);
        request.set("ENDTIME", endTime);
        request.set("MCHID", MyApplication.getInstance().mch_id());
        Logger.d("mch_id=" + MyApplication.getInstance().mch_id());
        CallServer.getHttpclient().add(3, request, new LoadBillListener(listener));
    }

    private class LoadBillListener implements HttpListener<JSONObject> {

        private LoadListener listener;

        public LoadBillListener(LoadListener listener) {
            this.listener = listener;
        }

        @Override
        public void success(int what, Response<JSONObject> response) {
            Gson gson = new Gson();
            Bill bill = gson.fromJson(response.get().toString(), Bill.class);
            listener.onSuccessShow(bill.getVarList());
            Logger.d(response.get().toString());
        }

        @Override
        public void fail(int what, Response<JSONObject> response) {
            listener.onFailShow("网络或无服务器异常！");
        }
    }
}

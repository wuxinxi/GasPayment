package com.szxb.module.swept.action;

import com.szxb.MyApplication;
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
 * Created by Tangren on 2016/11/25 0025
 * Email:wu_tangren@163.com
 * TOD:用一句话概括
 */

public class ISweptCompl implements ISweptModel {

    /**
     * 主扫请求
     *
     * @param auth_code
     * @param listener
     */
    @Override
    public void loadSweptResult(String auth_code, SweptListener listener) {
        Request<JSONObject> request = NoHttp.createJsonObjectRequest(Config.URL, RequestMethod.POST);
        request.setConnectTimeout(30 * 1000);
        request.setReadTimeout(30 * 1000);
        request.setCacheMode(CacheMode.ONLY_REQUEST_NETWORK);
        request.set("mch_id", MyApplication.getInstance().mch_id());
        request.set("auth_code", auth_code);
        request.set("total_fee", "0.01");
        request.set("payway", "scan");
        request.set("trantype", "0");
        CallServer.getHttpclient().add(3, request, new SweptResponseListener(listener));
    }

    private class SweptResponseListener implements HttpListener<JSONObject> {

        private SweptListener listener;

        public SweptResponseListener(SweptListener listener) {
            this.listener = listener;
        }

        @Override
        public void success(int what, Response<JSONObject> response) {
//            listener.onSuccess(response.get());
            Logger.d(response.get().toString());
        }


        @Override
        public void fail(int what, Response<JSONObject> response) {
            Logger.d("失败");
        }
    }

    /**
     * 主扫订单查询
     *
     * @param out_trade_no
     * @param listener
     */
    @Override
    public void loadOrderCheck(String out_trade_no, SweptOrderCheckListener listener) {

    }


}

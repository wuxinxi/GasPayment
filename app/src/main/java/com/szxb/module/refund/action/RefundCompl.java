package com.szxb.module.refund.action;

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

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 作者：Tangren_ on 2016/12/23 0023.
 * 邮箱：wu_tangren@163.com
 * TODO:用一句话概括
 */


public class RefundCompl implements IRefundModel {

    @Override
    public void fetchRefund(String out_trade_no, RefundListener listener) {
        Request<JSONObject> request = NoHttp.createJsonObjectRequest(Config.REFUND_URL, RequestMethod.POST);
        request.setCacheMode(CacheMode.ONLY_REQUEST_NETWORK);
        request.set("mch_id", MyApplication.getInstance().mch_id());
        request.set("out_trade_no", out_trade_no);
        CallServer.getHttpclient().add(4, request, new HttpRefundListener(listener));
    }

    private class HttpRefundListener implements HttpListener<JSONObject> {

        private RefundListener listener;

        public HttpRefundListener(RefundListener listener) {
            this.listener = listener;
        }

        @Override
        public void success(int what, Response<JSONObject> response) {
            try {
                Logger.d(response.get().toString());
                String result = response.get().getString("result");
                String rescode = response.get().getString("rescode");
                Logger.d(result);
                if (result.equals("success") && rescode.equals("00"))
                    listener.onRefundSuccess("已发起退款,将在2个小时内原路退款！");
                else listener.onRefundFail(response.get().toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void fail(int what, Response<JSONObject> response) {
            listener.onRefundFail("网络或服务器异常，请重试！");
        }
    }
}

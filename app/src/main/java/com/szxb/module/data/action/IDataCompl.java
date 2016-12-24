package com.szxb.module.data.action;

import com.google.gson.Gson;
import com.szxb.bean.DataParams;
import com.szxb.bean.YouHuiLv;
import com.szxb.httpclient.CallServer;
import com.szxb.httpclient.HttpListener;
import com.yolanda.nohttp.Logger;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.rest.CacheMode;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.Response;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Tangren on 2016/12/20 0020
 * Email:wu_tangren@163.com
 * TODO:用一句话概括
 */

public class IDataCompl implements IDataModel {

    @Override
    public void loadDatas(String num, String jihao, DateLoadListener listener) {
        String url = "http://www.szxiaobing.cn/Gas/servlet/QueryInfo";
        Request<JSONObject> request = NoHttp.createJsonObjectRequest(url, RequestMethod.GET);
        request.setCacheMode(CacheMode.ONLY_REQUEST_NETWORK);
        request.add("num", num);
        request.add("jihao", jihao);
        CallServer.getHttpclient().add(1, request, new DatasResponseListener(listener));
    }

    private class DatasResponseListener implements HttpListener<JSONObject> {

        private DateLoadListener listener;

        public DatasResponseListener(DateLoadListener listener) {
            this.listener = listener;
        }

        @Override
        public void success(int what, Response<JSONObject> response) {
            Logger.d(response.get().toString());
            try {
                if (Integer.valueOf(response.get().getString("code")) == 100) {
                    Gson gson = new Gson();
                    DataParams params = gson.fromJson(response.get().toString(), DataParams.class);
                    listener.showDatasList(params.getContent(), params.getBancino(), params.getYoupin());

                } else {
                    listener.error(400, "没有数据");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void fail(int what, Response<JSONObject> response) {
            listener.error(444, "网络异常！");
        }
    }

    /**
     * 查看是否有折扣
     *
     * @param youPin
     * @param listener
     */
    @Override
    public void loadYouPin(String youPin, YoupinLoadListener listener) {
        String url = "http://www.szxiaobing.cn/Gas/servlet/Query";
        Request<JSONObject> request = NoHttp.createJsonObjectRequest(url, RequestMethod.GET);
        request.setCacheMode(CacheMode.ONLY_REQUEST_NETWORK);
        request.add("youpinNo", youPin);
        CallServer.getHttpclient().add(2, request, new YoupinResponseListener(listener));
    }

    private class YoupinResponseListener implements HttpListener<JSONObject> {

        private YoupinLoadListener listener;

        public YoupinResponseListener(YoupinLoadListener listener) {
            this.listener = listener;
        }

        @Override
        public void success(int what, Response<JSONObject> response) {
            Logger.d(response.get().toString());
            try {
                if (Integer.valueOf(response.get().getString("code")) == 100) {
                    Gson gson = new Gson();
                    YouHuiLv youHuiLv = gson.fromJson(response.get().toString(), YouHuiLv.class);
                    listener.showYoupinInfo(youHuiLv);

                } else {
                    listener.error(444, "没有数据！");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void fail(int what, Response<JSONObject> response) {
            listener.error(400, "网络或服务器异常！");
        }
    }
}

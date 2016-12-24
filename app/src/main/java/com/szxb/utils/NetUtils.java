package com.szxb.utils;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;

import java.util.UnknownFormatConversionException;

/**
 * Created by Administrator on 2016/10/10 0010.
 */
public class NetUtils {
    private NetUtils() {
        throw new UnknownFormatConversionException("Error instantiated");
    }

    //检查是否有网络
    public static boolean checkNetwork(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context
                .getSystemService(context.CONNECTIVITY_SERVICE);
        boolean wifi = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
                .isConnectedOrConnecting();
        boolean internet = manager.getNetworkInfo(
                ConnectivityManager.TYPE_MOBILE).isConnectedOrConnecting();
        if (wifi | internet)
            return true;
        return false;
    }

    public static void openSetting(AppCompatActivity activity) {
        Intent intent = new Intent("/");
        ComponentName name = new ComponentName("com.android.settings", "com.android.settings.WirelessSettings");
        intent.setComponent(name);
        intent.setAction("android.intent.action.VIEW");
        activity.startActivity(intent);
    }

}

package com.szxb.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2016/9/1 0001.
 */
public class Utils {


    private static DecimalFormat df = new DecimalFormat("######0.00");

    /**
     * 获得订单号（当前时期+4位随机数）
     *
     * @return 下午3:38:07
     * @author TangRen
     */
    public static String OrderNo() {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = new Date();
        String dateString = format.format(date);// 当前系统时间
        int buildRandom = buildRandom(4);// 5位数的随机数
        return "gas" + dateString + String.valueOf(buildRandom);
    }

    public static int buildRandom(int length) {
        int num = 1;
        double random = Math.random();
        if (random < 0.1) {
            random = random + 0.1;
        }
        for (int i = 0; i < length; i++) {
            num = num * 10;
        }
        return (int) ((random * num));
    }

    //1分=0.01元---最终得到的是分为单位
    public static int yuanToFen(String amout) {
        return (int) (Float.valueOf(amout) * 100);
    }

    public static String fen2Yuan(Double amount) {
        return df.format(amount / 100);
    }

    //1分=0.01元---最终得到的是元为单位
    public static String fenToYuan(String amount) {
        int i = Integer.valueOf(amount);
        DecimalFormat format = new DecimalFormat("0.00");
        return format.format((float) i / (float) 100);
    }

    public static String getDate() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String dateString = format.format(date);// 当前系统时间
        return dateString;
    }

    /**
     * 设置权限界面
     *
     * @param context
     */
    public static void getAppDetailSettingIntent(Context context) {
        Intent localIntent = new Intent();
        localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= 9) {
            localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            localIntent.setData(Uri.fromParts("package", context.getPackageName(), null));
        } else if (Build.VERSION.SDK_INT <= 8) {
            localIntent.setAction(Intent.ACTION_VIEW);
            localIntent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
            localIntent.putExtra("com.android.settings.ApplicationPkgName", context.getPackageName());
        }
        context.startActivity(localIntent);
    }

    /**
     * 应收
     *
     * @param danjia
     * @param qty
     * @return
     */
    public static String yingshou(String danjia, String qty) {
        double dj = Double.valueOf(danjia);
        double q = Double.valueOf(qty);
        double count = dj * q;
        return df.format(count);
    }

    /**
     * 优惠
     *
     * @param qty
     * @param youhuilv
     * @return
     */
    public static String youhui(String qty, String youhuilv) {
        if (youhuilv.equals("0") || youhuilv.equals("0.00")) {
            return yingshou(qty, youhuilv);
        } else {
            double q = Double.valueOf(qty);
            double youhv = Double.valueOf(youhuilv) / 100;
            double youhuiEnd = q * youhv;
            return df.format(youhuiEnd);
        }
    }

    /**
     * 实收
     *
     * @param danjia
     * @param qty
     * @param youhuilv
     * @return
     */
    public static String shishou(String danjia, String qty, String youhuilv) {
        double shishou = Double.valueOf(yingshou(danjia, qty)) - Double.valueOf(youhui(qty, youhuilv));
        return df.format(shishou);
    }

    public static String clearchar(String charString) {
        return charString.replace("#", "");
    }

    public static String payType(String type) {
        if (type.equals("scan"))
            return "移动支付";
        else if (type.equals("cash"))
            return "现金支付";
        else if (type.equals("card")) {
            return "会员支付";
        }
        return type;
    }

    public static String payState(String state) {
        String payState = null;
        switch (state) {
            case "0":
                payState = "支付中";
                break;
            case "1":
                payState = "支付成功";
                break;
            case "2":
                payState = "支付失败";
                break;
            case "3":
                payState = "已冲正";
                break;
            case "4":
                payState = "已退款";
                break;
            case "5":
                payState = "退款";
                break;
            default:
                payState = "未知";
                break;
        }
        return payState;
    }
}

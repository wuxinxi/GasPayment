package com.szxb.utils;


import com.alibaba.fastjson.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 作者：Tangren_ on 2016/12/22 0022.
 * 邮箱：wu_tangren@163.com
 * TODO:用一句话概括
 */


public class DataUtils {


    public static String checkDate(String date) {
        try {
            if (IsToday(date)) {
                return "今天";
            } else if (IsYesterday(date)) {
                return "昨天";
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 判断是否为今天(效率比较高)
     *
     * @param day 传入的 时间  "2016-06-28 10:10:30" "2016-06-28" 都可以
     * @return true今天 false不是
     * @throws ParseException
     */

    private static boolean IsToday(String day) throws ParseException {

        Calendar pre = Calendar.getInstance();
        Date predate = new Date(System.currentTimeMillis());
        pre.setTime(predate);

        Calendar cal = Calendar.getInstance();
        Date date = getDateFormat().parse(day);
        cal.setTime(date);

        if (cal.get(Calendar.YEAR) == (pre.get(Calendar.YEAR))) {
            int diffDay = cal.get(Calendar.DAY_OF_YEAR)
                    - pre.get(Calendar.DAY_OF_YEAR);

            if (diffDay == 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断是否为昨天(效率比较高)
     *
     * @param day 传入的 时间  "2016-06-28 10:10:30" "2016-06-28" 都可以
     * @return true今天 false不是
     * @throws ParseException
     */
    private static boolean IsYesterday(String day) throws ParseException {

        Calendar pre = Calendar.getInstance();
        Date predate = new Date(System.currentTimeMillis());
        pre.setTime(predate);

        Calendar cal = Calendar.getInstance();
        Date date = getDateFormat().parse(day);
        cal.setTime(date);

        if (cal.get(Calendar.YEAR) == (pre.get(Calendar.YEAR))) {
            int diffDay = cal.get(Calendar.DAY_OF_YEAR)
                    - pre.get(Calendar.DAY_OF_YEAR);

            if (diffDay == -1) {
                return true;
            }
        }
        return false;
    }

    public static SimpleDateFormat getDateFormat() {
        if (null == DateLocal.get()) {
            DateLocal.set(new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA));
        }
        return DateLocal.get();
    }

    private static ThreadLocal<SimpleDateFormat> DateLocal = new ThreadLocal<SimpleDateFormat>();


    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 今天
     *
     * @return
     */
    public static JSONObject getToday() {
        JSONObject jsonObject = new JSONObject();
        Date date = new Date();
        String format = sdf.format(date);
        jsonObject.put("beaginTime", format);
        jsonObject.put("endTime", format);
        return jsonObject;
    }

    /**
     * 昨天
     *
     * @return
     */
    public static JSONObject getYesterday() {
        JSONObject jsonObject = new JSONObject();
        Calendar calendar = Calendar.getInstance();
        calendar.add(calendar.DATE, -1);
        String format = sdf.format(calendar.getTime());
        jsonObject.put("beaginTime", format);
        jsonObject.put("endTime", format);
        return jsonObject;
    }

    /**
     * 本周
     *
     * @return
     */
    public static JSONObject getTimerval() {
        JSONObject jsonObject = new JSONObject();
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        //获得当前日期是本周的第几天
        int dayWeek = calendar.get(Calendar.DAY_OF_WEEK);
        //如果是第一天表示是周日
        if (1 == dayWeek) {
            calendar.add(Calendar.DAY_OF_MONTH, -1);
        }
//        System.out.println("要计算日期为:" + sdf.format(calendar.getTime()));
        // 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        // 获得当前日期是一个星期的第几天
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        // 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        calendar.add(Calendar.DATE, calendar.getFirstDayOfWeek() - day);
        String imptimeBegin = sdf.format(calendar.getTime());
//        System.out.println("所在周星期一的日期：" + imptimeBegin);
        calendar.add(Calendar.DATE, 6);
        String imptimeEnd = sdf.format(calendar.getTime());
//        System.out.println("所在周星期日的日期：" + imptimeEnd);

        jsonObject.put("beaginTime", imptimeBegin);
        jsonObject.put("endTime", imptimeEnd);
        return jsonObject;
    }

    /**
     * 上周
     *
     * @return
     */
    public static JSONObject getLastTimeInterval() {
        JSONObject jsonObject = new JSONObject();
        Calendar calendar1 = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        int dayOfWeek = calendar1.get(Calendar.DAY_OF_WEEK) - 1;
        int offset1 = 1 - dayOfWeek;
        int offset2 = 7 - dayOfWeek;
        calendar1.add(Calendar.DATE, offset1 - 7);
        calendar2.add(Calendar.DATE, offset2 - 7);
        // System.out.println(sdf.format(calendar1.getTime()));// last Monday
        String lastBeginDate = sdf.format(calendar1.getTime());
        // System.out.println(sdf.format(calendar2.getTime()));// last Sunday
        String lastEndDate = sdf.format(calendar2.getTime());
        jsonObject.put("beaginTime", lastBeginDate);
        jsonObject.put("endTime", lastEndDate);
        return jsonObject;
    }

    /**
     * 获得当月
     *
     * @return
     */
    public static JSONObject getMonth() {
        JSONObject jsonObject = new JSONObject();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 0);//本月就是0，上月-1，以此类推
        calendar.set(Calendar.DAY_OF_MONTH, 1);//1號開始
        String beaginTime = sdf.format(calendar.getTime());
        jsonObject.put("beaginTime", beaginTime);

        //获取当月最后一天
        Calendar last = Calendar.getInstance();
        last.set(Calendar.DAY_OF_MONTH, last.getActualMaximum(Calendar.DAY_OF_MONTH));
        String endTime = sdf.format(last.getTime());
        jsonObject.put("endTime", endTime);
        return jsonObject;
    }

    public static JSONObject getLastMonth() {
        JSONObject jsonObject = new JSONObject();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        String beaginTime = sdf.format(calendar.getTime());
        jsonObject.put("beaginTime", beaginTime);

        //获取上个月的最后一天
        Calendar lastCalendar = Calendar.getInstance();
        int month = lastCalendar.get(Calendar.MONTH);
        lastCalendar.set(Calendar.MONTH, month - 1);
        lastCalendar.set(Calendar.DAY_OF_MONTH, lastCalendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        String endTime = sdf.format(lastCalendar.getTime());
        jsonObject.put("endTime", endTime);
        return jsonObject;
    }
}
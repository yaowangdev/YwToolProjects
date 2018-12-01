package com.appdev.common.lib.time;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * @author 创建人 ：yaowang
 * @version 1.0
 * @package 包名 ：com.appdev.common.lib
 * @createTime 创建时间 ：2018/11/29
 * @modifyBy 修改人 ：
 * @modifyTime 修改时间 ：
 * @modifyMemo 修改备注：
 */
public class TimeUtils {
    /**
     * 返回当前时间戳
     *
     * @return 当前时间戳, 单位 毫秒
     */
    public static long now() {
        return System.currentTimeMillis();
    }

    /**
     * 把long类型的时间戳转换成日期的格式来显示
     *
     * @param timeStamp 单位毫秒
     * @return 日期
     */
    public static String parseTimeStamps(long timeStamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        return sdf.format(new Date(timeStamp));
    }


    /**
     * 把long类型的时间戳转换成日期的格式来显示
     *
     * @param timeStamp 单位毫秒
     * @return 日期
     */
    public static String parseTimeStamp(long timeStamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
        return sdf.format(new Date(timeStamp));
    }

    /**
     * 把long类型的时间戳转换成日期的格式来显示
     *
     * @param date 日期
     * @return 日期
     */
    public static String parseTimeStamp(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
        return sdf.format(date);
    }

    /**
     * 把long类型的时间戳转换成日期的格式来显示:"yyyy-MM-dd 星期"
     *
     * @param timeStamp 单位毫秒
     * @return 日期
     */
    public static String parseTimeStamp2(long timeStamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd EEEE", Locale.CHINA);
        return sdf.format(new Date(timeStamp));
    }

    /**
     * 把long类型的时间戳转换成日期的格式来显示:"HH:mm:ss"
     *
     * @param timeStamp 单位毫秒
     * @return 日期
     */
    public static String parseTimeStamp3(long timeStamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss", Locale.CHINA);
        sdf.setTimeZone(TimeZone.getTimeZone("GMT +08：00"));
        return sdf.format(new Date(timeStamp));
    }


    /**
     * 显示当前时间
     *
     * @param format 显示格式
     * @return 当前日期
     */
    public static String getCurrentTime(String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.CHINA);
        return sdf.format(new Date(now()));
    }

    /**
     * 获取当前时间戳，精确到天
     *
     * @return 时间戳
     */
    public static long getCurDateTimeStamp() {
        return System.currentTimeMillis() / 86400000 * 86400000;
    }

    /**
     * 获取当前时间戳，精确到分钟
     */
    public static long getCurMinuteTimeStamp() {
        return System.currentTimeMillis() / 60000 * 60000;
    }

    /**
     * 获取当前是星期几
     *
     * @return 0[星期天] 1[星期一] 2[星期二] 3[星期三] 4[星期四] 5[星期五] 6[星期六]
     */
    public static int getCurWeekday() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date(System.currentTimeMillis()));
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        return w < 0 ? 0 : w;
    }

    /**
     * 将【秒数】转为【天-小时-分-秒】返回
     */
    public static String formatTime(long sec) {
        String time;
        long days = sec / (60 * 60 * 24);
        long hours = (sec % (60 * 60 * 24)) / (60 * 60);
        long minutes = (sec % (60 * 60)) / 60;
        long seconds = sec % 60;
        if (days > 0) {
            time = days + "天" + hours + "小时" + minutes + "分钟" + seconds + "秒";
        } else if (hours > 0) {
            time = hours + "小时" + minutes + "分钟" + seconds + "秒";
        } else if (minutes > 0) {
            time = minutes + "分钟" + seconds + "秒";
        } else {
            time = seconds + "秒";
        }
        return time;
    }
}

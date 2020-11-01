package com.plat.common.utils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ClassName: TimeUtil <br/>
 *
 * @Author: 范嘉恒 <br/>
 * Date: 2019/5/28 18:26 <br/>
 * Version: 1.0 <br/>
 * Description:  <br/>
 */
public class TimeUtil {
    private static final String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 计算指定日期距今多少年
     * @param times 指定日期
     * @return 年
     */
    public static int getTime(String times){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        String time=sdf.format(new Date());
        String t1 = time.replace('-','/');
        String t2 = times.replace('-','/');

        @SuppressWarnings("deprecation")
        Date dt1= new Date(t1);
        @SuppressWarnings("deprecation")
        Date dt2= new Date(t2);
        long i= (dt1.getTime() - dt2.getTime())/(1000*60*60*24);
        return (int) (i/365);
    }

    /**
     * 返回当前时间
     * @return
     */
    public static String getNowTime(){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time=sdf.format(new Date());
        return time;
    }

    /**
     * 返回当前时间
     * @return
     */
    public static String getNowTime(String format){
        SimpleDateFormat sdf=new SimpleDateFormat(format);
        String time=sdf.format(new Date());
        return time;
    }

    /**
     * 将java.util.Date类型转化位String类型
     *
     * @param date
     *            要转换的时间
     * @param format
     *            时间格式,如果未指定时间格式,那么采用默认的时间格式来转换
     *
     * @return 如果转换成功，返回指定格式字符串，如果转换失败，返回null
     */
    public static String date2String(Date date, String format) {
        if (StringUtil.isBlank(date)) {
            return null;
        }

        if (StringUtil.isBlank(format)) {
            format = DEFAULT_FORMAT;
        }

        SimpleDateFormat formator = new SimpleDateFormat(format);

        return formator.format(date);
    }

    /**
     * 字符串转日期格式
     * @param string
     * @param format
     * @return
     */
    public static Date string2Date(String string,String format){
        if (StringUtil.isBlank(string)) {
            return null;
        }

        if (StringUtil.isBlank(format)) {
            format = DEFAULT_FORMAT;
        }
        SimpleDateFormat formator = new SimpleDateFormat(format);
        try {
            return formator.parse(string);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * 计算两日期间相隔的天数
     * @param one
     * @param two
     * @return
     */
    public static long daysBetween(Date one, Date two) {
        long difference =  (one.getTime()-two.getTime())/86400000;
        return Math.abs(difference);
    }

    /**
     * 导入日期格式判断
     * @param str
     * @return
     */
    public static boolean isDaoRuDate(String str){
        boolean b = true;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try{
            format.setLenient(false);
            format.parse(str);
        }catch (Exception e){
            b = false;
        }
        return b;
    }
}

package com.plat.sysconfig.util;

import com.plat.sysconfig.dao.DateSetRepository;
import com.plat.sysconfig.dao.SysGlobalConfigRepository;
import com.plat.sysconfig.entity.DateSet;
import com.plat.sysconfig.entity.SysGlobalConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.*;
@Component
public class IntervalTimeUtil {

    @Autowired
    SysGlobalConfigRepository repository;

    @Autowired
    DateSetRepository dateSetRepository;

    public static IntervalTimeUtil util;

    @PostConstruct
    public void init() {
        util = this;
    }

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 初始化时间 2018-10-19 18:12:19 --> 2018-10-19 00:00:00/2018-10-20 00:00:00
     * @param dateStr
     * @param flag 初始化为当前天或者后一天（1）
     * @return
     */
    public static String initZeroDate(String dateStr, String flag,int hour,int min,int second) {
        SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = dfs.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, min);
        calendar.set(Calendar.SECOND,second);
        if ("1".equals(flag)) {
            calendar.add(Calendar.DAY_OF_MONTH, +1);
            return dfs.format(calendar.getTime()).toString();
        }
        return dfs.format(calendar.getTime()).toString();

    }

    /**
     *
     * @param startTime
     * @param intervalTime
     * @param type 1.不排除 2.只排除上下班 3.只排除节假日 4.排除上下班+节假日
     * @return
     * @throws ParseException
     */
    public static String getEndDate(String startTime,double intervalTime,int type) throws ParseException {
        String resTime = "";

        String morningStartDate = "09:00:00";
        String morningEndDate = "12:00:00";
        String afternoonStartTime = "14:00:00";
        String afternoonEndTime ="18:00:00";

        List<SysGlobalConfig> list = util.repository.findAll();
        if(list.size()>0)
        {
            SysGlobalConfig config = list.get(0);
            morningStartDate = config.getStartTimeOfWork1();
            morningEndDate = config.getEndTimeOfWork1();
            afternoonStartTime = config.getStartTimeOfWork2();
            afternoonEndTime = config.getEndTimeOfWork2();
        }

        List<DateSet> dateSets = util.dateSetRepository.findByDelTag(1);
        List<String> playDay = new ArrayList<>();
        for(DateSet dateSet : dateSets)
        {
            playDay.add(dateSet.getDatetime());
        }

        /*playDay.add("2020-05-21");
        playDay.add("2020-05-25");
        playDay.add("2020-05-27");
        playDay.add("2020-05-29");*/

        try {
            double surplusTime = intervalTime*60;//可用于增加的时间(分钟)
            //12：00-14：00休息
            //获取上班结束时间、获取节假日调休日期List、中午开始开始休息时间、中午结束休息时间
            Calendar cal = Calendar.getInstance();
            String nowTime = startTime;
            if(type == 1)
            {
                cal.setTime(sdf.parse(startTime));
                cal.add(Calendar.MINUTE, new BigDecimal(intervalTime).multiply(new BigDecimal(60)).intValue());// 24小时制
                resTime = sdf.format(cal.getTime());
            }else if(type==3)
            {
                morningStartDate = "00:00:00";
                morningEndDate = "12:00:00";
                afternoonStartTime = "12:00:00";
                afternoonEndTime ="24:00:00";

                resTime = handleTime( surplusTime, intervalTime, playDay,
                        nowTime, morningStartDate, morningEndDate, afternoonStartTime, afternoonEndTime, type);
            }else {
                resTime = handleTime( surplusTime, intervalTime, playDay,
                        nowTime, morningStartDate, morningEndDate, afternoonStartTime, afternoonEndTime, type);
            }


        }catch (Exception e)
        {
            e.printStackTrace();
        }



        //根据上下午去推移时间
        //进入下一天需要先判断下一天是否在工作日
        return resTime;
    }

    public static String handleTime(Double surplusTime,double intervalTime,List<String> playDay,
                                    String nowTime,String morningStartDate,String morningEndDate,String afternoonStartTime,String afternoonEndTime,int type) throws Exception {

        String resTime = "";

        String morningStartDateTmp = morningStartDate;
        String morningEndDateTmp = morningEndDate;
        String afternoonStartTimeTmp = afternoonStartTime;
        String afternoonEndTimeTmp = afternoonEndTime;
        Calendar cal = Calendar.getInstance();
        while(surplusTime <= intervalTime*60 & surplusTime > 0)
        {
            morningStartDate = nowTime.substring(0,11)+" "+morningStartDateTmp;
            morningEndDate = nowTime.substring(0,11)+" "+morningEndDateTmp;
            afternoonStartTime = nowTime.substring(0,11)+" "+afternoonStartTimeTmp;
            afternoonEndTime = nowTime.substring(0,11)+" "+afternoonEndTimeTmp;
            cal.setTime(sdf.parse(morningStartDate));
            if((type == 4 || type == 3) && (isWeekend(nowTime) || isHoliday(nowTime.substring(0,10),playDay)))
            {
                nowTime = initZeroDate(nowTime,"1",
                        cal.get( Calendar.HOUR_OF_DAY ),cal.get( Calendar.MINUTE ), cal.get( Calendar.SECOND ));
                continue;
            }
            int hour = cal.get( Calendar.HOUR_OF_DAY );
            if(hour < 12)
            {
                double morningInterval = dateDiff(nowTime,morningEndDate,"yyyy-MM-dd HH:mm:ss","m"); //上午消耗的时间
                double afternoonInterval = dateDiff(afternoonStartTime,afternoonEndTime,"yyyy-MM-dd HH:mm:ss","m"); //下午消耗的时间
                surplusTime = surplusTime-morningInterval-afternoonInterval;//减去
            }else {
                double afternoonInterval = dateDiff(nowTime,afternoonEndTime,"yyyy-MM-dd HH:mm:ss","m"); //下午消耗的时间
                surplusTime = surplusTime-afternoonInterval;//减去
            }
            if(surplusTime<0)
            {
                cal.setTime(sdf.parse(afternoonEndTime));
                if(type == 3)
                {
                    nowTime = initZeroDate(nowTime,"1",
                            cal.get( Calendar.HOUR_OF_DAY ),cal.get( Calendar.MINUTE ), cal.get( Calendar.SECOND ));
                }else {
                    nowTime = initZeroDate(nowTime,"0",
                            cal.get( Calendar.HOUR_OF_DAY ),cal.get( Calendar.MINUTE ), cal.get( Calendar.SECOND ));
                }

            }else {
                nowTime = initZeroDate(nowTime,"1",
                        cal.get( Calendar.HOUR_OF_DAY ),cal.get( Calendar.MINUTE ), cal.get( Calendar.SECOND ));
            }
        }

        if(surplusTime <0)
        {
            long time = sdf.parse(nowTime).getTime();
            resTime = timeStamp2Date(String.valueOf(time+(long) (surplusTime*60*1000)),"yyyy-MM-dd HH:mm:ss");
        }
        return resTime;
    }

    /**
     * 判断是否是weekend
     *
     * @param sdate
     * @return
     */
    public static boolean isWeekend(String sdate) throws Exception {
        Date date = sdf.parse(sdate);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        if(cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){
            return true;
        } else{
            return false;
        }

    }

    /**
     * 判断是否是holiday
     *
     * @param sdate
     * @param list
     * @return
     */
    public static boolean isHoliday(String sdate, List<String> list) throws Exception {
        if(list.size() > 0){
            for(int i = 0; i < list.size(); i++){
                if(sdate.equals(list.get(i))){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     *
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @param format 时间格式
     * @param str 返回的格式（以天数/小时/分钟返回）
     * @return 两个时间相差
     */
    public static double dateDiff(String startTime, String endTime,
                                  String format, String str) {
        System.out.println("dateDiffstartTime="+startTime);
        System.out.println("dateDiffendTime="+endTime);
        // 按照传入的格式生成一个simpledateformate对象
        SimpleDateFormat sd = new SimpleDateFormat(format);
        long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数
        long nh = 1000 * 60 * 60;// 一小时的毫秒数
        long nm = 1000 * 60;// 一分钟的毫秒数
        long ns = 1000;// 一秒钟的毫秒数
        long diff;
        long day = 0;
        long hour = 0;
        long min = 0;
        long sec = 0;
        // 获得两个时间的毫秒时间差异
        double time = 0;
        try {
            diff = sd.parse(endTime).getTime() - sd.parse(startTime).getTime();
            day = diff / nd;// 计算差多少天
            hour = diff % nd / nh + day * 24;// 计算差多少小时
            min = diff % nd % nh / nm + day * 24 * 60;// 计算差多少分钟
            sec = diff % nd % nh % nm / ns;// 计算差多少秒
            // 输出结果
          /*  System.out.println("时间相差：" + day + "天" + (hour - day * 24) + "小时"
                    + (min - day * 24 * 60) + "分钟" + sec + "秒。");
            System.out.println("hour=" + hour + ",min=" + min);*/
            if (str.equalsIgnoreCase("h")) {
                time = hour+(double)min/60+(double)sec/3600;
            } else if(str.equalsIgnoreCase("m")){
                /* return min;*/
                time = hour*60+min+(double)sec/60;
            }else {
                time = hour*60*60+min*60+sec;
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return time;
    }
    /**
     * 时间戳转换成日期格式字符串
     * @param seconds 精确到秒的字符串
     * @param format
     * @return
     */
    public static String timeStamp2Date(String seconds,String format) {
        if(seconds == null || seconds.isEmpty() || seconds.equals("null")){
            return "";
        }
        if(format == null || format.isEmpty()){
            format = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(Long.valueOf(seconds)));
    }
    /**
     * 日期格式字符串转换成时间戳
     * @param date 字符串日期
     * @param format 如：yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String date2TimeStamp(String date,String format){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return String.valueOf(sdf.parse(date).getTime()/1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
    public static void main(String[] args) throws ParseException {
     /*   playDay.add("2020-05-21");
        playDay.add("2020-05-25");
        playDay.add("2020-05-27");
        playDay.add("2020-05-29");*/
        //System.out.println(getDate("2020-05-20 10:20:00",20,3));
    }
}


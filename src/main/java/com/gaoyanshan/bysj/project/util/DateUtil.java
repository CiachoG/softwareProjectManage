package com.gaoyanshan.bysj.project.util;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringExclude;
import org.aspectj.weaver.ast.Test;

import java.util.Calendar;
import java.util.Date;

/**
 * <pre>类名: DateUtil</pre>
 * <pre>描述: 日期处理类</pre>
 * <pre>日期: 19-3-29 下午7:26</pre>
 * <pre>作者: gaoyanshan</pre>
 */
public class DateUtil {

    /**
     * 获取本周开始时间
     * @return
     */
    public static Date getTimesWeekBegin() {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return cal.getTime();
    }

    /**
     * 获取本周结束时间
     * @return
     */
    public static Date getTimesWeekEnd() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getTimesWeekBegin());
        cal.add(Calendar.DAY_OF_WEEK, 7);
        return cal.getTime();
    }


    /**
     * 本月开始时间
     * @return
     */
    public static Date getTimesMonthBegin() {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
        return cal.getTime();
    }

    /**
     * 本月结束时间
     * @return
     */
    public static Date getTimesMonthEnd() {
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        cal.set(Calendar.HOUR_OF_DAY, 24);
        return cal.getTime();
    }


    /**
     * 获取指定月份开始时间
     * @param mouth
     * @return
     */
    public static  Date getTimeAppointMouthBegin(int mouth){
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR),mouth,1,0,0,0);
        return cal.getTime();
    }

    /**
     * 获取指定月份结束时间
     * @param mouth
     * @return
     */
    public static Date getTimeAppointMouthEnd(int mouth){
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR),mouth,cal.get(Calendar.DAY_OF_MONTH),0,0,0);
        cal.set(Calendar.DAY_OF_MONTH,cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        cal.set(Calendar.HOUR_OF_DAY,24);
        return cal.getTime();
    }


    /**
     * 获取当前处于第几周
     * @return
     */
    public static int getWeekNumOfNow(){
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        return cal.get(Calendar.WEEK_OF_YEAR);
    }


    /**
     * 获取某周的时间
     * @param week
     * @return
     */
    public static Date getOneWeekBegin(int week){
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        cal.set(Calendar.WEEK_OF_YEAR,week);

        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return cal.getTime();
    }

    public static Date getOneWeekEnd(int week){
        Calendar cal = Calendar.getInstance();
        cal.setTime(getOneWeekBegin(week));
        cal.add(Calendar.DAY_OF_WEEK, 7);
        return cal.getTime();
    }



    /**
     * 今天0点
     * @return
     */
    public static Date getDayBegin() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }



    /**
     * 昨天零点
     * @return
     */

    public static Date getYesterdayBegin() {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(getDayBegin().getTime()-3600*24*1000);
        return cal.getTime();
    }

    /**
     * 明天零点
     * @return
     */
    public static Date getTomorrowBegin(){
        Calendar cal  = Calendar.getInstance();
        cal.setTimeInMillis(getDayBegin().getTime()+3600*24*1000);
        return  cal.getTime();
    }

}

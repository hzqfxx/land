package com.test.land.landparent.admin.common.utils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;
import org.joda.time.Days;
import org.joda.time.Hours;
import org.joda.time.LocalDate;
import org.joda.time.Minutes;
import org.joda.time.Months;
import org.joda.time.Seconds;
import org.joda.time.Weeks;
import org.joda.time.Years;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 日期工具
 *
 * @author linlun
 *
 */
public class DateUtils {

    private static Logger logger = LoggerFactory.getLogger(DateUtils.class);

    private static final SimpleDateFormat datetimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    private static final SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

    /**
     * 时间格式
     */
    public static final String dateType = "yyyy-MM-dd";

    /**
     * 获取两个日期相差的月数
     *
     * @Description:
     * @param @param
     *            date1
     * @param @param
     *            date2
     * @param @return
     * @param @throws
     *            ParseException
     * @return int
     * @throws @author
     *             liuzhaohui
     * @date 2017年10月26日
     */
    public static int getMonthSpace(String date1, String date2) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Calendar bef = Calendar.getInstance();
        Calendar aft = Calendar.getInstance();
        bef.setTime(sdf.parse(date1));
        aft.setTime(sdf.parse(date2));
        int result = aft.get(Calendar.MONTH) - bef.get(Calendar.MONTH);
        int month = (aft.get(Calendar.YEAR) - bef.get(Calendar.YEAR)) * 12;
        return Math.abs(month + result);
    }

    /**
     * 两个日期相差的年数
     * @param start
     * @param end
     * @return
     */
    public static int yearsBetween(Date start, Date end) {
        return Years.yearsBetween(LocalDate.fromDateFields(start), LocalDate.fromDateFields(end)).getYears();
    }

    /**
     * 两个日期相差的月数
     * @param start
     * @param end
     * @return
     */
    public static int monthsBetween(Date start, Date end) {
        return Months.monthsBetween(LocalDate.fromDateFields(start), LocalDate.fromDateFields(end)).getMonths();
    }

    /**
     * 两个日期相差的周数
     * @param start
     * @param end
     * @return
     */
    public static int weeksBetween(Date start, Date end) {
        return Weeks.weeksBetween(LocalDate.fromDateFields(start), LocalDate.fromDateFields(end)).getWeeks();
    }

    /**
     * 两个日期相差的天数
     * @param start
     * @param end
     * @return
     */
    public static int daysBetween(Date start, Date end) {
        return Days.daysBetween(LocalDate.fromDateFields(start), LocalDate.fromDateFields(end)).getDays();
    }

    /**
     * 两个日期相差的小时数
     * @param start
     * @param end
     * @return
     */
    public static int hoursBetween(Date start, Date end) {
        return Hours.hoursBetween(LocalDate.fromDateFields(start), LocalDate.fromDateFields(end)).getHours();
    }

    /**
     * 两个日期相差的分钟数
     * @param start
     * @param end
     * @return
     */
    public static int minutesBetween(Date start, Date end) {
        return Minutes.minutesBetween(LocalDate.fromDateFields(start), LocalDate.fromDateFields(end)).getMinutes();
    }

    /**
     * 两个日期相差的秒数
     * @param start
     * @param end
     * @return
     */
    public static int secondsBetween(Date start, Date end) {
        return Seconds.secondsBetween(LocalDate.fromDateFields(start), LocalDate.fromDateFields(end)).getSeconds();
    }

    /**
     * 指定时间+年数
     * @param time
     * @param years
     * @return
     * @throws Exception
     */
    public static Date addOrMinusYear(long time, int years) throws Exception {
        Date rtn = null;
        GregorianCalendar cal = new GregorianCalendar();
        Date date = new Date(time);
        cal.setTime(date);
        cal.add(Calendar.YEAR, years);
        rtn = cal.getTime();
        return rtn;
    }

    /**
     * 指定时间+月数
     * @param time
     * @param months
     * @return
     * @throws Exception
     */
    public static Date addOrMinusMonth(long time, int months) throws Exception {
        Date rtn = null;
        GregorianCalendar cal = new GregorianCalendar();
        Date date = new Date(time);
        cal.setTime(date);
        cal.add(Calendar.MONTH, months);
        rtn = cal.getTime();
        return rtn;
    }

    /**
     * 指定时间+周数
     * @param time
     * @param week
     * @return
     */
    public static Date addOrMinusWeek(long time, int week) {
        Date rtn = null;
        GregorianCalendar cal = new GregorianCalendar();
        Date date = new Date(time);
        cal.setTime(date);
        cal.add(Calendar.WEEK_OF_YEAR, week);
        rtn = cal.getTime();
        return rtn;
    }

    /**
     * 指定时间+天数
     * @param time
     * @param days
     * @return
     */
    public static Date addOrMinusDays(long time, int days) {
        Date rtn = null;
        GregorianCalendar cal = new GregorianCalendar();
        Date date = new Date(time);
        cal.setTime(date);
        cal.add(5, days);
        rtn = cal.getTime();
        return rtn;
    }

    /**
     * 指定时间+小时数
     * @param time
     * @param hours
     * @return
     */
    public static Date addOrMinusHours(long time, int hours) {
        Date rtn = null;
        GregorianCalendar cal = new GregorianCalendar();
        Date date = new Date(time);
        cal.setTime(date);
        cal.add(10, hours);
        rtn = cal.getTime();
        return rtn;
    }

    /**
     * 指定时间+分钟数
     * @param time
     * @param minites
     * @return
     */
    public static Date addOrMinusMinutes(long time, int minites) {
        Date rtn = null;
        GregorianCalendar cal = new GregorianCalendar();
        Date date = new Date(time);
        cal.setTime(date);
        cal.add(12, minites);
        rtn = cal.getTime();
        return rtn;
    }

    /**
     * 指定时间+秒数
     * @param time
     * @param seconds
     * @return
     */
    public static Date addOrMinusSecond(long time, int seconds) {
        Date rtn = null;
        GregorianCalendar cal = new GregorianCalendar();
        Date date = new Date(time);
        cal.setTime(date);
        cal.add(13, seconds);
        rtn = cal.getTime();
        return rtn;
    }

    /**
     * 获得当前日期时间
     * <p>
     * 日期时间格式yyyy-MM-dd HH:mm:ss
     *
     * @return
     */
    public static String currentDatetime() {
        return datetimeFormat.format(now());
    }

    /**
     * 格式化日期时间
     * <p>
     * 日期时间格式yyyy-MM-dd HH:mm:ss
     *
     * @return
     */
    public static String formatDatetime(Date date) {
        return datetimeFormat.format(date);
    }

    /**
     * 格式化日期时间
     *
     * @param date
     * @param pattern
     *            格式化模式，详见{@link SimpleDateFormat}构造器
     *            <code>SimpleDateFormat(String pattern)</code>
     * @return
     */
    public static String formatDatetime(Date date, String pattern) {
        SimpleDateFormat customFormat = (SimpleDateFormat) datetimeFormat.clone();
        customFormat.applyPattern(pattern);
        return customFormat.format(date);
    }

    /**
     * 获得当前日期
     * <p>
     * 日期格式yyyy-MM-dd
     *
     * @return
     */
    public static String currentDate() {
        return dateFormat.format(now());
    }

    /**
     * 格式化日期
     * <p>
     * 日期格式yyyy-MM-dd
     *
     * @return
     */
    public static String formatDate(Date date) {
        return dateFormat.format(date);
    }

    /**
     * 获得当前时间
     * <p>
     * 时间格式HH:mm:ss
     *
     * @return
     */
    public static String currentTime() {
        return timeFormat.format(now());
    }

    /**
     * 格式化时间
     * <p>
     * 时间格式HH:mm:ss
     *
     * @return
     */
    public static String formatTime(Date date) {
        return timeFormat.format(date);
    }

    /**
     * 获得当前时间的<code>java.util.Date</code>对象
     *
     * @return
     */
    public static Date now() {
        return new Date();
    }

    public static Calendar calendar() {
        Calendar cal = GregorianCalendar.getInstance(Locale.CHINESE);
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        return cal;
    }

    /**
     * 获得当前时间的毫秒数
     * <p>
     * 详见{@link System#currentTimeMillis()}
     *
     * @return
     */
    public static long millis() {
        return System.currentTimeMillis();
    }

    /**
     *
     * 获得当前Chinese月份
     *
     * @return
     */
    public static int month() {
        return calendar().get(Calendar.MONTH) + 1;
    }

    /**
     * 获得月份中的第几天
     *
     * @return
     */
    public static int dayOfMonth() {
        return calendar().get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 今天是星期的第几天
     *
     * @return
     */
    public static int dayOfWeek() {
        return calendar().get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 今天是年中的第几天
     *
     * @return
     */
    public static int dayOfYear() {
        return calendar().get(Calendar.DAY_OF_YEAR);
    }

    /**
     * 判断原日期是否在目标日期之前
     *
     * @param src
     * @param dst
     * @return
     */
    public static boolean isBefore(Date src, Date dst) {
        return src.before(dst);
    }

    /**
     * 判断原日期是否在目标日期之后
     *
     * @param src
     * @param dst
     * @return
     */
    public static boolean isAfter(Date src, Date dst) {
        return src.after(dst);
    }

    /**
     * 判断两日期是否相同
     *
     * @param date1
     * @param date2
     * @return
     */
    public static boolean isEqual(Date date1, Date date2) {
        return date1.compareTo(date2) == 0;
    }

    /**
     * 判断某个日期是否在某个日期范围
     *
     * @param beginDate
     *            日期范围开始
     * @param endDate
     *            日期范围结束
     * @param src
     *            需要判断的日期
     * @return
     */
    public static boolean between(Date beginDate, Date endDate, Date src) {
        return beginDate.before(src) && endDate.after(src);
    }

    /**
     * 获得当前月的最后一天
     * <p>
     * HH:mm:ss为0，毫秒为999
     *
     * @return
     */
    public static Date lastDayOfMonth() {
        Calendar cal = calendar();
        cal.set(Calendar.DAY_OF_MONTH, 0); // M月置零
        cal.set(Calendar.HOUR_OF_DAY, 0);// H置零
        cal.set(Calendar.MINUTE, 0);// m置零
        cal.set(Calendar.SECOND, 0);// s置零
        cal.set(Calendar.MILLISECOND, 0);// S置零
        cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + 1);// 月份+1
        cal.set(Calendar.MILLISECOND, -1);// 毫秒-1
        return cal.getTime();
    }

    /**
     * 获得当前月的第一天
     * <p>
     * HH:mm:ss SS为零
     *
     * @return
     */
    public static Date firstDayOfMonth() {
        Calendar cal = calendar();
        cal.set(Calendar.DAY_OF_MONTH, 1); // M月置1
        cal.set(Calendar.HOUR_OF_DAY, 0);// H置零
        cal.set(Calendar.MINUTE, 0);// m置零
        cal.set(Calendar.SECOND, 0);// s置零
        cal.set(Calendar.MILLISECOND, 0);// S置零
        return cal.getTime();
    }

    private static Date weekDay(int week) {
        Calendar cal = calendar();
        cal.set(Calendar.DAY_OF_WEEK, week);
        return cal.getTime();
    }

    /**
     * 获得周五日期
     * <p>
     * 注：日历工厂方法{@link #calendar()}设置类每个星期的第一天为Monday，US等每星期第一天为sunday
     *
     * @return
     */
    public static Date friday() {
        return weekDay(Calendar.FRIDAY);
    }

    /**
     * 获得周六日期
     * <p>
     * 注：日历工厂方法{@link #calendar()}设置类每个星期的第一天为Monday，US等每星期第一天为sunday
     *
     * @return
     */
    public static Date saturday() {
        return weekDay(Calendar.SATURDAY);
    }

    /**
     * 获得周日日期
     * <p>
     * 注：日历工厂方法{@link #calendar()}设置类每个星期的第一天为Monday，US等每星期第一天为sunday
     *
     * @return
     */
    public static Date sunday() {
        return weekDay(Calendar.SUNDAY);
    }

    /**
     * 将字符串日期时间转换成java.util.Date类型
     * <p>
     * 日期时间格式yyyy-MM-dd HH:mm:ss
     *
     * @param datetime
     * @return
     */
    public static Date parseDatetime(String datetime) throws ParseException {
        return datetimeFormat.parse(datetime);
    }

    /**
     * 将字符串日期转换成java.util.Date类型
     * <p>
     * 日期时间格式yyyy-MM-dd
     *
     * @param date
     * @return
     * @throws ParseException
     */
    public static Date parseDate(String date) throws ParseException {
        return dateFormat.parse(date);
    }

    /**
     * 将字符串日期转换成java.util.Date类型
     * <p>
     * 时间格式 HH:mm:ss
     *
     * @param time
     * @return
     * @throws ParseException
     */
    public static Date parseTime(String time) throws ParseException {
        return timeFormat.parse(time);
    }

    /**
     * 根据自定义pattern将字符串日期转换成java.util.Date类型
     *
     * @param datetime
     * @param pattern
     * @return
     * @throws ParseException
     */
    public static Date parseDatetime(String datetime, String pattern) throws ParseException {
        SimpleDateFormat format = (SimpleDateFormat) datetimeFormat.clone();
        format.applyPattern(pattern);
        return format.parse(datetime);
    }

    /**
     * 时间转换（date转string）
     *
     * @param date
     *            需要转换的date
     * @param format
     *            格式化类型 eg.yyyy-MM-dd
     */
    public static String dateToString(Date date, String format) {
        SimpleDateFormat simpledateformat = new SimpleDateFormat(format);
        if (date != null)
            return simpledateformat.format(date);
        else
            return "";
    }

    /**
     * 时间转换（date转string）
     *
     * @param date
     *            需要转换的date 默认为yyyy-MM-dd
     */
    public static String dateToString(Date date) {
        return dateToString(date, dateType);
    }

    /**
     * 时间转换（string转date）
     *
     * @param str
     *            需要转换的data，string格式
     * @param format
     *            格式化类型 eg.yyyy-MM-dd
     */
    public static Date stringToDate(String str, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        sdf.setLenient(Boolean.FALSE);
        Date date = null;
        try {
            date = sdf.parse(str);
        } catch (ParseException e) {
            logger.error("stringToDate error. str: " + str + " format: " + format, e);
        }
        return date;
    }

    /**
     * 时间转换（string转date）
     *
     * @param str
     *            需要转换的data，默认格式为yyyy-MM-dd
     */
    public static Date stringToDate(String str) {
        return stringToDate(str, dateType);
    }

    /**
     * 获得当前日期的第几周
     *
     * @param date
     *            需要转换的date
     */
    public static int getWeekOfDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd|w");
        String fmt = sdf.format(date);
        return new Integer(StringUtils.substringAfter(fmt, "|"));
    }

    /**
     * 以默认方式'yyyy-MM-dd'验证是否为合法日期格式
     *
     * @param str
     *            需要转换的data，string格式
     * @return
     */
    public static boolean isValidDate(String str) {
        return isValidDate(str, dateType);
    }

    /**
     * 以自定义方式验证是否为合法日期格式
     *
     * @param str
     *            需要转换的data，string格式
     * @param format
     *            格式化类型 eg.yyyy-MM-dd
     * @return
     */
    public static boolean isValidDate(String str, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        sdf.setLenient(Boolean.FALSE);
        try {
            sdf.parse(str);
            return Boolean.TRUE;
        } catch (ParseException e) {
            logger.error("isValidDate error. str: " + str + " format: " + format, e);
            return Boolean.FALSE;
        }
    }

    public static Date getStartTimeOfDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static Date getEndTimeOfDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }

    public static Date getAddDaysStartTimeOfDate(Date date, Integer days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, days);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static Date getAddDaysEndTimeOfDate(Date date, Integer days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, days);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }

    public static Date getAddMonthsStartTimeOfDate(Date date, Integer month) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, month);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static Date getWeekStartTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        return calendar.getTime();
    }

    /**
     * 比较两个时间
     *
     * @param dt1
     * @param dt2
     * @return
     * @return int
     * @author liuzhaohui
     * @date 2017年8月30日
     */
    public static int compareDate(Date dt1, Date dt2) {
        if (dt1.getTime() > dt2.getTime()) {
            System.out.println("dt1 在dt2前");
            return 1;
        } else if (dt1.getTime() < dt2.getTime()) {
            System.out.println("dt1在dt2后");
            return -1;
        } else {// 相等
            return 0;
        }
    }
}

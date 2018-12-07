package util;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class DateUtils {

	public static final String YYYYMMDD = "yyyyMMdd";
	public static final String YYYYMMDDHH = "yyyyMMddHH";
	public static final String YYYYMMDDHHMM = "yyyyMMddHHmm";
	public static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
	public static final String YYYY_MM_DD = "yyyy-MM-dd";
	public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
	public static final String YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
	public static final String YYYYMMDDHHMMSSSSS = "yyyyMMddhhmmssSSS";
	public static final String YYYYMMDDHH24MMSSSSS = "yyyyMMddHHmmssSSS";
	public static final String YYYY_MM_DD_HH_MM_SSSSS = "yyyy-MM-dd hh:mm:ss SSS";
    public static final String YYYY_MM_DD_HH24_MM_SSSSS = "yyyy-MM-dd HH:mm:ss SSS";
    public static final String YYYY_MM_DD_HH24_MM_SSDSSS = "yyyy-MM-dd HH:mm:ss.SSS";
	public static final String YYYYMM = "yyyyMM";
	public static final String HHmmss = "HHmmss";
	public static final String HH_mm = "HH:mm";
	public static final String M_YUE_DD_RI = "M月dd日";

	public static final String YYYY_NIAN_MM_YUE_DD_RI_HH_MM = "yyyy年MM月dd日 HH:mm";
	public static final String YYYY_NIAN_MM_YUE_DD_RI = "yyyy年MM月dd日";
	public static final String DDMMYY_HHMMSS = "dd/MM/yyyy hh:mm:ss";
	public static final String YYYYMMDD_HHMMSS = "yyyy/MM/dd HH:mm:ss";
	public static final String MMDDYY = "MM/dd/yyyy";
	public static final String YYYYMM_DD = "yyyy/MM/dd";
	public static final SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static long getDateDiffByDay(String time1, String time2) {
		long quot = 0;
		SimpleDateFormat ft = new SimpleDateFormat(YYYY_MM_DD);
		try {
			Date date1 = ft.parse(time1);
			Date date2 = ft.parse(time2);
			quot = date1.getTime() - date2.getTime();
			quot = quot / 1000 / 60 / 60 / 24;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return quot;
	}

	public static int getDateDiffByDay(Date time1, Date time2) {
		long quot = 0;
		quot = time1.getTime() - time2.getTime();
		quot = quot / 1000 / 60 / 60 / 24;
		return (int)quot;
	}

	public static Date getDateDiffNDay(String strDate, int n) {
		SimpleDateFormat ft = new SimpleDateFormat(YYYY_MM_DD);
		try {
			Date dDate = ft.parse(strDate);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(dDate);
			calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + n);
			return calendar.getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Date parseDate(String sDate, String pattern) {
		DateFormat format = new SimpleDateFormat(pattern);
		if (!ParamUtils.isEmpty(sDate)) {
			try {
				return format.parse(sDate);
			} catch (ParseException e) {
				e.printStackTrace(); // To change body of catch statement use
										// File | Settings | File Templates.
				return null;
			}
		}
		return null;
	}

	public static Date parseCnDate(String sDate) {
		return parseDate(sDate, YYYY_NIAN_MM_YUE_DD_RI);
	}

	public static Date parseDate(String sDate) {
		return parseDate(sDate, YYYY_MM_DD);
	}
	
	public static Date parseDateTime(String sDate) {
		return parseDate(sDate, YYYY_MM_DD_HH_MM_SS);
	}

	public static String formatDate(Date sDate) {
		return formatDate(sDate, YYYY_MM_DD);
	}

	public static String formatCnDate(Date sDate) {
		return formatDate(sDate, YYYY_NIAN_MM_YUE_DD_RI);
	}

	public static String formatDate(Date sDate, String formatStr) {
		if (sDate == null)
			return "";
		DateFormat format = new SimpleDateFormat(formatStr);
		return format.format(sDate);
	}

    public static String formatDate(Long sDate, String formatStr) {
        if (sDate == null)
            return "";
        Date date = new Date(sDate);
        DateFormat format = new SimpleDateFormat(formatStr);
        return format.format(date);
    }

	public static String defaultFormatDate(Date sDate) {
		return formatDate(sDate, YYYY_MM_DD_HH_MM_SS);
	}

	public static boolean isValidDate(String dateStr, String pattern) {
		return formatDate(parseDate(dateStr, pattern), pattern).equals(dateStr);
	}

	public static Date addMinutes(Date date, int amount) {
		return add(date, Calendar.MINUTE, amount);
	}
	
	public static Date addHours(Date date, int amount) {
		return add(date, Calendar.HOUR_OF_DAY, amount);
	}

	public static Date addDays(Date date, int amount) {
		return add(date, Calendar.DAY_OF_MONTH, amount);
	}

	public static Date add(Date date, int calendarField, int amount) {
		if (date == null) {
			throw new IllegalArgumentException("The date must not be null");
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(calendarField, amount);
		return c.getTime();
	}

	public static String getStringCurrentDateTime() {
		Calendar rightNow = Calendar.getInstance();
		int intYear = rightNow.get(Calendar.YEAR);

		int intMonth = rightNow.get(Calendar.MONTH) + 1;
		String strMonth = null;
		if (intMonth < 10) {
			strMonth = "0" + intMonth;
		} else {
			strMonth = "" + intMonth;
		}

		int intDate = rightNow.get(Calendar.DATE);
		String strDate = null;
		if (intDate < 10) {
			strDate = "0" + intDate;
		} else {
			strDate = "" + intDate;
		}

		int intHour = rightNow.get(Calendar.HOUR_OF_DAY);
		String strHour = null;
		if (intHour < 10) {
			strHour = "0" + intHour;
		} else {
			strHour = "" + intHour;
		}

		int intMinute = rightNow.get(Calendar.MINUTE);
		String strMinute = null;
		if (intMinute < 10) {
			strMinute = "0" + intMinute;
		} else {
			strMinute = "" + intMinute;
		}

		int intSecond = rightNow.get(Calendar.SECOND);
		String strSecond = null;
		if (intSecond < 10) {
			strSecond = "0" + intSecond;
		} else {
			strSecond = "" + intSecond;
		}

		return intYear + "-" + strMonth + "-" + strDate + " " + strHour + ":" + strMinute + ":" + strSecond;

	}

	/**
	 * 用来转换日期格式的公用方法
	 * 
	 * @param dateString
	 *            需要转换的时间
	 * @param sourceDateFormat
	 *            原时间格式
	 * @param destDateFormat
	 *            目的时间格式
	 * @return 转换后的时间
	 * @throws ParseException
	 *             日期格式错误时抛出的异常
	 */
	public static String convertDateFormat(String dateString, String sourceDateFormat, String destDateFormat)
			throws ParseException {
		DateFormat dfOne = new SimpleDateFormat(sourceDateFormat);
		DateFormat dfTwo = new SimpleDateFormat(destDateFormat);
		Date date = dfOne.parse(dateString);
		return dfTwo.format(date);
	}

	public static String getthreeMothDate() {
		Calendar time = Calendar.getInstance();
		time.add(Calendar.MONTH, 2);
		int dayNum = time.getActualMaximum(Calendar.DAY_OF_MONTH);
		time.set(Calendar.DATE, dayNum);
		Date date = time.getTime();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String format = df.format(date);
		return format;
	}
	
	public static String getPreThreeMothDate() {
		Calendar time = Calendar.getInstance();
		int day = time.get(Calendar.DAY_OF_MONTH);
		time.add(Calendar.MONTH, -3);
		int dayNum = time.getActualMaximum(Calendar.DAY_OF_MONTH);
		if(day > dayNum) {
			time.set(Calendar.DATE, dayNum);
		}
		Date date = time.getTime();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String format = df.format(date);
		return format;
	}
	
	 /**
     * 当月第一天 只含日期
     * @return
     */
    public static Date getFirstDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        SimpleDateFormat format = new SimpleDateFormat(YYYY_MM_DD);
        return parseDate(format.format(calendar.getTime()), YYYY_MM_DD);
    }
    
    /**
     * 当月最后一天 只含日期
     * @return
     */
    public static Date getEndDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DATE));
        SimpleDateFormat format = new SimpleDateFormat(YYYY_MM_DD);
        return parseDate(format.format(calendar.getTime()), YYYY_MM_DD);
    }

	public static String getDateToString() {
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(date);
	}

	public static String getNextDateToString() {
		Date date = new Date(new Date().getTime() + 1000 * 3600 * 24);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(date);
	}
	
	public static String getBeforeDateToString(long n) {
	    Date date = new Date(new Date().getTime() - 1000 * 3600 * 24*n);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

	public static Date getNowDate(){
		return new Date();
	}
	
	public static Date getDateNotTime(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}
	
	public static Date getNowDateWithOutTime() {
		Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

	public static boolean isBeforeEquals(Date date1, Date date2) {
		if (date1 == null || date2 == null) {
			return false;
		}
		Date d1 = parseDate(formatDate(date1));
		Date d2 = parseDate(formatDate(date2));
		return !d1.after(d2);
	}

	/**
	 * 比较日期，精确到天；date1 >= date2，return true；否则false
	 * 注意，不比较时分秒
	 * @param date1 
	 * @param date2
	 * @return
	 */
	public static boolean isAfterEquals(Date date1, Date date2) {
		if (date1 == null || date2 == null) {
			return false;
		}
		Date d1 = parseDate(formatDate(date1));
		Date d2 = parseDate(formatDate(date2));
		return !d1.before(d2);
	}
	
	public static Boolean isSameEquals(Date date1, Date date2) {
		if (date1 == null || date2 == null) {
			return false;
		}
		return formatDate(date1).equals(formatDate(date2));
	}
	
	public static int getYear(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.YEAR);
	}
	
	public static int getMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.MONTH);
	}

    public static int getWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_WEEK);
    }
	
	public static int getDay(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.DAY_OF_MONTH);
	}
	
	public static int getHour(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.HOUR);
	}
	
	/**
	 * 根据两个日期得出年龄，带小数
	 * 注：nowDate大于birthDay的日期时，年龄多加0.5岁（不考虑年份）;owDate小于birthDay的日期时，年龄多减0.5岁（不考虑年份）;
	 * 例：过了12岁，就是12.5岁;79未满80，就是79.5岁;
	 * @param birthDay
	 * @param nowDate
	 * @return
	 */
	public static double getAge(Date birthDay, Date nowDate) {
		int year = getYear(birthDay);
		int month = getMonth(birthDay);
		int day = getDay(birthDay);
		int year1 = getYear(nowDate);
		int month1 = getMonth(nowDate);
		int day1 = getDay(nowDate);
		
		double age = year1 - year;
		if(month1 > month || (month1 == month && day1 > day)) {
			age+=0.5;
		}
		if(month1 < month || (month1 == month && day1 < day)) {
			age-=0.5;
		}
		return age;
	}
	
	public static XMLGregorianCalendar convert(Date date) {
		try{
			GregorianCalendar gcal =new GregorianCalendar();			
			gcal.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
			gcal.setTime(date);
			return DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);
		} catch (Exception e) {
			return null;
		}
	}
	
	public static Date toDate(XMLGregorianCalendar c) {
		try{
			GregorianCalendar gcal = c.toGregorianCalendar();
			return gcal.getTime();
		} catch (Exception e) {
			return null;
		}
	}

	public static String formatDateTime(Date date) {
		if (date == null) {
			return null;
		}
		return dateTimeFormat.format(date);
	}
	
}

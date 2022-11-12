package demo.util;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.TimeZone;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

@Slf4j
public class DateHelper {

	/** 系统默认的日期格式 yyyy-MM-dd */
	public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";

	/** 默认的时间格式 HH:mm:ss */
	public static final String DEFAULT_TIME_FORMAT = "HH:mm:ss";

	/** 默认的日期时间格式 yyyy-MM-dd HH:mm:ss */
	public static final String DEFAULT_DATETIME_FORMAT = DEFAULT_DATE_FORMAT + " " + DEFAULT_TIME_FORMAT;

	/** 默认的带毫秒的日期时间格式 yyyy-MM-dd HH:mm:ss.SSS */
	public static final String DEFAULT_TIMESTAMP_FORMAT = DEFAULT_DATETIME_FORMAT + ".SSS";

	/** yyyy-MM-dd'T'HH:mm:ss'Z' */
	public static final String RFC3339_DATETIME = "yyyy-MM-dd'T'HH:mm:ss'Z'";
	/** yyyy-MM-dd'T'HH:mm:ss.SSS'Z' */
	public static final String RFC3339_TIMESTAMP = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

	/** yyyy-MM-dd'T'HH:mm:ss */
	public static final String RFC3339_DATETIME_SIMPLE = "yyyy-MM-dd'T'HH:mm:ss";
	/** yyyy-MM-dd'T'HH:mm:ss.SSS */
	public static final String RFC3339_TIMESTAMP_SIMPLE = "yyyy-MM-dd'T'HH:mm:ss.SSS";

	/** yyyy-MM-dd'T'HH:mm:ss'Z' */
	public static final String RFC3339_DATETIME_WITH_TIMEZONE = "yyyy-MM-dd'T'HH:mm:ssX";
	/** yyyy-MM-dd'T'HH:mm:ss.SSS'Z' */
	public static final String RFC3339_TIMESTAMP_WITH_TIMEZONE = "yyyy-MM-dd'T'HH:mm:ss.SSSX";

	/** 所有可能进行转换的日期格式 */
	private static final LinkedHashSet<String> ALL_FORMATS = new LinkedHashSet<>();

	static {

		//设置为上海时区
		TimeZone.setDefault(TimeZone.getTimeZone("GMT+8:00"));

		ALL_FORMATS.add(DEFAULT_TIMESTAMP_FORMAT);
		ALL_FORMATS.add(DEFAULT_DATETIME_FORMAT);
		ALL_FORMATS.add(DEFAULT_DATE_FORMAT);

		ALL_FORMATS.add(RFC3339_TIMESTAMP);
		ALL_FORMATS.add(RFC3339_DATETIME);
		ALL_FORMATS.add(RFC3339_TIMESTAMP_WITH_TIMEZONE);
		ALL_FORMATS.add(RFC3339_DATETIME_WITH_TIMEZONE);
		ALL_FORMATS.add(RFC3339_TIMESTAMP_SIMPLE);
		ALL_FORMATS.add(RFC3339_DATETIME_SIMPLE);

		ALL_FORMATS.add("yyyy-MM-dd HH:mm");
		ALL_FORMATS.add("yyyy-MM-dd HH");
		ALL_FORMATS.add("yyyy-MM");
		ALL_FORMATS.add("yyyy/MM/dd HH:mm:ss.SSS");
		ALL_FORMATS.add("yyyy/MM/dd HH:mm:ss");
		ALL_FORMATS.add("yyyy/MM/dd HH:mm");
		ALL_FORMATS.add("yyyy/MM/dd HH");
		ALL_FORMATS.add("yyyy/MM/dd");
		ALL_FORMATS.add("yyyy/MM");
		ALL_FORMATS.add("dd/MM/yyyy HH:mm:ss.SSS");
		ALL_FORMATS.add("dd/MM/yyyy HH:mm:ss");
		ALL_FORMATS.add("dd/MM/yyyy HH:mm");
		ALL_FORMATS.add("dd/MM/yyyy HH");
		ALL_FORMATS.add("dd/MM/yyyy");
		ALL_FORMATS.add("MM/dd/yyyy HH:mm:ss.SSS");
		ALL_FORMATS.add("MM/dd/yyyy HH:mm:ss");
		ALL_FORMATS.add("MM/dd/yyyy HH:mm");
		ALL_FORMATS.add("MM/dd/yyyy HH");
		ALL_FORMATS.add("MM/dd/yyyy");
		ALL_FORMATS.add("yyyy\\MM\\dd HH:mm:ss.SSS");
		ALL_FORMATS.add("yyyy\\MM\\dd HH:mm:ss");
		ALL_FORMATS.add("yyyy\\MM\\dd HH:mm");
		ALL_FORMATS.add("yyyy\\MM\\dd HH");
		ALL_FORMATS.add("yyyy\\MM\\dd");
		ALL_FORMATS.add("yyyy\\MM");
		ALL_FORMATS.add("yyyy.MM.dd HH:mm:ss.SSS");
		ALL_FORMATS.add("yyyy.MM.dd HH:mm:ss");
		ALL_FORMATS.add("yyyy.MM.dd HH:mm");
		ALL_FORMATS.add("yyyy.MM.dd HHmmssSSS");
		ALL_FORMATS.add("yyyy.MM.dd HHmmss");
		ALL_FORMATS.add("yyyy.MM.dd HHmm");
		ALL_FORMATS.add("yyyy.MM.dd HH");
		ALL_FORMATS.add("yyyy.MM.dd");
		ALL_FORMATS.add("yyyy.MM");

		ALL_FORMATS.add("yyyyMMdd HHmmssSSS");
		ALL_FORMATS.add("yyyyMMdd HHmmss");
		ALL_FORMATS.add("yyyyMMdd HHmm");
		ALL_FORMATS.add("yyyyMMdd HH");

		ALL_FORMATS.add("yyyy年MM月dd日HH时mm分ss秒SSS毫秒");
		ALL_FORMATS.add("yyyy年MM月dd日HH时mm分ss秒");
		ALL_FORMATS.add("yyyy年MM月dd日HH时mm分");
		ALL_FORMATS.add("yyyy年MM月dd日HH时");
		ALL_FORMATS.add("yyyy年MM月dd日");
		ALL_FORMATS.add("yyyy年MM月");
		ALL_FORMATS.add("yyyy年");
	}

	/**
	 * 将日期字符串source按指定的格式pattern转换为Date类型
	 *
	 * @param source 要转换的日期字符串
	 * @param pattern 日期格式字符串
	 * @return 转换后的日期
	 */
	public static Date parse(String source, String pattern) {
		ParsePosition pos = new ParsePosition(0);
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		format.setLenient(false);
		Date date = format.parse(source, pos);
		ALL_FORMATS.add(pattern);
		if (pos.getIndex() == 0 || pos.getIndex() < source.length()) {
			throw new RuntimeException("Unable parse date(" + pos.getErrorIndex() + "): \"" + source + "\"");
		}
		return date;
	}

	/**
	 * 转换source为日期类型,尝试所有默认的日期格式
	 *
	 * @param source 要转换的数据，可以为Date/Number/字符串
	 * @return 转换后的日期
	 * @see #parse(String)
	 */
	public static Date parse(Object source) {
		if (source == null) {
			return null;
		}
		if (source instanceof Date) {
			return (Date) source;
		}
		if (source instanceof Number) {
			return new Date(((Number) source).longValue());
		}
		return parse(source.toString());
	}

	/**
	 * 判断字符串是否为时间戳格式
	 *
	 * @param source 输入的时间字符串,应该为数字
	 * @return true是时间戳格式(source是一个纯数字)
	 */
	public static boolean isTimestamp(String source) {
		return NumberUtils.isCreatable(source);
	}

	/**
	 * 转换source为日期类型,尝试所有默认的日期格式
	 *
	 * @param source 日期字符串
	 * @return 转换后的日期
	 * @see #parse(String, String)
	 */
	public static Date parse(String source) {

		log.info("parse date {}",source);
		if (source == null || StringUtils.isBlank(source)) {
			return null;
		}

		source = source.trim();
		if (isTimestamp(source)) {
			// 毫秒时间戳
			return new Date(NumberUtils.toLong(source));
		}

		Date date;
		for (String pattern : ALL_FORMATS) {
			ParsePosition pos = new ParsePosition(0);
			SimpleDateFormat format = new SimpleDateFormat(pattern);
			format.setLenient(false);
			date = format.parse(source, pos);
			if (pos.getIndex() == 0 || pos.getIndex() < source.length()) {
				continue;
			}
			return date;
		}

		return null;
	}

	/**
	 * 格式化date为pattern格式的字符串
	 *
	 * @param date Date 日期
	 * @param pattern String 日期时间格式
	 * @return 转换后的日期时间字符串
	 * @see SimpleDateFormat#SimpleDateFormat(String)
	 * @see SimpleDateFormat#format(Date)
	 */
	public static String format(Date date, String pattern) {
		if (date != null){
			return new SimpleDateFormat(pattern).format(date);
		}
		return "";
	}

	/**
	 * 将source表示的日期转换为pattern格式的字符串
	 *
	 * @param source 表示日期的字符串
	 * @param pattern 转换后的格式
	 * @return 转换后的日期时间字符串
	 */
	public static String format(String source, String pattern) {
		Date date = parse(source);
		if (date == null) {
			return "";
		}
		return format(date, pattern);
	}

	/**
	 * 将date日期转换为默认日期格式yyyy-MM-dd的字符串
	 *
	 * @param date
	 * @return
	 * @see #DEFAULT_DATE_FORMAT
	 */
	public static String format(Date date) {
		return format(date, DEFAULT_DATE_FORMAT);
	}


	/**
	 * 格式化当前时间
	 *
	 * @return
	 * @see #format(Date)
	 * @see #DEFAULT_DATETIME_FORMAT
	 */
	public static String now() {
		return format(new Date(), DEFAULT_DATETIME_FORMAT);
	}

	/**
	 * 格式化当前日期
	 *
	 * @return
	 * @see #format(Date)
	 */
	public static String nowDate() {
		return format(new Date());
	}

	/**
	 * 当前年份
	 *
	 */
	public static int getYear() {
		return get(Calendar.YEAR);
	}

	/**
	 * 当前月份,从1开始
	 */
	public static int getMonth() {
		return get(Calendar.MONTH) + 1;
	}

	/**
	 * 当前日期
	 *
	 */
	public static int getDay() {
		return get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 小时,0-23
	 *
	 */
	public static int getHour() {
		return get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * 得到当前时间中指定的参数
	 *
	 * @return
	 * @see Calendar#get(int)
	 */
	public static int get(int field) {
		return get(new Date(), field);
	}

	/**
	 * 根据日期得到对应的Calendar对象.如果date为null,则返回的是当前日期
	 *
	 * @param date
	 * @return
	 */
	public static Calendar getCalendar(Date date) {
		Calendar c = Calendar.getInstance();
		if (date != null) {
			c.setTime(date);
		}
		return c;
	}

	/**
	 * 得到日期date中的key数据段
	 *
	 * @param date
	 * @param field
	 * @return
	 */
	public static int get(Date date, int field) {
		Calendar c = getCalendar(date);
		return c.get(field);
	}

	/**
	 * 在日期对象上增加天数等.
	 *
	 * @param date 要改变的日期
	 * @param field 表示增加年/月/日/时/分/秒等
	 * @param amount 可以为负数
	 * @return
	 */
	public static Date add(Date date, int field, int amount) {
		Calendar c = getCalendar(date);
		c.add(field, amount);
		return date = c.getTime();
	}

	/**
	 * 在日期date上增加days天
	 *
	 * @param date
	 * @param days 要增加的天数,可以为负数
	 * @return
	 */
	public static Date add(Date date, int days) {
		return add(date, Calendar.DAY_OF_MONTH, days);
	}

	/**
	 * 改变date中指定自读的值
	 *
	 * @param date
	 * @param field
	 * @param amount
	 * @return
	 */
	public static Date set(Date date, int field, int amount) {
		Calendar c = getCalendar(date);
		c.set(field, amount);
		return date = c.getTime();
	}

	/** {@link #of(int...)}方法每个参数依次的字段 */
	static final int[] DATE_FIELDS = { Calendar.YEAR, Calendar.MONTH, Calendar.DATE, Calendar.HOUR_OF_DAY, Calendar.MINUTE, Calendar.SECOND,
			Calendar.MILLISECOND };

	/**
	 * 根据参数得到一个日期
	 *
	 * @param dates 依次为年/月(从0开始)/日(从1开始)/时/分/秒/毫秒.为负数则表示使用当前的时间
	 * @return
	 */
	public static Date of(int... dates) {
		Calendar c = Calendar.getInstance();
		if (dates != null && dates.length > 0) {
			int[] a = { 0, 0, 1, 0, 0, 0, 0 };
			System.arraycopy(dates, 0, a, 0, dates.length);
			for (int i = 0, l = a.length; i < l; i++) {
				if (a[i] < 0) {
					continue;
				}
				c.set(DATE_FIELDS[i], a[i]);
			}
		}
		return c.getTime();
	}

	/**
	 * 清除date中指定的字段
	 *
	 * @param date
	 * @param field
	 * @return
	 * @see Calendar#clear(int)
	 */
	public static Date clear(Date date, int field) {
		Calendar c = getCalendar(date);
		c.clear(field);
		return c.getTime();
	}

	/** 删除日期 */
	public static final char CLEAR_DATE = 'd';
	/** 删除时间 */
	public static final char CLEAR_TIME = 't';
	/** 全部删除 */
	public static final char CLEAR_ALL = 'a';

	/**
	 * @param date
	 * @param field D/d清理日期,A全部清理,T/t/其他:清理时间
	 * @return
	 * @see Calendar#clear()
	 * @see Calendar#clear(int)
	 */
	public static Date clear(Date date, char field) {
		Calendar c = getCalendar(date);
		switch (field) {
			case CLEAR_DATE:
			case 'D':
				// 清理日期
				c.clear(Calendar.ERA);
				c.clear(Calendar.YEAR);
				c.clear(Calendar.MONTH);
				c.clear(Calendar.WEEK_OF_YEAR);
				c.clear(Calendar.WEEK_OF_MONTH);
				c.clear(Calendar.DATE);
				// c.clear(Calendar.DAY_OF_MONTH);
				c.clear(Calendar.DAY_OF_YEAR);
				c.clear(Calendar.DAY_OF_WEEK);
				c.clear(Calendar.DAY_OF_WEEK_IN_MONTH);
				break;
			case CLEAR_TIME:
			case 'T':
			default:
				// 清理时间
				c.clear(Calendar.AM_PM);
				c.clear(Calendar.HOUR);
				c.clear(Calendar.HOUR_OF_DAY);
				c.clear(Calendar.MINUTE);
				c.clear(Calendar.SECOND);
				c.clear(Calendar.MILLISECOND);
				c.clear(Calendar.DST_OFFSET);
				c.clear(Calendar.ZONE_OFFSET);
				break;
			case CLEAR_ALL:
			case 'A':
				c.clear();
				break;

		}
		return c.getTime();
	}

	/**
	 * 清除date的时间
	 *
	 * @param date
	 * @return
	 */
	public static Date clear(Date date) {
		return clear(date, CLEAR_TIME);
	}

	/**
	 * 将date的时间变为23:59:59
	 *
	 * @param date
	 * @return
	 */
	public static Date clearToEnd(Date date) {
		if (date == null) {
			return null;
		}
		return parse(format(date, DEFAULT_DATE_FORMAT) + " 23:59:59", DEFAULT_DATETIME_FORMAT);
	}

	/**
	 * 判断指定的date对象中是否包含有时分秒信息，如果时/分/秒/毫秒都为0则返回false。
	 *
	 * @param date
	 * @return 如果时/分/秒/毫秒都为0则返回false
	 */
	public static boolean hasTime(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return 0 != c.get(Calendar.HOUR) || 0 == c.get(Calendar.HOUR_OF_DAY) || 0 == c.get(Calendar.MINUTE) || 0 == c.get(Calendar.SECOND)
				|| 0 == c.get(Calendar.MILLISECOND);
	}

	/**
	 * 一天的毫秒数
	 */
	public static final int DAY = 24 * 60 * 60 * 1000;

	/**
	 * 判断cls是否是一个日期类型
	 *
	 * @param cls
	 * @return
	 */
	public static boolean isDate(Class<?> cls) {
		return cls != null && Date.class.isAssignableFrom(cls);
	}

	/**
	 * 判断date是否为日期对象
	 *
	 * @param date
	 * @return
	 */
	public static boolean isDate(Object date) {
		try {
			return date == null ? false : date instanceof Date;
		} catch (Exception e) {
			return isDate(date.toString());
		}
	}

	/**
	 * 判断指定的日期date与日期格式dateformat是否匹配
	 *
	 * @param date
	 * @param pattern
	 * @return ture 如果date能解析为pattern的日期格式
	 * @see #parse(String, String)
	 */
	public static boolean isDate(String date, String pattern) {
		try {
			return null != parse(date, pattern);
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * date是否能解析为日期
	 *
	 * @param date
	 * @return true 如果date为已知的日期格式
	 * @see #parse(String)
	 */
	public static boolean isDate(String date) {
		return null != parse(date);
	}

	/** 月大时每个月最多31天 */
	public static int DAYS_31_IN_MONTH = 31;
	/** 月小时每个月最多30天 */
	public static int DAYS_30_IN_MONTH = 30;
	/** 闰年2月为29天 */
	public static int DAYS_IN_FEBRUARY_LEAP = 29;
	/** 非闰年2月为28天 */
	public static int DAYS_IN_FEBRUARY = 28;

	/**
	 * year, month, day是否为日期
	 *
	 * @param year 年
	 * @param month 月，1~12
	 * @param day 日
	 * @return
	 */
	public static boolean isDate(int year, int month, int day) {
		if (month <= 0 || month > (Calendar.DECEMBER + 1)) {
			return false;
		}
		if (day < 0 || day > DAYS_31_IN_MONTH) {
			return false;
		}
		return day < getDays(year, month);
	}

	/**
	 * 是否闰年
	 *
	 * @param year 年份
	 * @return
	 */
	public static boolean isLeap(int year) {
		return (year % 400 == 0 || (year % 100 != 0 && year % 4 == 0));
	}

	/**
	 * 返回一个月的天数
	 *
	 * @param year 年份
	 * @param month 月份，1~12
	 * @return month当月的天数
	 */
	public static int getDays(int year, int month) {
		int m = month - 1;
		switch (m) {
			case Calendar.FEBRUARY:
				return isLeap(year) ? DAYS_IN_FEBRUARY_LEAP : DAYS_IN_FEBRUARY;
			case Calendar.JANUARY:
			case Calendar.MARCH:
			case Calendar.MAY:
			case Calendar.JULY:
			case Calendar.AUGUST:
			case Calendar.OCTOBER:
			case Calendar.DECEMBER:
				return DAYS_31_IN_MONTH;
			default:
				return DAYS_30_IN_MONTH;
		}
	}

	/**
	 * 取较小的日期
	 *
	 * @param date
	 * @param another
	 * @return
	 */
	public static Date min(Date date, Date another) {
		return date.before(another) ? date : another;
	}

	/**
	 * 取较大的日期
	 *
	 * @param date
	 * @param another
	 * @return
	 */
	public static Date max(Date date, Date another) {
		return another.after(date) ? another : date;
	}

}

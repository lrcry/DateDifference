package au.com.ioof.commons;

import java.util.HashMap;
import java.util.Map;

/**
 * Constants definitions<br/>
 * 
 * @author hansmong
 *
 */
public final class Constants {
	public static final String DATE_FORMAT_DD_MM_YYYY = "DD MM YYYY";

	public static final String DELIMITER_DATE_FIELD = " ";

	public static final short NUMBER_CUSTOM_DATE_FIELD = 3;

	public static final int NUMBER_MONTH_FEB = 2;

	public static final int NUMBER_MONTH_AUG = 8;

	public static final int NUMBER_LEAP_YEAR_INTERVAL = 4;

	public static final int NUMBER_COMMON_YEAR_DAYS = 365;

	public static final int NUMBER_LEAP_YEAR_DAYS = 366;

	public static final short INDEX_DATE_FIELD_DAY_DD = 0;

	public static final short INDEX_DATE_FIELD_MONTH_MM = 1;

	public static final short INDEX_DATE_FIELD_YEAR_YY = 2;

	public static final int MIN_DAY_VALUE = 1;

	public static final int MAX_DAY_VALUE_THIRTY_ONE = 31;

	public static final int MAX_DAY_VALUE_THIRTY = 30;

	public static final int MAX_DAY_VALUE_FEB_COMMON = 28;

	public static final int MAX_DAY_VALUE_FEB_LEAP = 29;

	public static final int MIN_MONTH_VALUE = 1;

	public static final int MAX_MONTH_VALUE = 12;

	public static final int MIN_YEAR_VALUE = 1900;

	public static final int MAX_YEAR_VALUE = 2010;

	public static final String YEAR_COMMON = "COMMON";

	public static final String YEAR_LEAP = "LEAP";

	public static Map<String, Map<Integer, Integer>> YEAR_MONTH_DAYS_MAP = new HashMap<>();

	/**
	 * Initialize the month-days map for common and leap years
	 */
	static {
		YEAR_MONTH_DAYS_MAP.put(YEAR_COMMON, getMonthDaysMap(false));
		YEAR_MONTH_DAYS_MAP.put(YEAR_LEAP, getMonthDaysMap(true));
	}

	private static Map<Integer, Integer> getMonthDaysMap(boolean isYearLeap) {
		Map<Integer, Integer> monthDaysMap = new HashMap<>();
		for (int i = MIN_MONTH_VALUE; i <= MAX_MONTH_VALUE; ++i) {
			if (i < NUMBER_MONTH_AUG) {
				// Jan, Feb, Mar, Apr, May, Jun, Jul
				if (i % 2 == 0) {
					if (i == NUMBER_MONTH_FEB) {
						// Feb, common year 28, leap year 29
						monthDaysMap.put(i, isYearLeap ? MAX_DAY_VALUE_FEB_LEAP : MAX_DAY_VALUE_FEB_COMMON);
					} else {
						// 30 days
						monthDaysMap.put(i, MAX_DAY_VALUE_THIRTY);
					}
				} else {
					// 31 days
					monthDaysMap.put(i, MAX_DAY_VALUE_THIRTY_ONE);
				}
			} else {
				if (i % 2 == 0) {
					// 31 days
					monthDaysMap.put(i, MAX_DAY_VALUE_THIRTY_ONE);
				} else {
					// 30 days
					monthDaysMap.put(i, MAX_DAY_VALUE_THIRTY);
				}
			}
		}
		return monthDaysMap;
	}
}

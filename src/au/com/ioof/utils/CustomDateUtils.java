package au.com.ioof.utils;

import java.util.Map;

import au.com.ioof.beans.CustomDateBean;
import au.com.ioof.commons.Constants;
import au.com.ioof.exceptions.InvalidInputException;
import au.com.ioof.exceptions.LogicalException;
import au.com.ioof.exceptions.UnsupportedDateFormatException;

/**
 * Custom date utils<br/>
 * 
 * @author hansmong
 *
 */
public final class CustomDateUtils {
	public static long getDatesDifference(CustomDateBean laterDate, CustomDateBean earlierDate)
			throws LogicalException {
		long diff = 0;

		if (laterDate.compare(earlierDate) <= 0) {
			throw new LogicalException(
					"The later date is no later than the earlier date given when calculating difference");
		}

		boolean isEarlierYearLeap = isLeapYear(earlierDate.getYear());
		boolean isLaterYearLeap = isLeapYear(laterDate.getYear());
		long offsetEarlierDate = getDateOffsetInYear(earlierDate, isEarlierYearLeap);
		long offsetLaterDate = getDateOffsetInYear(laterDate, isLaterYearLeap);
		long yearDiff = 0;
		if (laterDate.getYear() != earlierDate.getYear()) {
			yearDiff = getDayDifferenceBetweenYear(earlierDate.getYear(), laterDate.getYear());
		} else {
			// in the same year
		}

		diff = offsetLaterDate + yearDiff - offsetEarlierDate;

		return diff;
	}

	/**
	 * Get difference of days between two years<br/>
	 * 
	 * @param startYear
	 * @param isStartYearLeap
	 * @param endYear
	 * @return
	 */
	public static long getDayDifferenceBetweenYear(int startYear, int endYear) {
		long diff = 0;
		boolean isYearLeap = false;

		for (int i = startYear; i < endYear; ++i) {
			isYearLeap = isLeapYear(i);

			if (isYearLeap) {
				diff += Constants.NUMBER_LEAP_YEAR_DAYS;
				isYearLeap = false;
			} else {
				diff += Constants.NUMBER_COMMON_YEAR_DAYS;
			}
		}
		return diff;
	}

	/**
	 * Get the offset of the day in the year<br/>
	 * 
	 * @param customDate
	 *            date object
	 * @param isYearLeap
	 *            leap year flag
	 * @return offset of the day
	 */
	public static long getDateOffsetInYear(CustomDateBean customDate, boolean isYearLeap) {
		long offset = 0;
		Map<Integer, Integer> yearMonthDaysMap = Constants.YEAR_MONTH_DAYS_MAP
				.get(isYearLeap ? Constants.YEAR_LEAP : Constants.YEAR_COMMON);
		for (int i = 1; i < customDate.getMonth(); ++i) {
			offset += yearMonthDaysMap.get(i);
		}
		offset += customDate.getDay();
		return offset;
	}

	/**
	 * Check date format<br/>
	 * 
	 * @param dateFormat
	 *            date format string
	 * @throws InvalidInputException
	 *             thrown when the input date format is invalid
	 * @throws UnsupportedDateFormatException
	 *             thrown when the input date format is not supported
	 */
	private static void checkDateFormat(String dateFormat)
			throws InvalidInputException, UnsupportedDateFormatException {
		if (isStringEmpty(dateFormat)) {
			// empty date format
			throw new InvalidInputException("Date format string must be given");
		}

		if (!Constants.DATE_FORMAT_DD_MM_YYYY.equals(dateFormat.toUpperCase())) {
			// date format unsupported
			throw new UnsupportedDateFormatException("Date format [" + dateFormat + "] could not be supported");
		}
	}

	/**
	 * Get custom date from string<br/>
	 * 
	 * @param dateString
	 *            date string
	 * @param dateFormat
	 *            date format to be used
	 * @return custom date object
	 * @throws InvalidInputException
	 *             thrown when the input date format is invalid
	 * @throws UnsupportedDateFormatException
	 *             thrown when the input date format is not supported
	 */
	public static CustomDateBean getCustomDateFromString(String dateString, String dateFormat)
			throws InvalidInputException, UnsupportedDateFormatException {
		checkDateFormat(dateFormat);
		if (isStringEmpty(dateString)) {
			// empty date input
			throw new InvalidInputException("Input date string must be given");
		}

		// check date input
		String[] dateStringArr = dateString.split(Constants.DELIMITER_DATE_FIELD);
		if (isArrayEmpty(dateStringArr) || dateStringArr.length != Constants.NUMBER_CUSTOM_DATE_FIELD) {
			// incorrect field number of date
			throw new InvalidInputException("Invalid date input [" + dateString + "]");
		}

		return getCustomDateDD_MM_YY(dateStringArr);
	}

	/**
	 * Check the logistics of the date<br/>
	 * 
	 * @param dateFieldArr
	 *            string array of date fields
	 * @throws InvalidInputException
	 *             thrown when a date is not logical
	 */
	public static void checkDateLogistics(String[] dateFieldArr) throws InvalidInputException {
		int day = 0;
		int month = 0;
		int year = 0;

		if (dateFieldArr[Constants.INDEX_DATE_FIELD_DAY_DD].length() != 2) {
			throw new InvalidInputException(
					"Invalid day [" + dateFieldArr[Constants.INDEX_DATE_FIELD_DAY_DD] + "], must be in format [DD]");
		}
		if (dateFieldArr[Constants.INDEX_DATE_FIELD_MONTH_MM].length() != 2) {
			throw new InvalidInputException("Invalid month [" + dateFieldArr[Constants.INDEX_DATE_FIELD_MONTH_MM]
					+ "], must be in format [MM]");
		}
		if (dateFieldArr[Constants.INDEX_DATE_FIELD_YEAR_YY].length() != 4) {
			throw new InvalidInputException("Invalid year [" + dateFieldArr[Constants.INDEX_DATE_FIELD_YEAR_YY]
					+ "], must be in format [YYYY]");
		}

		try {
			// parse day
			day = Integer.parseInt(dateFieldArr[Constants.INDEX_DATE_FIELD_DAY_DD]);
		} catch (NumberFormatException e) {
			throw new InvalidInputException("Invalid day for date [" + dateFieldArr[Constants.INDEX_DATE_FIELD_DAY_DD]);
		}
		try {
			// parse month
			month = Integer.parseInt(dateFieldArr[Constants.INDEX_DATE_FIELD_MONTH_MM]);
		} catch (NumberFormatException e) {
			throw new InvalidInputException(
					"Invalid month for date [" + dateFieldArr[Constants.INDEX_DATE_FIELD_MONTH_MM]);
		}
		try {
			// parse year
			year = Integer.parseInt(dateFieldArr[Constants.INDEX_DATE_FIELD_YEAR_YY]);
		} catch (NumberFormatException e) {
			throw new InvalidInputException(
					"Invalid year for date [" + dateFieldArr[Constants.INDEX_DATE_FIELD_YEAR_YY]);
		}

		// validate year
		if (year < Constants.MIN_YEAR_VALUE || year > Constants.MAX_YEAR_VALUE) {
			throw new InvalidInputException("Date year [" + year + "] is out of range");
		}

		// validate month
		if (month < Constants.MIN_MONTH_VALUE || month > Constants.MAX_MONTH_VALUE) {
			throw new InvalidInputException("Date month [" + month + "] is out of range");
		}

		// validate day
		if (day < Constants.MIN_DAY_VALUE || day > Constants.MAX_DAY_VALUE_THIRTY_ONE) {
			throw new InvalidInputException("Date day [" + day + "] is out of range");
		}

		if (month < Constants.NUMBER_MONTH_AUG) {
			// Jan, Feb, Mar, Apr, May, Jun, Jul
			// in this range, single months has 31 days, double months has 30
			// days (Feb excl.)
			if (month == Constants.NUMBER_MONTH_FEB) {
				// Feb in a leap year has 29 days, otherwise 28 days
				boolean isLeap = isLeapYear(year);
				if (isLeap && day > Constants.MAX_DAY_VALUE_FEB_LEAP) {
					throw new InvalidInputException("Date day [" + day + "] is out of range for month [" + month
							+ "] in a leap year [" + year + "]");
				}
				if (!isLeap && day > Constants.MAX_DAY_VALUE_FEB_COMMON) {
					throw new InvalidInputException("Date day [" + day + "] is out of range for month [" + month
							+ "] in a common year [" + year + "]");
				}
			} else {
				if (month % 2 == 0 && day > Constants.MAX_DAY_VALUE_THIRTY) {
					throw new InvalidInputException("Date day [" + day + "] is out of range for month [" + month + "]");
				}
			}
		} else {
			// Aug, Sep, Oct, Nov, Dec
			// in this range, single months has 30 days, double month has 31
			// days
			if (month % 2 == 1 && day > Constants.MAX_DAY_VALUE_THIRTY) {
				throw new InvalidInputException("Date day [" + day + "] is out of range for month [" + month + "]");
			}
		}

	}

	/**
	 * Get custom date in the format of DD MM YY<br/>
	 * 
	 * @param dateFieldArr
	 *            string array of date fields
	 * @return custom date object
	 * @throws InvalidInputException
	 */
	private static CustomDateBean getCustomDateDD_MM_YY(String[] dateFieldArr) throws InvalidInputException {
		CustomDateBean customDate = new CustomDateBean();

		checkDateLogistics(dateFieldArr);

		// parse the date from string
		int day = Integer.parseInt(dateFieldArr[Constants.INDEX_DATE_FIELD_DAY_DD]);
		int month = Integer.parseInt(dateFieldArr[Constants.INDEX_DATE_FIELD_MONTH_MM]);
		int year = Integer.parseInt(dateFieldArr[Constants.INDEX_DATE_FIELD_YEAR_YY]);

		customDate.setDay(day);
		customDate.setMonth(month);
		customDate.setYear(year);
		return customDate;
	}

	/**
	 * Check if the year is a leap year<br/>
	 * 
	 * @param year
	 *            number of the year
	 * @return result of check
	 */
	public static boolean isLeapYear(int year) {
		if (year % 4 == 0) {
			if (year % 100 == 0) {
				// if the year can be evenly divided by 400, leap; otherwise
				// common
				return (year % 400 == 0);
			} else {
				// the year can be evenly divided by 4 but cannot by 100, leap
				return true;
			}
		} else {
			// 4 is not a divisor of the year, common
			return false;
		}
	}

	/**
	 * Check if an array is empty<br/>
	 * 
	 * @param array
	 *            array for check
	 * @return result of check
	 */
	public static boolean isArrayEmpty(Object[] array) {
		if (array == null) {
			return true;
		}

		if (array.length <= 0) {
			return true;
		}

		return false;
	}

	/**
	 * Check whether a string is empty<br/>
	 * 
	 * @param str
	 *            string for check
	 * @return result of check
	 */
	public static boolean isStringEmpty(String str) {
		if (str == null) {
			return true;
		}

		if (str.length() <= 0) {
			return true;
		}

		return false;
	}
}

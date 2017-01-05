package au.com.ioof.beans;

import java.io.Serializable;

/**
 * Custom date bean<br/>
 * 
 * @author hansmong
 *
 */
public class CustomDateBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1611366287713345634L;

	private int day;

	private int month;

	private int year;

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append(String.format("%02d", this.day)).append(" ");
		sb.append(String.format("%02d", this.month)).append(" ");
		sb.append(String.format("%04d", this.year));

		return sb.toString();
	}

	/**
	 * Compare the custom date with this<br/>
	 * 
	 * @param date
	 *            custom date object to be compared
	 * @return 0 for equal, -1 for <code>date</code> greater, 1 for
	 *         <code>this</code> greater
	 */
	public int compare(CustomDateBean date) {
		if (date.getYear() != this.year) {
			return this.year > date.getYear() ? 1 : -1;
		}

		if (date.getMonth() != this.month) {
			return this.month > date.getMonth() ? 1 : -1;
		}

		if (date.getDay() != this.day) {
			return this.day > date.getDay() ? 1 : -1;
		}

		return 0;
	}
}

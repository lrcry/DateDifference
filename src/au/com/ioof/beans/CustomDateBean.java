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

	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append(String.format("%02d", this.day)).append(" ");
		sb.append(String.format("%02d", this.month)).append(" ");
		sb.append(String.format("%04d", this.year));

		return sb.toString();
	}
}

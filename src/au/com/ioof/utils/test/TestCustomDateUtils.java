package au.com.ioof.utils.test;

import static org.junit.Assert.*;

import org.junit.Test;

import au.com.ioof.beans.CustomDateBean;
import au.com.ioof.exceptions.InvalidInputException;
import au.com.ioof.utils.CustomDateUtils;

public class TestCustomDateUtils {

	@Test
	public void testGetCustomDateFromString() {
		try {
			CustomDateBean date = CustomDateUtils.getCustomDateFromString("20 03 1986", "DD MM YYYY");
			System.out.println(date);
		} catch (Exception e) {
			assertNull(e);
		}
	}

	@Test
	public void testCheckDateLogistics() {
		// normal
		try {
			String[] dateFieldArr = new String[] { "21", "07", "1992" };
			CustomDateUtils.checkDateLogistics(dateFieldArr);
		} catch (Exception e) {
			assertNull(e);
		}

		// invalid year
		try {
			String[] dateFieldArr = new String[] { "30", "01", "195" };
			CustomDateUtils.checkDateLogistics(dateFieldArr);
		} catch (Exception e) {
			assertTrue(e instanceof InvalidInputException);
			System.err.println(e.getMessage());
		}
		try {
			String[] dateFieldArr = new String[] { "30", "06", "2020" };
			CustomDateUtils.checkDateLogistics(dateFieldArr);
		} catch (Exception e) {
			assertTrue(e instanceof InvalidInputException);
			System.err.println(e.getMessage());
		}
		try {
			String[] dateFieldArr = new String[] { "24", "01", "1024" };
			CustomDateUtils.checkDateLogistics(dateFieldArr);
		} catch (Exception e) {
			assertTrue(e instanceof InvalidInputException);
			System.err.println(e.getMessage());
		}

		// invalid month
		try {
			String[] dateFieldArr = new String[] { "30", "0", "2000" };
			CustomDateUtils.checkDateLogistics(dateFieldArr);
		} catch (Exception e) {
			assertTrue(e instanceof InvalidInputException);
			System.err.println(e.getMessage());
		}
		try {
			String[] dateFieldArr = new String[] { "30", "13", "2000" };
			CustomDateUtils.checkDateLogistics(dateFieldArr);
		} catch (Exception e) {
			assertTrue(e instanceof InvalidInputException);
			System.err.println(e.getMessage());
		}
		try {
			String[] dateFieldArr = new String[] { "21", "00", "2000" };
			CustomDateUtils.checkDateLogistics(dateFieldArr);
		} catch (Exception e) {
			assertTrue(e instanceof InvalidInputException);
			System.err.println(e.getMessage());
		}

		// invalid day
		try {
			String[] dateFieldArr = new String[] { "1", "01", "2000" };
			CustomDateUtils.checkDateLogistics(dateFieldArr);
		} catch (Exception e) {
			assertTrue(e instanceof InvalidInputException);
			System.err.println(e.getMessage());
		}
		try {
			String[] dateFieldArr = new String[] { "32", "01", "2000" };
			CustomDateUtils.checkDateLogistics(dateFieldArr);
		} catch (Exception e) {
			assertTrue(e instanceof InvalidInputException);
			System.err.println(e.getMessage());
		}
		try {
			String[] dateFieldArr = new String[] { "00", "01", "2000" };
			CustomDateUtils.checkDateLogistics(dateFieldArr);
		} catch (Exception e) {
			assertTrue(e instanceof InvalidInputException);
			System.err.println(e.getMessage());
		}
		try {
			String[] dateFieldArr = new String[] { "29", "02", "2000" };
			CustomDateUtils.checkDateLogistics(dateFieldArr);
		} catch (Exception e) {
			assertNull(e);
		}
		try {
			String[] dateFieldArr = new String[] { "28", "02", "2001" };
			CustomDateUtils.checkDateLogistics(dateFieldArr);
		} catch (Exception e) {
			assertNull(e);
		}
		try {
			String[] dateFieldArr = new String[] { "29", "02", "2001" };
			CustomDateUtils.checkDateLogistics(dateFieldArr);
		} catch (Exception e) {
			assertTrue(e instanceof InvalidInputException);
			System.err.println(e.getMessage());
		}
		try {
			String[] dateFieldArr = new String[] { "31", "06", "2001" };
			CustomDateUtils.checkDateLogistics(dateFieldArr);
		} catch (Exception e) {
			assertTrue(e instanceof InvalidInputException);
			System.err.println(e.getMessage());
		}
		try {
			String[] dateFieldArr = new String[] { "30", "08", "2001" };
			CustomDateUtils.checkDateLogistics(dateFieldArr);
		} catch (Exception e) {
			assertNull(e);
		}
		try {
			String[] dateFieldArr = new String[] { "31", "09", "2001" };
			CustomDateUtils.checkDateLogistics(dateFieldArr);
		} catch (Exception e) {
			assertTrue(e instanceof InvalidInputException);
			System.err.println(e.getMessage());
		}
	}

	@Test
	public void testIsLeapYear() {
		assertTrue(CustomDateUtils.isLeapYear(2000));
		assertTrue(CustomDateUtils.isLeapYear(1996));
		assertFalse(CustomDateUtils.isLeapYear(1900));
	}

	@Test
	public void testIsArrayEmpty() {
		String[] arrNull = null;
		String[] arrEmpty = new String[] {};
		String[] arrFull = new String[] { "a", "b", "c" };
		assertTrue(CustomDateUtils.isArrayEmpty(arrNull));
		assertTrue(CustomDateUtils.isArrayEmpty(arrEmpty));
		assertFalse(CustomDateUtils.isArrayEmpty(arrFull));
	}

	@Test
	public void testIsStringEmpty() {
		String strNull = null;
		String strEmpty = "";
		String str = "Hello world";
		assertTrue(CustomDateUtils.isStringEmpty(strNull));
		assertTrue(CustomDateUtils.isStringEmpty(strEmpty));
		assertFalse(CustomDateUtils.isStringEmpty(str));
	}

}

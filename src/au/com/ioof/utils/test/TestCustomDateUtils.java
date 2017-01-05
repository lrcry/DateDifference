package au.com.ioof.utils.test;

import static org.junit.Assert.*;

import org.junit.Test;

import au.com.ioof.utils.CustomDateUtils;

public class TestCustomDateUtils {

	@Test
	public void testGetCustomDateFromString() {
		// fail("Not yet implemented");
	}

	@Test
	public void testCheckDateLogistics() {
		// fail("Not yet implemented");
	}

	@Test
	public void testIsLeapYear() {
		assertTrue(CustomDateUtils.isLeapYear(2000));
		assertTrue(CustomDateUtils.isLeapYear(1996));
		assertFalse(CustomDateUtils.isLeapYear(1900));
	}

	@Test
	public void testIsArrayEmpty() {
		// fail("Not yet implemented");
	}

	@Test
	public void testIsStringEmpty() {
		// fail("Not yet implemented");
	}

}

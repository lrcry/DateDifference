package au.com.ioof.beans.test;

import static org.junit.Assert.*;

import org.junit.Test;

import au.com.ioof.beans.CustomDateBean;

public class TestCustomDateBean {

	@Test
	public void testCompare() {
		CustomDateBean d1 = new CustomDateBean();
		CustomDateBean d2 = new CustomDateBean();
		d1.setDay(03);
		d1.setMonth(02);
		d1.setYear(1994); // 03 02 1994
		d2.setDay(02);
		d2.setMonth(03);
		d2.setYear(1995); // 02 03 1995
		assertTrue(d1.compare(d2) < 0);

		d2.setDay(03);
		d2.setMonth(03);
		d2.setYear(1994); // 03 03 1994
		assertTrue(d1.compare(d2) < 0);

		d2.setDay(01);
		d2.setMonth(02);
		d2.setYear(1994); // 01 02 1994
		assertTrue(d1.compare(d2) > 0);

		d2.setDay(03); // 03 02 1994
		assertTrue(d1.compare(d2) == 0);
	}

}

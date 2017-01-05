package au.com.ioof.commons.test;

import java.util.Map;
import java.util.Map.Entry;

import org.junit.Test;

import au.com.ioof.commons.Constants;

public class TestConstants {

	@Test
	public void test() {
		for (Entry<String, Map<Integer, Integer>> eYear : Constants.YEAR_MONTH_DAYS_MAP.entrySet()) {
			System.out.println("======== " + eYear.getKey() + " Year ========");
			int daysTotal = 0;
			for (Entry<Integer, Integer> eMonth : eYear.getValue().entrySet()) {
				System.out.println(eMonth.getKey() + " " + eMonth.getValue());
				daysTotal += eMonth.getValue();
			}
			System.out.println("++++++++ " + eYear.getKey() + " Year has " + daysTotal + " days in total ++++++++");
		}
	}

}

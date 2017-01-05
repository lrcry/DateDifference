package au.com.ioof.main;

import java.util.Scanner;

import au.com.ioof.beans.CustomDateBean;
import au.com.ioof.commons.Constants;
import au.com.ioof.exceptions.DateDifferenceException;
import au.com.ioof.exceptions.InvalidInputException;
import au.com.ioof.utils.CustomDateUtils;

/**
 * Main runnable for date difference<br/>
 * 
 * @author hansmong
 *
 */
public class DateDifferenceMain {
	public static void main(String[] args) {
		Scanner stdInScanner = new Scanner(System.in);
		try {
			while (stdInScanner.hasNext()) {
				try {
					long dateDiff = 0;
					String input = stdInScanner.nextLine();
					if (CustomDateUtils.isStringEmpty(input.trim())) {
						// ignore empty lines
						continue;
					}

					String[] inputDateArr = input.split(",");
					if (CustomDateUtils.isArrayEmpty(inputDateArr) || inputDateArr.length != 2) {
						// invalid input
						throw new InvalidInputException("Invalid input [" + input + "], should follow the pattern ["
								+ Constants.DATE_FORMAT_DD_MM_YYYY + "]");
					}

					CustomDateBean dateOne = CustomDateUtils.getCustomDateFromString(inputDateArr[0].trim(),
							Constants.DATE_FORMAT_DD_MM_YYYY);
					CustomDateBean dateTwo = CustomDateUtils.getCustomDateFromString(inputDateArr[1].trim(),
							Constants.DATE_FORMAT_DD_MM_YYYY);
					// System.out.println(dateOne);
					// System.out.println(dateTwo);

					int dateCompare = dateTwo.compare(dateOne);
					if (dateCompare < 0) {
						// dateTwo is earlier
						dateDiff = CustomDateUtils.getDatesDifference(dateOne, dateTwo);
						System.out.println(dateTwo + ", " + dateOne + ", " + dateDiff);
					} else if (dateCompare > 0) {
						// dateOne is earlier
						dateDiff = CustomDateUtils.getDatesDifference(dateTwo, dateOne);
						System.out.println(dateOne + ", " + dateTwo + ", " + dateDiff);
					} else {
						// two dates are the same
						dateDiff = 0;
						System.out.println(dateOne + ", " + dateTwo + ", " + dateDiff);
					}

				} catch (Exception e) {
					if (e instanceof DateDifferenceException) {
						System.err.println(e.getMessage());
					} else {
						e.printStackTrace();
						return;
					}
				}
			}
		} finally {
			// release resources
			if (stdInScanner != null) {
				stdInScanner.close();
			}
		}
	}
}

# Date Difference

## Author

Hans Mong (Ran Meng)

## Date

5 Jan 2017

## Deploy & Run

### In IDE (Eclipse)

* Use Eclipse => Import => Existing projects into workspace to import the project

* Run `au.com.ioof.main.DateDifferenceMain` as a Java Application

* Type the data in an Eclipse Console to see the results

### Under Unix Terminal

* Go to the project root directory

* Execute `find src/ -name \*.java | grep -v test > allFiles.txt` to get all the non-test java files of the project

* Execute `mkdir -p classes && javac @allFiles.txt -d classes/` to compile the project

* Execute `java -cp classes/` to run the application with input from standard input

* Execute `cat testInput.data | java -cp classes/ au.com.ioof.main.DateDifferenceMain` to run the application with input from a file `testInput.txt`

## Original Problem Spec

Description:

Create an application that can read in pairs of dates in the following
format -

    DD MM YYYY, DD MM YYYY

Validate the input data, and compute the difference between the two dates
in days.

Output of the application should be of the form -

    DD MM YYYY, DD MM YYYY, difference

Where the first date is the earliest, the second date is the latest and the difference is the number of days.

Input can be from a file, or from standard input, as the developer chooses.

Provide test data to exercise the application.

Constraints:

The application may not make use of the platform / SDK libraries for date manipulation
(for example Date, Calendar classes).

The application can limit calculation on an input range of dates from 1900 to 2010


Deliverables:

The source files, the test data and any test code.

package safety.net.alerts.util;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Age Calculator class to calculate age of the person and check if he is a
 * child
 *
 */
public class AgeCalculator {

	/**
	 * Age calculation ** this method allows to calculate a age
	 * 
	 * @param birthdate string in format MM/dd/yyyy
	 * @return age
	 * @throws Exception
	 */
	public static int agePerson(String birthdate) throws Exception {
		final Logger logger = LogManager.getLogger("AgeCalculator");

		/**
		 * Convert birthdate string to date
		 */
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		LocalDate personsBirthdate = LocalDate.parse(birthdate, formatter);
		LocalDate currentDate = LocalDate.now();

		int age = Period.between(personsBirthdate, currentDate).getYears();

		if (personsBirthdate.isAfter(currentDate)) {
			logger.error("Person not yet born !!");
			throw new Exception("Error, birthdate after current date !");
		}
		if(age ==0) {
			logger.info("Baby before one year");
			age++;
		}

		return age;
	}

	/**
	 * is child ** this method allows to check if the person is a child
	 * 
	 * @param birthdate
	 * @return true if child false otherwise
	 * @throws Exception
	 */
	public static boolean isChild(String birthdate) throws Exception {
		int age = agePerson(birthdate);
		if (age <= 18) {
			return true;
		}
		return false;

	}
}

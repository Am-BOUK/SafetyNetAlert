package safety.net.alerts.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import safety.net.alerts.entities.Person;
import safety.net.alerts.service.ISafetyNetAlertMetier;

/**
 * Implementing the management of interactions between the application user and
 * the application.
 *
 */
@RestController
public class SafetyNetAlertRestService {
	private static final Logger logger = LogManager.getLogger("SafetyNetAlertRestService");

	/**
	 * implementation of safety net alerts business processing
	 * 
	 */
	@Autowired
	private ISafetyNetAlertMetier safetyNetAlertMetier;

	/**
	 * Get a list of person and count of the number of adults and the number of
	 * children covered by station number
	 * 
	 * @param first name and last name of the person whose we want to get
	 * @return a list of person. The list must include: first name, last name,
	 *         address, phone number. a count of the number of adults and the number
	 *         of children
	 * @throws Exception
	 */
//	http://localhost:8080/firestation?stationNumber=<station_number>
	@RequestMapping(value = "/firestation", method = RequestMethod.GET)
	public Map<String, List<String>> getAllPersonsByStationNumber(@RequestParam int stationNumber) throws Exception {

		return safetyNetAlertMetier.getAllPersonsByStationNumber(stationNumber);
	}

	/**
	 * Get a list of children information And his family member by address
	 * 
	 * @param address
	 * @return a list of children. The list must include: first name, last name, age
	 *         and a list of other household members
	 * @throws Exception
	 */
	// http://localhost:8080/childAlert?address=<address>
	@RequestMapping(value = "/childAlert", method = RequestMethod.GET)
	public Map<String, List<Person>> getchildrendAndFamilyMemberByAddress(@RequestParam String address)
			throws Exception {
		return safetyNetAlertMetier.getchildrendAndFamilyMemberByAddress(address);
	}

	/**
	 * get All Phone Numbers By FireStation number
	 * 
	 * @param station number of the fire station
	 * @return list of the phone numbers of the persons covered by the corresponding
	 *         fire station
	 * @throws Exception
	 */
	// http://localhost:8080/phoneAlert?firestation=<firestation_number>
	@RequestMapping(value = "/phoneAlert", method = RequestMethod.GET)
	public Map<String, List<String>> getAllPhoneNumbersByFireStationNumber(@RequestParam int firestation)
			throws Exception {
		return safetyNetAlertMetier.getAllPhoneNumbersByFireStationNumber(firestation);

	}

	/**
	 * get list of persons, station number And medical record by Address
	 * 
	 * @param address
	 * @return list of persons. The list must include: first name, last name, phone
	 *         number, age and medical record
	 * @throws Exception
	 */
	// http://localhost:8080/fire?address=<address>
	@RequestMapping(value = "/fire", method = RequestMethod.GET)
	public Map<String, List<String>> getAllPersonsAndStationNumberAndMedicalRecordByAddress(
			@RequestParam String address) throws Exception {
		return safetyNetAlertMetier.getAllPersonsAndStationNumberAndMedicalRecordByAddress(address);
	}

	/**
	 * Get a list of all the addresses served by the list of fire station numbers
	 * 
	 * 
	 * @param station
	 * @return list of address. The list must include: first name, last name, phone
	 *         number, age and their medical record
	 * @throws Exception
	 */
	// http://localhost:8080/flood/stations?stations=<a list of station_numbers>
	@RequestMapping(value = "/flood/stations", method = RequestMethod.GET)
	public Map<String, List<String>> getAllPersonsByListOfStationNumber(@RequestParam int[] stations) throws Exception {
		return safetyNetAlertMetier.getAllPersonsByListOfStationNumber(stations);
	}

	/**
	 * get list of persons info by first name and last name
	 *
	 * @param firstName the first name of the person we want to get his information
	 * @param lastName  the last name of the person we want to get his information
	 * 
	 * @return list of persons info. The list must include: first name, last name,
	 *         address, age, email address and medical record
	 * @throws Exception
	 */

	// http://localhost:8080/personInfo?firstName=<firstName>&lastName=<lastName>
	@RequestMapping(value = "/personInfo", method = RequestMethod.GET)
	public Map<String, List<String>> getPersonsInfoByFirstNameAndLastName(@RequestParam String firstName,
			@RequestParam String lastName) throws Exception {
		return safetyNetAlertMetier.getPersonsInfoByFirstNameAndLastName(firstName, lastName);
	}

	/**
	 * Getting a list of all email addresses by the city
	 *
	 * @param city the
	 * 
	 * @return list of email
	 * @throws Exception
	 */
	// http://localhost:8080/communityEmail?city=<city>
	@RequestMapping(value = "/communityEmail", method = RequestMethod.GET)
	public Map<String, List<String>> getAllEmailsByCity(@RequestParam String city) throws Exception {
		return safetyNetAlertMetier.getAllEmailsByCity(city);
	}

	/**
	 * Handle specified types of exceptions ** Processing the conflict errors:
	 * 
	 * @param ex argument of method not valid
	 * @return message of errors
	 */
	@ResponseStatus(HttpStatus.CONFLICT)
	@ExceptionHandler(Exception.class)
	public Map<String, String> handleExceptions(Exception ex) {
		Map<String, String> errors = new HashMap<>();
		String fieldName = "";
		String errorMessage = ex.getMessage();
		errors.put(fieldName, errorMessage);

		logger.info("the specified Person object is invalid : " + errors);
		return errors;
	}
}

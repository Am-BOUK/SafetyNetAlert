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

@RestController
public class SafetyNetAlertRestService {
	private static final Logger logger = LogManager.getLogger("SafetyNetAlertRestService");

	@Autowired
	private ISafetyNetAlertMetier safetyNetAlertMetier;

//	http://localhost:8080/firestation?stationNumber=<station_number>
	@RequestMapping(value = "/firestation", method = RequestMethod.GET)
	public Map<String, List<String>> getAllPersonsByStationNumber(@RequestParam int stationNumber) throws Exception {

		return safetyNetAlertMetier.getAllPersonsByStationNumber(stationNumber);
	}

	// http://localhost:8080/childAlert?address=<address>
	@RequestMapping(value = "/childAlert", method = RequestMethod.GET)
	public Map<String, List<Person>> getchildrendAndFamilyMemberByAddress(@RequestParam String address)
			throws Exception {
		return safetyNetAlertMetier.getchildrendAndFamilyMemberByAddress(address);
	}

	// http://localhost:8080/phoneAlert?firestation=<firestation_number>
	@RequestMapping(value = "/phoneAlert", method = RequestMethod.GET)
	public Map<String, List<String>> getAllPhoneNumbersByFireStationNumber(@RequestParam int firestation) throws Exception {
		return safetyNetAlertMetier.getAllPhoneNumbersByFireStationNumber(firestation);

	}

	// http://localhost:8080/fire?address=<address>
	@RequestMapping(value = "/fire", method = RequestMethod.GET)
	public Map<String, List<String>> getAllPersonsAndStationNumberAndMedicalRecordByAddress(
			@RequestParam String address) throws Exception {
		return safetyNetAlertMetier.getAllPersonsAndStationNumberAndMedicalRecordByAddress(address);
	}

	// http://localhost:8080/flood/stations?stations=<a list of station_numbers>
	@RequestMapping(value = "/flood/stations", method = RequestMethod.GET)
	public Map<String, List<String>> getAllPersonsByListOfStationNumber(@RequestParam int[] stations) throws Exception {
		return safetyNetAlertMetier.getAllPersonsByListOfStationNumber(stations);
	}

	// http://localhost:8080/personInfo?firstName=<firstName>&lastName=<lastName>
	@RequestMapping(value = "/personInfo", method = RequestMethod.GET)
	public Map<String, List<String>> getPersonsInfoByFirstNameAndLastName(@RequestParam String firstName,
			@RequestParam String lastName) throws Exception {
		return safetyNetAlertMetier.getPersonsInfoByFirstNameAndLastName(firstName, lastName);
	}

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

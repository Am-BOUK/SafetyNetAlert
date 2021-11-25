package safety.net.alerts.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import safety.net.alerts.entities.FireStation;
import safety.net.alerts.entities.FireStationDTO;
import safety.net.alerts.service.IFireStationMetier;

/**
 * Implementing the management of interactions between the application user and
 * the application.
 *
 */

@RestController
public class FireStationRestService {
	private static final Logger logger = LogManager.getLogger("PersonRestService");

	/**
	 * implementation of fire station business processing
	 * 
	 */
	@Autowired
	private IFireStationMetier fireStationMetier;

	/**
	 * Read-Get one fire station
	 * 
	 * @param address of the fire station to get
	 * @return a fireStation object full filled
	 * @throws Exception
	 */
	@RequestMapping(value = "/fireStation", method = RequestMethod.GET)
	public FireStation getFireStation(@RequestParam String address) throws Exception {
		logger.info("Get fire station record for address : " + address);
		return fireStationMetier.getFireStation(address);
	}

	/**
	 * Read-Get List of all fire station by address
	 * 
	 * @param address the address of fire station to get
	 * @return a list of fire station full filled
	 * @throws Exception
	 */
	@RequestMapping(value = "/fireStationsList", method = RequestMethod.GET)
	public List<FireStation> getListFireStationByAddress(@RequestParam String address) throws Exception {
		return fireStationMetier.getListFireStationByAddress(address);
	}

	/**
	 * Create - Add a new fire station
	 * 
	 * @param fire station object
	 * @return The fire station object added
	 * @throws Exception
	 */
	@RequestMapping(value = "/fireStation", method = RequestMethod.POST)
	public FireStation addFireStation(@Valid @RequestBody FireStation fireStation) throws Exception {
		logger.info("Adding fire station : " + fireStation);
		return fireStationMetier.addFireStation(fireStation);

	}

	/**
	 * Update - update an existing fire station
	 * 
	 * @param address        of the fire station to update
	 * @param fireStationDto the fire station data transfer object updated
	 * @return fireStation the fire station object updated
	 * @throws Exception
	 */
	@RequestMapping(value = "/fireStation", method = RequestMethod.PUT)
	public FireStation updateFireStation(@RequestParam String address, @RequestBody FireStationDTO fireStationDTO)
			throws Exception {
		logger.info("Updating fire station of the address :" + address);
		return fireStationMetier.updateFireStation(address, fireStationDTO);
	}

	/**
	 * Delete - delete a fire station by address
	 * 
	 * @param address the address of the fire station to delete
	 * @throws Exception
	 */
	@RequestMapping(value = "/fireStation", method = RequestMethod.DELETE)
	public void deleteFireStation(@RequestParam String address) throws Exception {
		logger.info("Deleting fire station : " + address);
		fireStationMetier.deleteFireStation(address);

	}

	/**
	 * Read - Get all fire stations
	 * 
	 * @return list of fire stations
	 * @throws Exception
	 */
	@RequestMapping(value = "/fireStations", method = RequestMethod.GET)
	public List<FireStation> getAllFireStations() throws Exception {
		logger.info("Getting all fire stations");
		return fireStationMetier.getAllFireStations();
	}

	/**
	 * Handle specified types of exceptions ** Processing the validation errors:
	 * 
	 * @param ex argument of method not valid
	 * @return
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		logger.info("the specified fire station object is invalid : " + errors);
		return errors;
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

		logger.info("the specified fire station object is invalid : " + errors);
		return errors;
	}
}

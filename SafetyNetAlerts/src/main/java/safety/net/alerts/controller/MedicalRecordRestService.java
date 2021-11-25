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

import safety.net.alerts.entities.MedicalRecord;
import safety.net.alerts.entities.MedicalRecordDTO;
import safety.net.alerts.service.IMedicalRecordMetier;

/**
 * Implementing the management of interactions between the application user and
 * the application.
 *
 */
@RestController
public class MedicalRecordRestService {
	private static final Logger logger = LogManager.getLogger("PersonRestService");

	@Autowired
	private IMedicalRecordMetier medicalRecordMetier;

	/**
	 * Read-Get one medical record **This operation allows to get the information
	 * for the medical record
	 * 
	 * @param firstName the first name of the person we want to get its medical
	 *                  record
	 * @param lastName  the last name of the person we want to get its medical
	 *                  record
	 * @return a medical record object full filled
	 * @throws Exception
	 */
	@RequestMapping(value = "/medicalRecord", method = RequestMethod.GET)
	public MedicalRecord getMedicalRecord(@RequestParam String firstName, @RequestParam String lastName)
			throws Exception {
		logger.info("Getting medical record of the person: " + firstName + " " + lastName);
		return medicalRecordMetier.getMedicalRecord(firstName, lastName);
	}

	/**
	 * Read-Get List of medical record by first name and last name
	 * 
	 * @param firstName the first name of the person we want to get its medical
	 *                  record
	 * @param lastName  the last name of the person we want to get its medical
	 *                  record
	 * @return a list of person full filled
	 * @throws Exception
	 */
	@RequestMapping(value = "/medicalRecordsList", method = RequestMethod.GET)
	public List<MedicalRecord> getListPersonByFirstNameAndLastName(@RequestParam String firstName,
			@RequestParam String lastName) throws Exception {
		return medicalRecordMetier.getListMedicalRecord(firstName, lastName);
	}

	/**
	 * Create - Add a new medical record **This operation allows to add a new
	 * medical record
	 * 
	 * @param medicalRecord a medical record object
	 * @return The medical record object added
	 * @throws Exception
	 */
	@RequestMapping(value = "/medicalRecord", method = RequestMethod.POST)
	public MedicalRecord addMedicalRecord(@Valid @RequestBody MedicalRecord medicalRecord) throws Exception {
		logger.info("Adding medical record : " + medicalRecord);
		return medicalRecordMetier.addMedicalRecord(medicalRecord);

	}

	/**
	 * Update information of the medical record ** This operation allows to update
	 * the information of the medical record
	 * 
	 * 
	 * @param firstName the first name of the person we want to update its medical
	 *                  record
	 * @param lastName  the last name of the person we want to update its medical
	 *                  record
	 * @param personDto the medical record data transfer object modified
	 * @return medicalRecord the medical record updated
	 * @throws Exception
	 */
	@RequestMapping(value = "/medicalRecord", method = RequestMethod.PUT)
	public MedicalRecord updateMedicalRecord(@RequestParam String firstName, @RequestParam String lastName,
			@RequestBody MedicalRecordDTO medicalRecordDTO) throws Exception {
		logger.info("Update the medical record of : " + firstName + " " + lastName);
		return medicalRecordMetier.updateMedicalRecord(firstName, lastName, medicalRecordDTO);
	}

	/**
	 * Delete the medical record ** This operation allows to to delete the medical
	 * record object in the given index
	 * 
	 * @param index of the medical record object to delete
	 * 
	 */
	@RequestMapping(value = "/medicalRecord", method = RequestMethod.DELETE)
	public void deleteMedicalRecord(@RequestParam String firstName, @RequestParam String lastName) throws Exception {
		logger.info("Delete medical record of : " + firstName + " " + lastName);
		medicalRecordMetier.deleteMedicalRecord(firstName, lastName);
	}

	/**
	 * Read - Get all medical records **This method allows to get all medical
	 * records exist in json file
	 * 
	 * @return list of medical records
	 * @throws Exception
	 */
	@RequestMapping(value = "/medicalRecords", method = RequestMethod.GET)
	public List<MedicalRecord> getAllMedicalRecord() throws Exception {
		logger.info("Getting list of all medical records !");
		return medicalRecordMetier.getAllMedicalRecords();
	}

	/**
	 * Handle specified types of exceptions ** Processing the validation errors:
	 * 
	 * @param ex argument of method not valid
	 * @return message errors
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
		logger.info("the specified medical record object is invalid : " + errors);
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

		logger.info("the specified medical record object is invalid : " + errors);
		return errors;
	}

}

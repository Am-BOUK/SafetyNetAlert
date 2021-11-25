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

import safety.net.alerts.entities.Person;
import safety.net.alerts.entities.PersonDTO;
import safety.net.alerts.service.IPersonMetier;

/**
 * Implementing the management of interactions between the application user and
 * the application.
 *
 */
@RestController
public class PersonRestService {
	private static final Logger logger = LogManager.getLogger("PersonRestService");

	/**
	 * implementation of person business processing
	 * 
	 */
	@Autowired
	private IPersonMetier personMetier;

	/**
	 * Read-Get one person
	 * 
	 * @param firstName the first name of the person
	 * @param lastName  the last name of the person
	 * @return a person object full filled
	 * @throws Exception
	 */
	@RequestMapping(value = "/person", method = RequestMethod.GET)
	public Person getPerson(@RequestParam String firstName, @RequestParam String lastName) throws Exception {
		return personMetier.getPerson(firstName, lastName);

	}

	/**
	 * Read-Get List of person by first name and last name
	 * 
	 * @param firstName the first name of the person
	 * @param lastName  the last name of the person
	 * @return a list of person full filled
	 * @throws Exception
	 */
	@RequestMapping(value = "/personsList", method = RequestMethod.GET)
	public List<Person> getListPersonByFirstNameAndLastName(@RequestParam String firstName, @RequestParam String lastName) throws Exception {
		return personMetier.getListPerson(firstName, lastName);
	}

	/**
	 * Create - Add a new person
	 * 
	 * @param person a person object
	 * @return The person object added
	 * @throws Exception
	 */
	@RequestMapping(value = "/person", method = RequestMethod.POST)
	public Person addPerson(@Valid @RequestBody Person person) throws Exception {

		return personMetier.addPerson(person);

	}

	/**
	 * Update - update an existing person
	 * 
	 * @param firstName the first name of the person to update
	 * @param lastName  the last name of the person to update
	 * @param personDto the person data transfer object updated
	 * @return person the person updated
	 * @throws Exception
	 */
	@RequestMapping(value = "/person", method = RequestMethod.PUT)
	public Person updatePerson(@RequestParam String firstName, @RequestParam String lastName,
			@RequestBody PersonDTO personDto) throws Exception {
		return personMetier.updatePerson(firstName, lastName, personDto);

	}

	/**
	 * Delete - delete a person
	 * 
	 * @param firstName the first name of the person to delete
	 * @param lastName  the last name of the person to delete
	 * @throws Exception
	 */
	@RequestMapping(value = "/person", method = RequestMethod.DELETE)
	public void deletePersonByName(@RequestParam String firstName, @RequestParam String lastName) throws Exception {
		personMetier.deletePerson(firstName, lastName);
	}

	/**
	 * Read - Get all persons
	 * 
	 * @return list of persons
	 * @throws Exception 
	 */
	@RequestMapping(value = "/persons", method = RequestMethod.GET)
	public List<Person> getAllPersons() throws Exception {
			logger.info("Getting All Persons");
			return personMetier.getAllPersons();
		

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
		logger.info("the specified Person object is invalid : " + errors);
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

		logger.info("the specified Person object is invalid : " + errors);
		return errors;
	}
}
package safety.net.alerts.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import safety.net.alerts.dao.IPersonRepository;
import safety.net.alerts.entities.FireStation;
import safety.net.alerts.entities.Person;
import safety.net.alerts.entities.PersonDTO;

/**
 * implementation of person business processing
 * 
 */
@Service
public class PersonMetierImpl implements IPersonMetier {

	private static final Logger logger = LogManager.getLogger("PersonMetierImpl");

	/**
	 * Calling operations of the person repository
	 */
	@Autowired
	private IPersonRepository personRepository;

	/**
	 * Get One person object ** This operation allows to check if the first name and
	 * last name of the person we want to get its information already exist in the
	 * json file, then allows to use its index to get him
	 * 
	 * @param first name and last name of the person whose we want to get
	 * @return person object if it exists
	 * @throws Exception
	 */
	@Override
	public Person getPerson(String firstName, String lastName) throws Exception {
		logger.info("Get person : " + firstName + " " + lastName);
		Map<Integer, Person> personMap = personRepository.findPersonByFirstNameAndLastName(firstName, lastName);
		int index = -1;
		if (personMap.size() > 0) {
			index = personMap.keySet().stream().findFirst().get();
			logger.info("Getting : " + personMap.values());
			return personRepository.getPerson(index);
		} else {
			logger.info("The person " + firstName + " " + lastName + ", you want to get, does not exist !");
			throw new RuntimeException(
					"The person " + firstName + " " + lastName + ", you want to get, does not exist !");
		}

	}

	/**
	 * Get List of person by its first name and last name ** This operation allows
	 * to check if the first name and last name of the person we want to get its
	 * information already exist in the json file, then allows to print all persons
	 * contain the first and last name in question
	 *
	 * 
	 * @param first name and last name of all person whose we want to get
	 * @return List of persons if it exists
	 * @throws Exception
	 */
	@Override
	public List<Person> getListPerson(String firstName, String lastName) throws Exception {
		logger.info(
				"Get list of all person contains: " + firstName + " as first name and " + lastName + " as last name");
		List<Person> persons = personRepository.getAllPersons();
		List<Person> personList = new ArrayList<>();

		for (Person p : persons) {
			if (p.getFirstName().toLowerCase().contains(firstName.toLowerCase())
					&& p.getLastName().toLowerCase().contains(lastName.toLowerCase())) {
				personList.add(p);

			}
		}
		if (personList.isEmpty()) {
			logger.info("The list of person " + firstName + " " + lastName + ", you want to get, is empty !");
			throw new RuntimeException(
					"The list of person " + firstName + " " + lastName + ", you want to get, is empty !");
		}
		return personList;

	}

	/**
	 * Add a new person ** This operation allows to check if the first name and last
	 * name of the person we want to add already exist in the json file, then allows
	 * to add it
	 * 
	 * @param person object to add
	 * @return person object added
	 * @throws Exception
	 */
	@Override
	public Person addPerson(Person person) throws Exception {
		logger.info("Add a new person : " + person);
		Map<Integer, Person> personMap = personRepository.findPersonByFirstNameAndLastName(person.getFirstName(),
				person.getLastName());
		if (personMap.isEmpty()) {
			logger.info("Adding new person : " + person);
			return personRepository.addPerson(person);
		} else {
			logger.info("The person " + person.getFirstName() + " " + person.getLastName()
					+ " you want to add, already exists !");
			throw new RuntimeException("The person " + person.getFirstName() + " " + person.getLastName()
					+ " you want to add, already exists !");
		}
	}

	/**
	 * Update information of the person ** This operation allows to check if the
	 * first name and last name of the person we want to update already exist in the
	 * json file, then allows to use its index to update the information of the
	 * person
	 * 
	 * @param first name, last name of the person we want to update and data
	 *              transfer object of the modified person
	 * @return person object updated
	 * @throws Exception
	 */
	@Override
	public Person updatePerson(String firstName, String lastName, PersonDTO personDto) throws Exception {
		logger.info("Update person ");
		Map<Integer, Person> personMap = personRepository.findPersonByFirstNameAndLastName(firstName, lastName);
		int index = -1;
		Person personFound = null;
		if (personMap.size() > 0) {
			index = personMap.keySet().stream().findFirst().get();
//			Person personFound = personMap.entrySet().stream().findFirst().get(); //ne fonctionne pas!!
			personFound = personMap.get(index);

			String address = personDto.getAddress();
			if (address != null) {
				personFound.setAddress(address);
			}

			personFound.setCity(personDto.getCity() != null ? personDto.getCity() : personFound.getAddress());
			personFound.setEmail(personDto.getEmail() != null ? personDto.getEmail() : personFound.getEmail());
			personFound.setPhone(personDto.getPhone() != null ? personDto.getPhone() : personFound.getPhone());
			personFound.setZip(personDto.getZip() != null ? personDto.getZip() : personFound.getZip());

			logger.info("Updating : " + personFound);
			return personRepository.updatePerson(index, personFound);
		} else {
			logger.info("The person " + firstName + " " + lastName + " you want to update, does not exist !");
			throw new RuntimeException(
					"The person " + firstName + " " + lastName + " you want to update, does not exist !");
		}

	}

	/**
	 * Delete the person ** This operation allows to check if the first name and
	 * last name of the person we want to delete already exist in the json file,
	 * then allows to use its index to delete it
	 * 
	 * @param first name and last name of the person whose we want to delete
	 * @throws Exception
	 */
	@Override
	public void deletePerson(String firstName, String lastName) throws Exception {
		logger.info("Delete person  : " + firstName + " " + lastName);
		Map<Integer, Person> personMap = personRepository.findPersonByFirstNameAndLastName(firstName, lastName);
		int index = -1;
		if (personMap.size() > 0) {
			index = personMap.keySet().stream().findFirst().get();
			logger.info("Deleting person : " + firstName + " " + lastName);
			personRepository.deletePerson(index);
			return;
		} else {
			logger.info("The person " + firstName + " " + lastName + " you want to delete, does not exist !");
			throw new RuntimeException(
					"The person " + firstName + " " + lastName + " you want to delete, does not exist !");
		}
	}

	/**
	 * Print all persons
	 * 
	 * @return list of all persons existing in the json file
	 */
	@Override
	public List<Person> getAllPersons() throws Exception {
		logger.info("get list of all persons ");
		return personRepository.getAllPersons();

	}

}

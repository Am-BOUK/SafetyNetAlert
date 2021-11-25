package safety.net.alerts.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import safety.net.alerts.entities.FireStation;
import safety.net.alerts.entities.Person;
import safety.net.alerts.entities.SafetyNet;

/**
 * Implementation of person repository
 * 
 */
@Repository
public class PersonRepositoryImpl implements IPersonRepository {

	private static final Logger logger = LogManager.getLogger("PersonRepositoryImpl");
	/**
	 * Calling serialize and deserialize methods of the safety net repository
	 */
	@Autowired
	private ISafetyNetRepository safetyNetRepository;

	/**
	 * Find a person by his first name and last name ** This operation allows to
	 * check if the first name and last name of the person we want to find already
	 * exist in the json file, then returns a map of its index as key and its person
	 * object as value
	 * 
	 * @param first name and last name of the person to find
	 * @return Map of index as key and person object as value
	 * @throws Exception
	 */
	public Map<Integer, Person> findPersonByFirstNameAndLastName(String firstName, String lastName) throws Exception {
		logger.info("Find person by its first name and last name :" + firstName + " " + lastName);
		SafetyNet safetyNet = safetyNetRepository.deserialize();
		Map<Integer, Person> result = new HashMap<Integer, Person>();
		for (Person person : safetyNet.getPersons()) {
			if (person.getFirstName().equalsIgnoreCase(firstName) && person.getLastName().equalsIgnoreCase(lastName)) {
				int index = safetyNet.getPersons().indexOf(person);
				result.put(index, person);

			}
		}
		return result;

	}

	/**
	 * Get the person ** This operation allows to deserialize a json file to obtain
	 * a safety net object, then to return the person object in the given index that
	 * we want to retrieve its information
	 * 
	 * 
	 * @param index of the person to get
	 * @return person object
	 * @throws Exception
	 */
	public Person getPerson(int index) throws Exception {
		logger.info("Get person on index : " + index);
		SafetyNet safetyNet = safetyNetRepository.deserialize();
		return safetyNet.getPersons().get(index);
	}

	/**
	 * Add a new person ** This operation allows to deserialize a json file to
	 * obtain a safety net object, to add the person object, to serialize a safety
	 * net object to a json file and then to return the person we just added
	 * 
	 * @param person object to add
	 * @return person object added
	 * @throws Exception
	 */
	@Override
	public Person addPerson(Person person) throws Exception {
		SafetyNet safetyNet = safetyNetRepository.deserialize();
		safetyNet.getPersons().add(person);
		safetyNetRepository.serialize(safetyNet);
		logger.info("Add person : " + person);
		return person;
	}

	/**
	 * Update information of the person ** This operation allows to deserialize a
	 * json file to obtain a safety net object,to update the person object in the
	 * given index that we want to modify its information, to serialize a safety net
	 * object to a json file, then to return the person object updated
	 * 
	 * @param index  of the person to update
	 * @param person object to update
	 * @return person object updated
	 * @throws Exception
	 */
	@Override
	public Person updatePerson(int index, Person personDto) throws Exception {
		SafetyNet safetyNet = safetyNetRepository.deserialize();
		safetyNet.getPersons().set(index, personDto);
		safetyNetRepository.serialize(safetyNet);
		logger.info("update person " + personDto + " on index : " + index);
		return safetyNet.getPersons().get(index);
	}

	/**
	 * Delete the person ** This operation allows to deserialize a json file to
	 * obtain a safety net object,to delete the person object in the given index and
	 * then to serialize a safety net object to a json file
	 * 
	 * @param index of the person object to delete
	 * @throws Exception
	 */
	@Override
	public void deletePerson(int index) throws Exception {
		SafetyNet safetyNet = safetyNetRepository.deserialize();
		safetyNet.getPersons().remove(index);
		safetyNetRepository.serialize(safetyNet);
		logger.info("delete person on index : " + index);
	}

	/**
	 * Print all persons
	 * 
	 * @return list of all persons existing in the json file
	 * @throws Exception
	 */
	@Override
	public List<Person> getAllPersons() throws Exception {
		logger.info("getting list of all persons");
		return safetyNetRepository.deserialize().getPersons();

	}

}

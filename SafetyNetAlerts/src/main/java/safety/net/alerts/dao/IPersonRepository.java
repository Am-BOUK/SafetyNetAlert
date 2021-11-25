package safety.net.alerts.dao;

import java.util.List;
import java.util.Map;

import safety.net.alerts.entities.Person;

/**
 * DAO Interface that will allow interaction with external data sources
 * 
 * The important part is, the return value from operations which are a Person
 * object and index
 * 
 */
public interface IPersonRepository {

	public Map<Integer, Person> findPersonByFirstNameAndLastName(String firstName, String lastName) throws Exception;

	public Person getPerson(int index) throws Exception;

	public Person addPerson(Person person) throws Exception;

	public Person updatePerson(int index, Person personDto) throws Exception;

	public void deletePerson(int index) throws Exception;

	public List<Person> getAllPersons() throws Exception;

}

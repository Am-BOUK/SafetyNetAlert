package safety.net.alerts.service;

import java.util.List;

import safety.net.alerts.entities.Person;
import safety.net.alerts.entities.PersonDTO;

/**
 * 
 * business interface that will allow implementation of application-specific
 * business processing
 * 
 * The important part is, the return value from operations which is a person
 * object
 * 
 */
public interface IPersonMetier {

	public Person getPerson(String firstName, String lastName) throws Exception;

	public List<Person> getListPerson(String firstName, String lastName) throws Exception;

	public Person addPerson(Person person) throws Exception;

	public Person updatePerson(String firstName, String lastName, PersonDTO personDto) throws Exception;

	public void deletePerson(String firstName, String lastName) throws Exception;

	public List<Person> getAllPersons() throws Exception;

}
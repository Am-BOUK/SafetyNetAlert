package safety.net.alerts.metier;

import java.util.List;

import safety.net.alerts.entities.Person;
import safety.net.alerts.entities.PersonDTO;

public interface IPersonService {

	public Person addPerson(Person person);

	public Person updatePerson(String firstName, String lastName, PersonDTO personDto);

	public void deletePersonByName(String firstName, String lastName);

	public Person getPerson(String firstName, String lastName);

	public List<Person> getAllPersons();
}
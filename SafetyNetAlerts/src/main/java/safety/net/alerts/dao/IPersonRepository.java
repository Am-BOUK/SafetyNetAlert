package safety.net.alerts.dao;

import java.util.List;

import safety.net.alerts.entities.Person;

public interface IPersonRepository {


	public Person getPerson(int index);

	public Person addPerson(Person person);

	public Person updatePerson(int index, Person personDto);

	public void deletePerson(int index);

	public List<Person> getAllPersons();

}

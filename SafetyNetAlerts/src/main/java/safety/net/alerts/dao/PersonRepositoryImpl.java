package safety.net.alerts.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import safety.net.alerts.entities.Person;
import safety.net.alerts.entities.SafetyNet;

@Repository
public class PersonRepositoryImpl implements IPersonRepository {

	@Autowired
	private ISafetyNetRepository safetyNetRepository;

	@Override
	public Person addPerson(Person person) {
		SafetyNet safetyNet = safetyNetRepository.deserialize();
		safetyNet.getPersons().add(person);
		safetyNetRepository.serialize(safetyNet);

		return person;
	}

	@Override
	public Person updatePerson(int index, Person personDto) {
		SafetyNet safetyNet = safetyNetRepository.deserialize();
		safetyNet.getPersons().set(index, personDto);
		safetyNetRepository.serialize(safetyNet);
		return safetyNet.getPersons().get(index);
	}

	public Person getPerson(int index) {
		SafetyNet safetyNet = safetyNetRepository.deserialize();
		return safetyNet.getPersons().get(index);
//		safetyNet.getPersons();
//		Person person = null;
//		for (Person person1 : safetyNet.getPersons()) {
//			if ((person1.getFirstName().contentEquals(firstName)) && (person1.getLastName().contentEquals(lastName))) {
//				return person1;
//			}
//		}
//		return person;
	}

	@Override
	public void deletePerson(int index) {
		SafetyNet safetyNet = safetyNetRepository.deserialize();
		safetyNet.getPersons().remove(index);
		safetyNetRepository.serialize(safetyNet);
	}

	@Override
	public List<Person> getAllPersons() {
		return safetyNetRepository.deserialize().getPersons();
	}

}

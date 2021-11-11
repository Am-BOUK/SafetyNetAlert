package safety.net.alerts.metier;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import safety.net.alerts.dao.IPersonRepository;
import safety.net.alerts.entities.Person;
import safety.net.alerts.entities.PersonDTO;
import safety.net.alerts.entities.SafetyNet;

@Service
public class PersonServiceImpl implements IPersonService {

	@Autowired
	private IPersonRepository personRepository;
	@Autowired
	private ISafetyNetService safetyNetService;

	@Override
	public Person addPerson(Person person) {
		SafetyNet safetyNet = safetyNetService.deserialize();
		for (Person p : safetyNet.getPersons()) {
			if (p.getFirstName().contains(person.getFirstName()) && p.getLastName().contains(person.getLastName())) {
				System.out.println("The person " + person.getFirstName() + " " + person.getLastName()
						+ "you want to add, already exists !");
				return null;
			}
		}

		System.out.println("Adding :" + person);
		return personRepository.addPerson(person);

	}

	@Override
	public Person updatePerson(String firstName, String lastName, PersonDTO personDto) {
		SafetyNet safetyNet = safetyNetService.deserialize();
		int index = -1;
		Person p = new Person();
		for (Person p1 : safetyNet.getPersons()) {
			index = safetyNet.getPersons().indexOf(p1);
			if (p1.getFirstName().contains(firstName) && p1.getLastName().contains(lastName)) {
				p = p1;
				p.setAddress(personDto.getAddress() != null ? personDto.getAddress() : p.getAddress());
				p.setCity(personDto.getCity() != null ? personDto.getCity() : p.getCity());
				p.setEmail(personDto.getEmail() != null ? personDto.getEmail() : p.getEmail());
				p.setPhone(personDto.getPhone() != null ? personDto.getPhone() : p.getPhone());
				p.setZip(personDto.getZip() != null ? personDto.getZip() : p.getZip());

				System.out.println("Updated : " + p);
				return personRepository.updatePerson(index, p);
			}
		}
		return null;

	}

	@Override
	public void deletePersonByName(String firstName, String lastName) {
		SafetyNet safetyNet = safetyNetService.deserialize();
		int index = -1;
		for (Person p : safetyNet.getPersons()) {
			index = safetyNet.getPersons().indexOf(p);
			if (p.getFirstName().contains(firstName) && p.getLastName().contains(lastName)) {
				System.out.println("Deleting :" + firstName + " " + lastName);
				personRepository.deletePerson(index);
				return;
			}
		}

		System.out.println("person : " + firstName + " " + lastName + " does not exists !");

	}

	@Override
	public Person getPerson(String firstName, String lastName) {

		SafetyNet safetyNet = safetyNetService.deserialize();
		int index = -1;
		for (Person p : safetyNet.getPersons()) {
			index = safetyNet.getPersons().indexOf(p);
			if (p.getFirstName().contains(firstName) && p.getLastName().contains(lastName)) {
				System.out.println("Getting : " + p);
				return personRepository.getPerson(index);
			}
		}
		System.out.println("person : " + firstName + " " + lastName + " does not exists !");
		return null;

	}

	@Override
	public List<Person> getAllPersons() {
		System.out.println("Getting All Persons");
		return personRepository.getAllPersons();
	}

}

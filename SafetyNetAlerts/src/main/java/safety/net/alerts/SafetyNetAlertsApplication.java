package safety.net.alerts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import safety.net.alerts.dao.IFireStationRepository;
import safety.net.alerts.dao.IMedicalRecordRepository;
import safety.net.alerts.dao.IPersonRepository;
import safety.net.alerts.dao.ISafetyNetRepository;
//import safety.net.alerts.dao.IPersonRepository;
import safety.net.alerts.entities.FireStation;
import safety.net.alerts.entities.FireStationDTO;
import safety.net.alerts.entities.MedicalRecord;
import safety.net.alerts.entities.MedicalRecordDTO;
//import safety.net.alerts.dao.ISafetyNetRepository;
import safety.net.alerts.entities.Person;
import safety.net.alerts.entities.PersonDTO;
import safety.net.alerts.entities.SafetyNet;
import safety.net.alerts.service.IFireStationMetier;
import safety.net.alerts.service.IMedicalRecordMetier;
import safety.net.alerts.service.IPersonMetier;

@SpringBootApplication
public class SafetyNetAlertsApplication implements CommandLineRunner {
//	@Autowired
//	private IPersonRepository personRepository;
	@Autowired
	private IPersonMetier personMetier;

//	@Autowired
//	private IFireStationRepository fireStationRepository;
	@Autowired
	private IFireStationMetier fireStationMetier;

//	@Autowired
//	private IMedicalRecordRepository medicalRecordRepository;
	@Autowired
	private IMedicalRecordMetier medicalRecordMetier;

	@Autowired
	private ISafetyNetRepository safetyNetRepository;

	private Person person;
	private PersonDTO personDTO;

	private FireStation fireStation;

	private MedicalRecord medicalRecord;

	public static void main(String[] args) {
		SpringApplication.run(SafetyNetAlertsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		SafetyNet s = safetyNetRepository.deserialize();
//		System.out.println(s);

		/*
		 * Person
		 */
		person = new Person();
		person.setFirstName("ahlam");
		person.setLastName("Boukili");
		person.setAddress("Bouskoura");
		person.setCity("Maroc");
		person.setZip("20000");
		person.setPhone("11111111");
		person.setEmail("ahlam@boukili.com");

		personDTO = new PersonDTO();
		personDTO.setAddress("modified");
		personDTO.setCity("modified");
		personDTO.setZip("modified");

//		personRepository.addPerson(person);
//		personMetier.addPerson(person);
		
//		personMetier.getPerson("Jacob", "Boyd");
//		personMetier.getPerson("test", "test");
//		System.out.println(personMetier.getPerson("Jacob", "Boyd"));

		
//		personMetier.updatePerson("ahlam", "Boukili", personDTO); 



//		personMetier.deletePersonByName("ahlam", "Boukili");

//		System.out.println(personMetier.getAllPersons());

		/*
		 * FireStation
		 */
		fireStation = new FireStation();
		fireStation.setAddress("20, Avenue Saint Louis");
		fireStation.setStation("10000000");

		FireStationDTO fireStationDTO = new FireStationDTO();
		fireStationDTO.setStation("5000");

//		System.out.println(s.getFirestations());

//		fireStationRepository.getFireStation(1);
//		System.out.println(fireStationRepository.getFireStation(1));
//		fireStationMetier.getFireStation("748 Townings Dr");
//		fireStationMetier.getFireStation("N'importe ou!" );

//		fireStationRepository.addFireStation(fireStation);
//		System.out.println(fireStationRepository.addFireStation(fireStation));
//		fireStationMetier.addFireStation(fireStation);

//		fireStationRepository.updateFireStation(0, fireStation);
//		System.out.println(fireStationRepository.updateFireStation(0, fireStation));
//		fireStationMetier.updateFireStation("20, Avenue Saint Louis", fireStationDTO);
//		fireStationMetier.updateFireStation("N'importe ou !", fireStationDTO);

//		fireStationRepository.deleteFireStation(0);
//		fireStationMetier.deleteFireStation("20, Avenue Saint Louis");
//		fireStationMetier.deleteFireStation("test");

//		fireStationRepository.getAllFireStations();
//		System.out.println(fireStationRepository.getAllFireStations());
//		fireStationMetier.getAllFireStations();
//		System.out.println(fireStationMetier.getAllFireStations());

		/*
		 * MedicalRecord
		 */
//		System.out.println(s.getMedicalrecords());
		medicalRecord = new MedicalRecord();

		medicalRecord.setFirstName("Amal");
		medicalRecord.setLastName("Boukili");
		medicalRecord.setBirthdate("06/08/1987");
		medicalRecord.setMedications(null);
		medicalRecord.setAllergies(null);

		MedicalRecordDTO medicalRecordDTO = new MedicalRecordDTO();
		medicalRecordDTO.setBirthdate("test");

//		medicalRecordRepository.getMedicalRecord(2);
//		System.out.println(medicalRecordRepository.getMedicalRecord(2));
//		medicalRecordMetier.getMedicalRecord("Clive", "Ferguson");
//		medicalRecordMetier.getMedicalRecord("test", "test");

//		medicalRecordRepository.addMedicalRecord(medicalRecord);
//		System.out.println(medicalRecordRepository.addMedicalRecord(medicalRecord));
//		medicalRecordMetier.addMedicalRecord(medicalRecord);

//		medicalRecordRepository.updateMedicalRecord(0, medicalRecord);
//		System.out.println(medicalRecordRepository.updateMedicalRecord(0, medicalRecord));
//		medicalRecordMetier.updateMedicalRecord("Amal", "Boukili", medicalRecordDTO);
//		medicalRecordMetier.updateMedicalRecord("test", "test", medicalRecordDTO);

//		medicalRecordRepository.deleteMedicalRecord(0);
//		medicalRecordMetier.deleteMedicalRecord("Amal", "Boukili");
//		medicalRecordMetier.deleteMedicalRecord("test", "test");

//		
//		medicalRecordRepository.getAllMedicalRecords();
//		System.out.println(medicalRecordRepository.getAllMedicalRecords());
//		System.out.println(medicalRecordMetier.getAllMedicalRecord());

	}

}

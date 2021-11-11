package safety.net.alerts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import safety.net.alerts.dao.IFireStationRepository;
import safety.net.alerts.dao.IMedicalRecordRepository;
//import safety.net.alerts.dao.IPersonRepository;
import safety.net.alerts.entities.FireStation;
import safety.net.alerts.entities.FireStationDTO;
import safety.net.alerts.entities.MedicalRecord;
import safety.net.alerts.entities.MedicalRecordDTO;
//import safety.net.alerts.dao.ISafetyNetRepository;
import safety.net.alerts.entities.Person;
import safety.net.alerts.entities.PersonDTO;
import safety.net.alerts.entities.SafetyNet;
import safety.net.alerts.metier.ISafetyNetService;
import safety.net.alerts.metier.IFireStationService;
import safety.net.alerts.metier.IMedicalRecordService;
import safety.net.alerts.metier.IPersonService;

@SpringBootApplication
public class SafetyNetAlertsApplication implements CommandLineRunner {
//	@Autowired
//	private IPersonRepository personRepository;
	@Autowired
	private IPersonService personService;

//	@Autowired
//	private IFireStationRepository fireStationRepository;
	@Autowired
	private IFireStationService fireStationService;

//	@Autowired
//	private IMedicalRecordRepository medicalRecordRepository;
	@Autowired
	private IMedicalRecordService medicalRecordService;
	
//	@Autowired
//	private ISafetyNetRepository safetyNetRepository;
	@Autowired
	private ISafetyNetService safetyNetService;

	private Person person;
	private PersonDTO personDTO;

	private FireStation fireStation;

	private MedicalRecord medicalRecord;

	public static void main(String[] args) {
		SpringApplication.run(SafetyNetAlertsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		SafetyNet s = safetyNetService.deserialize();
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

//		personService.addPerson(person);

//		personService.updatePerson("ahlam", "Boukili", personDTO);

//		personService.getPerson("Jacob", "Boyd");
//		personService.getPerson("test", "test");
//		System.out.println(personService.getPerson("Jacob", "Boyd"));

//		personService.deletePersonByName("ahlam", "Boukili");

//		System.out.println(personService.getAllPersons());

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
//		fireStationService.getFireStation("748 Townings Dr");
//		fireStationService.getFireStation("N'importe ou!" );

//		fireStationRepository.addFireStation(fireStation);
//		System.out.println(fireStationRepository.addFireStation(fireStation));
//		fireStationService.addFireStation(fireStation);

//		fireStationRepository.updateFireStation(0, fireStation);
//		System.out.println(fireStationRepository.updateFireStation(0, fireStation));
//		fireStationService.updateFireStation("20, Avenue Saint Louis", fireStationDTO);
//		fireStationService.updateFireStation("N'importe ou !", fireStationDTO);

//		fireStationRepository.deleteFireStation(0);
//		fireStationService.deleteFireStation("20, Avenue Saint Louis");
//		fireStationService.deleteFireStation("test");

//		fireStationRepository.getAllFireStations();
//		System.out.println(fireStationRepository.getAllFireStations());
//		fireStationService.getAllFireStations();
//		System.out.println(fireStationService.getAllFireStations());

		/*
		 * MedicalRecord
		 */
		System.out.println(s.getMedicalrecords());
		medicalRecord = new MedicalRecord();

		medicalRecord.setFirstName("Amal");
		medicalRecord.setLastName("Boukili");
		medicalRecord.setBirthdate("08/06/1987");
		medicalRecord.setMedications(null);
		medicalRecord.setAllergies(null);
		
		MedicalRecordDTO medicalRecordDTO = new MedicalRecordDTO();
		medicalRecordDTO.setBirthdate("test");

//		medicalRecordRepository.getMedicalRecord(2);
//		System.out.println(medicalRecordRepository.getMedicalRecord(2));
//		medicalRecordService.getMedicalRecord("Clive", "Ferguson");
//		System.out.println(medicalRecordService.getMedicalRecord("Clive", "Ferguson"));
//		System.out.println(medicalRecordService.getMedicalRecord("test", "test"));

//		medicalRecordRepository.addMedicalRecord(medicalRecord);
//		System.out.println(medicalRecordRepository.addMedicalRecord(medicalRecord));
//		System.out.println(medicalRecordService.addMedicalRecord(medicalRecord));
//		System.out.println(medicalRecordService.addMedicalRecord(medicalRecord));

//		medicalRecordRepository.updateMedicalRecord(0, medicalRecord);
//		System.out.println(medicalRecordRepository.updateMedicalRecord(0, medicalRecord));
//		medicalRecordService.updateMedicalRecord("Amal", "Boukili", medicalRecordDTO);
//		System.out.println(medicalRecordService.updateMedicalRecord("Amal", "Boukili", medicalRecordDTO));
//		System.out.println(medicalRecordService.updateMedicalRecord("test", "test", medicalRecordDTO));

//		medicalRecordRepository.deleteMedicalRecord(0);
		medicalRecordService.deleteMedicalRecord("Amal", "Boukili");
		medicalRecordService.deleteMedicalRecord("test", "test");
		
//		
//		medicalRecordRepository.getAllMedicalRecords();
//		System.out.println(medicalRecordRepository.getAllMedicalRecords());
		System.out.println(medicalRecordService.getAllMedicalRecord());

	}

}

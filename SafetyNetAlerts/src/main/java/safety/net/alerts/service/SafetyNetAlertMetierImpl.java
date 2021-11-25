package safety.net.alerts.service;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import safety.net.alerts.dao.IFireStationRepository;
import safety.net.alerts.dao.IMedicalRecordRepository;
import safety.net.alerts.dao.IPersonRepository;
import safety.net.alerts.entities.FireStation;
import safety.net.alerts.entities.MedicalRecord;
import safety.net.alerts.entities.Person;

/**
 * implementation of safety net alert business processing
 * 
 */
@Service
public class SafetyNetAlertMetierImpl implements ISafetyNetAlertMetier {
	private static final Logger logger = LogManager.getLogger("SafetyNetAlertMetierImpl");
	/**
	 * Calling operations of the person, fire station and medical record repository
	 */
	@Autowired
	private IPersonRepository personRepository;
	@Autowired
	private IFireStationRepository fireStationRepository;
	@Autowired
	private IMedicalRecordRepository medicalRecordRepository;

	/**
	 * Get a list of person and count of the number of adults and the number of
	 * children covered by station number ** This operation allows to get a list of
	 * person covered by the corresponding fire station in the given station number.
	 * The list must include the following specific information: first name, last
	 * name, address, telephone number. In addition, it must provide a count of the
	 * number of adults and the number of children (aged 18 or under) in the area
	 * served.
	 * 
	 * @param first name and last name of the person whose we want to get
	 * @return a list of person. The list must include: first name, last name,
	 *         address, telephone number. a count of the number of adults and the
	 *         number of children
	 * @throws Exception
	 */
	@Override
	public Map<String, List<String>> getAllPersonsByStationNumber(int station) throws Exception {

		logger.info("Getting a list of person covered by the corresponding fire station  : " + station);

		// initialize variables
		Map<String, List<String>> personByStation = new HashMap<String, List<String>>();
		List<String> personCount = new ArrayList<String>();
		List<Person> persons = personRepository.getAllPersons();
		List<FireStation> fireStations = fireStationRepository.getAllFireStations();
		List<String> value = new ArrayList<String>();
		String address = null;
		int childCount = 0;
		int adultCount = 0;

		for (FireStation fireStation : fireStations) {
			if (fireStation.getStation().contains(Integer.toString(station))) {
				address = fireStation.getAddress();
				String stationKey = "Station number :" + station;
				personByStation.put(stationKey, value);

				for (Person person : persons) {
					if (person.getAddress().equalsIgnoreCase(address)) {
						personByStation.get(stationKey).add("First name " + person.getFirstName());
						personByStation.get(stationKey).add("Last name " + person.getLastName());
						personByStation.get(stationKey).add("Adress " + person.getAddress());
						personByStation.get(stationKey).add("Phone number " + person.getPhone());

						/**
						 * Get medical record of the person
						 */
						int index = medicalRecordRepository
								.findMedicalRecordByFirstNameAndLastName(person.getFirstName(), person.getLastName())
								.keySet().stream().findFirst().get();
						MedicalRecord medicalRecordFound = medicalRecordRepository.getMedicalRecord(index);
						/**
						 * Get birthdate of the person by using his medical record.
						 */
						String birthdate = medicalRecordFound.getBirthdate();
						/**
						 * Convert birthdate string to date
						 */
						SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
						Date date = formatter.parse(birthdate);
						Instant instant = date.toInstant();
						ZonedDateTime zone = instant.atZone(ZoneId.systemDefault());
						LocalDate givenDate = zone.toLocalDate();
						Period period = Period.between(givenDate, LocalDate.now());

						if (period.getYears() > 18) {
							adultCount++;

						} else {
							childCount++;
						}
					}
				}
			}
		}
		if (personByStation.isEmpty()) {
			logger.info("Success getting list of persons information by station number : " + station);
			throw new RuntimeException("The fire station number : " + station + " , does not exist !");
		} else {
			personByStation.put("Person count ", personCount);
			personByStation.get("Person count ").add("Adult count " + Integer.toString(adultCount));
			personByStation.get("Person count ").add("Child count " + Integer.toString(childCount));

			logger.info("Success getting list of persons information by station number : " + station);

			return personByStation;
		}
	}

	/**
	 * Get a list of children information And his family member by address ** This
	 * method should return a list of children (aged 18 or under) living at the
	 * given address. The list should include each child's first and last name, age,
	 * and a list of other household members.
	 * 
	 * @param address
	 * @return a list of children. The list must include: first name, last name, age
	 *         and a list of other household members
	 * @throws Exception
	 */
	@Override
	public Map<String, List<Person>> getchildrendAndFamilyMemberByAddress(String address) throws Exception {
		logger.info("Getting information about child and his family members by address : " + address);
		Map<String, List<Person>> childFamilyMap = new HashMap<String, List<Person>>();
		String childKey = null;

		List<Person> persons = personRepository.getAllPersons();
		List<Person> childFamily = new ArrayList<Person>();

		for (Person person : persons) {

			if (person.getAddress().equalsIgnoreCase(address)) {
				String firstName = person.getFirstName();
				String lastName = person.getLastName();

				/**
				 * Get medical record of the child
				 */
				int index = medicalRecordRepository.findMedicalRecordByFirstNameAndLastName(firstName, lastName)
						.keySet().stream().findFirst().get();
				MedicalRecord medicalRecordFound = medicalRecordRepository.getMedicalRecord(index);
				/**
				 * Get birthdate of the child by using his medical record.
				 */
				String birthdate = medicalRecordFound.getBirthdate();
				/**
				 * Convert birthdate string to date
				 */
				SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
				Date date = formatter.parse(birthdate);
				Instant instant = date.toInstant();
				ZonedDateTime zone = instant.atZone(ZoneId.systemDefault());
				LocalDate givenDate = zone.toLocalDate();
				Period period = Period.between(givenDate, LocalDate.now());

				if (period.getYears() <= 18) {

					childKey = (person.getFirstName() + " " + person.getLastName() + " "
							+ Integer.toString(period.getYears()) + " ans");
					childFamilyMap.put(childKey, childFamily);

				} else if (period.getYears() > 18) {
					childFamily.add(person);
				}
			}
		}
		if (childFamilyMap.isEmpty()) {
			throw new RuntimeException("No child in address :" + address);
		}
		if (childFamilyMap.containsValue(null)) {
			throw new RuntimeException("No person whith child in address :" + address);

		} else {
			logger.info("Success Getting list of family child information in address :" + address);
			return childFamilyMap;
		}
	}

	/**
	 * Get a list of all the addresses served by the fire station number ** This
	 * method should return a list of all the addresses served by the fire station.
	 * This list should group person by address. It should also include the name,
	 * phone number and age of residents, and include their medical history
	 * (medications, dosage, and allergies) next to each name.
	 * 
	 * 
	 * @param station
	 * @return list of address. The list must include: first name, last name, phone
	 *         number, age and their medical history
	 * @throws Exception
	 */
	@Override
	public Map<String, List<String>> getAllPersonsByAddressOfStationNumber(int station) throws Exception {
		logger.info("Getting a list of all the addresses served by the fire station number : " + station);
		Map<String, List<String>> personByStation = new HashMap<String, List<String>>();

		List<Person> persons = personRepository.getAllPersons();
		List<FireStation> fireStations = fireStationRepository.getAllFireStations();

		String address = null;

		for (FireStation fireStation : fireStations) {
			if (fireStation.getStation().contains(Integer.toString(station))) {
				address = fireStation.getAddress();
				List<String> value = new ArrayList<String>();
				personByStation.put(address, value);
				for (Person person : persons) {
					if (person.getAddress().equalsIgnoreCase(address)) {

						/**
						 * Get medical record of the child
						 */
						int index = medicalRecordRepository
								.findMedicalRecordByFirstNameAndLastName(person.getFirstName(), person.getLastName())
								.keySet().stream().findFirst().get();
						MedicalRecord medicalRecordFound = medicalRecordRepository.getMedicalRecord(index);
						/**
						 * Get birthdate of the child by using his medical record.
						 */
						String birthdate = medicalRecordFound.getBirthdate();
						/**
						 * Convert birthdate string to date
						 */
						SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
						Date date = formatter.parse(birthdate);
						Instant instant = date.toInstant();
						ZonedDateTime zone = instant.atZone(ZoneId.systemDefault());
						LocalDate givenDate = zone.toLocalDate();
						Period period = Period.between(givenDate, LocalDate.now());

						personByStation.get(address).add("First name : " + person.getFirstName());
						personByStation.get(address).add("Last name : " + person.getLastName());
						personByStation.get(address).add("Medications : " + medicalRecordFound.getMedications());
						personByStation.get(address).add("Allergies : " + medicalRecordFound.getAllergies());
						personByStation.get(address).add("Phone number : " + person.getPhone());
						personByStation.get(address).add("Age : " + period.getYears());
					}
				}
			}
		}
		if (personByStation.isEmpty()) {

			logger.info("The fire station number : " + station + " , does not exist !");
			throw new RuntimeException("The fire station number : " + station + " , does not exist !");

		} else {
			logger.info("Success getting a list of all the addresses served by the fire station number : " + station);
			return personByStation;
		}

	}
}

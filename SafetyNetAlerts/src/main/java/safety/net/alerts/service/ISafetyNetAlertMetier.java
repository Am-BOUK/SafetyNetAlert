package safety.net.alerts.service;

import java.util.List;
import java.util.Map;

import safety.net.alerts.entities.Person;

public interface ISafetyNetAlertMetier {

	public Map<String, List<String>> getAllPersonsByStationNumber(int station) throws Exception;

	public Map<String, List<Person>> getchildrendAndFamilyMemberByAddress(String address) throws Exception;

	public Map<String, List<String>> getAllPhoneNumbersByFireStationNumber(int station) throws Exception;

	public Map<String, List<String>> getAllPersonsAndStationNumberAndMedicalRecordByAddress(String address)
			throws Exception;

	public Map<String, List<String>> getAllPersonsByListOfStationNumber(int[] stations) throws Exception;

	public Map<String, List<String>> getPersonsInfoByFirstNameAndLastName(String firstName, String lastName)
			throws Exception;

	public Map<String, List<String>> getAllEmailsByCity(String city) throws Exception;
}

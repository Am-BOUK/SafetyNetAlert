package safety.net.alerts.dao;

import java.util.List;
import java.util.Map;

import safety.net.alerts.entities.MedicalRecord;

/**
 * DAO Interface that will allow interaction with external data sources
 * 
 * The important part is, the return value from operations which are a
 * medicalRecord object and index
 * 
 */
public interface IMedicalRecordRepository {

	Map<Integer, MedicalRecord> findMedicalRecordByFirstNameAndLastName(String firstName, String lastName)
			throws Exception;

	public MedicalRecord getMedicalRecord(int index) throws Exception;

	public MedicalRecord addMedicalRecord(MedicalRecord medicalRecord) throws Exception;

	public MedicalRecord updateMedicalRecord(int index, MedicalRecord medicalRecord) throws Exception;

	public void deleteMedicalRecord(int index) throws Exception;

	public List<MedicalRecord> getAllMedicalRecords() throws Exception;
}

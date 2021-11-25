package safety.net.alerts.service;

import java.util.List;

import safety.net.alerts.entities.MedicalRecord;
import safety.net.alerts.entities.MedicalRecordDTO;

/**
 * 
 * business interface that will allow implementation of application-specific
 * business processing
 * 
 * The important part is, the return value from operations which is a medical
 * record object
 * 
 */
public interface IMedicalRecordMetier {

	public MedicalRecord getMedicalRecord(String firstName, String lastName) throws Exception;

	public List<MedicalRecord> getListMedicalRecord(String firstName, String lastName) throws Exception;

	public MedicalRecord addMedicalRecord(MedicalRecord medicalRecord) throws Exception;

	public MedicalRecord updateMedicalRecord(String firstName, String lastName, MedicalRecordDTO medicalRecordDTO)
			throws Exception;

	public void deleteMedicalRecord(String firstName, String lastName) throws Exception;

	public List<MedicalRecord> getAllMedicalRecords() throws Exception;
}

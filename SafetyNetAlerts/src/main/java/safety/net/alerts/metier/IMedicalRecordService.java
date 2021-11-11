package safety.net.alerts.metier;

import java.util.List;

import safety.net.alerts.entities.MedicalRecord;
import safety.net.alerts.entities.MedicalRecordDTO;

public interface IMedicalRecordService {

	public MedicalRecord getMedicalRecord(String firstName, String lastName);

	public MedicalRecord addMedicalRecord(MedicalRecord medicalRecord);

	public MedicalRecord updateMedicalRecord(String firstName, String lastName, MedicalRecordDTO medicalRecordDTO);

	public void deleteMedicalRecord(String firstName, String lastName);

	public List<MedicalRecord> getAllMedicalRecord();
}

package safety.net.alerts.dao;

import java.util.List;

import safety.net.alerts.entities.MedicalRecord;

public interface IMedicalRecordRepository {

	public MedicalRecord getMedicalRecord(int index);

	public MedicalRecord addMedicalRecord(MedicalRecord medicalRecord);

	public MedicalRecord updateMedicalRecord(int index, MedicalRecord medicalRecord);

	public void deleteMedicalRecord(int index);

	public List<MedicalRecord> getAllMedicalRecords();
}

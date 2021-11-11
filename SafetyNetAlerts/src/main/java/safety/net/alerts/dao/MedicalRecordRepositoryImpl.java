package safety.net.alerts.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import safety.net.alerts.entities.MedicalRecord;
import safety.net.alerts.entities.SafetyNet;

@Repository
public class MedicalRecordRepositoryImpl implements IMedicalRecordRepository {
	@Autowired
	private ISafetyNetRepository safetyNetRepository;

	@Override
	public MedicalRecord getMedicalRecord(int index) {
		SafetyNet safetyNet = safetyNetRepository.deserialize();

		return safetyNet.getMedicalrecords().get(index);
	}

	@Override
	public MedicalRecord addMedicalRecord(MedicalRecord medicalRecord) {
		SafetyNet safetyNet = safetyNetRepository.deserialize();
		safetyNet.getMedicalrecords().add(medicalRecord);
		safetyNetRepository.serialize(safetyNet);

		return medicalRecord;
	}

	@Override
	public MedicalRecord updateMedicalRecord(int index, MedicalRecord medicalRecord) {
		SafetyNet safetyNet = safetyNetRepository.deserialize();
		safetyNet.getMedicalrecords().set(index, medicalRecord);
		safetyNetRepository.serialize(safetyNet);
		return medicalRecord;
	}

	@Override
	public void deleteMedicalRecord(int index) {
		SafetyNet safetyNet = safetyNetRepository.deserialize();
		safetyNet.getMedicalrecords().remove(index);
		safetyNetRepository.serialize(safetyNet);
	}

	@Override
	public List<MedicalRecord> getAllMedicalRecords() {
		SafetyNet safetyNet = safetyNetRepository.deserialize();
		return safetyNet.getMedicalrecords();
	}

}

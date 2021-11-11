package safety.net.alerts.metier;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import safety.net.alerts.dao.IMedicalRecordRepository;
import safety.net.alerts.entities.MedicalRecord;
import safety.net.alerts.entities.MedicalRecordDTO;
import safety.net.alerts.entities.SafetyNet;

@Service
public class MedicalRecordServiceImpl implements IMedicalRecordService {
	@Autowired
	private ISafetyNetService safetyNetService;

	@Autowired
	private IMedicalRecordRepository medicalRecordRepository;

	@Override
	public MedicalRecord getMedicalRecord(String firstName, String lastName) {

		SafetyNet safetyNet = safetyNetService.deserialize();
		int index = -1;

		for (MedicalRecord mr : safetyNet.getMedicalrecords()) {
			index = safetyNet.getMedicalrecords().indexOf(mr);
			if (mr.getFirstName().contains(firstName) && mr.getLastName().contains(lastName)) {
				System.out.println("Getting medical record : " + mr);
				return medicalRecordRepository.getMedicalRecord(index);
			}
		}
		System.out.println("person : " + firstName + " " + lastName + " does not exists !");
		return null;

	}

	@Override
	public MedicalRecord addMedicalRecord(MedicalRecord medicalRecord) {
		SafetyNet safetyNet = safetyNetService.deserialize();
		for (MedicalRecord mr : safetyNet.getMedicalrecords()) {
			if (mr.getFirstName().contains(medicalRecord.getFirstName())
					&& mr.getLastName().contains(medicalRecord.getLastName())) {
				System.out.println("the medical record of " + mr.getFirstName() + " " + mr.getLastName()
						+ " you want to add, already exists !");
				return null;
			}
		}
		System.out.println(
				"Adding medical record of " + medicalRecord.getFirstName() + " " + medicalRecord.getLastName());
		return medicalRecordRepository.addMedicalRecord(medicalRecord);
	}

	@Override
	public MedicalRecord updateMedicalRecord(String firstName, String lastName, MedicalRecordDTO medicalRecordDTO) {
		SafetyNet safetyNet = safetyNetService.deserialize();
		int index = -1;
		MedicalRecord medicalRecord = new MedicalRecord();
		for (MedicalRecord mr : safetyNet.getMedicalrecords()) {
			index = safetyNet.getMedicalrecords().indexOf(mr);
			if (mr.getFirstName().contains(firstName) && mr.getLastName().contains(lastName)) {
				medicalRecord = mr;
				medicalRecord.setBirthdate(medicalRecordDTO.getBirthdate() != null ? medicalRecordDTO.getBirthdate()
						: medicalRecord.getBirthdate());
				medicalRecord

						.setMedications(medicalRecordDTO.getMedications() != null ? medicalRecordDTO.getMedications()
								: medicalRecord.getMedications());
				medicalRecord.setAllergies(medicalRecordDTO.getAllergies() != null ? medicalRecordDTO.getAllergies()
						: medicalRecord.getAllergies());
				System.out.println("Updating the medical record of " + firstName + " " + lastName);
				return medicalRecordRepository.updateMedicalRecord(index, medicalRecord);
			}
		}
		System.out.println(
				"The medical record does not exist, because " + firstName + " " + lastName + " does not exist !");
		return null;
	}

	@Override
	public void deleteMedicalRecord(String firstName, String lastName) {
		SafetyNet safetyNet = safetyNetService.deserialize();
		int index = -1;
		for (MedicalRecord mr : safetyNet.getMedicalrecords()) {
			index = safetyNet.getMedicalrecords().indexOf(mr);
			if (mr.getFirstName().contains(firstName) && mr.getLastName().contains(lastName)) {
				System.out.println("Deleting the medical record of " + firstName + " " + lastName);
				medicalRecordRepository.deleteMedicalRecord(index);
				return;
			}
		}
		System.out.println("The medical record does not exist, because " + firstName + " " + lastName + " does not exist !");
	}

	@Override
	public List<MedicalRecord> getAllMedicalRecord() {
		SafetyNet safetyNet = safetyNetService.deserialize();
		System.out.println("Getting all medical records !");
		return safetyNet.getMedicalrecords();
	}

}

package safety.net.alerts.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import safety.net.alerts.dao.IMedicalRecordRepository;
import safety.net.alerts.entities.MedicalRecord;
import safety.net.alerts.entities.MedicalRecordDTO;

/**
 * implementation of medical record business processing
 * 
 */
@Service
public class MedicalRecordMetierImpl implements IMedicalRecordMetier {

	private static final Logger logger = LogManager.getLogger("PersonMetierImpl");

	/**
	 * Calling operations of the medical record repository
	 */
	@Autowired
	private IMedicalRecordRepository medicalRecordRepository;

	/**
	 * Read-Get one medical record **This operation allows to check if the first
	 * name and last name of the person we want to get its medical record already
	 * exist in the json file, then allows to use its index to get the information
	 * from its medical record
	 * 
	 * @param firstName the first name of the person we want to get its medical
	 *                  record
	 * @param lastName  the last name of the person we want to get its medical
	 *                  record
	 * @return a medical record object full filled
	 * @throws Exception
	 */
	public MedicalRecord getMedicalRecord(String firstName, String lastName) throws Exception {
		Map<Integer, MedicalRecord> medicalRecordMap = medicalRecordRepository
				.findMedicalRecordByFirstNameAndLastName(firstName, lastName);
		int index = -1;
		if (medicalRecordMap.size() > 0) {
			index = medicalRecordMap.keySet().stream().findFirst().get();
			logger.info("Getting medical record : " + medicalRecordMap.get(index));
			return medicalRecordRepository.getMedicalRecord(index);
		} else {
			logger.info("The medical record you want getting of the person : " + firstName + " " + lastName
					+ " ,does not exists !");
			throw new RuntimeException("The medical record you want getting of the person : " + firstName + " "
					+ lastName + " ,does not exists !");
		}
	}

	/**
	 * Get List of medical record by its first name and last name ** This operation
	 * allows to check if the first name and last name of the person we want to get
	 * its medical record information already exist in the json file, then allows to
	 * print all medical records contain the first and last name of the person in
	 * question
	 *
	 * 
	 * @param first name and last name of all person whose we want to get its
	 *              medical record
	 * @return List of persons if it exists
	 * @throws Exception
	 */
	@Override
	public List<MedicalRecord> getListMedicalRecord(String firstName, String lastName) throws Exception {
		logger.info("Get list of all medical records the person who contain: " + firstName + " as first name and " + lastName
				+ " as last name");
		List<MedicalRecord> medicalRecords = medicalRecordRepository.getAllMedicalRecords();
		List<MedicalRecord> medicalRecordList = new ArrayList<>();

		for (MedicalRecord mr : medicalRecords) {
			if (mr.getFirstName().toLowerCase().contains(firstName.toLowerCase())
					&& mr.getLastName().toLowerCase().contains(lastName.toLowerCase())) {
				medicalRecordList.add(mr);

			}
		}
		if (medicalRecordList.isEmpty()) {
			logger.info("The list of its medical record of " + firstName + " " + lastName + ", you want to get, is empty !");
			throw new RuntimeException(
					"The list of its medical record of " + firstName + " " + lastName + ", you want to get, is empty !");
		}
		return medicalRecordList;
	}

	/**
	 * Add a new medical record ** This operation allows to check if the first name
	 * and last name of the person we want to add its medical record already exist
	 * in the json file, then allows to add its medical record
	 * 
	 * 
	 * @param medical record object to add
	 * @return medicalRecord object added
	 */
	@Override
	public MedicalRecord addMedicalRecord(MedicalRecord medicalRecord) throws Exception {
		logger.info("Add a  new medical record : " + medicalRecord);
		Map<Integer, MedicalRecord> medicalRecordMap = medicalRecordRepository
				.findMedicalRecordByFirstNameAndLastName(medicalRecord.getFirstName(), medicalRecord.getLastName());
		if (medicalRecordMap.isEmpty()) {
			logger.info("Adding medical record : " + medicalRecord);
			return medicalRecordRepository.addMedicalRecord(medicalRecord);
		} else {
			logger.info("the medical record you want to add of " + medicalRecord.getFirstName() + " "
					+ medicalRecord.getLastName() + " , already exists !");
			throw new RuntimeException("the medical record you want to add of " + medicalRecord.getFirstName() + " "
					+ medicalRecord.getLastName() + " , already exists !");
		}
	}

	/**
	 * Update - update an existing medical record **This operation allows to check
	 * if the first name and last name of the person we want to update its medical
	 * record already exist in the json file, then allows to use its index to update
	 * the information of its medical record
	 * 
	 * @param firstName the first name of the person we want to update its medical
	 *                  record
	 * @param lastName  the last name of the person we want to update its medical
	 *                  record
	 * @param personDto the medical record data transfer object modified
	 * @return medicalRecord the medical record updated
	 * @throws Exception
	 */
	@Override
	public MedicalRecord updateMedicalRecord(String firstName, String lastName, MedicalRecordDTO medicalRecordDTO)
			throws Exception {
		logger.info("Update the medical record of : " + firstName + " " + lastName);
		Map<Integer, MedicalRecord> medicalRecordMap = medicalRecordRepository
				.findMedicalRecordByFirstNameAndLastName(firstName, lastName);
		int index = -1;
		if (medicalRecordMap.size() > 0) {
			index = medicalRecordMap.keySet().stream().findFirst().get();
			MedicalRecord medicalRecordFound = medicalRecordMap.get(index);
			medicalRecordFound.setBirthdate(medicalRecordDTO.getBirthdate() != null ? medicalRecordDTO.getBirthdate()
					: medicalRecordFound.getBirthdate());
			medicalRecordFound
					.setMedications(medicalRecordDTO.getMedications() != null ? medicalRecordDTO.getMedications()
							: medicalRecordFound.getMedications());
			medicalRecordFound.setAllergies(medicalRecordDTO.getAllergies() != null ? medicalRecordDTO.getAllergies()
					: medicalRecordFound.getAllergies());

			logger.info("Updating medical record : " + medicalRecordFound);
			return medicalRecordRepository.updateMedicalRecord(index, medicalRecordFound);
		} else {
			logger.info("The medical record you want to update of " + firstName + " " + lastName + " does not exist !");
			throw new RuntimeException(
					"The medical record you want to update of " + firstName + " " + lastName + " does not exist !");
		}
	}

	/**
	 * 
	 * Delete - delete a person **This operation allows to check if the first name
	 * and last name of the person we want to delete its medical record already
	 * exist in the json file, then allows to use its index to delete its medical
	 * record
	 * 
	 * @param firstName the first name of the person we want to delete its medical
	 *                  record
	 * @param lastName  the last name of the person we want to delete its medical
	 *                  record
	 * @throws Exception
	 */
	@Override
	public void deleteMedicalRecord(String firstName, String lastName) throws Exception {
		logger.info("Delete medical record of : " + firstName + " " + lastName);
		Map<Integer, MedicalRecord> medicalRecordMap = medicalRecordRepository
				.findMedicalRecordByFirstNameAndLastName(firstName, lastName);
		int index = -1;
		if (medicalRecordMap.size() > 0) {
			index = medicalRecordMap.keySet().stream().findFirst().get();
			logger.info("Deleting the medical record of " + firstName + " " + lastName);
			medicalRecordRepository.deleteMedicalRecord(index);
			return;
		} else {
			logger.info("The medical record you want to delete of " + firstName + " " + lastName + " does not exist !");
			throw new RuntimeException(
					"The medical record you want to delete of " + firstName + " " + lastName + " does not exist !");
		}
	}

	/**
	 * Print all persons ** this operation allows to get a list of all medical
	 * records exist in the json file
	 * 
	 * @return list of all medical records
	 * @throws Exception
	 */
	@Override
	public List<MedicalRecord> getAllMedicalRecords() throws Exception {
		logger.info("get list of all medical records ");
		return medicalRecordRepository.getAllMedicalRecords();
	}

}

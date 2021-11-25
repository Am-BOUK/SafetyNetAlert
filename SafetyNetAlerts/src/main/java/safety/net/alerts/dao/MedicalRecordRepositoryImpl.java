package safety.net.alerts.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import safety.net.alerts.entities.MedicalRecord;
import safety.net.alerts.entities.SafetyNet;

/**
 * Implementation of medical record repository
 * 
 */
@Repository
public class MedicalRecordRepositoryImpl implements IMedicalRecordRepository {
	private static final Logger logger = LogManager.getLogger("PersonRepositoryImpl");

	/**
	 *
	 * Calling serialize and deserialize methods of the safety net repository
	 */
	@Autowired
	private ISafetyNetRepository safetyNetRepository;

	/**
	 * Find a medical record of a person by its first name and last name ** This
	 * operation allows to check if the first name and last name of the person we
	 * want to find its medical record already exist in the json file, then returns
	 * a map of its index as key and its medical record object as value
	 * 
	 * @param first name and last name of the person to find
	 * @return Map of index as key and medicalRecord object as value
	 * @throws Exception
	 */
	@Override
	public Map<Integer, MedicalRecord> findMedicalRecordByFirstNameAndLastName(String firstName, String lastName)
			throws Exception {
		logger.info("Find medical record by its first name and last name :" + firstName + " " + lastName);
		SafetyNet safetyNet = safetyNetRepository.deserialize();
		Map<Integer, MedicalRecord> result = new HashMap<Integer, MedicalRecord>();
		for (MedicalRecord medicalRecord : safetyNet.getMedicalrecords()) {
			if (medicalRecord.getFirstName().equalsIgnoreCase(firstName)
					&& medicalRecord.getLastName().equalsIgnoreCase(lastName)) {
				int index = safetyNet.getMedicalrecords().indexOf(medicalRecord);
				result.put(index, medicalRecord);
			}
		}
		return result;
	}

	/**
	 * Get the medical record ** This operation allows to deserialize a json file to
	 * obtain a safety net object, then to return the medical record object in the
	 * given index that we want to retrieve its information
	 * 
	 * 
	 * @param index of the medical record to get
	 * @return medical record object
	 * @throws Exception
	 */
	@Override
	public MedicalRecord getMedicalRecord(int index) throws Exception {
		logger.info("Get medical record on index : " + index);
		SafetyNet safetyNet = safetyNetRepository.deserialize();
		return safetyNet.getMedicalrecords().get(index);
	}

	/**
	 * Add a new medical record ** This operation allows to deserialize a json file
	 * to obtain a safety net object, to add the medical record object, to serialize
	 * a safety net object to a json file and then to return the medical record we
	 * just added
	 * 
	 * @param medical record object to add
	 * @return medicalRecord object added
	 * @throws Exception
	 */
	@Override
	public MedicalRecord addMedicalRecord(MedicalRecord medicalRecord) throws Exception {
		logger.info("Add new medical record : " + medicalRecord);
		SafetyNet safetyNet = safetyNetRepository.deserialize();
		safetyNet.getMedicalrecords().add(medicalRecord);
		safetyNetRepository.serialize(safetyNet);
		return medicalRecord;
	}

	/**
	 * Update information of the medical record ** This operation allows to
	 * deserialize a json file to obtain a safety net object,to update the medical
	 * record object in the given index that we want to modify its information, to
	 * serialize a safety net object to a json file, then to return the medical
	 * record updated
	 * 
	 * 
	 * @param index   and medical record object to update
	 * @param medical record object to update
	 * @return medicalRecord object updated
	 * @throws Exception
	 */
	@Override
	public MedicalRecord updateMedicalRecord(int index, MedicalRecord medicalRecord) throws Exception {
		logger.info("update medical record : " + medicalRecord + " on index : " + index);
		SafetyNet safetyNet = safetyNetRepository.deserialize();
		safetyNet.getMedicalrecords().set(index, medicalRecord);
		safetyNetRepository.serialize(safetyNet);
		return medicalRecord;
	}

	/**
	 * Delete the medical record ** This operation allows to deserialize a json file
	 * to obtain a safety net object,to delete the medical record object in the
	 * given index and then to serialize a safety net object to a json file
	 * 
	 * @param index of medical record object to delete
	 * @throws Exception
	 */
	@Override
	public void deleteMedicalRecord(int index) throws Exception {
		logger.info("delete  medical record on index : " + index);
		SafetyNet safetyNet = safetyNetRepository.deserialize();
		safetyNet.getMedicalrecords().remove(index);
		safetyNetRepository.serialize(safetyNet);
	}

	/**
	 * Print all medical records
	 * 
	 * @return list of all medical records existing in the json file
	 * @throws Exception
	 */
	@Override
	public List<MedicalRecord> getAllMedicalRecords() throws Exception {
		logger.info("getting list of all medical records");
		SafetyNet safetyNet = safetyNetRepository.deserialize();
		return safetyNet.getMedicalrecords();
	}

}

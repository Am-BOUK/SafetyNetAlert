package safety.net.alerts.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import safety.net.alerts.entities.FireStation;
import safety.net.alerts.entities.SafetyNet;

/**
 * Implementation of fireStation repository
 * 
 */
@Repository
public class FireStationRepositoryImpl implements IFireStationRepository {

	private static final Logger logger = LogManager.getLogger("PersonRepositoryImpl");

	/**
	 *
	 * Calling serialize and deserialize methods of the safety net repository
	 */
	@Autowired
	private ISafetyNetRepository safetyNetRepository;

	/**
	 * Find a fire Station by address ** This operation allows to check if the
	 * address of the fire station we want to find already exist in the json file,
	 * then returns a map of its index as key and its fire station object as value
	 * 
	 * @param address to find
	 * @return Map of index as key and fireStation object as value
	 * @throws Exception
	 */
	@Override
	public Map<Integer, FireStation> findFireStationByAddress(String address) throws Exception {
		SafetyNet safetyNet = safetyNetRepository.deserialize();
		Map<Integer, FireStation> result = new HashMap<Integer, FireStation>();
		for (FireStation fireStation : safetyNet.getFirestations()) {
			if (fireStation.getAddress().equalsIgnoreCase(address)) {
				int index = safetyNet.getFirestations().indexOf(fireStation);
				result.put(index, fireStation);
				logger.info("Find fire station by its address :" + address);
			}
		}
		return result;
	}

	/**
	 * Get the fire station ** This operation allows to deserialize a json file to
	 * obtain a safety net object, then to return the fire station object in the
	 * given index that we want to retrieve its information
	 * 
	 * @param index of fire station to get
	 * @return fireStation object
	 */
	@Override
	public FireStation getFireStation(int index) throws Exception {
		SafetyNet safetyNet = safetyNetRepository.deserialize();
		logger.info("Get fire station on index : " + index);
		return safetyNet.getFirestations().get(index);
	}

	/**
	 * Add a new fire station ** This operation allows to deserialize a json file to
	 * obtain a safety net object, to add the fire station object, to serialise a
	 * safety net object to a json file, then to return the fire station we just
	 * added
	 * 
	 * @param fireStation object to add
	 * @return fireStation object added
	 */
	@Override
	public FireStation addFireStation(FireStation fireStation) throws Exception {
		SafetyNet safetyNet = safetyNetRepository.deserialize();
		safetyNet.getFirestations().add(fireStation);
		safetyNetRepository.serialize(safetyNet);
		logger.info("Add new fire station : " + fireStation);
		return fireStation;
	}

	/**
	 * Update information of the fire station ** This operation allows to
	 * deserialize a json file to obtain a safety net object,to update the fire
	 * station object in the given index that we want to modify its information, to
	 * serialise a safety net object to a json file, then to return the fire station
	 * updated
	 * 
	 * @param index and fire station object to update
	 * @return fire station object updated
	 */
	@Override
	public FireStation updateFireStation(int index, FireStation fireStationDto) throws Exception {
		SafetyNet safetyNet = safetyNetRepository.deserialize();
		safetyNet.getFirestations().set(index, fireStationDto);
		safetyNetRepository.serialize(safetyNet);
		logger.info("update fire station : " + fireStationDto + " on index : " + index);
		return fireStationDto;
	}

	/**
	 * Delete the fire station ** This operation allows to deserialize a json file
	 * to obtain a safety net object,to delete the fire station object in the given
	 * index and to serialise a safety net object to a json file
	 * 
	 * @param index of the fire station to delete
	 */
	@Override
	public void deleteFireStation(int index) throws Exception {
		SafetyNet safetyNet = safetyNetRepository.deserialize();
		safetyNet.getFirestations().remove(index);
		safetyNetRepository.serialize(safetyNet);
		logger.info("delete fire station on index : " + index);
	}

	/**
	 * Get all fire stations
	 * 
	 * @return list of all fire stations existing in the json file
	 */
	@Override
	public List<FireStation> getAllFireStations() throws Exception {
		SafetyNet safetyNet = safetyNetRepository.deserialize();
		logger.info("getting list of all fire stations");
		return safetyNet.getFirestations();
	}

}

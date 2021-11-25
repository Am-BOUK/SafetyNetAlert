package safety.net.alerts.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import safety.net.alerts.dao.IFireStationRepository;
import safety.net.alerts.entities.FireStation;
import safety.net.alerts.entities.FireStationDTO;

/**
 * implementation of fire station business processing
 * 
 */
@Service
public class FireStationMetierImpl implements IFireStationMetier {
	private static final Logger logger = LogManager.getLogger("PersonMetierImpl");

	/**
	 * Calling operations of the fire station repository
	 */
	@Autowired
	private IFireStationRepository fireStationRepository;

	/**
	 * Get fire station ** This operation allows to check if the address of the fire
	 * station we want to get its information already exist in the json file, then
	 * allows to get it
	 * 
	 * @param address of the fire station to get
	 * @return fireStation object
	 * @throws Exception
	 */
	@Override
	public FireStation getFireStation(String address) throws Exception {
		logger.info("Get fire station record for address : " + address);

		Map<Integer, FireStation> fireStationMap = fireStationRepository.findFireStationByAddress(address);
		int index = -1;
		if (fireStationMap.size() > 0) {
			index = fireStationMap.keySet().stream().findFirst().get();
			FireStation fireStationFound = fireStationMap.get(index);
			logger.info("Getting fire station : " + fireStationFound);
			return fireStationRepository.getFireStation(index);
		} else {

			logger.info("Fire station of the address : " + address + " you want to get, does not exist !");
			throw new RuntimeException(
					"The fire station of the address " + address + ", you want to get, does not exist !");
		}

	}

	/**
	 * Find a list of fire station by its address ** This operation returns a map of
	 * fire station object as value and their index as key
	 * 
	 * @param address of the fire station to find
	 * @return List of FireStation
	 * @throws Exception
	 */
	@Override
	public List<FireStation> getListFireStationByAddress(String address) throws Exception {
		logger.info("Getting all fire station contains : " + address + " as address");
		List<FireStation> fireStations = fireStationRepository.getAllFireStations();
		List<FireStation> fireStationList = new ArrayList<>();

		for (FireStation fs : fireStations) {

			if (fs.getAddress().toLowerCase().contains(address.toLowerCase())) {
				fireStationList.add(fs);
			}
		}
		if (fireStationList.isEmpty()) {
			logger.info("The fire station of the address " + address + ", you want to get, is empty !");
			throw new RuntimeException("The fire station of the address " + address + ", you want to get, is empty !");
		}
		return fireStationList;
	}

	/**
	 * Add fire station ** This operation allows to check if the address of the fire
	 * station we want to add already exist in the json file, then allows to add it
	 * 
	 * @param fire station object to add
	 * @return fireStation object added
	 * @throws Exception
	 */
	@Override
	public FireStation addFireStation(FireStation fireStation) throws Exception {
		logger.info("Add a  new fire station : " + fireStation);
		Map<Integer, FireStation> fireStationMap = fireStationRepository
				.findFireStationByAddress(fireStation.getAddress());

		if (fireStationMap.isEmpty()) {
			logger.info("Adding fire station : " + fireStation);
			return fireStationRepository.addFireStation(fireStation);
		} else {
			logger.info("the fire station you want to add : " + fireStation + ", Already exist !");
			throw new RuntimeException("the fire station you want to add : " + fireStation + ", Already exist !");
		}
	}

	/**
	 * Update information of the fire station ** This operation allows to check if
	 * the address of the fire station we want to update already exist in the json
	 * file, then allows to update the information of the fire station
	 * 
	 * 
	 * 
	 * @param address of the fire station object to update
	 * @param fire    station data transfer object to update
	 * @return fireStation object updated
	 * @throws Exception
	 */
	@Override
	public FireStation updateFireStation(String address, FireStationDTO fireStationDto) throws Exception {
		logger.info("Update the fire station : " + fireStationDto);
		Map<Integer, FireStation> fireStationMap = fireStationRepository.findFireStationByAddress(address);
		int index = -1;
		if (fireStationMap.size() > 0) {
			index = fireStationMap.keySet().stream().findFirst().get();
			FireStation fireStationFound = fireStationMap.get(index);

			fireStationFound.setStation(
					fireStationDto.getStation() != null ? fireStationDto.getStation() : fireStationFound.getStation());

			logger.info("Updating fire station :" + fireStationFound);
			return fireStationRepository.updateFireStation(index, fireStationFound);
		} else {

			logger.info("Fire station of the address : " + address + " you want to update, does not exist !");
			throw new RuntimeException(
					"Fire station of the address : " + address + " you want to update, does not exist !");

		}
	}

	/**
	 * Delete the fire station ** This operation allows to check if the address of
	 * the fire station we want to delete already exist in the json file, then
	 * allows to use its address to delete the fire station
	 * 
	 * @param address of the fire station object to delete
	 * @throws Exception
	 */
	@Override
	public void deleteFireStation(String address) throws Exception {
		logger.info("Delete fire station of address : " + address);
		Map<Integer, FireStation> fireStationMap = fireStationRepository.findFireStationByAddress(address);
		int index = -1;
		if (fireStationMap.size() > 0) {
			index = fireStationMap.keySet().stream().findFirst().get();
			logger.info("Deleting fire station : " + fireStationMap.values());
			fireStationRepository.deleteFireStation(index);
			return;
		} else {

			logger.info("Fire station of the address : " + address + " you want to delete, does not exist !");
			throw new RuntimeException(
					"Fire station of the address : " + address + " you want to delete, does not exist !");
		}
	}

	/**
	 * Print all fire stations
	 * 
	 * @return list of all fire stations existing in the json file
	 * @throws Exception
	 */
	@Override
	public List<FireStation> getAllFireStations() throws Exception {
		logger.info("get list of all fire stations ");
		return fireStationRepository.getAllFireStations();
	}

}

// 	/**
//	 * Get the fire station object ** This operation allows to check if the address
//	 * of the fire station we want to get its information already exist in the json
//	 * file, then allows to use its index to get it
//	 * 
//	 * @param address of the fire station we want to get
//	 * @return fireStation object if it exists, otherwise returns null
//	 */
//	@Override
//	public FireStation getFireStation(String address) {
//		Map<Integer, FireStation> fireStationMap = fireStationRepository.findFireStationByAddress(address);
//		int index = -1;
//		if (fireStationMap.size() > 0) {
//			index = fireStationMap.keySet().stream().findFirst().get();
//			FireStation fireStationFound = fireStationMap.get(index);
//			System.out.println("Getting fire station : " + fireStationFound);
//			return fireStationRepository.getFireStation(index);
//		}
//
//		System.out.println("Fire station of the address : " + address + " you want to get, does not exist !");
//		return null;
//	}
//
//	/**
//	 * Add a new fire station ** This operation allows to check if the address of
//	 * the fire station we want to add already exist in the json file, then allows
//	 * to add it
//	 * 
//	 * @param fireStation object to add
//	 * @return fireStation object if added, null otherwise
//	 */
//	@Override
//	public FireStation addFireStation(FireStation fireStation) {
//		Map<Integer, FireStation> fireStationMap = fireStationRepository
//				.findFireStationByAddress(fireStation.getAddress());
//
//		if (fireStationMap.isEmpty()) {
//			System.out.println("Adding fire station : " + fireStation);
//			return fireStationRepository.addFireStation(fireStation);
//		} else {
//			System.out.println("the fire station you want to add : " + fireStation + ", Already exist !");
//			return null;
//		}
//	}
//
//	/**
//	 * Update information of the fire station ** This operation allows to check if
//	 * the address of the fire station we want to update already exist in the json
//	 * file, then allows to use its index to update the information of the fire
//	 * station
//	 * 
//	 * @param address of the fire station and data transfer object of the modified
//	 *                fire station
//	 * @return updated fire station object if the address found, null otherwise
//	 */
//	@Override
//	public FireStation updateFireStation(String address, FireStationDTO fireStationDTO) {
//		Map<Integer, FireStation> fireStationMap = fireStationRepository.findFireStationByAddress(address);
//		int index = -1;
//		if (fireStationMap.size() > 0) {
//			index = fireStationMap.keySet().stream().findFirst().get();
//			FireStation fireStationFound = fireStationMap.get(index);
//			fireStationFound.setStation(fireStationDTO.getStation());
//
//			System.out.println("Updating fire station :" + fireStationFound);
//			return fireStationRepository.updateFireStation(index, fireStationFound);
//		}
//
//		System.out.println("Fire station of the address : " + address + " you want to update, does not exist !");
//		return null;
//	}
//
//	/**
//	 * Delete the fire station ** This operation allows to check if the address of
//	 * the fire station we want to delete already exist in the json file, then
//	 * allows to use its index to delete the fire station
//	 * 
//	 * @param address of the fire station we want to delete
//	 */
//	@Override
//	public void deleteFireStation(String address) {
//		Map<Integer, FireStation> fireStationMap = fireStationRepository.findFireStationByAddress(address);
//		int index = -1;
//		if (fireStationMap.size() > 0) {
//			index = fireStationMap.keySet().stream().findFirst().get();
//			System.out.println("Deleting fire station : " + fireStationMap.values());
//			fireStationRepository.deleteFireStation(index);
//			return;
//		}
//
//		System.out.println("Fire station of the address : " + address + " you want to delete, does not exist !");
//	}
//
//	/**
//	 * Get all fire stations
//	 * 
//	 * @return list of all fire stations existing in the json file
//	 */
//	@Override
//	public List<FireStation> getAllFireStations() {
//		System.out.println("Getting all fire stations");
//		return fireStationRepository.getAllFireStations();
//	}

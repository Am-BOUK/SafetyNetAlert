package safety.net.alerts.service;

import java.util.List;

import safety.net.alerts.entities.FireStation;
import safety.net.alerts.entities.FireStationDTO;

/**
 * 
 * business interface that will allow implementation of application-specific
 * business processing
 * 
 * The important part is, the return value from operations which is a fire
 * station object
 * 
 */
public interface IFireStationMetier {

	public List<FireStation> getListFireStationByAddress(String address) throws Exception;

	public FireStation getFireStation(String address) throws Exception;

	public FireStation addFireStation(FireStation fireStation) throws Exception;

	public FireStation updateFireStation(String address, FireStationDTO fireStationDto) throws Exception;

	public void deleteFireStation(String address) throws Exception;

	public List<FireStation> getAllFireStations() throws Exception;

}
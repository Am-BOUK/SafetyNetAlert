package safety.net.alerts.dao;

import java.util.List;
import java.util.Map;

import safety.net.alerts.entities.FireStation;

/**
 * DAO Interface that will allow interaction with external data sources
 * 
 * The important part is, the return value from operations which are a fire
 * station object and index
 * 
 */

public interface IFireStationRepository {

	public Map<Integer, FireStation> findFireStationByAddress(String address) throws Exception;

	public FireStation getFireStation(int index) throws Exception;

	public FireStation addFireStation(FireStation fireStation) throws Exception;

	public FireStation updateFireStation(int index, FireStation fireStationDto) throws Exception;

	public void deleteFireStation(int index) throws Exception;

	public List<FireStation> getAllFireStations() throws Exception;

}

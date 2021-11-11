package safety.net.alerts.metier;

import java.util.List;

import safety.net.alerts.entities.FireStation;
import safety.net.alerts.entities.FireStationDTO;

public interface IFireStationService {
	public FireStation getFireStation(String address);

	public FireStation addFireStation(FireStation fireStation);

	public FireStation updateFireStation(String address, FireStationDTO fireStationDTO);

	public void deleteFireStation(String address);

	public List<FireStation> getAllFireStations();

}
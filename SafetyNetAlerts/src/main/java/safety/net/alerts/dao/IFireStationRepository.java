package safety.net.alerts.dao;

import java.util.List;

import safety.net.alerts.entities.FireStation;

public interface IFireStationRepository {

	public FireStation getFireStation(int index);

	public FireStation addFireStation(FireStation fireStation);

	public FireStation updateFireStation(int index, FireStation fireStationDto);

	public void deleteFireStation(int index);

	public List<FireStation> getAllFireStations();
}

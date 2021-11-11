package safety.net.alerts.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import safety.net.alerts.entities.FireStation;
import safety.net.alerts.entities.SafetyNet;

@Repository
public class FireStationRepositoryImpl implements IFireStationRepository {

	@Autowired
	private ISafetyNetRepository safetyNetRepository;

	@Override
	public FireStation getFireStation(int index) {
		SafetyNet safetyNet = safetyNetRepository.deserialize();
		return safetyNet.getFirestations().get(index);
	}

	@Override
	public FireStation addFireStation(FireStation fireStation) {
		SafetyNet safetyNet = safetyNetRepository.deserialize();
		safetyNet.getFirestations().add(fireStation);
		safetyNetRepository.serialize(safetyNet);
		return fireStation;
	}

	@Override
	public FireStation updateFireStation(int index, FireStation fireStationDto) {
		SafetyNet safetyNet = safetyNetRepository.deserialize();
		safetyNet.getFirestations().set(index, fireStationDto);
		safetyNetRepository.serialize(safetyNet);
		return fireStationDto;
	}

	@Override
	public void deleteFireStation(int index) {
		SafetyNet safetyNet = safetyNetRepository.deserialize();
		safetyNet.getFirestations().remove(index);
		safetyNetRepository.serialize(safetyNet);

	}

	@Override
	public List<FireStation> getAllFireStations() {
		SafetyNet safetyNet = safetyNetRepository.deserialize();
		return safetyNet.getFirestations();
	}

}

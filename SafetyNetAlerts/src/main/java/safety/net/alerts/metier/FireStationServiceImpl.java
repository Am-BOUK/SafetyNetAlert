package safety.net.alerts.metier;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import safety.net.alerts.dao.IFireStationRepository;
import safety.net.alerts.entities.FireStation;
import safety.net.alerts.entities.FireStationDTO;
import safety.net.alerts.entities.SafetyNet;

@Service
public class FireStationServiceImpl implements IFireStationService {
	@Autowired
	private IFireStationRepository fireStationRepository;

	@Autowired
	private ISafetyNetService safetyNetService;

	@Override
	public FireStation getFireStation(String address) {
		SafetyNet safetyNet = safetyNetService.deserialize();
		int index = -1;
		for (FireStation fs : safetyNet.getFirestations()) {
			index = safetyNet.getFirestations().indexOf(fs);
			if (fs.getAddress().contains(address)) {
				System.out.println("Getting fire station : " + fs);
				return fireStationRepository.getFireStation(index);
			}
		}
		System.out.println("Fire station of the address : " + address + " does not exist !");
		return null;
	}

	@Override
	public FireStation addFireStation(FireStation fireStation) {
		SafetyNet safetyNet = safetyNetService.deserialize();

		for (FireStation fs : safetyNet.getFirestations()) {
			if (fs.getAddress().contains(fireStation.getAddress())) {
				System.out.println("the fire station you want to add : " + fs + ", Already exist !");
				return null;
			}
		}
		System.out.println("Adding fire station : " + fireStation);
		return fireStationRepository.addFireStation(fireStation);

	}

	@Override
	public FireStation updateFireStation(String address, FireStationDTO fireStationDTO) {
		SafetyNet safetyNet = safetyNetService.deserialize();
		int index = -1;
		FireStation f = new FireStation();
		for (FireStation fs : safetyNet.getFirestations()) {
			index = safetyNet.getFirestations().indexOf(fs);
			if (fs.getAddress().contains(address)) {
				f = fs;
				f.setAddress(fireStationDTO.getAddress() != null ? fireStationDTO.getAddress() : f.getAddress());
				f.setStation(fireStationDTO.getStation() != null ? fireStationDTO.getStation() : f.getStation());

				System.out.println("Updating fire station : " + f);
				return fireStationRepository.updateFireStation(index, f);
			}

		}
		System.out.println("Fire station of the address : " + address + " does not exist !");
		return null;
	}

	@Override
	public void deleteFireStation(String address) {
		SafetyNet safetyNet = safetyNetService.deserialize();
		int index = -1;
		for (FireStation fs : safetyNet.getFirestations()) {
			index = safetyNet.getFirestations().indexOf(fs);
			if (fs.getAddress().contains(address)) {
				System.out.println("Deleting fire station of the address : " + address);
				fireStationRepository.deleteFireStation(index);
				return;
			}
		}
		System.out.println("Fire station of the address : " + address + " , does not exist !");
	}

	@Override
	public List<FireStation> getAllFireStations() {
		SafetyNet safetyNet = safetyNetService.deserialize();
		System.out.println("Getting all fire stations");
		return safetyNet.getFirestations();
	}

}

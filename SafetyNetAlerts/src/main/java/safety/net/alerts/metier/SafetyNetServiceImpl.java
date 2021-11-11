package safety.net.alerts.metier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import safety.net.alerts.dao.ISafetyNetRepository;
import safety.net.alerts.entities.SafetyNet;

@Service
public class SafetyNetServiceImpl implements ISafetyNetService {
	@Autowired
	private ISafetyNetRepository safetyNetRepository;

	public static final String fileName = "C:\\Users\\Amal\\Google Drive\\OpenClassrooms\\workplace\\P5\\SafetyNet\\SafetyNetAlerts\\src\\main\\resources\\data.json";
	@Override
	public void serialize(SafetyNet safetyNet) {
		safetyNetRepository.serialize(safetyNet);
	}

	@Override
	public SafetyNet deserialize() {
		return safetyNetRepository.deserialize();

	}

}

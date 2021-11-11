package safety.net.alerts.dao;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;

import safety.net.alerts.entities.SafetyNet;

@Repository
public class SafetyNetRepositoryImpl implements ISafetyNetRepository {
	public static final String fileName = "C:\\Users\\Amal\\Google Drive\\OpenClassrooms\\workplace\\P5\\SafetyNet\\SafetyNetAlerts\\src\\main\\resources\\data.json";
	@Override
	public void serialize(SafetyNet safetyNet) {
		ObjectMapper om = new ObjectMapper();

		try {
			File jsonFile = new File(fileName);
			om.writeValue(jsonFile, safetyNet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public SafetyNet deserialize() {
		SafetyNet safetyNet = new SafetyNet();
		ObjectMapper om = new ObjectMapper();

		try {
			File jsonFile = new File(fileName);
			 safetyNet = om.readValue(jsonFile, SafetyNet.class);
		} catch (IOException e) {
			e.printStackTrace();
		}

		
		return safetyNet;

	}

}

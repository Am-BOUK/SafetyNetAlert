package safety.net.alerts.dao;

import java.io.File;

import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import safety.net.alerts.entities.SafetyNet;

/**
 * Implementation of safetyNet repository
 * 
 */
@Repository
public class SafetyNetRepositoryImpl implements ISafetyNetRepository {
	public static final String fileName = "src/main/resources/data.json";

	/**
	 * Operation that allow to convert from an object to JSON form and make it in a
	 * file
	 * 
	 * @param safetyNet object
	 * @see JSON form
	 */
	@Override
	public void serialize(SafetyNet safetyNet) throws Exception {
		ObjectMapper om = new ObjectMapper();
			File jsonFile = new File(fileName);
			om.writeValue(jsonFile, safetyNet);
		
	}

	/**
	 * Method that allow to get a safetyNet object from a JSON file
	 * 
	 * @return safetyNet object
	 * @throws  
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	@Override
	public SafetyNet deserialize() throws Exception {
		SafetyNet safetyNet = new SafetyNet();
		ObjectMapper om = new ObjectMapper();
			File jsonFile = new File(fileName);
			safetyNet = om.readValue(jsonFile, SafetyNet.class);
		return safetyNet;

	}

}

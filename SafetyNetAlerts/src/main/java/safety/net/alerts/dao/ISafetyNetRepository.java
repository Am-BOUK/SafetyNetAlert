package safety.net.alerts.dao;

import safety.net.alerts.entities.SafetyNet;

/**
 * DAO Interface that will allow interaction with external data sources
 * 
 * The important part is, the return/result value from operations which are a
 * SafetyNet object and print to JSON form
 * 
 */
public interface ISafetyNetRepository {

	void serialize(SafetyNet safetyNet) throws Exception;

	SafetyNet deserialize() throws Exception;
}

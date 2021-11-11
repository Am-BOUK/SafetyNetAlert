package safety.net.alerts.dao;

import safety.net.alerts.entities.SafetyNet;

public interface ISafetyNetRepository{

	void serialize(SafetyNet safetyNet);

	SafetyNet deserialize() ;
}

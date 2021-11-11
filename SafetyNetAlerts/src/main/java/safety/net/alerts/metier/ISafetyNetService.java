package safety.net.alerts.metier;


import safety.net.alerts.entities.SafetyNet;

public interface ISafetyNetService {
	public void serialize(SafetyNet safetyNet);
	public SafetyNet deserialize();
}

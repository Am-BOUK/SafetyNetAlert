package safety.net.alerts.entities;

import java.util.List;

/**
 * 
 * implementation of business object: safety net that will be manipulated by the
 * other layers.
 *
 * the safety net has three attribute : list of persons, list of medical records
 * and list of fire stations
 */
public class SafetyNet {
	private List<Person> persons;
	private List<MedicalRecord> medicalrecords;
	private List<FireStation> firestations;

	public List<Person> getPersons() {
		return persons;
	}

	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}

	public List<MedicalRecord> getMedicalrecords() {
		return medicalrecords;
	}

	public void setMedicalrecords(List<MedicalRecord> medicalrecords) {
		this.medicalrecords = medicalrecords;
	}

	public List<FireStation> getFirestations() {
		return firestations;
	}

	public void setFirestations(List<FireStation> firestations) {
		this.firestations = firestations;
	}
//
//	@Override
//	public String toString() {
//		return "[persons = " + persons + ", firestations = " + firestations + ", medicalrecords = " + medicalrecords
//				+ "]";
//	}

}

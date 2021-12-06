package safety.net.alerts.entities;

import java.util.List;

/**
 * 
 * implementation of business data transfer object: medical record that will be
 * manipulated by the other layers.
 *
 * the medical record data transfer object has three attribute : birthdate,
 * medications and allergies
 */
public class MedicalRecordDTO {
	private String birthdate;
	private List<String> medications;
	private List<String> allergies;

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public List<String> getMedications() {
		return medications;
	}

	public void setMedications(List<String> medications) {
		this.medications = medications;
	}

	public List<String> getAllergies() {
		return allergies;
	}

	public void setAllergies(List<String> allergies) {
		this.allergies = allergies;
	}

}

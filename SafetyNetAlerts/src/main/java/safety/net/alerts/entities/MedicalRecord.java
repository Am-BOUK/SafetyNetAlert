package safety.net.alerts.entities;

import java.util.List;

import javax.validation.constraints.NotBlank;

/**
 * 
 * implementation of business object: medical record that will be manipulated by
 * the other layers.
 *
 * the medical record object has five attribute : first name, last name,
 * birthdate, medications and allergies
 */
public class MedicalRecord {

	@NotBlank(message = "First name is mandatory")
	private String firstName;

	@NotBlank(message = "Last name is mandatory")
	private String lastName;

	@NotBlank(message = "birthdate is mandatory")
	private String birthdate;

	private List<String> medications;
	private List<String> allergies;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

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

	@Override
	public String toString() {
		return "[ firstName = " + firstName + ", lastName = " + lastName + ", birthdate = " + birthdate
				+ ", medications = " + medications + ", allergies = " + allergies + " ]";
	}

}
